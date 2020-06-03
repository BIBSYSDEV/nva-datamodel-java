package no.unit.nva.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.jsonldjava.utils.JsonUtils;
import no.unit.nva.model.exceptions.InvalidIssnException;
import no.unit.nva.model.exceptions.InvalidPageRangeException;
import no.unit.nva.model.exceptions.InvalidPageTypeException;
import no.unit.nva.model.exceptions.MalformedContributorException;
import no.unit.nva.model.instancetypes.JournalArticle;
import no.unit.nva.model.pages.Range;
import no.unit.nva.model.util.PublicationGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class PublicationJournalArticleTest extends PublicationTest {

    public static final String HTTPS_NVA_UNIT_NO_PUBLICATION_MAIN_TITLE = "https://nva.unit.no/publication#mainTitle";

    /**
     * Constructor for PublicationJournalArticleTest.
     */
    public PublicationJournalArticleTest() {
        super();
    }

    @DisplayName("The Publication class object can (de-)serialize valid JSON input")
    @Test
    public void publicationClassReturnsDeserializedJsonWhenValidJsonInput()
            throws IOException, MalformedContributorException, InvalidIssnException, InvalidPageTypeException,
            InvalidPageRangeException {

        Publication publication = PublicationGenerator.generateJournalArticlePublication();

        JsonNode document = toPublicationWithContext(publication);

        Publication publicationFromJson = objectMapper.readValue(objectMapper.writeValueAsString(document),
                Publication.class);
        Assertions.assertEquals(publication, publicationFromJson);
    }

    @DisplayName("The serialized Publication class can be framed to match the RDF data model")
    @Test
    public void objectMappingOfPublicationClassReturnsSerializedJsonWithJsonLdFrame() throws IOException,
            MalformedContributorException, InvalidIssnException, InvalidPageTypeException, InvalidPageRangeException {

        Publication publication = PublicationGenerator.generateJournalArticlePublication();

        JsonNode publicationWithContext = toPublicationWithContext(publication);

        Object framedPublication = produceFramedPublication(publicationWithContext);

        Assertions.assertTrue(JsonUtils.toString(framedPublication).contains(HTTPS_NVA_UNIT_NO_PUBLICATION_MAIN_TITLE));
    }

    @DisplayName("JournalArticle does not throw exception if pages is null")
    @ParameterizedTest
    @NullSource
    void journalArticleReturnsJournalArticleObjectWhenPagesIsNull(Range pages) {
        assertDoesNotThrow(
            () -> {
                new JournalArticle.Builder()
                        .withArticleNumber("123")
                        .withIssue("5")
                        .withPages(pages)
                        .withVolume("4")
                        .withPeerReviewed(false)
                        .build();
            }
        );
    }
}
