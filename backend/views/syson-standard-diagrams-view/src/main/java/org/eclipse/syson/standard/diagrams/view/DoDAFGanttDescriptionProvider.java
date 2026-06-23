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
 * <p>
 * Tasks are created manually (no auto-discovered candidates). The task list is a
 * tree: root tasks are placed directly under the ViewUsage, and any task can have
 * child sub-tasks. Name, description, start time, end time, and progress are all
 * manually editable.
 * </p>
 */
public class DoDAFGanttDescriptionProvider implements IRepresentationDescriptionProvider {

    public static final String DESCRIPTION_NAME = "DoDAF Gantt View";

    private final GanttBuilders ganttBuilders = new GanttBuilders();
    private final ViewBuilders viewBuilders = new ViewBuilders();

    @Override
    public RepresentationDescription create(IColorProvider colorProvider) {
        String domainType = SysMLMetamodelHelper.buildQualifiedName(SysmlPackage.eINSTANCE.getNamespace());

        // ---- Sub-task description (children of a task) ----
        // semantic candidates come from the parent task's owned elements
        var subTaskDescription = this.ganttBuilders.newTaskDescription()
                .name("DoDAFGantt-SubTask")
                .domainType(SysMLMetamodelHelper.buildQualifiedName(SysmlPackage.eINSTANCE.getPartUsage()))
                .semanticCandidatesExpression("aql:OrderedSet{}")
                .nameExpression("aql:self.declaredName")
                .descriptionExpression("aql:self.getGanttDescription()")
                .startTimeExpression("aql:self.getGanttStartTime()")
                .endTimeExpression("aql:self.getGanttEndTime()")
                .progressExpression("aql:self.getGanttProgress()")
                .computeStartEndDynamicallyExpression("aql:false")
                .build();

        // ---- Root task description ----
        // semanticCandidatesExpression is empty → no auto-discovery, manual creation only
        var taskDescription = this.ganttBuilders.newTaskDescription()
                .name("DoDAFGantt-Task")
                .domainType(SysMLMetamodelHelper.buildQualifiedName(SysmlPackage.eINSTANCE.getPartUsage()))
                .semanticCandidatesExpression("aql:self.getOrCreateDefaultGanttTasks()")
                .nameExpression("aql:self.declaredName")
                .descriptionExpression("aql:self.getGanttDescription()")
                .startTimeExpression("aql:self.getGanttStartTime()")
                .endTimeExpression("aql:self.getGanttEndTime()")
                .progressExpression("aql:self.getGanttProgress()")
                .computeStartEndDynamicallyExpression("aql:false")
                .subTaskElementDescriptions(subTaskDescription)
                .build();

        var createTaskTool = this.ganttBuilders.newCreateTaskTool()
                .name("新建任务")
                .body(this.viewBuilders.newChangeContext()
                        .expression(ServiceMethod.of0(DoDAFGanttQueryServices::createDefaultGanttTask).aqlSelf())
                        .build())
                .build();

        var editTaskTool = this.ganttBuilders.newEditTaskTool()
                .name("编辑任务")
                .body(
                        this.viewBuilders.newSetValue()
                                .featureName("declaredName")
                                .valueExpression("aql:newName")
                                .build(),
                        this.viewBuilders.newChangeContext()
                                .expression("aql:self.setGanttDescription(newDescription)")
                                .build(),
                        this.viewBuilders.newChangeContext()
                                .expression("aql:self.setGanttStartTime(newStartTime)")
                                .build(),
                        this.viewBuilders.newChangeContext()
                                .expression("aql:self.setGanttEndTime(newEndTime)")
                                .build(),
                        this.viewBuilders.newChangeContext()
                                .expression("aql:self.setGanttProgress(newProgress.toInteger())")
                                .build()
                )
                .build();

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
