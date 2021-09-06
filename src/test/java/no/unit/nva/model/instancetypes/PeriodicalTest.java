package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.core.JsonProcessingException;
import no.unit.nva.model.contexttypes.Journal;
import no.unit.nva.model.contexttypes.UnconfirmedJournal;
import no.unit.nva.model.exceptions.InvalidSeriesException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static java.util.Objects.isNull;
import static nva.commons.core.JsonUtils.objectMapper;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PeriodicalTest {

    @Test
    public void canDeserializeJournalWithEmptyPrintIssn() throws JsonProcessingException {
        String json = "{ \"type\": \"UnconfirmedJournal\", \"printIssn\": \"\" }";

        UnconfirmedJournal journal = objectMapper.readValue(json, UnconfirmedJournal.class);

        assertNotNull(journal);
        assertNull(journal.getPrintIssn());
    }

    @Test
    public void canDeserializeJournalWithNullPrintIssn() throws JsonProcessingException {
        String json = "{ \"type\": \"UnconfirmedJournal\", \"printIssn\": null }";

        UnconfirmedJournal journal = objectMapper.readValue(json, UnconfirmedJournal.class);

        assertNotNull(journal);
        assertNull(journal.getPrintIssn());
    }

    @Test
    public void canDeserializeJournalWithEmptyOnlineIssn() throws JsonProcessingException {
        String json = "{ \"type\": \"UnconfirmedJournal\", \"onlineIssn\": \"\" }";

        UnconfirmedJournal journal = objectMapper.readValue(json, UnconfirmedJournal.class);

        assertNotNull(journal);
        assertNull(journal.getPrintIssn());
    }

    @Test
    public void canDeserializeJournalWithNullOnlineIssn() throws JsonProcessingException {
        String json = "{ \"type\": \"UnconfirmedJournal\", \"onlineIssn\": null }";

        UnconfirmedJournal journal = objectMapper.readValue(json, UnconfirmedJournal.class);

        assertNotNull(journal);
        assertNull(journal.getPrintIssn());
    }

    @Test
    public void canDeserializeJournalWithId() throws JsonProcessingException {
        String json = "{ \"type\": \"Journal\", \"id\": \"https://example.org/series\" }";
        Journal journal = objectMapper.readValue(json, Journal.class);
        assertNotNull(journal);
        assertNotNull(journal.getId());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void deserializationThrowsExceptionWhenIdIsInvalid(String value) {
        String jsonizedValue = isNull(value) ? value : "\"" + value + "\"";
        String json = "{ \"type\": \"Journal\", \"id\": " + jsonizedValue + " }";
        Exception exception = assertThrows(Exception.class, () -> objectMapper.readValue(json, Journal.class));
        String expected = String.format(InvalidSeriesException.MESSAGE, "null");
        assertEquals(expected, exception.getCause().getMessage());
    }
}
