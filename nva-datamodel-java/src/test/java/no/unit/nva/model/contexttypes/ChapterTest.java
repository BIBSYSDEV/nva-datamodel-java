package no.unit.nva.model.contexttypes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nva.commons.core.JsonUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.net.MalformedURLException;
import java.net.URI;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ChapterTest {

    public static final String TEST_URI_STRING = "https://example.org/123123";
    public static final URI TEST_URI = URI.create(TEST_URI_STRING);

    public static final String LINKED_CONTEXT_TEMPLATE = "{\n  \"type\" : \"Chapter\",\n"
            + "  \"partOf\" : \"%s\"\n}";
    public static final String LINKED_CONTEXT_NULL_TEMPLATE = "{\n  \"type\" : \"Chapter\",\n"
            + "  \"partOf\" : %s\n}";
    private final ObjectMapper objectMapper = JsonUtils.objectMapper;

    @DisplayName("ObjectMapper deserializes Chapter")
    @Test
    void objectMapperDeserializesChapterWhenTheInputIsValid() throws JsonProcessingException {
        URI expectedPartOf = TEST_URI;
        String json = generateChapterJson(expectedPartOf.toString());
        Chapter chapter = objectMapper.readValue(json, Chapter.class);
        assertEquals(expectedPartOf, chapter.getPartOf());
    }

    @DisplayName("Chapter: ObjectMapper throws JsonMappingException when linkingContext URI is null")
    @ParameterizedTest
    @NullAndEmptySource
    void objectMapperThrowsJsonMappingExceptionWhenInputIsNull(String input) {
        String displayValue = isNull(input) || input.isEmpty() ? "null" : input;
        Executable executable = () -> objectMapper.readValue(generateChapterJson(input), Chapter.class);
        JsonMappingException exception = assertThrows(JsonMappingException.class, executable);
    }

    @DisplayName("Chapter: ObjectMapper throws JsonMappingException when linkingContext URI is invalid")
    @ParameterizedTest
    @ValueSource(strings = {
            "htps://example.org/badScheme",
            ":/nonUri",
            "someStuff"
    })
    void objectMapperThrowsJsonMappingExceptionWhenLinkingUriIsInvalid(String input) throws MalformedURLException {
        Executable executable = () -> objectMapper.readValue(generateChapterJson(input), Chapter.class);
        JsonMappingException exception = assertThrows(JsonMappingException.class, executable);
    }

    @DisplayName("Book serializes expected json")
    @Test
    void objectMapperProducesProperlyFormattedJsonWhenInputIsChapter() throws JsonProcessingException {
        String expectedJson = generateChapterJson(TEST_URI_STRING);
        Chapter chapter = new Chapter();
        chapter.setPartOf(TEST_URI);
        String actualJson = objectMapper.writeValueAsString(chapter);
        assertEquals(expectedJson, actualJson);
    }

    private String generateChapterJson(String uri) {
        String template = nonNull(uri) ? LINKED_CONTEXT_TEMPLATE : LINKED_CONTEXT_NULL_TEMPLATE;
        return String.format(template, uri);
    }
}
