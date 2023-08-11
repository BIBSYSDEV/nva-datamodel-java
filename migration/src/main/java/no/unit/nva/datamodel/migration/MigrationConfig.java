package no.unit.nva.datamodel.migration;

import com.fasterxml.jackson.databind.ObjectMapper;
import no.unit.nva.commons.json.JsonUtils;
import nva.commons.core.JacocoGenerated;

@JacocoGenerated
public final class MigrationConfig {

    public static final ObjectMapper objectMapper = JsonUtils.dtoObjectMapper;

    private MigrationConfig() {

    }
}
