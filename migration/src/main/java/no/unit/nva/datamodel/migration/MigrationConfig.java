package no.unit.nva.datamodel.migration;

import com.fasterxml.jackson.databind.ObjectMapper;
import nva.commons.core.JsonUtils;

public final class MigrationConfig {

    public static final ObjectMapper objectMapper = JsonUtils.dtoObjectMapper;

    private MigrationConfig() {

    }
}
