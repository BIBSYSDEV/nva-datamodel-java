package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import no.unit.nva.model.ModelTest;
import no.unit.nva.model.instancetypes.journal.JournalArticle;
import no.unit.nva.model.pages.Range;
import nva.commons.utils.IoUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PublicationInstanceTest extends ModelTest {

    public static final String PUBLICATION_INSTANCE_JSON = "publication_instance_pages.json";

    @DisplayName("Publication instance exists")
    @Test
    void publicationInstanceCanBeCreated() {
        new JournalArticle();
    }

    @DisplayName("Publication instance can be serialized with Range object")
    @Test
    void publicationInstanceReturnsSerializedJsonWhenValidRangeIsInput() throws JsonProcessingException {
        Range range = new Range.Builder()
                .withBegin("1")
                .withEnd("15")
                .build();
        PublicationInstance<Range> publicationInstance = new JournalArticle();
        publicationInstance.setPages(range);
        JsonNode expected = jsonStringToJsonNode(IoUtils.stringFromResources(Path.of(PUBLICATION_INSTANCE_JSON)));
        JsonNode actual = objectMapper.convertValue(publicationInstance, JsonNode.class);
        assertEquals(expected, actual);
    }
}
