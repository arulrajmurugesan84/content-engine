package org.content.resolver;

import com.fasterxml.jackson.databind.JsonNode;
import com.jayway.jsonpath.JsonPath;

import java.util.List;
import java.util.Optional;

public final class JsonPathValueResolver {

    public Optional<Object> resolveFirst(JsonNode root, List<String> paths) {
        for (String path : paths) {
            try {
                Object value = JsonPath.read(root.toString(), path);
                if (value != null) return Optional.of(value);
            } catch (Exception ignored) {}
        }
        return Optional.empty();
    }

    public Object resolve(JsonNode root, String path) {
        try {
            return JsonPath.read(root.toString(), path);
        } catch (Exception e) {
            return null;
        }
    }
}
