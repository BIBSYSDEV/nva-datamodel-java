package no.unit.nva.model.instancetypes.journal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import no.unit.nva.model.exceptions.InvalidPageRangeException;
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

class JournalLetterTest extends InstanceTest {

    private static final String JOURNAL_LETTER = "JournalLetter";

    @DisplayName("Journal letters to editor can be created from JSON")
    @Test
    void journalLetterReturnsObjectWhenJsonInputIsCorrectlySerialized() throws JsonProcessingException,
            InvalidPageRangeException {
        JournalTestData testData = new JournalTestData(false);
        JournalLetter expected = generateJournalLetter(testData);
        String json = generateArticleJsonString(JOURNAL_LETTER, testData);
        JournalLetter journalLetter = objectMapper.readValue(json, JournalLetter.class);
        assertEquals(expected, journalLetter);
    }

    private JournalLetter generateJournalLetter(JournalTestData testData) {

        return new JournalLetter.Builder()
                .withVolume(testData.getVolume())
                .withPages(testData.getPages())
                .withIssue(testData.getIssue())
                .withArticleNumber(testData.getArticleNumber())
                .build();
    }
}