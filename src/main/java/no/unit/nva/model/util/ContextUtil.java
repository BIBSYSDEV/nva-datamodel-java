package no.unit.nva.model.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ContextUtil {

    /**
     * Inject a context json object into a json object.
     *
     * @param jsonNode json object to inject context into
     * @param context context as json
     */
    public static void injectContext(JsonNode jsonNode, JsonNode context) {
        if (jsonNode.isObject()) {
            ((ObjectNode) jsonNode).set("@context", context);
        } else {
            throw new IllegalArgumentException("JsonNode is not an object");
        }
    }
}
