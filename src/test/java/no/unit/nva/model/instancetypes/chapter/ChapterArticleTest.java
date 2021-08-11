package no.unit.nva.model.instancetypes.chapter;

import static no.unit.nva.hamcrest.DoesNotHaveEmptyValues.doesNotHaveEmptyValues;
import static no.unit.nva.utils.RandomData.randomBoolean;
import static no.unit.nva.utils.RandomData.randomElement;
import static no.unit.nva.utils.RandomData.randomString;
import static no.unit.nva.utils.RandomData.randomUri;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.unit.nva.JsonHandlingTest;
import no.unit.nva.model.instancetypes.InstanceTest;
import no.unit.nva.model.pages.Range;
import nva.commons.core.JsonUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class ChapterArticleTest extends InstanceTest implements JsonHandlingTest {

    private static final ObjectMapper objectMapper = JsonUtils.objectMapper;

    @Test
    void objectMapperSerializesAndDeserializesChapterArticleWithoutInformationLoss() throws JsonProcessingException {
        ChapterArticle chapterArticle = sampleChapterArticle();
        String json = objectMapper.writeValueAsString(chapterArticle);
        ChapterArticle deserialized = objectMapper.readValue(json, ChapterArticle.class);
        assertThat(deserialized, is(equalTo(chapterArticle)));
        assertThat(chapterArticle, doesNotHaveEmptyValues());
    }

    @ParameterizedTest(name = "Test ChapterArticle with Content type {0} can be (de-)serialized")
    @EnumSource(ChapterArticleContentType.class)
    void objectMapperReturnsSerializesAndDeserializesChapterArticleWithContentType(
        ChapterArticleContentType expectedContentType)
        throws JsonProcessingException {

        ChapterArticle expectedChapterArticle =
            sampleChapterArticle().copy().withContentType(expectedContentType).build();
        String json = objectMapper.writeValueAsString(expectedChapterArticle);
        ChapterArticle actualChapterArticle = objectMapper.readValue(json, ChapterArticle.class);

        assertThat(actualChapterArticle.getContentType(), is(equalTo(expectedContentType)));
        assertThat(expectedChapterArticle, is(equalTo(actualChapterArticle)));
        assertThat(expectedChapterArticle, doesNotHaveEmptyValues());
    }

    private ChapterArticle sampleChapterArticle() {
        return new ChapterArticle.Builder()
            .withPartOf(randomUri())
            .withPeerReviewed(randomBoolean())
            .withOriginalResearch(randomBoolean())
            .withContentType(sampleChapterArticleContentType())
            .withPages(samplePages())
            .build();
    }

    private Range samplePages() {
        return new Range.Builder().withBegin(randomString()).withEnd(randomString()).build();
    }

    private ChapterArticleContentType sampleChapterArticleContentType() {
        return randomElement(ChapterArticleContentType.values());
    }
}
