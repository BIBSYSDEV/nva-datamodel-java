package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.unit.nva.model.exceptions.InvalidPageTypeException;
import no.unit.nva.model.pages.Pages;
import no.unit.nva.model.pages.Range;
import nva.commons.utils.IoUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JournalLetterTest {

    public static final String JOURNAL_LETTER_JSON = "journal_letter.json";

    @DisplayName("Journal letters to editor can be created from JSON")
    @Test
    void journalLetterReturnsObjectWhenJsonInputIsCorrectlySerialized() throws JsonProcessingException,
            InvalidPageTypeException {
        ObjectMapper objectMapper = new ObjectMapper();
        JournalLetter expected = generateJournalLetter("1", "3", "123", false, "2", "3");
        String json = objectMapper.writeValueAsString(expected);
        JournalLetter journalLetter = objectMapper.readValue(json, JournalLetter.class);
        assertEquals(expected, journalLetter);
    }

    @DisplayName("Journal letters cannot be peer reviewed")
    @Test
    void journalLetterThrowsJsonMappingExceptionWhenPeerReviewIsTrue() {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = IoUtils.stringFromResources(Path.of(
                JOURNAL_LETTER_JSON));
        Exception exception = assertThrows(JsonMappingException.class, () -> {
            objectMapper.readValue(json, JournalLetter.class);
        });

        assertEquals(JournalLetter.NON_PEER_REVIEW_ERROR, exception.getMessage());
    }

    private JournalLetter generateJournalLetter(String volume,
                                                String issue,
                                                String articleNumber,
                                                boolean peerReview,
                                                String begin,
                                                String end) throws InvalidPageTypeException {
        Range pages = new Range.Builder()
                .withBegin(begin)
                .withEnd(end)
                .build();

        return new JournalLetter.Builder()
                .withPeerReviewed(peerReview)
                .withVolume(volume)
                .withPages(pages)
                .withIssue(issue)
                .withArticleNumber(articleNumber)
                .build();
    }
}