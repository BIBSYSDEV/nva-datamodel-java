package no.unit.nva.model.instancetypes;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.unit.nva.model.pages.MonographPages;
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
        new PublicationInstance();
    }

    @DisplayName("Publication instance can be serialized with Range object")
    @Test
    void publicationInstanceReturnsSerializedJsonWhenValidRangeIsInput() throws JsonProcessingException {
        PublicationInstance publicationInstance = new PublicationInstance();

        Range range = new Range.Builder()
                .withBegin("1")
                .withEnd("15")
                .build();

        publicationInstance.setPages(range);

        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals(IoUtils.stringFromResources(Path.of("publication_instance_pages.json")),
                objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(publicationInstance));
    }

    @DisplayName("Publication instance can be serialized with Range object")
    @Test
    void publicationInstanceReturnsSerializedJsonWhenValidMonographPagesIsInput() throws JsonProcessingException {
        PublicationInstance publicationInstance = new PublicationInstance();

        MonographPages monographPages = new MonographPages.Builder()
                .withIllustrated(true)
                .withIntroduction(new Range.Builder()
                        .withBegin("i")
                        .withEnd("ix")
                        .build())
                .withPages("90")
                .build();

        publicationInstance.setPages(monographPages);

        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals(IoUtils.stringFromResources(Path.of("publication_instance_monograph_pages.json")),
                objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(publicationInstance));
    }
}