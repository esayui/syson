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

import React, { forwardRef, useRef } from 'react';

const PLOTTING_ORIGIN = 'http://localhost:3100';
const HIDE_RIGHT_PANEL_STYLE_ID = 'ov1-blank-hide-right-panel';

const CREATE_CHILD = '\n  mutation createChild($input: CreateChildInput!) {\n    createChild(input: $input) {\n      __typename\n      ... on CreateChildSuccessPayload { object { id } }\n      ... on ErrorPayload { message }\n    }\n  }';

var symbolToPartUsageMap: Record<string, string> = {};   // _symbolId → EMF elementId
var symbolToSiriusMap: Record<string, string> = {};       // _symbolId → Sirius object ID (for tree matching)

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
  var iframeRef = useRef<HTMLIFrameElement>(null);
  React.useEffect(function() {
    var pendingSymbols: any[] = [];

    // Fetch targetObjectId via REST API (queries PostgreSQL directly)
    if (editingContextId && representationId) {
      fetch(PLOTTING_ORIGIN + '/api/targetObjectId/' + encodeURIComponent(representationId))
        .then(function(r: any) { if (!r.ok) throw new Error('status ' + r.status); return r.json(); })
        .then(function(data: any) {
          var parentId = data && data.targetObjectId;
          if (parentId) {
            (window as any).__ov1TargetObjectId = parentId;
            // Pass targetObjectId to iframe via URL param update
            if (iframeRef.current) {
              var currentSrc = iframeRef.current.src;
              if (currentSrc.indexOf('targetObjectId=') < 0) {
                iframeRef.current.src = currentSrc + '&targetObjectId=' + encodeURIComponent(parentId);
              }
            }
            console.log('[OV-1] parentId resolved from DB:', parentId, 'pending:', pendingSymbols.length);
            for (var p = 0; p < pendingSymbols.length; p++) {
              createPartUsage(pendingSymbols[p], parentId);
            }
            pendingSymbols = [];
          } else {
            console.warn('[OV-1] parentId NOT found in DB');
          }
        }).catch(function(e: any) {
          console.warn('[OV-1] parentId query failed', e.message || e);
        });
    }

    function createPartUsage(symbolMsg: any, parentId: string) {
      var sid = symbolMsg._symbolId || '';
      var symbolName = symbolMsg.name || '';
      var descId = 'SysMLv2EditService-PartUsage' + (symbolName ? ':' + symbolName : '');
      console.log('[OV-1] createPartUsage sid=' + sid + ' name=' + symbolName);
      callGraphQL(CREATE_CHILD, {
        input: {
          id: crypto.randomUUID(),
          editingContextId: editingContextId,
          objectId: parentId,
          childCreationDescriptionId: descId,
        },
      }).then(function(result: any) {
        var obj = result && result.data && result.data.createChild && result.data.createChild.object;
        if (obj && obj.id) {
          if (sid) {
            symbolToSiriusMap[sid] = obj.id; // Sirius object ID (for tree matching)
            symbolToPartUsageMap[sid] = obj.id; // temporary until EMF resolved
            // Resolve EMF elementId for deleteOv1PartUsage
            fetch(PLOTTING_ORIGIN + '/api/elementId/' + encodeURIComponent(editingContextId) + '/' + encodeURIComponent(obj.id))
              .then(function(r) { if (r.ok) return r.json(); throw new Error('no mapping'); })
              .then(function(data: any) {
                if (data.elementId) symbolToPartUsageMap[sid] = data.elementId;
                console.log('[OV-1] EMF elementId resolved:', data.elementId);
              }).catch(function(){});
          }
          console.log('[OV-1] PartUsage created:', (symbolMsg.name || '(default)'), obj.id);
        } else {
          console.warn('[OV-1] createChild failed', JSON.stringify(result));
        }
      }).catch(function(e: any) { console.warn('[OV-1] createChild error', e); });
    }

    // Hide right panel
    var style = document.createElement('style');
    style.id = HIDE_RIGHT_PANEL_STYLE_ID;
    style.textContent = '[data-testid="sidebar-right"],[data-testid="site-right"],[data-testid="right-resizer"]{display:none!important}[data-panel-id]:has([data-testid="site-right"]){flex:0 0 0px!important;max-width:0!important;min-width:0!important;overflow:hidden!important}';
    document.head.appendChild(style);

    // postMessage listener
    function handleMessage(event: MessageEvent) {
      if (event.origin !== PLOTTING_ORIGIN) return;
      var msg = event.data;
      if (!msg || !msg.type) return;

      if (msg.type === 'createSymbol') {
        if (!msg.symbol) return;
        var tid = (window as any).__ov1TargetObjectId || '';
        if (!tid) { pendingSymbols.push(msg.symbol); console.log('[OV-1] queued', pendingSymbols.length); return; }
        createPartUsage(msg.symbol, tid);
      }

      if (msg.type === 'deleteSymbol') {
        console.info('[OV-1] deleteSymbol received, symbolId=' + msg.symbolId);
        var puId = symbolToPartUsageMap[msg.symbolId];
        console.info('[OV-1] deleteSymbol mapped PartUsage=' + puId);
        // deleteOv1PartUsage mutation (goes through proper Sirius event pipeline)
        var elId = puId || msg.symbolId;
        console.info('[OV-1] deleteOv1PartUsage elementId=' + elId);
        callGraphQL('mutation deleteOv1PartUsage($input: DeleteOv1PartUsageInput!) { deleteOv1PartUsage(input: $input) { __typename ... on ErrorPayload { message } } }', {
          input: { id: crypto.randomUUID(), editingContextId: editingContextId, elementId: elId },
        }).then(function(result: any) {
          var payload = result && result.data && result.data.deleteOv1PartUsage;
          if (payload && payload.__typename === 'ErrorPayload') {
            console.warn('[OV-1] deleteOv1PartUsage failed', payload.message);
          } else {
            if (puId) delete symbolToPartUsageMap[msg.symbolId];
            console.info('[OV-1] delete OK');
          }
        }).catch(function(e: any) { console.warn('[OV-1] delete error', e); });
      }
    }

    window.addEventListener('message', handleMessage);

    // Listen for tree item deletions from RM. Cannot use polling (DB out of sync).
    // Instead, expose a callback on window for Apollo Link to call when deleteTreeItem succeeds.
    (window as any).__ov1OnDeleteItem = function(treeItemId: string) {
      // Match by Sirius object ID (tree uses this, not EMF elementId)
      for (var sid in symbolToSiriusMap) {
        if (symbolToSiriusMap[sid] === treeItemId) {
          delete symbolToSiriusMap[sid];
          delete symbolToPartUsageMap[sid];
          if (iframeRef.current && iframeRef.current.contentWindow) {
            iframeRef.current.contentWindow.postMessage({ type: 'deleteSymbol', symbolId: sid }, PLOTTING_ORIGIN);
          }
          console.info('[OV-1] RM→iframe delete:', sid);
          break;
        }
      }
    };

    return function() {
      delete (window as any).__ov1OnDeleteItem;
      var el = document.getElementById(HIDE_RIGHT_PANEL_STYLE_ID);
      if (el) el.remove();
      window.removeEventListener('message', handleMessage);
    };
  }, []);

  var src = PLOTTING_ORIGIN + '?representationId=' + encodeURIComponent(representationId) + '&editingContextId=' + encodeURIComponent(editingContextId);

  return React.createElement('iframe', {
    ref: iframeRef,
    src: src,
    style: { width: '100%', height: '100%', border: 'none', display: 'block', background: 'rgb(25,40,79)' },
    title: 'OV-1 Plotting Tool',
  });
});

export default Ov1BlankView;
