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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static no.unit.nva.hamcrest.DoesNotHaveEmptyValues.doesNotHaveEmptyValues;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
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
        String json = generateChapterArticleJsonString(expectedBegin, expectedEnd, true, false);
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
        String json = generateChapterArticleJsonString(expectedBegin, expectedEnd, true, true);
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
        ChapterArticle chapterArticle = new ChapterArticle.Builder()
                .withPages(generatePages(expectedBegin, expectedEnd))
                .withPeerReviewed(false)
                .withOriginalResearch(true)
                .build();
        JsonNode expectedJson = generateChapterArticleJson(expectedBegin, expectedEnd, false, true);
        JsonNode actualJson = objectMapper.convertValue(chapterArticle, JsonNode.class);
        assertEquals(expectedJson, actualJson);
    }

    @DisplayName("ChapterArticle: objectMapper can serialize valid input with ArticleContentType")
    @ParameterizedTest(name = "Test ChapterArticle with Content type {0} can be (de-)serialized")
    @ValueSource(strings = {
            "Academic Chapter",
            "Non-fiction Chapter",
            "Popular Science Chapter",
            "Textbook Chapter",
            "Encyclopedia Chapter"
    })
    void objectMapperReturnsSerializesAndDeserializesChapterArticleWithContentType(String contentTypeString)
            throws JsonProcessingException {
        String expectedBegin = "222";
        String expectedEnd = "232";
        boolean expectedOriginalResearch = true;
        final ChapterArticleContentType expectedContentType = ChapterArticleContentType.lookup(contentTypeString);
        ChapterArticle expectedChapterArticle = new ChapterArticle.Builder()
                .withPages(generatePages(expectedBegin, expectedEnd))
                .withPeerReviewed(false)
                .withOriginalResearch(expectedOriginalResearch)
                .withContentType(expectedContentType)
                .build();

        ChapterArticle actualChapterArticle =
                objectMapper.readValue(objectMapper.writeValueAsString(expectedChapterArticle), ChapterArticle.class);
        assertThat(actualChapterArticle.getContentType(), is(equalTo(expectedContentType)));
        assertThat(expectedChapterArticle, is(equalTo(actualChapterArticle)));
    }

    @ParameterizedTest(name = "Test ChapterArticle with Content type {0} is build without empty values")
    @ValueSource(strings = {
            "Academic Chapter",
            "Non-fiction Chapter",
            "Popular Science Chapter",
            "Textbook Chapter",
            "Encyclopedia Chapter"
    })
    void chapterArticleBuilderCreatesChapterArticleWithoutEmptyValues(String contentTypeString) {
        String expectedBegin = "222";
        String expectedEnd = "232";
        boolean expectedOriginalResearch = true;
        final ChapterArticleContentType expectedContentType = ChapterArticleContentType.lookup(contentTypeString);
        ChapterArticle expectedChapterArticle = new ChapterArticle.Builder()
                .withPages(generatePages(expectedBegin, expectedEnd))
                .withPeerReviewed(false)
                .withOriginalResearch(expectedOriginalResearch)
                .withContentType(expectedContentType)
                .build();

        assertThat(expectedChapterArticle.getContentType(), is(equalTo(expectedContentType)));
        assertThat(expectedChapterArticle, doesNotHaveEmptyValues());
    }

    private Range generatePages(String begin, String end) {
        return new Range.Builder()
                .withBegin(begin)
                .withEnd(end)
                .build();
    }
}
