package org.content.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;

public final class JsonLoader {

    private final ObjectMapper objectMapper;

    public JsonLoader(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public JsonNode load(Path path) {
        try {
            return objectMapper.readTree(path.toFile());
        } catch (IOException e) {
            throw new RuntimeException("Failed to load JSON: " + path, e);
        }
    }
}
