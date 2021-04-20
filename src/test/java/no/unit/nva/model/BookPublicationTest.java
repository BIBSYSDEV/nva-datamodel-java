package no.unit.nva.model;

import com.fasterxml.jackson.databind.JsonNode;
import no.unit.nva.model.exceptions.InvalidIsbnException;
import no.unit.nva.model.exceptions.MalformedContributorException;
import no.unit.nva.model.util.PublicationGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class BookPublicationTest extends PublicationTest {

    @DisplayName("Book publications can be created")
    @Test
    void publicationReturnPublicationWhenInputIsValid() throws MalformedContributorException, IOException,
            InvalidIsbnException {
        Publication bookPublication = PublicationGenerator.generateBookMonographPublication();
        JsonNode document = toPublicationWithContext(bookPublication);

        Publication publicationFromJson = objectMapper.readValue(objectMapper.writeValueAsString(document),
                Publication.class);
        Assertions.assertEquals(bookPublication, publicationFromJson);
    }
}
