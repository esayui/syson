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

import { NodeTypeContribution } from '@eclipse-sirius/sirius-components-diagrams';
import { NodeTypeRegistry } from '@eclipse-sirius/sirius-web-application';

import { NodeProps } from '@xyflow/react';
import { SysMLImportedPackageNode } from '../../nodes/imported_package/SysMLImportedPackageNode';
import { SysMLImportedPackageNodeConverter } from '../../nodes/imported_package/SysMLImportedPackageNodeConverter';
import { SysMLImportedPackageNodeLayoutHandler } from '../../nodes/imported_package/SysMLImportedPackageNodeLayoutHandler';
import { SysMLNoteNode } from '../../nodes/note/SysMLNoteNode';
import { SysMLNoteNodeConverter } from '../../nodes/note/SysMLNoteNodeConverter';
import { SysMLNoteNodeLayoutHandler } from '../../nodes/note/SysMLNoteNodeLayoutHandler';
import { SysMLPackageNode } from '../../nodes/package/SysMLPackageNode';
import { SysMLPackageNodeConverter } from '../../nodes/package/SysMLPackageNodeConverter';
import { SysMLPackageNodeLayoutHandler } from '../../nodes/package/SysMLPackageNodeLayoutHandler';
import { SysMLViewFrameNode } from '../../nodes/view_frame/SysMLViewFrameNode';
import { SysMLViewFrameNodeConverter } from '../../nodes/view_frame/SysMLViewFrameNodeConverter';
import { SysMLViewFrameNodeLayoutHandler } from '../../nodes/view_frame/SysMLViewFrameNodeLayoutHandler';
import { DodafOperationalNode } from '../../nodes/dodaf_operational_node/DodafOperationalNode';
import { DodafOperationalNodeConverter } from '../../nodes/dodaf_operational_node/DodafOperationalNodeConverter';
import { DodafOperationalNodeLayoutHandler } from '../../nodes/dodaf_operational_node/DodafOperationalNodeLayoutHandler';
import { DodafSystemNode } from '../../nodes/dodaf_system_node/DodafSystemNode';
import { DodafSystemNodeConverter } from '../../nodes/dodaf_system_node/DodafSystemNodeConverter';
import { DodafSystemNodeLayoutHandler } from '../../nodes/dodaf_system_node/DodafSystemNodeLayoutHandler';
import { DodafCapability } from '../../nodes/dodaf_capability/DodafCapability';
import { DodafCapabilityConverter } from '../../nodes/dodaf_capability/DodafCapabilityConverter';
import { DodafCapabilityLayoutHandler } from '../../nodes/dodaf_capability/DodafCapabilityLayoutHandler';
import { DodafOrganization } from '../../nodes/dodaf_organization/DodafOrganization';
import { DodafOrganizationConverter } from '../../nodes/dodaf_organization/DodafOrganizationConverter';
import { DodafOrganizationLayoutHandler } from '../../nodes/dodaf_organization/DodafOrganizationLayoutHandler';
import { DodafInformationExchange } from '../../nodes/dodaf_information_exchange/DodafInformationExchange';
import { DodafInformationExchangeConverter } from '../../nodes/dodaf_information_exchange/DodafInformationExchangeConverter';
import { DodafInformationExchangeLayoutHandler } from '../../nodes/dodaf_information_exchange/DodafInformationExchangeLayoutHandler';

/*******************************************************************************
 *
 * Custom nodes contributions
 *
 *******************************************************************************/
const sysONNodeTypeRegistry: NodeTypeRegistry = {
  nodeLayoutHandlers: [
    new SysMLPackageNodeLayoutHandler(),
    new SysMLNoteNodeLayoutHandler(),
    new SysMLImportedPackageNodeLayoutHandler(),
    new SysMLViewFrameNodeLayoutHandler(),
    new DodafOperationalNodeLayoutHandler(),
    new DodafSystemNodeLayoutHandler(),
    new DodafCapabilityLayoutHandler(),
    new DodafOrganizationLayoutHandler(),
    new DodafInformationExchangeLayoutHandler(),
  ],
  nodeConverters: [
    new SysMLPackageNodeConverter(),
    new SysMLNoteNodeConverter(),
    new SysMLImportedPackageNodeConverter(),
    new SysMLViewFrameNodeConverter(),
    new DodafOperationalNodeConverter(),
    new DodafSystemNodeConverter(),
    new DodafCapabilityConverter(),
    new DodafOrganizationConverter(),
    new DodafInformationExchangeConverter(),
  ],
  nodeTypeContributions: [
    <NodeTypeContribution component={SysMLPackageNode as unknown as React.FC<NodeProps>} type={'sysMLPackageNode'} />,
    <NodeTypeContribution component={SysMLNoteNode as unknown as React.FC<NodeProps>} type={'sysMLNoteNode'} />,
    <NodeTypeContribution
      component={SysMLImportedPackageNode as unknown as React.FC<NodeProps>}
      type={'sysMLImportedPackageNode'}
    />,
    <NodeTypeContribution
      component={SysMLViewFrameNode as unknown as React.FC<NodeProps>}
      type={'sysMLViewFrameNode'}
    />,
    <NodeTypeContribution
      component={DodafOperationalNode as unknown as React.FC<NodeProps>}
      type={'dodafOperationalNode'}
    />,
    <NodeTypeContribution
      component={DodafSystemNode as unknown as React.FC<NodeProps>}
      type={'dodafSystemNode'}
    />,
    <NodeTypeContribution
      component={DodafCapability as unknown as React.FC<NodeProps>}
      type={'dodafCapability'}
    />,
    <NodeTypeContribution
      component={DodafOrganization as unknown as React.FC<NodeProps>}
      type={'dodafOrganization'}
    />,
    <NodeTypeContribution
      component={DodafInformationExchange as unknown as React.FC<NodeProps>}
      type={'dodafInformationExchange'}
    />,
  ],
};

export { sysONNodeTypeRegistry };
