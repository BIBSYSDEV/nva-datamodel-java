package no.unit.nva.model.instancetypes;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.unit.nva.model.exceptions.InvalidPageTypeException;
import no.unit.nva.model.pages.Pages;
import no.unit.nva.model.pages.Range;
import no.unit.nva.model.util.JournalNonPeerReviewedContentUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JournalLeaderTest {

    @DisplayName("JournalLeader can be created")
    @Test
    void journalLeaderReturnsValidJournalLeaderWhenInputDataIsValid() throws InvalidPageTypeException,
            JsonProcessingException {
        String type = "JournalLeader";
        String volume = "22";
        String issue = "5";
        String articleNumber = "5521";
        String begin = "5";
        String end = "6";
        String json = JournalNonPeerReviewedContentUtil.generateJsonString(type, volume, issue,
                articleNumber, begin, end, false);
        ObjectMapper objectMapper = new ObjectMapper();
        JournalLeader expected = objectMapper.readValue(json, JournalLeader.class);
        Pages pages = new Range.Builder()
                .withBegin(begin)
                .withEnd(end)
                .build();
        boolean peerReviewed = true;
        JournalLeader journalLeader = new JournalLeader(volume, issue, articleNumber, pages, peerReviewed);
        assertEquals(expected, journalLeader);
    }
}
