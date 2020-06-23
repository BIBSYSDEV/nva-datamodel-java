package no.unit.nva;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import nva.commons.utils.JsonUtils;

public interface JsonHandlingTest {

    default JsonNode jsonStringToJsonNode(String json) throws JsonProcessingException {
        return JsonUtils.objectMapper.readTree(json);
    }
}
