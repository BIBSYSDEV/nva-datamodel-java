package no.unit.nva;

import com.fasterxml.jackson.databind.ObjectMapper;
import nva.commons.core.JsonUtils;

public class DatamodelConfig {

    public static final ObjectMapper objectMapper = JsonUtils.dtoObjectMapper;
}
