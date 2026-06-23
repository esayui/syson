/*******************************************************************************
 * Copyright (c) 2026 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.syson.standard.diagrams.view.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Data service for DoDAF Gantt tasks stored as JSON in representation content.
 * <p>
 * Tasks are persisted as a JSON array in the Gantt representation's content field.
 * Each task has: id, parentId, name, description, startDate, endDate, progress.
 * </p>
 */
public class DoDAFGanttDataService {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * A single Gantt task.
     */
    public record GanttTaskData(
            String id,
            String parentId,
            String name,
            String description,
            String startDate,
            String endDate,
            int progress
    ) {
        public GanttTaskData withName(String n) { return new GanttTaskData(id, parentId, n, description, startDate, endDate, progress); }
        public GanttTaskData withDescription(String d) { return new GanttTaskData(id, parentId, name, d, startDate, endDate, progress); }
        public GanttTaskData withStartDate(String s) { return new GanttTaskData(id, parentId, name, description, s, endDate, progress); }
        public GanttTaskData withEndDate(String e) { return new GanttTaskData(id, parentId, name, description, startDate, e, progress); }
        public GanttTaskData withProgress(int p) { return new GanttTaskData(id, parentId, name, description, startDate, endDate, p); }
    }

    /** In-memory store keyed by representationId. */
    private static final Map<String, List<GanttTaskData>> store = new ConcurrentHashMap<>();

    // ---- JSON serialization ----

    public static String toJson(List<GanttTaskData> tasks) {
        try { return MAPPER.writeValueAsString(tasks); } catch (JsonProcessingException e) { return "[]"; }
    }

    public static List<GanttTaskData> fromJson(String json) {
        if (json == null || json.isBlank()) return new ArrayList<>();
        try {
            return MAPPER.readValue(json, new TypeReference<List<GanttTaskData>>() {});
        } catch (JsonProcessingException e) {
            return new ArrayList<>();
        }
    }

    // ---- CRUD ----

    public static List<GanttTaskData> getTasks(String representationId) {
        return store.computeIfAbsent(representationId, k -> new ArrayList<>());
    }

    public static GanttTaskData createTask(String representationId, String parentId) {
        List<GanttTaskData> tasks = getTasks(representationId);
        var task = new GanttTaskData(
                UUID.randomUUID().toString(),
                parentId,
                "新任务",
                "",
                LocalDate.now().format(DATE_FMT),
                LocalDate.now().plusDays(30).format(DATE_FMT),
                0
        );
        tasks.add(task);
        return task;
    }

    public static GanttTaskData updateTask(String representationId, GanttTaskData updated) {
        List<GanttTaskData> tasks = getTasks(representationId);
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).id().equals(updated.id())) {
                tasks.set(i, updated);
                return updated;
            }
        }
        return null;
    }

    public static boolean deleteTask(String representationId, String taskId) {
        List<GanttTaskData> tasks = getTasks(representationId);
        // also delete children
        tasks.removeIf(t -> t.id().equals(taskId) || taskId.equals(t.parentId()));
        return true;
    }

    public static List<GanttTaskData> getChildTasks(String representationId, String parentId) {
        return getTasks(representationId).stream()
                .filter(t -> parentId == null ? t.parentId() == null : parentId.equals(t.parentId()))
                .toList();
    }
}
