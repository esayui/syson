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
import org.eclipse.sirius.components.view.builder.generated.gantt.GanttBuilders;
import org.eclipse.sirius.components.view.builder.generated.view.ViewBuilders;
import org.eclipse.sirius.components.view.builder.providers.IColorProvider;
import org.eclipse.sirius.components.view.builder.providers.IRepresentationDescriptionProvider;
import org.eclipse.syson.services.DeleteService;
import org.eclipse.syson.standard.diagrams.view.services.DoDAFGanttQueryServices;
import org.eclipse.syson.sysml.SysmlPackage;
import org.eclipse.syson.util.ServiceMethod;
import org.eclipse.syson.util.SysMLMetamodelHelper;

/**
 * Gantt description for DoDAF Gantt views (CV-3, PV-2, SV-8).
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
                .semanticCandidatesExpression("aql:self.getGanttTasks()")
                .nameExpression("aql:self.declaredName")
                .descriptionExpression("aql:self.getDocumentationBody()")
                .startTimeExpression("aql:OrderedSet{1}->at(1)")
                .endTimeExpression("aql:OrderedSet{10}->at(1)")
                .computeStartEndDynamicallyExpression("aql:true")
                .build();

        // Create task tool - creates a new PartUsage with default name (no dialog)
        var createTaskTool = this.ganttBuilders.newCreateTaskTool()
                .name("创建任务")
                .body(this.viewBuilders.newChangeContext()
                        .expression(ServiceMethod.of0(DoDAFGanttQueryServices::createDefaultGanttTask).aqlSelf())
                        .build())
                .build();

        // Edit task tool - SetValue on declaredName
        var editTaskTool = this.ganttBuilders.newEditTaskTool()
                .name("编辑任务")
                .body(this.viewBuilders.newSetValue()
                        .featureName("declaredName")
                        .valueExpression("aql:newName")
                        .build())
                .build();

        // Delete task tool
        var deleteTaskTool = this.ganttBuilders.newDeleteTaskTool()
                .name("删除任务")
                .body(this.viewBuilders.newChangeContext()
                        .expression(ServiceMethod.of0(DeleteService::deleteFromModel).aqlSelf())
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
}
