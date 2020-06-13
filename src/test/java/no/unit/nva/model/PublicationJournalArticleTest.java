package no.unit.nva.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.jsonldjava.utils.JsonUtils;
import no.unit.nva.model.exceptions.InvalidIssnException;
import no.unit.nva.model.exceptions.InvalidPageRangeException;
import no.unit.nva.model.exceptions.MalformedContributorException;
import no.unit.nva.model.util.PublicationGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;

public class PublicationJournalArticleTest extends PublicationTest {

    public static final String HTTPS_NVA_UNIT_NO_PUBLICATION_MAIN_TITLE = "https://nva.unit.no/publication#mainTitle";

    /**
     * Constructor for PublicationJournalArticleTest.
     */
    public PublicationJournalArticleTest() {
        super();
    }

    @DisplayName("The serialized Publication class can be framed to match the RDF data model")
    @Test
    public void objectMappingOfPublicationClassReturnsSerializedJsonWithJsonLdFrame() throws IOException,
            MalformedContributorException, InvalidIssnException, InvalidPageRangeException {

        Publication publication = PublicationGenerator.generateJournalArticlePublication();

        JsonNode publicationWithContext = toPublicationWithContext(publication);

        Object framedPublication = produceFramedPublication(publicationWithContext);

        Assertions.assertTrue(JsonUtils.toString(framedPublication).contains(HTTPS_NVA_UNIT_NO_PUBLICATION_MAIN_TITLE));
    }

    @DisplayName("Test publications can be serialized/deserialized")
    @ParameterizedTest(name = "Test Publication context Journal with Instance type {0} can be (de-)serialized")
    @ValueSource(strings = {
            "JournalArticle",
            "JournalLeader",
            "JournalLetter",
            "JournalReview",
            "JournalShortCommunication"
        }
    )
    void publicationReturnsJsonWhenInputIsValid(String type) throws MalformedContributorException,
            InvalidIssnException, IOException, InvalidPageRangeException {
        Publication publication = PublicationGenerator.generatePublication(type);
        JsonNode document = toPublicationWithContext(publication);
        Publication publicationFromJson = objectMapper.readValue(objectMapper.writeValueAsString(document),
                Publication.class);
        Assertions.assertEquals(publication, publicationFromJson);
    }
}
