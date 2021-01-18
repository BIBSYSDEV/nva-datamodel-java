package no.unit.nva.model.instancetypes.journal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.unit.nva.model.instancetypes.InstanceTest;
import no.unit.nva.model.instancetypes.JournalTestData;
import nva.commons.core.JsonUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JournalShortCommunicationTest extends InstanceTest {

    public static final ObjectMapper objectMapper = JsonUtils.objectMapper;
    public static final String JOURNAL_SHORT_COMMUNICATION = "JournalShortCommunication";

    @DisplayName("Journal short communication can be created from JSON")
    @Test
    void journalShortCommunicationReturnsObjectWhenJsonInputIsCorrectlySerialized() throws JsonProcessingException {
        JournalTestData testData = new JournalTestData(false);
        JournalShortCommunication expected = generateJournalShortCommunication(testData);
        String json = generateArticleJsonString(JOURNAL_SHORT_COMMUNICATION, testData);
        JournalShortCommunication actual = objectMapper.readValue(json, JournalShortCommunication.class);
        assertEquals(expected, actual);
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