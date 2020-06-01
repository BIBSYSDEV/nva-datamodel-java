package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.unit.nva.model.exceptions.InvalidPageTypeException;
import no.unit.nva.model.pages.MonographPages;
import no.unit.nva.model.pages.Pages;
import no.unit.nva.model.pages.Range;
import nva.commons.utils.JsonUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChapterChapterTest {

    private static final ObjectMapper objectMapper = JsonUtils.objectMapper;
    public static final String CHAPTER_CHAPTER = "ChapterChapter";

    @DisplayName("ChapterChapter exists")
    @Test
    void chapterChapterExists() {
        new ChapterChapter();
    }

    @DisplayName("ChapterChapter: objectMapper can deserialize object")
    @Test
    void objectMapperReturnsChapterChapterWhenInputJsonIsWellFormed() throws JsonProcessingException {
        String expectedBegin = "225";
        String expectedEnd = "275";
        Pages expectedPages = generatePages(expectedBegin, expectedEnd);
        String json = generateWellFormedJson(expectedBegin, expectedEnd, true);
        ChapterChapter chapterChapter = objectMapper.readValue(json, ChapterChapter.class);
        assertEquals(expectedPages, chapterChapter.getPages());
        assertTrue(chapterChapter.isPeerReviewed());
    }

    @DisplayName("ChapterChapter: objectMapper can serialize valid input")
    @Test
    void objectMapperReturnsValidJsonWhenInputIsValidChapterChapter() throws JsonProcessingException,
            InvalidPageTypeException {
        String expectedBegin = "222";
        String expectedEnd = "232";
        ChapterChapter chapterChapter = new ChapterChapter.Builder()
                .withPages(generatePages(expectedBegin, expectedEnd))
                .withPeerReviewed(false)
                .build();
        String expectedJson = generateWellFormedJson(expectedBegin, expectedEnd, false);
        assertEquals(expectedJson, objectMapper.writeValueAsString(chapterChapter));
    }

    @DisplayName("ChapterChapterThrows InvalidPageTypeException if page type is not Range")
    @Test
    void chapterChapterThrowsInvalidPageTypeExceptionIfPageTypeIsNotRange() {
        Range intro = new Range.Builder().withBegin("i").withEnd("iv").build();
        Pages pages = new MonographPages.Builder()
                .withPages("222")
                .withIllustrated(false)
                .withIntroduction(intro)
                .build();
        InvalidPageTypeException exception = assertThrows(InvalidPageTypeException.class, () ->
            new ChapterChapter.Builder()
                .withPages(pages)
                .withPeerReviewed(true)
                .build());
        String expectedMessage = String.format(InvalidPageTypeException.INVALID_CLASS_MESSAGE,
                ChapterChapter.class.getTypeName(),
                Range.class.getTypeName(),
                pages.getClass().getTypeName());
        assertEquals(expectedMessage, exception.getMessage());
    }

    private Pages generatePages(String begin, String end) {
        return new Range.Builder()
                .withBegin(begin)
                .withEnd(end)
                .build();
    }

    private String generateWellFormedJson(String begin,
                                          String end,
                                          boolean peerReviewed) {
        return "{\n"
                + "  \"type\" : \"" + CHAPTER_CHAPTER + "\",\n"
                + "  \"pages\" : {\n"
                + "    \"type\" : \"Range\",\n"
                + "    \"begin\" : \"" + begin + "\",\n"
                + "    \"end\" : \"" + end + "\"\n"
                + "  },\n"
                + "  \"peerReviewed\" : " + peerReviewed + "\n"
                + "}";
    }
}
