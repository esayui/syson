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

import org.eclipse.syson.sysml.Element;
import org.eclipse.syson.sysml.SysmlFactory;
import org.eclipse.syson.sysml.ViewUsage;
import org.eclipse.syson.sysml.metamodel.util.ElementUtil;

/**
 * Query services for the DoDAF Gantt View.
 */
public class DoDAFGanttQueryServices {

    /**
     * Returns all elements owned by the ViewUsage for Gantt task display.
     */
    public List<Element> getGanttTasks(ViewUsage viewUsage) {
        return viewUsage.getOwnedElement().stream().toList();
    }

    /**
     * Creates a new PartUsage element as a Gantt task inside the ViewUsage (no arguments).
     * Used by the CreateTaskTool which doesn't provide AQL variables.
     */
    public Element createDefaultGanttTask(ViewUsage viewUsage) {
        var partUsage = SysmlFactory.eINSTANCE.createPartUsage();
        partUsage.setDeclaredName("新任务");
        partUsage.setElementId(ElementUtil.generateUUID(partUsage).toString());
        var owningMembership = SysmlFactory.eINSTANCE.createOwningMembership();
        viewUsage.getOwnedRelationship().add(owningMembership);
        owningMembership.getOwnedRelatedElement().add(partUsage);
        return partUsage;
    }

    /**
     * Creates a new PartUsage element as a Gantt task inside the ViewUsage.
     */
    public Element createGanttTask(ViewUsage viewUsage, String name) {
        var partUsage = SysmlFactory.eINSTANCE.createPartUsage();
        partUsage.setDeclaredName(name != null && !name.isBlank() ? name : "新任务");
        partUsage.setElementId(ElementUtil.generateUUID(partUsage).toString());
        var owningMembership = SysmlFactory.eINSTANCE.createOwningMembership();
        viewUsage.getOwnedRelationship().add(owningMembership);
        owningMembership.getOwnedRelatedElement().add(partUsage);
        return partUsage;
    }
}
