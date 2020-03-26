package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.jsonldjava.core.JsonLdOptions;
import com.github.jsonldjava.core.JsonLdProcessor;
import com.github.jsonldjava.utils.JsonUtils;
import no.unit.nva.model.util.ContextUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class PublicationTest {

    public static final String PUBLICATION_CONTEXT_JSON = "src/main/resources/publicationContext.json";
    public static final String PUBLICATION_FRAME_JSON = "src/main/resources/publicationFrame.json";
    public static final String HTTPS_NVA_UNIT_NO_PUBLICATION_MAIN_TITLE = "https://nva.unit.no/publication#mainTitle";
    private ObjectMapper objectMapper;

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
    void publicationClassReturnsDeserializedJsonWhenValidJsonInput() throws IOException {

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
    void objectMappingOfPublicationClassReturnsSerializedJsonWithJsonLdFrame() throws IOException {

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

    private Publication getPublication(UUID publicationIdentifier, UUID fileIdentifier, Instant now) {
        return new Publication.Builder()
                .withIdentifier(publicationIdentifier)
                .withCreatedDate(now)
                .withModifiedDate(now)
                .withHandle(URI.create("http://example.org/handle/123"))
                .withLink(URI.create("http://example.org/link"))
                .withStatus(PublicationStatus.DRAFT)
                .withPublisher(getOrganization())
                .withFileSet(getFileSet(fileIdentifier))
                .withLicense(getLicense())
                .withEntityDescription(getEntityDescription())
                .withOwner("eier@example.org")
                .withProject(getProject())
                .build();
    }

    private ResearchProject getProject() {
        return new ResearchProject.Builder()
                .withId(URI.create("http://link.to.cristin.example.org/123"))
                .withName("Det gode prosjektet")
                .withApprovals(getApprovals())
                .withGrants(getGrants())
                .build();
    }

    private List<Grant> getGrants() {
        return Collections.singletonList(new Grant.Builder()
                .withId("123123")
                .withSource("Norsk rødt felaget")
                .build());
    }

    private List<Approval> getApprovals() {
        return Collections.singletonList(new Approval.Builder()
                .withApplicationCode("123123")
                .withApprovedBy(ApprovalsBody.REK)
                .withDate(Instant.now())
                .withApprovalStatus(ApprovalStatus.APPLIED)
                .build());
    }

    private EntityDescription getEntityDescription() {
        return new EntityDescription.Builder()
                .withMainTitle("Hovedtittelen")
                .withLanguage(URI.create("http://example.org/norsk"))
                .withAlternativeTitles(Collections.singletonMap("en", "English title"))
                .withDate(getPublicationDate())
                .withPublicationType(PublicationType.JOURNAL_ARTICLE)
                .withPublicationSubtype(PublicationSubtype.ARTICLE)
                .withContributors(Collections.singletonList(getContributor()))
                .withAbstract("En lang streng som beskriver innholdet i dokumentet metadataene omtaler.")
                .withNpiSubjectHeading("010")
                .withTags(Arrays.asList("dokumenter", "publikasjoner"))
                .withDescription("En streng som beskriver innholdet i dokumentet på en annen måte enn abstrakt")
                .withReference(getJournalReference())
                .withMetadataSource(URI.create("https://example.org/doi?doi=123/123"))
                .build();
    }

    private Reference getJournalReference() {
        return new Reference.Builder()
                .withPublishingContext(getPublishingContext())
                .withDoi("123123/213123")
                .withPublicationInstance(getPublicationInstance())
                .build();
    }

    private PublicationInstance getPublicationInstance() {
        return new PublicationInstance.Builder()
                .withArticleNumber("1234456")
                .withIssue("2")
                .withVolume("24")
                .withPages(getPages())
                .build();
    }

    private PublicationContext getPublishingContext() {
        return new PublicationContext.Builder()
                .withLevel(Level.LEVEL_1)
                .withName("Tim's lovely publishing house")
                .withPeerReviewed(true)
                .withOpenAccess(true)
                .build();
    }

    private Contributor getContributor() {
        return new Contributor.Builder()
                .withSequence(0)
                .withRole(Role.CREATOR)
                .withAffiliations(Collections.singletonList(getOrganization()))
                .withIdentity(getIdentity())
                .build();
    }

    private Identity getIdentity() {
        return new Identity.Builder()
                .withId(URI.create("http://example.org/person/123"))
                .withArpId("arp123")
                .withOrcId("orc123")
                .withName("Navnesen, Navn")
                .withNameType(NameType.PERSONAL)
                .build();
    }

    private PublicationDate getPublicationDate() {
        return new PublicationDate.Builder()
                .withYear("2020")
                .withMonth("4")
                .withDay("7")
                .build();
    }

    private License getLicense() {
        return new License.Builder()
                .withIdentifier("NTNU-CC-BY-4.0")
                .withLink(URI.create("http://example.org/license/123"))
                .withLabels(Collections.singletonMap("no", "CC-BY 4.0"))
                .build();
    }

    private FileSet getFileSet(UUID fileIdentifier) {
        return new FileSet.Builder()
                .withFiles(Collections.singletonList(getFile(fileIdentifier)))
                .build();
    }

    private File getFile(UUID fileIdentifier) {
        return new File.Builder()
                .withIdentifier(fileIdentifier)
                .withMimeType("application/pdf")
                .withSize(2L)
                .withName("new document(1)")
                .withLicense(getLicense())
                .withAdministrativeAgreement(true)
                .withPublisherAuthority(true)
                .withEmbargoDate(Instant.now())
                .build();
    }

    private Organization getOrganization() {
        return new Organization.Builder()
                .withId(URI.create("http://example.org/org/123"))
                .withLabels(Collections.singletonMap("no", "Eksempelforlaget"))
                .build();
    }

    private Pages getPages() {
        return new Pages.Builder()
                .withBegins("1")
                .withEnds("15")
                .build();
    }

}
