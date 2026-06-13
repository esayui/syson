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

export interface UseUpdateDodafSystemNodeAppearanceValue {
  updateDodafSystemNodeAppearance: (
    editingContextId: string,
    representationId: string,
    nodeIds: string[],
    appearance: Partial<GQLDodafSystemNodeAppearanceInput>
  ) => void;
}

export interface GQLEditDodafSystemNodeAppearanceData {
  editDodafSystemNodeAppearance: GQLEditDodafSystemNodeAppearancePayload;
}

export type GQLEditDodafSystemNodeAppearancePayload = GQLErrorPayload | GQLSuccessPayload;

export interface GQLEditDodafSystemNodeAppearanceVariables {
  input: GQLEditDodafSystemNodeAppearanceInput;
}

export interface GQLEditDodafSystemNodeAppearanceInput {
  id: string;
  editingContextId: string;
  representationId: string;
  nodeIds: string[];
  appearance: Partial<GQLDodafSystemNodeAppearanceInput>;
}

export interface GQLDodafSystemNodeAppearanceInput {
  background: string;
  borderColor: string;
  borderSize: number;
  borderStyle: string;
}
