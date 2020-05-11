package no.unit.nva.model.instancetypes;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.unit.nva.model.exceptions.InvalidPageTypeException;
import no.unit.nva.model.pages.MonographPages;
import no.unit.nva.model.pages.Range;
import nva.commons.utils.IoUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PublicationInstanceTest {
    @DisplayName("Publication instance exists")
    @Test
    void publicationInstanceCanBeCreated() {
        new JournalArticle();
    }

    @DisplayName("Publication instance can be serialized with Range object")
    @Test
    void publicationInstanceReturnsSerializedJsonWhenValidRangeIsInput() throws JsonProcessingException,
            InvalidPageTypeException {
        PublicationInstance publicationInstance = new JournalArticle();

        Range range = new Range.Builder()
                .withBegin("1")
                .withEnd("15")
                .build();

        publicationInstance.setPages(range);

        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals(IoUtils.stringFromResources(Path.of("publication_instance_pages.json")),
                objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(publicationInstance));
    }

    @DisplayName("Publication instance can ONLY be serialized with Range object")
    @Test
    void journalArticleThrowsInvalidPageExceptionWhenValidMonographPagesIsInput() {
        PublicationInstance publicationInstance = new JournalArticle();

        MonographPages monographPages = new MonographPages.Builder()
                .withIllustrated(true)
                .withIntroduction(new Range.Builder()
                        .withBegin("i")
                        .withEnd("ix")
                        .build())
                .withPages("90")
                .build();

        assertThrows(InvalidPageTypeException.class, () -> {
            publicationInstance.setPages(monographPages);
        });
    }
}