package no.unit.nva.model.instancetypes.journal;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import no.unit.nva.model.instancetypes.InstanceTest;
import no.unit.nva.model.instancetypes.JournalTestData;
import no.unit.nva.model.instancetypes.NonPeerReviewed;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JournalLeaderTest extends InstanceTest {

    private static final String JOURNAL_LEADER = "JournalLeader";

    @DisplayName("JournalLeader can be created")
    @Test
    void journalLeaderReturnsValidJournalLeaderWhenInputDataIsValid() throws JsonProcessingException {
        JournalTestData testData = new JournalTestData(false);
        String json = generateArticleJsonString(JOURNAL_LEADER, testData);
        JournalLeader expected = objectMapper.readValue(json, JournalLeader.class);
        JournalLeader actual = generateJournalLeader(testData);
        assertEquals(expected, actual);
    }

    private JournalLeader generateJournalLeader(JournalTestData testData) {
        return new JournalLeader(testData.getVolume(), testData.getIssue(), testData.getArticleNumber(),
                testData.getPages());
    }

    @DisplayName("Journal letters cannot be peer reviewed")
    @Test
    void journalLetterThrowsExceptionWhenPeerReviewIsTrue() throws JsonProcessingException {
        JournalTestData testData = new JournalTestData(true);
        String json = generateArticleJsonString(JOURNAL_LEADER, testData);
        Executable executable = () -> objectMapper.readValue(json, JournalLeader.class);
        JsonMappingException exception = assertThrows(JsonMappingException.class, executable);
        String expected = String.format(NonPeerReviewed.PEER_REVIEWED_ERROR_TEMPLATE,
                JournalLeader.class.getSimpleName());
        assertThat(exception.getMessage(), containsString(expected));
    }
}
