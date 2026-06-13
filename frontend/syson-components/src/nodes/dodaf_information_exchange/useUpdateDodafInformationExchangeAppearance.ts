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
  GQLEditDodafInformationExchangeAppearanceData,
  GQLEditDodafInformationExchangeAppearanceVariables,
  GQLDodafInformationExchangeAppearanceInput,
  UseUpdateDodafInformationExchangeAppearanceValue,
} from './useUpdateDodafInformationExchangeAppearance.types';

export const editDodafInformationExchangeAppearanceMutation = gql`
  mutation editDodafInformationExchangeAppearance($input: EditDodafInformationExchangeAppearanceInput!) {
    editDodafInformationExchangeAppearance(input: $input) {
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

export const useUpdateDodafInformationExchangeAppearance = (): UseUpdateDodafInformationExchangeAppearanceValue => {
  const [editDodafInformationExchangeAppearance, editDodafInformationExchangeAppearanceResult] = useMutation<
    GQLEditDodafInformationExchangeAppearanceData,
    GQLEditDodafInformationExchangeAppearanceVariables
  >(editDodafInformationExchangeAppearanceMutation);

  useReporting(
    editDodafInformationExchangeAppearanceResult,
    (data: GQLEditDodafInformationExchangeAppearanceData) => data.editDodafInformationExchangeAppearance
  );

  const updateDodafInformationExchangeAppearance = (
    editingContextId: string,
    representationId: string,
    nodeIds: string[],
    appearance: Partial<GQLDodafInformationExchangeAppearanceInput>
  ) =>
    editDodafInformationExchangeAppearance({
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
    updateDodafInformationExchangeAppearance,
  };
};
