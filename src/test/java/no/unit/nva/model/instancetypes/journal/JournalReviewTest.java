package no.unit.nva.model.instancetypes.journal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import no.unit.nva.model.exceptions.InvalidPageRangeException;
import no.unit.nva.model.instancetypes.InstanceTest;
import no.unit.nva.model.instancetypes.JournalTestData;
import no.unit.nva.model.instancetypes.NonPeerReviewed;
import no.unit.nva.model.instancetypes.journal.JournalReview;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JournalReviewTest extends InstanceTest {

    public static final String JOURNAL_REVIEW = "JournalReview";

    @DisplayName("Journal review can be created from JSON")
    @Test
    void journalReviewReturnsObjectWhenJsonInputIsCorrectlySerialized() throws JsonProcessingException,
            InvalidPageRangeException {
        JournalTestData testData = new JournalTestData(false);
        JournalReview expected = generateJournalReview(testData);
        String journalReview = generateArticleJsonString(JOURNAL_REVIEW, testData);
        JournalReview actual = objectMapper.readValue(journalReview, JournalReview.class);
        assertEquals(expected, actual);
    }

    @DisplayName("Journal review cannot be peer reviewed")
    @Test
    void journalReviewThrowsExceptionWhenPeerReviewIsTrue() throws JsonProcessingException, InvalidPageRangeException {
        String json = generateArticleWithPeerReview(JOURNAL_REVIEW);
        Executable executable = () -> objectMapper.readValue(json, JournalReview.class);
        JsonMappingException exception = assertThrows(JsonMappingException.class, executable);
        String expected = String.format(NonPeerReviewed.PEER_REVIEWED_ERROR_TEMPLATE,
                JournalReview.class.getSimpleName());
        assertThat(exception.getMessage(), containsString(expected));
    }

    private JournalReview generateJournalReview(JournalTestData testData) {
        return new JournalReview.Builder()
            .withVolume(testData.getVolume())
            .withPages(testData.getPages())
            .withIssue(testData.getIssue())
            .withArticleNumber(testData.getArticleNumber())
            .build();
    }
}