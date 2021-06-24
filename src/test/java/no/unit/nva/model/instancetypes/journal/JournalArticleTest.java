package no.unit.nva.model.instancetypes.journal;

import com.fasterxml.jackson.core.JsonProcessingException;
import no.unit.nva.model.instancetypes.JournalTestData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;

import static no.unit.nva.hamcrest.DoesNotHaveEmptyValues.doesNotHaveEmptyValues;
import static nva.commons.core.JsonUtils.objectMapper;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JournalArticleTest {

    @DisplayName("Journal articles can be either peer-reviewed or non-peer-reviewed")
    @ParameterizedTest(name = "Journal articles can be created when peerReviewed is {0}")
    @ValueSource(booleans = {true, false})
    void journalArticleReturnsObjectWhenPeerReviewIsTrueOrFalse(boolean peerReviewed) {
        JournalTestData testData = new JournalTestData(peerReviewed);
        assertDoesNotThrow(() -> generateJournalArticle(testData));
    }

    @Test
    public void canSerializeAndDeserializeJournalArticleWithContentTypeResearchArticle()
            throws JsonProcessingException {

        JournalArticle expectedJournalArticle = new JournalArticle.Builder()
                .withContent(JournalArticleContentType.PROFESSIONAL_ARTICLE).build();
        String expectedJson = objectMapper.writeValueAsString(expectedJournalArticle);

        JournalArticle actualJournalArticle = objectMapper.readValue(expectedJson, JournalArticle.class);
        assertEquals(expectedJournalArticle, actualJournalArticle);

        String actualJson = objectMapper.writeValueAsString(expectedJournalArticle);
        assertEquals(expectedJson, actualJson);
    }


    @DisplayName("Test JournalArticle with content can be serialized/deserialized")
    @ParameterizedTest(name = "Test JournalArticle with Content type {0} can be (de-)serialized")
    @ValueSource(strings = {
            "Research article",
            "Review article",
            "Case report",
            "Study protocol",
            "Professional article",
            "Popular science article"
    })
    void publicationReturnsJsonWhenInputIsValid(String content) throws IOException {

        JournalTestData expectedJournalArticleTestData = new JournalTestData();
        expectedJournalArticleTestData.setContent(content);

        JournalArticle expectedJournalArticle = generateJournalArticle(expectedJournalArticleTestData);
        String expectedJson = objectMapper.writeValueAsString(expectedJournalArticle);

        JournalArticle actualJournalArticle = objectMapper.readValue(expectedJson, JournalArticle.class);
        assertEquals(expectedJournalArticle, actualJournalArticle);

        String actualJson = objectMapper.writeValueAsString(expectedJournalArticle);
        assertEquals(expectedJson, actualJson);
    }

    @Test
    public void canSerializeJournalArticleWithContentTypeWithoutDataloss() throws JsonProcessingException {
        String contentTypeString = "Research article";
        JournalTestData expectedJournalArticleTestData = new JournalTestData();
        expectedJournalArticleTestData.setContent(contentTypeString);

        JournalArticle expectedJournalArticle = generateJournalArticle(expectedJournalArticleTestData);
        String expectedJson = objectMapper.writeValueAsString(expectedJournalArticle);
        JournalArticle actualJournalArticle = objectMapper.readValue(expectedJson, JournalArticle.class);

        assertEquals(expectedJournalArticle, actualJournalArticle);
        assertThat(expectedJournalArticle, is(equalTo(actualJournalArticle)));
    }

    @Test
    public void journalArticleBuilderCreatesObjectWithoutEmptyValues() throws JsonProcessingException {
        String contentTypeString = "Research article";
        JournalTestData journalTestData = new JournalTestData();
        journalTestData.setContent(contentTypeString);
        JournalArticle journalArticle = generateJournalArticle(journalTestData);
        assertThat(journalArticle, doesNotHaveEmptyValues());
    }

    private JournalArticle generateJournalArticle(JournalTestData testData) {
        return new JournalArticle.Builder()
                .withVolume(testData.getVolume())
                .withIssue(testData.getIssue())
                .withPages(testData.getPages())
                .withArticleNumber(testData.getArticleNumber())
                .withPeerReviewed(testData.isPeerReviewed())
                .withContent(testData.getContent())
                .build();
    }

}
