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

export interface UseUpdateDodafOrganizationAppearanceValue {
  updateDodafOrganizationAppearance: (
    editingContextId: string,
    representationId: string,
    nodeIds: string[],
    appearance: Partial<GQLDodafOrganizationAppearanceInput>
  ) => void;
}

export interface GQLEditDodafOrganizationAppearanceData {
  editDodafOrganizationAppearance: GQLEditDodafOrganizationAppearancePayload;
}

export type GQLEditDodafOrganizationAppearancePayload = GQLErrorPayload | GQLSuccessPayload;

export interface GQLEditDodafOrganizationAppearanceVariables {
  input: GQLEditDodafOrganizationAppearanceInput;
}

export interface GQLEditDodafOrganizationAppearanceInput {
  id: string;
  editingContextId: string;
  representationId: string;
  nodeIds: string[];
  appearance: Partial<GQLDodafOrganizationAppearanceInput>;
}

export interface GQLDodafOrganizationAppearanceInput {
  background: string;
  borderColor: string;
  borderSize: number;
  borderStyle: string;
}
