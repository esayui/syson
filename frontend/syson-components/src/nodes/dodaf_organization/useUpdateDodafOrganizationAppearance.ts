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
import { gql, useMutation } from '@apollo/client';
import { useReporting } from '@eclipse-sirius/sirius-components-core';
import {
  GQLEditDodafOrganizationAppearanceData,
  GQLEditDodafOrganizationAppearanceVariables,
  GQLDodafOrganizationAppearanceInput,
  UseUpdateDodafOrganizationAppearanceValue,
} from './useUpdateDodafOrganizationAppearance.types';

export const editDodafOrganizationAppearanceMutation = gql`
  mutation editDodafOrganizationAppearance($input: EditDodafOrganizationAppearanceInput!) {
    editDodafOrganizationAppearance(input: $input) {
      __typename
      ... on ErrorPayload {
        messages {
          body
          level
        }
      }
      ... on SuccessPayload {
        messages {
          body
          level
        }
      }
    }
  }
`;

export const useUpdateDodafOrganizationAppearance = (): UseUpdateDodafOrganizationAppearanceValue => {
  const [editDodafOrganizationAppearance, editDodafOrganizationAppearanceResult] = useMutation<
    GQLEditDodafOrganizationAppearanceData,
    GQLEditDodafOrganizationAppearanceVariables
  >(editDodafOrganizationAppearanceMutation);

  useReporting(
    editDodafOrganizationAppearanceResult,
    (data: GQLEditDodafOrganizationAppearanceData) => data.editDodafOrganizationAppearance
  );

  const updateDodafOrganizationAppearance = (
    editingContextId: string,
    representationId: string,
    nodeIds: string[],
    appearance: Partial<GQLDodafOrganizationAppearanceInput>
  ) =>
    editDodafOrganizationAppearance({
      variables: {
        input: {
          id: crypto.randomUUID(),
          editingContextId,
          representationId,
          nodeIds,
          appearance,
        },
      },
    });

  return {
    updateDodafOrganizationAppearance,
  };
};
