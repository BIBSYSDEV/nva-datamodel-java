package no.unit.nva.model.instancetypes.journal;


import com.fasterxml.jackson.core.JsonProcessingException;
import no.unit.nva.model.instancetypes.InstanceTest;
import no.unit.nva.model.instancetypes.JournalTestData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
