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

/**
 * A single tool entry within a palette section.
 */
export interface GQLTool {
  id: string;
  label: string;
  iconURL?: string;
  dialogDescriptionId?: string;
}

/**
 * A palette entry (tool section) grouping related tools.
 */
export interface GQLPaletteEntry {
  id: string;
  label: string;
  iconURL?: string;
  tools?: GQLTool[];  // only present on ToolSection, absent on individual tools
}

/**
 * Full palette data from the backend.
 */
export interface GQLPalette {
  id: string;
  paletteEntries: GQLPaletteEntry[];
  quickAccessTools: GQLTool[];
}

/**
 * Palette query response within the representation description.
 */
export interface GQLGetPaletteData {
  viewer: {
    editingContext: {
      representation: {
        description: {
          __typename?: string;
          palette?: GQLPalette;
        };
      };
    };
  };
}

/**
 * Variables for the palette query.
 */
export interface GQLGetPaletteVariables {
  editingContextId: string;
  representationId: string;
  diagramElementIds: string[];
}

/**
 * State for the tool sidebar component.
 */
export interface ToolSidebarState {
  activeTab: 'canvas' | 'element';
  palette: GQLPalette | null;
  loading: boolean;
  error: string | null;
  selectedElementIds: string[];
  expandedSections: string[];
}

/**
 * Invoke tool mutation input.
 */
export interface GQLInvokeToolInput {
  id: string;
  editingContextId: string;
  representationId: string;
  toolId: string;
  diagramElementIds: string[];
  startingPositionX: number;
  startingPositionY: number;
  variables: any[];
}

/**
 * Invoke tool mutation variables.
 */
export interface GQLInvokeToolVariables {
  input: GQLInvokeToolInput;
}

/**
 * Invoke tool mutation response.
 */
export interface GQLInvokeToolData {
  invokeSingleClickOnDiagramElementTool: {
    __typename: string;
  };
}

/**
 * Payload for errors from tool invocation.
 */
export interface GQLErrorPayload {
  __typename: 'ErrorPayload';
  message: string;
}
