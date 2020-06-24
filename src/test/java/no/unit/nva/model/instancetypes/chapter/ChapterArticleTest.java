package no.unit.nva.model.instancetypes.chapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.unit.nva.JsonHandlingTest;
import no.unit.nva.model.exceptions.InvalidPageRangeException;
import no.unit.nva.model.instancetypes.InstanceTest;
import no.unit.nva.model.pages.Pages;
import no.unit.nva.model.pages.Range;
import nva.commons.utils.JsonUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChapterArticleTest extends InstanceTest implements JsonHandlingTest {

    private static final ObjectMapper objectMapper = JsonUtils.objectMapper;

    @DisplayName("ChapterArticle exists")
    @Test
    void chapterArticleExists() {
        new ChapterArticle();
    }

    @DisplayName("ChapterArticle: objectMapper can deserialize object")
    @Test
    void objectMapperReturnsChapterArticleWhenInputJsonIsWellFormed() throws JsonProcessingException,
            InvalidPageRangeException {
        String expectedBegin = "225";
        String expectedEnd = "275";
        Pages expectedPages = generatePages(expectedBegin, expectedEnd);
        String json = generateChapterArticleJsonString(expectedBegin, expectedEnd, true);
        ChapterArticle chapterArticle = objectMapper.readValue(json, ChapterArticle.class);
        assertEquals(expectedPages, chapterArticle.getPages());
        assertTrue(chapterArticle.isPeerReviewed());
    }

    @DisplayName("ChapterArticle: objectMapper can serialize valid input")
    @Test
    void objectMapperReturnsValidJsonWhenInputIsValidChapterArticle() throws Exception {
        String expectedBegin = "222";
        String expectedEnd = "232";
        ChapterArticle chapterArticle = new ChapterArticle.Builder()
                .withPages(generatePages(expectedBegin, expectedEnd))
                .withPeerReviewed(false)
                .build();
        JsonNode expectedJson = generateChapterArticleJson(expectedBegin, expectedEnd, false);
        JsonNode actualJson = objectMapper.convertValue(chapterArticle, JsonNode.class);
        assertEquals(expectedJson, actualJson);
    }


    private Range generatePages(String begin, String end) throws InvalidPageRangeException {
        return new Range.Builder()
                .withBegin(begin)
                .withEnd(end)
                .build();
    }
}
