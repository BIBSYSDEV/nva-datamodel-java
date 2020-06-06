package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.unit.nva.model.exceptions.InvalidPageRangeException;
import no.unit.nva.model.pages.Range;
import no.unit.nva.model.util.JournalNonPeerReviewedContentUtil;
import nva.commons.utils.JsonUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JournalShortCommunicationTest {

    public static final ObjectMapper objectMapper = JsonUtils.objectMapper;

    @DisplayName("Journal short communication can be created from JSON")
    @Test
    void journalShortCommunicationReturnsObjectWhenJsonInputIsCorrectlySerialized() throws JsonProcessingException,
            InvalidPageRangeException {
        JournalShortCommunication expected =
                generateJournalShortCommunication("1", "3", "123", "2", "3");
        String json = objectMapper.writeValueAsString(expected);
        JournalShortCommunication journalShortCommunication =
                objectMapper.readValue(json, JournalShortCommunication.class);
        assertEquals(expected, journalShortCommunication);
    }

    @DisplayName("Journal short communication cannot be peer reviewed")
    @Test
    void journalShortCommunicationSetsPeerReviewedToFalseWhenPeerReviewIsTrue() throws JsonProcessingException,
            InvalidPageRangeException {
        ObjectMapper objectMapper = new ObjectMapper();
        String type = "JournalShortCommunication";
        String volume = "1";
        String issue = "3";
        String articleNumber = "123";
        String begin = "2";
        String end = "3";
        JournalShortCommunication expected =
                generateJournalShortCommunication(volume, issue, articleNumber, begin, end);

        String json = JournalNonPeerReviewedContentUtil.generateJsonString(type,
                volume, issue, articleNumber, begin, end, true);
        assertEquals(expected, objectMapper.readValue(json, JournalShortCommunication.class));
    }

    private JournalShortCommunication generateJournalShortCommunication(String volume,
                                                String issue,
                                                String articleNumber,
                                                String begin,
                                                String end) throws InvalidPageRangeException {
        Range pages = new Range.Builder()
                .withBegin(begin)
                .withEnd(end)
                .build();

        return new JournalShortCommunication.Builder()
                .withVolume(volume)
                .withPages(pages)
                .withIssue(issue)
                .withArticleNumber(articleNumber)
                .build();
    }
}