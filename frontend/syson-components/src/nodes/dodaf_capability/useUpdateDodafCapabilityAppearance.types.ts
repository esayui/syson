/*******************************************************************************
 * Copyright (c) 2025 Obeo.
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
import { GQLErrorPayload, GQLSuccessPayload } from '@eclipse-sirius/sirius-components-core';

export interface UseUpdateDodafCapabilityAppearanceValue {
  updateDodafCapabilityAppearance: (
    editingContextId: string,
    representationId: string,
    nodeIds: string[],
    appearance: Partial<GQLDodafCapabilityAppearanceInput>
  ) => void;
}

export interface GQLEditDodafCapabilityAppearanceData {
  editDodafCapabilityAppearance: GQLEditDodafCapabilityAppearancePayload;
}

export type GQLEditDodafCapabilityAppearancePayload = GQLErrorPayload | GQLSuccessPayload;

export interface GQLEditDodafCapabilityAppearanceVariables {
  input: GQLEditDodafCapabilityAppearanceInput;
}

export interface GQLEditDodafCapabilityAppearanceInput {
  id: string;
  editingContextId: string;
  representationId: string;
  nodeIds: string[];
  appearance: Partial<GQLDodafCapabilityAppearanceInput>;
}

export interface GQLDodafCapabilityAppearanceInput {
  background: string;
  borderColor: string;
  borderSize: number;
  borderStyle: string;
}
