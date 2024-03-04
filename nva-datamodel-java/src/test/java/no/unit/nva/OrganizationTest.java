package no.unit.nva;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.fasterxml.jackson.core.JsonProcessingException;
import no.unit.nva.commons.json.JsonUtils;
import no.unit.nva.model.Organization;
import org.junit.jupiter.api.Test;

public class OrganizationTest {

    public static final String TOP_LEVEL_ORG_POINTER = "/topLevelOrg";

    @Test
    void shouldNotAddTopLevelOrgDuringDeserializationIfNotPresent() throws JsonProcessingException {
        var deserializedJsonStr = "{\n"
                    + "        \"type\" : \"Organization\",\n"
                    + "        \"id\" : \"https://www.example.org/4a49816e-abc0-42c4-a293-fa32740c2eba\"\n"
                    + "      };";

        var dto = JsonUtils.dtoObjectMapper.readValue(deserializedJsonStr, Organization.class);
        assertNull(dto.getTopLevelOrg());
        var jsonNode = JsonUtils.dtoObjectMapper.valueToTree(dto);
        assertTrue(jsonNode.at(TOP_LEVEL_ORG_POINTER).isMissingNode());
    }

    @Test
    void shouldAddTopLevelOrgDuringDeserializationIfPresent() throws JsonProcessingException {
        var deserializedJsonStr = "{\n"
                                  + "        \"type\" : \"Organization\",\n"
                                  + "        \"id\" : \"https://www.example.org/id\",\n"
                                  + "        \"topLevelOrg\" : \"https://www.example.org/topLevel\"\n"
                                  + "      };";

        var dto = JsonUtils.dtoObjectMapper.readValue(deserializedJsonStr, Organization.class);
        var jsonNode = JsonUtils.dtoObjectMapper.valueToTree(dto);
        assertFalse(jsonNode.at(TOP_LEVEL_ORG_POINTER).isMissingNode());
    }
}
