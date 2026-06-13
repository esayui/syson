package org.eclipse.syson.application.sysmlv2;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.syson.sysml.Element;
import org.eclipse.syson.sysml.Namespace;
import org.eclipse.syson.sysml.ViewUsage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Injects MembershipExpose entries into a DoDAFv2 template resource JSON,
 * so that all ViewUsage children are auto-displayed on their diagrams.
 * This mirrors how Batmobile.json was created - via JSON manipulation after programmatic build.
 */
public class Dodafv2JsonExposeInjector {

    private static final Logger logger = LoggerFactory.getLogger(Dodafv2JsonExposeInjector.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void inject(Resource resource) {
        try {
            // Save to JSON
            var baos = new ByteArrayOutputStream();
            resource.save(baos, null);
            String json = baos.toString();
            JsonNode root = mapper.readTree(json);

            // Build element ID -> JSON pointer map
            Map<String, String> idToPath = new HashMap<>();
            buildIdMap(root, "", idToPath);

            // Walk the content tree to find ViewUsages, inject expose for their children
            ArrayNode content = (ArrayNode) root.get("content");
            if (content != null) {
                injectExposeForViewUsages(content.get(0), idToPath);
            }

            // Reload modified JSON
            String modified = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(root);
            resource.unload();
            resource.load(new ByteArrayInputStream(modified.getBytes("UTF-8")), null);
            logger.info("DoDAFv2 template: MembershipExpose injected via JSON manipulation");
        } catch (IOException e) {
            logger.warn("Failed to inject DoDAFv2 expose: {}", e.getMessage());
        }
    }

    private static void buildIdMap(JsonNode node, String path, Map<String, String> idToPath) {
        if (node.isObject()) {
            JsonNode idNode = node.get("id");
            if (idNode != null) {
                idToPath.put(idNode.asText(), path);
            }
            var fields = node.fields();
            while (fields.hasNext()) {
                var entry = fields.next();
                buildIdMap(entry.getValue(), path + "/" + entry.getKey(), idToPath);
            }
        } else if (node.isArray()) {
            for (int i = 0; i < node.size(); i++) {
                buildIdMap(node.get(i), path + "/" + i, idToPath);
            }
        }
    }

    private static void injectExposeForViewUsages(JsonNode contentRoot, Map<String, String> idToPath) {
        if (contentRoot == null) return;
        JsonNode data = contentRoot.get("data");
        if (data == null) return;
        JsonNode ownedRelationships = data.get("ownedRelationship");
        if (ownedRelationships == null || !ownedRelationships.isArray()) return;

        // Walk all owned relationships to find ViewUsages
        for (var rel : ownedRelationships) {
            processNode(rel, idToPath);
        }
    }

    private static void processNode(JsonNode relNode, Map<String, String> idToPath) {
        // Check if this relationship contains a ViewUsage
        JsonNode relatedElements = relNode.get("data") != null ? relNode.get("data").get("ownedRelatedElement") : null;
        if (relatedElements == null || !relatedElements.isArray()) return;

        for (var elem : relatedElements) {
            String eClass = elem.has("eClass") ? elem.get("eClass").asText() : "";
            if (eClass.contains("ViewUsage")) {
                // Found a ViewUsage - inject expose for its children
                injectExposeForViewUsage(elem, idToPath);
            }
            // Recurse into this element's owned relationships
            JsonNode elemData = elem.get("data");
            if (elemData != null) {
                JsonNode elemRels = elemData.get("ownedRelationship");
                if (elemRels != null && elemRels.isArray()) {
                    for (var subRel : elemRels) {
                        processNode(subRel, idToPath);
                    }
                }
            }
        }
    }

    private static void injectExposeForViewUsage(JsonNode viewUsage, Map<String, String> idToPath) {
        JsonNode data = viewUsage.get("data");
        if (data == null) return;
        JsonNode ownedRels = data.get("ownedRelationship");
        if (ownedRels == null || !ownedRels.isArray()) return;

        // Collect exposes first, then add (avoid ConcurrentModificationException)
        java.util.List<ObjectNode> newExposes = new java.util.ArrayList<>();
        for (var rel : ownedRels) {
            String relEClass = rel.has("eClass") ? rel.get("eClass").asText() : "";
            if (!relEClass.contains("OwningMembership")) continue;

            JsonNode relData = rel.get("data");
            if (relData == null) continue;
            JsonNode ownedRelated = relData.get("ownedRelatedElement");
            if (ownedRelated == null || !ownedRelated.isArray() || ownedRelated.size() == 0) continue;

            JsonNode childElem = ownedRelated.get(0);
            String childId = childElem.has("id") ? childElem.get("id").asText() : null;
            String childEClass = childElem.has("eClass") ? childElem.get("eClass").asText() : "";
            if (childId == null || childEClass.contains("ViewUsage")) continue;

            String exposeId = java.util.UUID.randomUUID().toString();
            ObjectNode expose = mapper.createObjectNode();
            expose.put("id", exposeId);
            expose.put("eClass", "sysml:MembershipExpose");
            ObjectNode exposeData = mapper.createObjectNode();
            exposeData.put("elementId", exposeId);
            ArrayNode exposeOwnedRel = mapper.createArrayNode();
            ObjectNode ref = mapper.createObjectNode();
            ref.put("$ref", childId);
            exposeOwnedRel.add(ref);
            exposeData.set("ownedRelatedElement", exposeOwnedRel);
            expose.set("data", exposeData);
            newExposes.add(expose);
        }
        // Add all new exposes after iteration
        ArrayNode viewRels = (ArrayNode) data.get("ownedRelationship");
        newExposes.forEach(viewRels::add);
    }
}
