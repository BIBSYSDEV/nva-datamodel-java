package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.unit.nva.model.exceptions.InvalidPageRangeException;
import no.unit.nva.model.instancetypes.chapter.ChapterArticle;
import no.unit.nva.model.pages.Pages;
import no.unit.nva.model.pages.Range;
import nva.commons.utils.JsonUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChapterArticleTest {

    private static final ObjectMapper objectMapper = JsonUtils.objectMapper;
    public static final String CHAPTER_ARTICLE = "ChapterArticle";

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
        String json = generateWellFormedJson(expectedBegin, expectedEnd, true);
        ChapterArticle chapterArticle = objectMapper.readValue(json, ChapterArticle.class);
        assertEquals(expectedPages, chapterArticle.getPages());
        assertTrue(chapterArticle.isPeerReviewed());
    }

    @DisplayName("ChapterArticle: objectMapper can serialize valid input")
    @Test
    void objectMapperReturnsValidJsonWhenInputIsValidChapterArticle() throws JsonProcessingException,
            InvalidPageRangeException {
        String expectedBegin = "222";
        String expectedEnd = "232";
        ChapterArticle chapterArticle = new ChapterArticle.Builder()
                .withPages(generatePages(expectedBegin, expectedEnd))
                .withPeerReviewed(false)
                .build();
        String expectedJson = generateWellFormedJson(expectedBegin, expectedEnd, false);
        assertEquals(expectedJson, objectMapper.writeValueAsString(chapterArticle));
    }

    private Range generatePages(String begin, String end) throws InvalidPageRangeException {
        return new Range.Builder()
                .withBegin(begin)
                .withEnd(end)
                .build();
    }

    private String generateWellFormedJson(String begin,
                                          String end,
                                          boolean peerReviewed) {
        return "{\n"
                + "  \"type\" : \"" + CHAPTER_ARTICLE + "\",\n"
                + "  \"pages\" : {\n"
                + "    \"type\" : \"Range\",\n"
                + "    \"begin\" : \"" + begin + "\",\n"
                + "    \"end\" : \"" + end + "\"\n"
                + "  },\n"
                + "  \"peerReviewed\" : " + peerReviewed + "\n"
                + "}";
    }
}
