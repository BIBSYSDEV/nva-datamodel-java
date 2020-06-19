package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.unit.nva.model.exceptions.InvalidPageRangeException;
import no.unit.nva.model.instancetypes.journal.JournalArticle;
import no.unit.nva.model.pages.Range;
import nva.commons.utils.IoUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PublicationInstanceTest {
    @DisplayName("Publication instance exists")
    @Test
    void publicationInstanceCanBeCreated() {
        new JournalArticle();
    }

    @DisplayName("Publication instance can be serialized with Range object")
    @Test
    void publicationInstanceReturnsSerializedJsonWhenValidRangeIsInput() throws JsonProcessingException,
            InvalidPageRangeException {
        PublicationInstance<Range> publicationInstance = new JournalArticle();

        Range range = new Range.Builder()
                .withBegin("1")
                .withEnd("15")
                .build();

        publicationInstance.setPages(range);

        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals(IoUtils.stringFromResources(Path.of("publication_instance_pages.json")),
                objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(publicationInstance));
    }
}
