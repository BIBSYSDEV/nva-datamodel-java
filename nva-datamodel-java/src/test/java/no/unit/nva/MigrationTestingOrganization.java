package no.unit.nva;

import com.fasterxml.jackson.core.JsonProcessingException;
import no.unit.nva.commons.json.JsonUtils;
import no.unit.nva.model.Organization;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@Deprecated
class MigrationTestingOrganizationTest {

    @ParameterizedTest
    @ValueSource(strings = {"{\"type\": \"Organization\"}",
            "{\"type\": \"Organization\", \"labels\": {\"en\":\"A label\"}}"})
    void shouldRemoveImperfectlyFormedOrganizations(String value) throws JsonProcessingException {
        assertNull(JsonUtils.dtoObjectMapper.readValue(value, Organization.class));
    }

    @ParameterizedTest
    @ValueSource(strings = {"{\"type\": \"Organization\", \"id\": \"https://example.org\", "
            + "\"labels\": {\"en\":\"A label\"}}",
            "{\"type\": \"Organization\", \"id\": \"https://example.org\"}"})
    void shouldRetainWellFormedOrganizations(String value) throws JsonProcessingException {
        assertNotNull(JsonUtils.dtoObjectMapper.readValue(value, Organization.class));
    }
}
