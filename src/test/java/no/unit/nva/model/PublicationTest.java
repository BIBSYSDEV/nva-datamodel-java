package no.unit.nva.model;

import static no.unit.nva.model.util.PublicationGenerator.getPublication;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.jsonldjava.core.JsonLdOptions;
import com.github.jsonldjava.core.JsonLdProcessor;
import com.github.jsonldjava.utils.JsonUtils;
import no.unit.nva.model.contexttypes.Journal;
import no.unit.nva.model.contexttypes.PublicationContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.UUID;
import no.unit.nva.model.exceptions.InvalidIssnException;
import no.unit.nva.model.exceptions.InvalidPageTypeException;
import no.unit.nva.model.exceptions.MalformedContributorException;
import no.unit.nva.model.util.ContextUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PublicationTest {

    public static final String PUBLICATION_CONTEXT_JSON = "src/main/resources/publicationContext.json";
    public static final String PUBLICATION_FRAME_JSON = "src/main/resources/publicationFrame.json";
    public static final String HTTPS_NVA_UNIT_NO_PUBLICATION_MAIN_TITLE = "https://nva.unit.no/publication#mainTitle";

    private final ObjectMapper objectMapper;

    /**
     * Constructor for PublicationTest.
     */
    public PublicationTest() {
        this.objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .enable(SerializationFeature.INDENT_OUTPUT)
                .enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @DisplayName("The Publication class object can (de-)serialize valid JSON input")
    @Test
    public void publicationClassReturnsDeserializedJsonWhenValidJsonInput()
        throws IOException, MalformedContributorException,
            InvalidIssnException, InvalidPageTypeException {

        UUID publicationIdentifier = UUID.randomUUID();
        UUID fileIdentifier = UUID.randomUUID();
        Instant now = Instant.now();

        Publication publication = getPublication(publicationIdentifier, fileIdentifier, now);

        JsonNode document = toPublicationWithContext(publication);

        Publication publicationFromJson = objectMapper.readValue(objectMapper.writeValueAsString(document),
                Publication.class);
        Assertions.assertEquals(publication, publicationFromJson);
    }

    private JsonNode toPublicationWithContext(Publication publication) throws IOException {
        JsonNode document = objectMapper.readTree(objectMapper.writeValueAsString(publication));
        JsonNode context = objectMapper.readTree(new FileInputStream(PUBLICATION_CONTEXT_JSON));
        ContextUtil.injectContext(document, context);
        return document;
    }

    @DisplayName("The serialized Publication class can be framed to match the RDF data model")
    @Test
    public void objectMappingOfPublicationClassReturnsSerializedJsonWithJsonLdFrame() throws IOException,
            MalformedContributorException, InvalidIssnException, InvalidPageTypeException {

        UUID publicationIdentifier = UUID.randomUUID();
        UUID fileIdentifier = UUID.randomUUID();
        Instant now = Instant.now();

        Publication publication = getPublication(publicationIdentifier, fileIdentifier, now);

        JsonNode publicationWithContext = toPublicationWithContext(publication);

        Object framedPublication = produceFramedPublication(publicationWithContext);

        Assertions.assertTrue(JsonUtils.toString(framedPublication).contains(HTTPS_NVA_UNIT_NO_PUBLICATION_MAIN_TITLE));
    }

    private Object produceFramedPublication(JsonNode publicationWithContext) throws IOException {
        Object input = JsonUtils.fromString(objectMapper.writeValueAsString(publicationWithContext));
        Object frame = JsonUtils.fromInputStream(new FileInputStream(PUBLICATION_FRAME_JSON));
        JsonLdOptions options = new JsonLdOptions();
        options.setOmitGraph(true);
        options.setPruneBlankNodeIdentifiers(true);
        return JsonLdProcessor.frame(input, frame, options);
    }
}
