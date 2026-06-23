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
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.syson.sysml.Documentation;
import org.eclipse.syson.sysml.Element;
import org.eclipse.syson.sysml.PartUsage;
import org.eclipse.syson.sysml.SysmlFactory;
import org.eclipse.syson.sysml.ViewUsage;
import org.eclipse.syson.sysml.metamodel.util.ElementUtil;

/**
 * Query services for the DoDAF Gantt View.
 * <p>
 * Stores start/end time and progress in memory (keyed by element UUID).
 * In production, these would be persisted to the EMF model.
 * </p>
 */
public class DoDAFGanttQueryServices {

    private static final String DEFAULT_START = "2026-01-01";
    private static final String DEFAULT_END = "2026-01-31";

    private static final Map<String, String> startTimeMap = new ConcurrentHashMap<>();
    private static final Map<String, String> endTimeMap = new ConcurrentHashMap<>();
    private static final Map<String, Integer> progressMap = new ConcurrentHashMap<>();

    private static String idOf(Element e) {
        return e.getElementId() != null ? e.getElementId() : "";
    }

    // ---- Task retrieval ----

    /**
     * Returns existing tasks; creates a default one if none exist (so the user has
     * an anchor to right-click for the ContextualPalette "create" button).
     */
    public List<Element> getOrCreateDefaultGanttTasks(ViewUsage viewUsage) {
        var tasks = viewUsage.getOwnedElement().stream()
                .filter(PartUsage.class::isInstance)
                .toList();
        if (tasks.isEmpty()) {
            return List.of(createDefaultGanttTask(viewUsage));
        }
        return tasks;
    }

    public List<Element> getGanttTasks(ViewUsage viewUsage) {
        return viewUsage.getOwnedElement().stream()
                .filter(PartUsage.class::isInstance)
                .toList();
    }

    public List<Element> getSubTasks(PartUsage parentTask) {
        return parentTask.getOwnedElement().stream()
                .filter(PartUsage.class::isInstance)
                .toList();
    }

    // ---- Create tasks ----

    public Element createDefaultGanttTask(ViewUsage viewUsage) {
        return createPartUsageUnder(viewUsage, "新任务");
    }

    private PartUsage createPartUsageUnder(Element parent, String name) {
        var partUsage = SysmlFactory.eINSTANCE.createPartUsage();
        partUsage.setDeclaredName(name);
        String id = ElementUtil.generateUUID(partUsage).toString();
        partUsage.setElementId(id);
        var owningMembership = SysmlFactory.eINSTANCE.createOwningMembership();
        parent.getOwnedRelationship().add(owningMembership);
        owningMembership.getOwnedRelatedElement().add(partUsage);
        startTimeMap.put(id, DEFAULT_START);
        endTimeMap.put(id, DEFAULT_END);
        progressMap.put(id, 0);
        return partUsage;
    }

    // ---- Start time ----

    public String getGanttStartTime(Element task) {
        return startTimeMap.getOrDefault(idOf(task), DEFAULT_START);
    }

    public Element setGanttStartTime(Element task, String dateStr) {
        if (dateStr != null && !dateStr.isBlank()) {
            startTimeMap.put(idOf(task), dateStr);
        }
        return task;
    }

    // ---- End time ----

    public String getGanttEndTime(Element task) {
        return endTimeMap.getOrDefault(idOf(task), DEFAULT_END);
    }

    public Element setGanttEndTime(Element task, String dateStr) {
        if (dateStr != null && !dateStr.isBlank()) {
            endTimeMap.put(idOf(task), dateStr);
        }
        return task;
    }

    // ---- Progress (0-100) ----

    public int getGanttProgress(Element task) {
        return progressMap.getOrDefault(idOf(task), 0);
    }

    public Element setGanttProgress(Element task, int progress) {
        int clamped = progress < 0 ? 0 : (progress > 100 ? 100 : progress);
        progressMap.put(idOf(task), clamped);
        return task;
    }

    // ---- Documentation (description) ----

    public String getGanttDescription(Element task) {
        return task.getOwnedElement().stream()
                .filter(Documentation.class::isInstance)
                .map(Documentation.class::cast)
                .findFirst()
                .map(d -> d.getBody() != null ? d.getBody() : "")
                .orElse("");
    }

    public Element setGanttDescription(Element task, String text) {
        var doc = task.getOwnedElement().stream()
                .filter(Documentation.class::isInstance)
                .map(Documentation.class::cast)
                .findFirst()
                .orElseGet(() -> {
                    var d = SysmlFactory.eINSTANCE.createDocumentation();
                    var om = SysmlFactory.eINSTANCE.createOwningMembership();
                    task.getOwnedRelationship().add(om);
                    om.getOwnedRelatedElement().add(d);
                    return d;
                });
        doc.setBody(text != null ? text : "");
        return task;
    }
}
