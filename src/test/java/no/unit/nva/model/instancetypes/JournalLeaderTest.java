package no.unit.nva.model.instancetypes;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.unit.nva.model.exceptions.InvalidPageRangeException;
import no.unit.nva.model.pages.Range;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JournalLeaderTest extends InstanceTest {

    @DisplayName("JournalLeader can be created")
    @Test
    void journalLeaderReturnsValidJournalLeaderWhenInputDataIsValid() throws JsonProcessingException,
            InvalidPageRangeException {
        String type = "JournalLeader";
        String volume = "22";
        String issue = "5";
        String articleNumber = "5521";
        String begin = "5";
        String end = "6";
        String json = generateArticleJsonString(type, volume, issue,
                articleNumber, begin, end, false);
        JournalLeader expected = objectMapper.readValue(json, JournalLeader.class);
        JournalLeader actual = generateJournalLeader(volume, issue, articleNumber, begin, end);
        assertEquals(expected, actual);
    }

    private JournalLeader generateJournalLeader(String volume,
                                                String issue,
                                                String articleNumber,
                                                String begin,
                                                String end) throws InvalidPageRangeException {
        Range pages = new Range.Builder()
                .withBegin(begin)
                .withEnd(end)
                .build();
        return new JournalLeader(volume, issue, articleNumber, pages, false);
    }

    @DisplayName("Journal letters cannot be peer reviewed")
    @Test
    void journalLetterSetsPeerReviewedToFalseWhenPeerReviewIsTrue() throws JsonProcessingException,
            InvalidPageRangeException {
        ObjectMapper objectMapper = new ObjectMapper();
        String type = "JournalLeader";
        String volume = "1";
        String issue = "3";
        String articleNumber = "123";
        String begin = "2";
        String end = "3";
        JournalLeader expected = generateJournalLeader(volume, issue, articleNumber, begin, end);
        String json = generateArticleJsonString(type, volume, issue, articleNumber, begin, end, true);
        assertEquals(expected, objectMapper.readValue(json, JournalLeader.class));
    }
}
