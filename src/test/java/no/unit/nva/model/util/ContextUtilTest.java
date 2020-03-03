package no.unit.nva.model.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContextUtilTest {

    @Test
    public void testInjectContextIntoJsonNodeNotAnObject() {

        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode arrayNode = objectMapper.createArrayNode();
        ObjectNode objectNode = objectMapper.createObjectNode();

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            ContextUtil.injectContext(arrayNode, objectNode);
        });

    }

}
