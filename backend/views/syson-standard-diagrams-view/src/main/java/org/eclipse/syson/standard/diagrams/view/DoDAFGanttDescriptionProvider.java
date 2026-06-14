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

import java.util.ArrayList;

import org.eclipse.sirius.components.view.RepresentationDescription;
import org.eclipse.sirius.components.view.builder.generated.gantt.GanttBuilders;
import org.eclipse.sirius.components.view.builder.generated.view.ViewBuilders;
import org.eclipse.sirius.components.view.builder.providers.IColorProvider;
import org.eclipse.sirius.components.view.builder.providers.IRepresentationDescriptionProvider;
import org.eclipse.syson.sysml.SysmlPackage;
import org.eclipse.syson.util.SysMLMetamodelHelper;

/**
 * Gantt description for DoDAF Gantt views (CV-3, PV-2, SV-8).
 * Displays a left table (milestones) with right Gantt chart showing timeline bars.
 * Tasks are linked via dependency relationships.
 *
 * @author dousheng
 */
public class DoDAFGanttDescriptionProvider implements IRepresentationDescriptionProvider {

    public static final String DESCRIPTION_NAME = "DoDAF Gantt View";

    private final GanttBuilders ganttBuilders = new GanttBuilders();
    private final ViewBuilders viewBuilders = new ViewBuilders();

    @Override
    public RepresentationDescription create(IColorProvider colorProvider) {
        String domainType = SysMLMetamodelHelper.buildQualifiedName(SysmlPackage.eINSTANCE.getNamespace());

        var taskDescription = this.ganttBuilders.newTaskDescription()
                .name("DoDAFGantt-Task")
                .domainType(SysMLMetamodelHelper.buildQualifiedName(SysmlPackage.eINSTANCE.getPartUsage()))
                .semanticCandidatesExpression("aql:self.getExposedElements(PartUsage)")
                .nameExpression("aql:self.declaredName")
                .descriptionExpression("aql:self.getDocumentationBody()")
                .startTimeExpression("aql:self.ownedElementIndexOf->toDate()")
                .endTimeExpression("aql:self.ownedElementIndexOf->toDate()+7")
                .computeStartEndDynamicallyExpression("aql:true")
                .taskDependenciesExpression("aql:self.getDependencyTargets()")
                .build();

        var createTaskTool = this.ganttBuilders.newCreateTaskTool()
                .name("Create Task")
                .body(this.viewBuilders.newChangeContext()
                        .expression("aql:self.createChildPartUsage('New Task')")
                        .build())
                .build();

        var editTaskTool = this.ganttBuilders.newEditTaskTool()
                .name("Edit Task")
                .body(this.viewBuilders.newChangeContext()
                        .expression("aql:self.directEdit(newLabel)")
                        .build())
                .build();

        var deleteTaskTool = this.ganttBuilders.newDeleteTaskTool()
                .name("Delete Task")
                .body(this.viewBuilders.newChangeContext()
                        .expression("aql:self.deleteFromModel()")
                        .build())
                .build();

        return this.ganttBuilders.newGanttDescription()
                .name(DESCRIPTION_NAME)
                .titleExpression("aql:self.name")
                .domainType(domainType)
                .taskElementDescriptions(taskDescription)
                .createTool(createTaskTool)
                .editTool(editTaskTool)
                .deleteTool(deleteTaskTool)
                .build();
    }

    /**
     * Returns a list of task descriptions. The first one is the main task,
     * additional ones can be defined for different element types.
     */
    private org.eclipse.sirius.components.view.gantt.TaskDescription[] createTaskDescriptions() {
        var mainTask = this.ganttBuilders.newTaskDescription()
                .name("DoDAFGantt-MainTask")
                .domainType(SysMLMetamodelHelper.buildQualifiedName(SysmlPackage.eINSTANCE.getPartUsage()))
                .semanticCandidatesExpression("aql:self.getExposedElements(PartUsage)")
                .nameExpression("aql:self.declaredName")
                .descriptionExpression("aql:self.getDocumentationBody()")
                .startTimeExpression("aql:Sequence{1}->at(1)")
                .endTimeExpression("aql:Sequence{10}->at(1)")
                .computeStartEndDynamicallyExpression("aql:true")
                .build();

        return new org.eclipse.sirius.components.view.gantt.TaskDescription[] { mainTask };
    }
}
