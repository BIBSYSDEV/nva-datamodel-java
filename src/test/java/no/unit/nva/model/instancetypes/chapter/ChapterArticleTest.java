package no.unit.nva.model.instancetypes.chapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.unit.nva.JsonHandlingTest;
import no.unit.nva.model.instancetypes.InstanceTest;
import no.unit.nva.model.pages.Pages;
import no.unit.nva.model.pages.Range;
import nva.commons.core.JsonUtils;
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
    void objectMapperReturnsChapterArticleWhenInputJsonIsWellFormed() throws JsonProcessingException {
        String expectedBegin = "225";
        String expectedEnd = "275";
        Pages expectedPages = generatePages(expectedBegin, expectedEnd);
        String json = generateChapterArticleJsonString(expectedBegin, expectedEnd, true, true, false);
        ChapterArticle chapterArticle = objectMapper.readValue(json, ChapterArticle.class);
        assertEquals(expectedPages, chapterArticle.getPages());
        assertTrue(chapterArticle.isPeerReviewed());
    }

    @DisplayName("ChapterArticle: objectMapper can serialize valid input")
    @Test
    void objectMapperReturnsValidJsonWhenInputIsValidChapterArticle() {
        String expectedBegin = "222";
        String expectedEnd = "232";
        ChapterArticle chapterArticle = new ChapterArticle.Builder()
                .withPages(generatePages(expectedBegin, expectedEnd))
                .withPeerReviewed(false)
                .withOriginalResearch(false)
                .build();
        JsonNode expectedJson = generateChapterArticleJson(expectedBegin, expectedEnd, false, false);
        JsonNode actualJson = objectMapper.convertValue(chapterArticle, JsonNode.class);
        assertEquals(expectedJson, actualJson);
    }

    @DisplayName("ChapterArticle: objectMapper can deserialize object with originalResearch")
    @Test
    void objectMapperReturnsChapterArticleWhenInputJsonIsWellFormedWithOriginalResearch()
            throws JsonProcessingException {
        String expectedBegin = "225";
        String expectedEnd = "275";
        Pages expectedPages = generatePages(expectedBegin, expectedEnd);
        String json = generateChapterArticleJsonString(expectedBegin, expectedEnd, true, true, true);
        ChapterArticle chapterArticle = objectMapper.readValue(json, ChapterArticle.class);
        assertEquals(expectedPages, chapterArticle.getPages());
        assertTrue(chapterArticle.isPeerReviewed());
        assertTrue(chapterArticle.isOriginalResearch());
    }

    @DisplayName("ChapterArticle: objectMapper can serialize valid input with originalResearch")
    @Test
    void objectMapperReturnsValidJsonWhenInputIsValidChapterArticleWithOriginalResearch() {
        String expectedBegin = "222";
        String expectedEnd = "232";
        boolean expectedOriginalResearch = true;
        ChapterArticle chapterArticle = new ChapterArticle.Builder()
                .withPages(generatePages(expectedBegin, expectedEnd))
                .withPeerReviewed(false)
                .withOriginalResearch(expectedOriginalResearch)
                .build();
        JsonNode expectedJson = generateChapterArticleJson(expectedBegin, expectedEnd, false, expectedOriginalResearch);
        JsonNode actualJson = objectMapper.convertValue(chapterArticle, JsonNode.class);
        assertEquals(expectedJson, actualJson);
    }




    private Range generatePages(String begin, String end) {
        return new Range.Builder()
                .withBegin(begin)
                .withEnd(end)
                .build();
    }
}
