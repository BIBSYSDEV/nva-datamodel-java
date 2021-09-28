package no.unit.nva.model.instancetypes;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import java.nio.file.Path;
import no.unit.nva.model.ModelTest;
import no.unit.nva.model.instancetypes.journal.JournalArticle;
import no.unit.nva.model.pages.Range;
import nva.commons.core.ioutils.IoUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PublicationInstanceTest extends ModelTest {

    public static final String PUBLICATION_INSTANCE_JSON = "publication_instance_pages.json";
    public static final String EXPECTED_INSTANCE_TYPE = "JournalArticle";

    @DisplayName("Publication instance exists")
    @Test
    void publicationInstanceCanBeCreated() {
        new JournalArticle();
    }

    @DisplayName("Publication instance can be serialized with Range object")
    @Test
    void publicationInstanceReturnsSerializedJsonWhenValidRangeIsInput() throws JsonProcessingException {
        PublicationInstance<Range> publicationInstance = samplePublicationInstanceWithPages();
        JsonNode expected = jsonStringToJsonNode(IoUtils.stringFromResources(Path.of(PUBLICATION_INSTANCE_JSON)));
        JsonNode actual = objectMapper.convertValue(publicationInstance, JsonNode.class);
        assertEquals(expected, actual);
    }

    @Test
    void getInstanceTypeReturnsTheInstanceTypeOfTheObject() {
        PublicationInstance<Range> publicationInstance = samplePublicationInstanceWithPages();
        assertThat(publicationInstance.getInstanceType(),is(equalTo(EXPECTED_INSTANCE_TYPE)));

    }

    private PublicationInstance<Range> samplePublicationInstanceWithPages() {
        Range range = new Range.Builder()
            .withBegin("1")
            .withEnd("15")
            .build();
        PublicationInstance<Range> publicationInstance = new JournalArticle();
        publicationInstance.setPages(range);
        return publicationInstance;
    }
}
