package no.unit.nva;

import static no.unit.nva.DatamodelConfig.objectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public interface JsonHandlingTest {

    default JsonNode jsonStringToJsonNode(String json) throws JsonProcessingException {
        return objectMapper.readTree(json);
    }
}
