package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.unit.nva.model.exceptions.InvalidPageRangeException;
import no.unit.nva.model.instancetypes.journal.JournalLetter;
import no.unit.nva.model.pages.Range;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JournalLetterTest extends InstanceTest {

    @DisplayName("Journal letters to editor can be created from JSON")
    @Test
    void journalLetterReturnsObjectWhenJsonInputIsCorrectlySerialized() throws JsonProcessingException,
            InvalidPageRangeException {
        JournalLetter expected = generateJournalLetter("1", "3", "123", "2", "3");
        String json = objectMapper.writeValueAsString(expected);
        JournalLetter journalLetter = objectMapper.readValue(json, JournalLetter.class);
        assertEquals(expected, journalLetter);
    }

    @DisplayName("Journal letters cannot be peer reviewed")
    @Test
    void journalLetterSetsPeerReviewedToFalseWhenPeerReviewIsTrue() throws JsonProcessingException,
            InvalidPageRangeException {
        ObjectMapper objectMapper = new ObjectMapper();
        String type = "JournalLetter";
        String volume = "1";
        String issue = "3";
        String articleNumber = "123";
        String begin = "2";
        String end = "3";
        JournalLetter expected = generateJournalLetter(volume, issue, articleNumber, begin, end);

        String json = generateArticleJsonString(type, volume, issue, articleNumber, begin, end, true);
        assertEquals(expected, objectMapper.readValue(json, JournalLetter.class));
    }

    private JournalLetter generateJournalLetter(String volume,
                                                String issue,
                                                String articleNumber,
                                                String begin,
                                                String end) throws InvalidPageRangeException {
        Range pages = new Range.Builder()
                .withBegin(begin)
                .withEnd(end)
                .build();

        return new JournalLetter.Builder()
                .withVolume(volume)
                .withPages(pages)
                .withIssue(issue)
                .withArticleNumber(articleNumber)
                .build();
    }
}