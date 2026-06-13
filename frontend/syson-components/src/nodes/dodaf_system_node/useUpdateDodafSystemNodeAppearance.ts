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
  GQLEditDodafSystemNodeAppearanceData,
  GQLEditDodafSystemNodeAppearanceVariables,
  GQLDodafSystemNodeAppearanceInput,
  UseUpdateDodafSystemNodeAppearanceValue,
} from './useUpdateDodafSystemNodeAppearance.types';

export const editDodafSystemNodeAppearanceMutation = gql`
  mutation editDodafSystemNodeAppearance($input: EditDodafSystemNodeAppearanceInput!) {
    editDodafSystemNodeAppearance(input: $input) {
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

export const useUpdateDodafSystemNodeAppearance = (): UseUpdateDodafSystemNodeAppearanceValue => {
  const [editDodafSystemNodeAppearance, editDodafSystemNodeAppearanceResult] = useMutation<
    GQLEditDodafSystemNodeAppearanceData,
    GQLEditDodafSystemNodeAppearanceVariables
  >(editDodafSystemNodeAppearanceMutation);

  useReporting(
    editDodafSystemNodeAppearanceResult,
    (data: GQLEditDodafSystemNodeAppearanceData) => data.editDodafSystemNodeAppearance
  );

  const updateDodafSystemNodeAppearance = (
    editingContextId: string,
    representationId: string,
    nodeIds: string[],
    appearance: Partial<GQLDodafSystemNodeAppearanceInput>
  ) =>
    editDodafSystemNodeAppearance({
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
    updateDodafSystemNodeAppearance,
  };
};
