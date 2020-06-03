package no.unit.nva.model.contexttypes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nva.commons.utils.JsonUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.net.URI;

import static java.util.Objects.nonNull;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ChapterTest {

    public static final String TEST_URI = "https://example.org/123123";
    public static final String LINKED_CONTEXT_TEMPLATE = "{\n  \"type\" : \"Chapter\",\n"
            + "  \"linkedContext\" : \"%s\"\n}";
    public static final String LINKED_CONTEXT_NULL_TEMPLATE = "{\n  \"type\" : \"Chapter\",\n"
            + "  \"linkedContext\" : %s\n}";
    private final ObjectMapper objectMapper = JsonUtils.objectMapper;

    @DisplayName("ObjectMapper deserializes Chapter")
    @Test
    void objectMapperDeserializesChapterWhenTheInputIsValid() throws JsonProcessingException {
        URI expectedLinkedContext = URI.create(TEST_URI);
        String json = generateChapterJson(expectedLinkedContext.toString());
        Chapter chapter = objectMapper.readValue(json, Chapter.class);
        assertEquals(expectedLinkedContext, chapter.getLinkedContext());
    }

    @DisplayName("Chapter: ObjectMapper throws JsonMappingException when linkingContext URI is null")
    @ParameterizedTest
    @NullSource
    void objectMapperThrowsJsonMappingExceptionWhenInputIsNull(String input) {
        Executable executable = () -> objectMapper.readValue(generateChapterJson(input), Chapter.class);
        JsonMappingException exception = assertThrows(JsonMappingException.class, executable);
        String expectedMessage = String.format(Chapter.ERROR_TEMPLATE, input);
        assertThat(exception.getMessage(), containsString(expectedMessage));
    }

    @DisplayName("Chapter: ObjectMapper throws JsonMappingException when linkingContext URI is invalid")
    @ParameterizedTest
    @ValueSource(strings = {
            "htps://example.org/badScheme",
            ":/nonUri",
            "someStuff"
    })
    void objectMapperThrowsJsonMappingExceptionWhenLinkingUriIsInvalid(String input) {
        Executable executable = () -> objectMapper.readValue(generateChapterJson(input), Chapter.class);
        JsonMappingException exception = assertThrows(JsonMappingException.class, executable);
        String expectedMessage = String.format(Chapter.ERROR_TEMPLATE, input);
        assertThat(exception.getMessage(), containsString(expectedMessage));
    }

    @DisplayName("Book serializes expected json")
    @Test
    void objectMapperProducesProperlyFormattedJsonWhenInputIsChapter() throws JsonProcessingException {
        String expectedJson = generateChapterJson(TEST_URI);
        Chapter chapter = new Chapter();
        chapter.setLinkedContext(TEST_URI);
        String actualJson = objectMapper.writeValueAsString(chapter);
        assertEquals(expectedJson, actualJson);
    }

    private String generateChapterJson(String uri) {
        String template = nonNull(uri) ? LINKED_CONTEXT_TEMPLATE : LINKED_CONTEXT_NULL_TEMPLATE;
        return String.format(template, uri);
    }
}
