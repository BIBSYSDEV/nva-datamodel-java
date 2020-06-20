package no.unit.nva.model.instancetypes.journal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.unit.nva.model.exceptions.InvalidPageRangeException;
import no.unit.nva.model.instancetypes.InstanceTest;
import no.unit.nva.model.instancetypes.JournalTestData;
import no.unit.nva.model.instancetypes.NonPeerReviewedPaper;
import no.unit.nva.model.instancetypes.journal.JournalShortCommunication;
import nva.commons.utils.JsonUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JournalShortCommunicationTest extends InstanceTest {

    public static final ObjectMapper objectMapper = JsonUtils.objectMapper;
    public static final String JOURNAL_SHORT_COMMUNICATION = "JournalShortCommunication";

    @DisplayName("Journal short communication can be created from JSON")
    @Test
    void journalShortCommunicationReturnsObjectWhenJsonInputIsCorrectlySerialized() throws JsonProcessingException,
            InvalidPageRangeException {
        JournalTestData testData = new JournalTestData(false);
        JournalShortCommunication expected = generateJournalShortCommunication(testData);
        String json = generateArticleJsonString(JOURNAL_SHORT_COMMUNICATION, testData);
        JournalShortCommunication actual = objectMapper.readValue(json, JournalShortCommunication.class);
        assertEquals(expected, actual);
    }

    @DisplayName("Journal short communication cannot be peer reviewed")
    @Test
    void journalShortCommunicationSetsPeerReviewedThrowsExceptionWhenPeerReviewIsTrue() throws JsonProcessingException,
            InvalidPageRangeException {
        String json = generateArticleWithPeerReview(JOURNAL_SHORT_COMMUNICATION);
        Executable executable = () -> objectMapper.readValue(json, JournalShortCommunication.class);
        JsonMappingException exception = assertThrows(JsonMappingException.class, executable);
        String expected = String.format(NonPeerReviewedPaper.PEER_REVIEWED_ERROR_TEMPLATE,
                JournalShortCommunication.class.getSimpleName());
        assertThat(exception.getMessage(), containsString(expected));
    }

    private JournalShortCommunication generateJournalShortCommunication(JournalTestData testData) {
        return new JournalShortCommunication.Builder()
                .withVolume(testData.getVolume())
                .withPages(testData.getPages())
                .withIssue(testData.getIssue())
                .withArticleNumber(testData.getArticleNumber())
                .build();
    }
}