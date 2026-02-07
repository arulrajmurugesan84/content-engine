package org.content.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Map;

public final class JsonMerger {

    private final ObjectMapper objectMapper;

    public JsonMerger(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public JsonNode merge(Map<String, JsonNode> nodes) {
        ObjectNode root = objectMapper.createObjectNode();
        nodes.forEach(root::set);
        return root;
    }
}
