package no.unit.nva.model.instancetypes;

import static nva.commons.core.JsonUtils.objectMapper;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import no.unit.nva.model.contexttypes.Journal;
import no.unit.nva.model.exceptions.InvalidIssnException;
import org.junit.jupiter.api.Test;

public class JournalTest {

    public static final String SAMPLE_LINKED_CONTEXT = "https://example.org/linkedContext";

    @Test
    public void canDeserializeJournalWithEmptyPrintIssn() throws JsonProcessingException {
        String json = "{ \"type\": \"Journal\", \"printIssn\": \"\" }";

        Journal journal = objectMapper.readValue(json, Journal.class);

        assertNotNull(journal);
        assertNull(journal.getPrintIssn());
    }

    @Test
    public void canDeserializeJournalWithNullPrintIssn() throws JsonProcessingException {
        String json = "{ \"type\": \"Journal\", \"printIssn\": null }";

        Journal journal = objectMapper.readValue(json, Journal.class);

        assertNotNull(journal);
        assertNull(journal.getPrintIssn());
    }

    @Test
    public void canDeserializeJournalWithEmptyOnlineIssn() throws JsonProcessingException {
        String json = "{ \"type\": \"Journal\", \"onlineIssn\": \"\" }";

        Journal journal = objectMapper.readValue(json, Journal.class);

        assertNotNull(journal);
        assertNull(journal.getPrintIssn());
    }

    @Test
    public void canDeserializeJournalWithNullOnlineIssn() throws JsonProcessingException {
        String json = "{ \"type\": \"Journal\", \"onlineIssn\": null }";

        Journal journal = objectMapper.readValue(json, Journal.class);

        assertNotNull(journal);
        assertNull(journal.getPrintIssn());
    }

    @Test
    public void canSerializeAndDeserializeJournalWithLinkedContext()
            throws JsonProcessingException, InvalidIssnException {
        String expectedJson = "{ \"type\": \"Journal\", \"linkedContext\": \"" + SAMPLE_LINKED_CONTEXT + "\" }";
        Journal expectedJournal = new Journal.Builder().withLinkedContext(SAMPLE_LINKED_CONTEXT).build();
        Journal actualJournal = objectMapper.readValue(expectedJson, Journal.class);

        assertEquals(expectedJournal, actualJournal);
        assertEquals(SAMPLE_LINKED_CONTEXT, actualJournal.getLinkedContext().toString());

        String actualJson = objectMapper.writeValueAsString(actualJournal);
        String linkedContextPhrase = "\"linkedContext\" : \"" + SAMPLE_LINKED_CONTEXT + "\"";
        assertTrue(actualJson.contains(linkedContextPhrase));
    }
}
