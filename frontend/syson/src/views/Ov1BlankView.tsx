/*******************************************************************************
 * Copyright (c) 2026 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/

import React, { forwardRef } from 'react';

const PLOTTING_ORIGIN = 'http://localhost:3100';
const HIDE_RIGHT_PANEL_STYLE_ID = 'ov1-blank-hide-right-panel';

const CREATE_CHILD = '\n  mutation createChild($input: CreateChildInput!) {\n    createChild(input: $input) {\n      __typename\n      ... on CreateChildSuccessPayload { object { id } }\n      ... on ErrorPayload { message }\n    }\n  }';

const DELETE_FROM_MODEL = '\n  mutation deleteFromModel($input: DeleteFromModelInput!) {\n    deleteFromModel(input: $input) {\n      __typename\n      ... on ErrorPayload { message }\n    }\n  }';

var symbolToPartUsageMap: Record<string, string> = {};

function callGraphQL(query: string, variables: any) {
  return fetch('http://localhost:8080/api/graphql', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ query: query, variables: variables }),
  }).then(function(r: any) { return r.json(); });
}

export var Ov1BlankView = forwardRef<any, any>(function Ov1BlankView(props, _ref) {
  var editingContextId = (props && props.editingContextId) || '';
  var representationId = (props && props.representationId) || '';

  React.useEffect(function() {
    var targetObjectId = (window as any).__ov1TargetObjectId || '';
    console.log('[OV-1] mounted', { editingContextId: editingContextId, representationId: representationId, targetObjectId: targetObjectId });

    // Hide right panel
    var style = document.createElement('style');
    style.id = HIDE_RIGHT_PANEL_STYLE_ID;
    style.textContent = [
      '[data-testid="sidebar-right"]',
      '[data-testid="site-right"]',
      '[data-testid="right-resizer"]',
    ].map(function(sel: any) { return sel + ' { display: none !important; }'; }).join('\n') + '\n' +
    '[data-panel-id]:has([data-testid="site-right"]) { flex: 0 0 0px !important; max-width: 0 !important; min-width: 0 !important; overflow: hidden !important; }';
    document.head.appendChild(style);

    // postMessage listener
    function handleMessage(event: MessageEvent) {
      if (event.origin !== PLOTTING_ORIGIN) return;
      var msg = event.data;
      if (!msg || !msg.type) return;

      if (msg.type === 'createSymbol' && msg.symbol) {
        var tid = (window as any).__ov1TargetObjectId || '';
        if (!tid) { console.warn('[OV-1] createSymbol: no targetObjectId'); return; }
        console.log('[OV-1] createSymbol', msg.symbol.name || msg.symbol.label);
        callGraphQL(CREATE_CHILD, {
          input: {
            id: crypto.randomUUID(),
            editingContextId: editingContextId,
            objectId: tid,
            childCreationDescriptionId: 'SysMLv2EditService-PartUsage',
          },
        }).then(function(result: any) {
          var obj = result && result.data && result.data.createChild && result.data.createChild.object;
          if (obj && obj.id) {
            var sid = msg.symbol.id || msg.symbol._id || msg.symbol.guid || '';
            if (sid) symbolToPartUsageMap[sid] = obj.id;
            console.log('[OV-1] PartUsage created', obj.id);
          } else {
            console.warn('[OV-1] createChild failed', JSON.stringify(result));
          }
        }).catch(function(e: any) { console.warn('[OV-1] createChild error', e); });
      }

      if (msg.type === 'deleteSymbol') {
        var pid = symbolToPartUsageMap[msg.symbolId];
        var ids = pid ? [pid] : [msg.symbolId];
        callGraphQL(DELETE_FROM_MODEL, {
          input: { id: crypto.randomUUID(), editingContextId: editingContextId, elementIds: ids },
        }).then(function() {
          if (pid) delete symbolToPartUsageMap[msg.symbolId];
          console.log('[OV-1] PartUsage deleted', ids);
        }).catch(function(e: any) { console.warn('[OV-1] deleteSymbol failed', e); });
      }
    }

    window.addEventListener('message', handleMessage);

    return function() {
      var el = document.getElementById(HIDE_RIGHT_PANEL_STYLE_ID);
      if (el) el.remove();
      window.removeEventListener('message', handleMessage);
    };
  }, []);

  var src = PLOTTING_ORIGIN + '?representationId=' + encodeURIComponent(representationId) + '&editingContextId=' + encodeURIComponent(editingContextId);

  return React.createElement('iframe', {
    src: src,
    style: { width: '100%', height: '100%', border: 'none', display: 'block', background: 'rgb(25,40,79)' },
    title: 'OV-1 Plotting Tool',
  });
});

export default Ov1BlankView;
