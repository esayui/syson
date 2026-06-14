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
package org.eclipse.syson.standard.diagrams.view.services;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.syson.sysml.Element;
import org.eclipse.syson.sysml.SysmlPackage;

/**
 * Creation-related Java services used by the {@link org.eclipse.syson.standard.diagrams.view.DoDAFMatrixViewDiagramDescriptionProvider}.
 */
public class DoDAFMatrixViewCreateService {

    public boolean canCreateDiagram(Element element) {
        List<EClass> acceptedRootTypes = List.of(
                SysmlPackage.eINSTANCE.getPackage(),
                SysmlPackage.eINSTANCE.getViewUsage());
        return acceptedRootTypes.stream().anyMatch(rootType -> rootType.isSuperTypeOf(element.eClass()));
    }
}
