package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.unit.nva.model.exceptions.InvalidPageRangeException;
import no.unit.nva.model.instancetypes.journal.JournalReview;
import no.unit.nva.model.pages.Range;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JournalReviewTest extends InstanceTest {

    public static final String JOURNAL_REVIEW = "JournalReview";

    @DisplayName("Journal review can be created from JSON")
    @Test
    void journalReviewReturnsObjectWhenJsonInputIsCorrectlySerialized() throws JsonProcessingException,
            InvalidPageRangeException {
        String volume = "1";
        String issue = "3";
        String articleNumber = "123";
        String begin = "2";
        String end = "3";
        JournalReview expected =
                generateJournalReview(volume, issue, articleNumber, begin, end);
        String journalReview = generateArticleJsonString(JOURNAL_REVIEW, volume, issue,
                articleNumber, begin, end, false);
        JournalReview actual = objectMapper.readValue(journalReview, JournalReview.class);
        assertEquals(expected, actual);
    }

    @DisplayName("Journal review cannot be peer reviewed")
    @Test
    void journalReviewSetsPeerReviewedToFalseWhenPeerReviewIsTrue() throws JsonProcessingException,
            InvalidPageRangeException {
        ObjectMapper objectMapper = new ObjectMapper();
        String volume = "1";
        String issue = "3";
        String articleNumber = "123";
        String begin = "2";
        String end = "3";
        JournalReview expected =
                generateJournalReview(volume, issue, articleNumber, begin, end);

        String json = generateArticleJsonString(JOURNAL_REVIEW,
                volume, issue, articleNumber, begin, end, true);
        assertEquals(expected, objectMapper.readValue(json, JournalReview.class));
    }

    private JournalReview generateJournalReview(String volume,
                                                String issue,
                                                String articleNumber,
                                                String begin,
                                                String end) throws InvalidPageRangeException {
        Range pages = new Range.Builder()
                .withBegin(begin)
                .withEnd(end)
                .build();

        return new JournalReview.Builder()
                .withVolume(volume)
                .withPages(pages)
                .withIssue(issue)
                .withArticleNumber(articleNumber)
                .build();
    }
}