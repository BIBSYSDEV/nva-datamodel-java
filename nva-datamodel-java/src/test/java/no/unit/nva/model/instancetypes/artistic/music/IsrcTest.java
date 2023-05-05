package no.unit.nva.model.instancetypes.artistic.music;

import static no.unit.nva.testutils.RandomDataGenerator.randomString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import no.unit.nva.commons.json.JsonUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

class IsrcTest {

    public static final String VALID_ISRC = "USRC17607839";

    @Test
    void shouldAcceptValidIsrc() {
        assertDoesNotThrow(() -> new Isrc(VALID_ISRC));
    }

    @ParameterizedTest
    @NullSource
    void shouldReturnNullValueWhenNullValueIsProvided(String value) throws InvalidIsrcException {
        var isrc = new Isrc(value);
        assertThat(isrc.getValue(), is(nullValue()));
    }

    @Test
    void shouldThrowExceptionWhenIsrcIsInvalid() {
        var invalidIsrc = randomString();
        var exception = assertThrows(InvalidIsrcException.class, () -> new Isrc(invalidIsrc));
        assertThat(exception.getMessage(), is(equalTo(InvalidIsrcException.formatErrorMessage(invalidIsrc))));
    }

    @Test
    void shouldSerializeAsTypedObject() throws InvalidIsrcException, JsonProcessingException {
        var isrc = new Isrc(VALID_ISRC);
        var actualJsonString = JsonUtils.dtoObjectMapper.writeValueAsString(isrc);
        var actualJson = JsonUtils.dtoObjectMapper.readTree(actualJsonString);
        assertThat(actualJson, is(equalTo(createExpectedJson(isrc))));
    }

    private ObjectNode createExpectedJson(Isrc isrc) {
        var expectedJson = JsonUtils.dtoObjectMapper.createObjectNode();
        expectedJson.put("type", Isrc.class.getSimpleName());
        expectedJson.put("value", isrc.getValue());
        return expectedJson;
    }
}