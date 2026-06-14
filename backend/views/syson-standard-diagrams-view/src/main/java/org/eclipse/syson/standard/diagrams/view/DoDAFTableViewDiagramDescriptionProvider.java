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
package org.eclipse.syson.standard.diagrams.view;

import org.eclipse.sirius.components.view.RepresentationDescription;
import org.eclipse.sirius.components.view.builder.generated.diagram.DiagramBuilders;
import org.eclipse.sirius.components.view.builder.providers.IColorProvider;
import org.eclipse.sirius.components.view.builder.providers.IRepresentationDescriptionProvider;
import org.eclipse.sirius.components.view.diagram.ArrangeLayoutDirection;
import org.eclipse.sirius.components.view.diagram.DiagramLayoutOption;
import org.eclipse.syson.common.view.api.IViewDescriptionProvider;
import org.eclipse.syson.standard.diagrams.view.services.DoDAFTableViewCreateService;
import org.eclipse.syson.sysml.SysmlPackage;
import org.eclipse.syson.util.ServiceMethod;
import org.eclipse.syson.util.SysMLMetamodelHelper;
import org.springframework.stereotype.Service;

/**
 * Allows to register the DoDAF Table View diagram in the application. Used for DoDAF table/list views
 * (StdV-1, StdV-2, AV-2).
 * The actual diagram created is the SDV one.
 *
 * @author dousheng
 */
@Service
public class DoDAFTableViewDiagramDescriptionProvider implements IViewDescriptionProvider {

    public static final String DESCRIPTION_NAME = "DoDAF Table View";

    @Override
    public String getViewId() {
        return "DoDAFTableViewDiagram";
    }

    @Override
    public IRepresentationDescriptionProvider getRepresentationDescriptionProvider() {
        return new DoDAFTableDescriptionProvider();
    }
}
