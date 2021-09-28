package no.unit.nva.model.instancetypes.journal;

import com.fasterxml.jackson.core.JsonProcessingException;
import no.unit.nva.model.instancetypes.JournalTestData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;

import static no.unit.nva.hamcrest.DoesNotHaveEmptyValues.doesNotHaveEmptyValues;
import static no.unit.nva.model.instancetypes.journal.JournalArticleContentType.PROFESSIONAL_ARTICLE;
import static no.unit.nva.model.instancetypes.journal.JournalArticleContentType.RESEARCH_ARTICLE;
import static nva.commons.core.JsonUtils.objectMapper;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JournalArticleTest {

    @DisplayName("Journal articles can be either peer-reviewed or non-peer-reviewed")
    @ParameterizedTest(name = "Journal articles can be created when peerReviewed is {0}")
    @ValueSource(booleans = {true, false})
    void journalArticleReturnsObjectWhenPeerReviewIsTrueOrFalse(boolean peerReviewed) {
        JournalTestData testData = new JournalTestData(peerReviewed);
        assertDoesNotThrow(() -> generateJournalArticle(testData));
    }

    @Test
    void canSerializeAndDeserializeJournalArticleWithContentTypeResearchArticle()
            throws JsonProcessingException {

        JournalArticle expectedJournalArticle = new JournalArticle.Builder()
                .withContent(PROFESSIONAL_ARTICLE).build();
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

        JournalTestData expectedJournalArticleTestData = new JournalTestData(content);

        JournalArticle expectedJournalArticle = generateJournalArticle(expectedJournalArticleTestData);
        String expectedJson = objectMapper.writeValueAsString(expectedJournalArticle);

        JournalArticle actualJournalArticle = objectMapper.readValue(expectedJson, JournalArticle.class);
        assertEquals(expectedJournalArticle, actualJournalArticle);

        String actualJson = objectMapper.writeValueAsString(expectedJournalArticle);
        assertEquals(expectedJson, actualJson);
    }

    @Test
    void canSerializeJournalArticleWithContentTypeWithoutDataloss() throws JsonProcessingException {
        JournalTestData expectedJournalArticleTestData = new JournalTestData(RESEARCH_ARTICLE);

        JournalArticle expectedJournalArticle = generateJournalArticle(expectedJournalArticleTestData);
        String expectedJson = objectMapper.writeValueAsString(expectedJournalArticle);
        JournalArticle actualJournalArticle = objectMapper.readValue(expectedJson, JournalArticle.class);

        assertEquals(expectedJournalArticle, actualJournalArticle);
        assertThat(expectedJournalArticle, is(equalTo(actualJournalArticle)));
    }

    @Test
    void journalArticleBuilderCreatesJournalArticleWithoutEmptyValues() {
        JournalTestData journalTestData = new JournalTestData(RESEARCH_ARTICLE);
        JournalArticle journalArticle = generateJournalArticle(journalTestData);
        assertThat(journalArticle, doesNotHaveEmptyValues());
    }

    @Test
    void journalArticleSerializationContainsJournalArticleContentType() throws JsonProcessingException {
        JournalTestData journalTestData = new JournalTestData(RESEARCH_ARTICLE);
        JournalArticle journalArticle = generateJournalArticle(journalTestData);
        String json = objectMapper.writeValueAsString(journalArticle);
        String expectedContentPhrase = "contentType\" : \"" + RESEARCH_ARTICLE.getValue() + "\"";
        assertThat(json,containsString(expectedContentPhrase));
    }

    @Test
    void journalArticleSerializationContainsJournalArticleOriginalResearch() throws JsonProcessingException {
        JournalTestData journalTestData = new JournalTestData(RESEARCH_ARTICLE);
        JournalArticle journalArticle = generateJournalArticle(journalTestData);
        journalArticle.setOriginalResearch(true);
        String json = objectMapper.writeValueAsString(journalArticle);
        String expectedOriginalityPhrase = "\"originalResearch\" : true";
        assertTrue(json.contains(expectedOriginalityPhrase));
    }

    @ParameterizedTest(name = "Test JournalArticle with Content type {0} can be annotated with 'Original Research'")
    @ValueSource(strings = {
            "Research article",
            "Review article"
    })
    void originalResearchCanBeAssignedToContentTypeResearchOrReviewArticle(String content) {
        JournalTestData journalTestData = new JournalTestData(content);
        JournalArticle journalArticle = generateJournalArticle(journalTestData);
        journalArticle.setOriginalResearch(true);
        assertTrue(journalArticle.isOriginalResearch());
    }

    @ParameterizedTest(name = "Test JournalArticle with Content type {0} can not be annotated with 'Original Research'")
    @ValueSource(strings = {
            "Case report",
            "Study protocol",
            "Professional article",
            "Popular science article"
    })
    void originalResearchCanOnlyBeAssignedToContentTypeResearchOrReviewArticle(String content)  {
        JournalTestData journalTestData = new JournalTestData(content);
        JournalArticle journalArticle = generateJournalArticle(journalTestData);
        journalArticle.setOriginalResearch(true);
        assertFalse(journalArticle.isOriginalResearch());
    }




    private JournalArticle generateJournalArticle(JournalTestData testData) {
        return new JournalArticle.Builder()
                .withVolume(testData.getVolume())
                .withIssue(testData.getIssue())
                .withPages(testData.getPages())
                .withArticleNumber(testData.getArticleNumber())
                .withPeerReviewed(testData.isPeerReviewed())
                .withContent(testData.getContent())
                .withOriginalResearch(testData.isOriginalResearch())
                .build();
    }

}
