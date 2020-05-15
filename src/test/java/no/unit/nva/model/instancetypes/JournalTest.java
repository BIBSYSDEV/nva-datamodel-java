package no.unit.nva.model.instancetypes;

import static nva.commons.utils.JsonUtils.jsonParser;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.fasterxml.jackson.core.JsonProcessingException;
import no.unit.nva.model.Journal;
import org.junit.jupiter.api.Test;

public class JournalTest {

    @Test
    public void canDeserializeJournalWithEmptyPrintIssn() throws JsonProcessingException {
        String json = "{ \"type\": \"Journal\", \"printIssn\": \"\" }";

        Journal journal = jsonParser.readValue(json, Journal.class);

        assertNotNull(journal);
        assertNull(journal.getPrintIssn());
    }

    @Test
    public void canDeserializeJournalWithNullPrintIssn() throws JsonProcessingException {
        String json = "{ \"type\": \"Journal\", \"printIssn\": null }";

        Journal journal = jsonParser.readValue(json, Journal.class);

        assertNotNull(journal);
        assertNull(journal.getPrintIssn());
    }

    @Test
    public void canDeserializeJournalWithEmptyOnlineIssn() throws JsonProcessingException {
        String json = "{ \"type\": \"Journal\", \"onlineIssn\": \"\" }";

        Journal journal = jsonParser.readValue(json, Journal.class);

        assertNotNull(journal);
        assertNull(journal.getPrintIssn());
    }

    @Test
    public void canDeserializeJournalWithNullOnlineIssn() throws JsonProcessingException {
        String json = "{ \"type\": \"Journal\", \"onlineIssn\": null }";

        Journal journal = jsonParser.readValue(json, Journal.class);

        assertNotNull(journal);
        assertNull(journal.getPrintIssn());
    }

}