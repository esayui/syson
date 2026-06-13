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
  GQLEditDodafOperationalNodeAppearanceData,
  GQLEditDodafOperationalNodeAppearanceVariables,
  GQLDodafOperationalNodeAppearanceInput,
  UseUpdateDodafOperationalNodeAppearanceValue,
} from './useUpdateDodafOperationalNodeAppearance.types';

export const editDodafOperationalNodeAppearanceMutation = gql`
  mutation editDodafOperationalNodeAppearance($input: EditDodafOperationalNodeAppearanceInput!) {
    editDodafOperationalNodeAppearance(input: $input) {
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

export const useUpdateDodafOperationalNodeAppearance = (): UseUpdateDodafOperationalNodeAppearanceValue => {
  const [editDodafOperationalNodeAppearance, editDodafOperationalNodeAppearanceResult] = useMutation<
    GQLEditDodafOperationalNodeAppearanceData,
    GQLEditDodafOperationalNodeAppearanceVariables
  >(editDodafOperationalNodeAppearanceMutation);

  useReporting(
    editDodafOperationalNodeAppearanceResult,
    (data: GQLEditDodafOperationalNodeAppearanceData) => data.editDodafOperationalNodeAppearance
  );

  const updateDodafOperationalNodeAppearance = (
    editingContextId: string,
    representationId: string,
    nodeIds: string[],
    appearance: Partial<GQLDodafOperationalNodeAppearanceInput>
  ) =>
    editDodafOperationalNodeAppearance({
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
    updateDodafOperationalNodeAppearance,
  };
};
