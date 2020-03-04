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
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.time.Instant;
import java.util.Collections;
import java.util.UUID;

public class PublicationTest {

    private ObjectMapper objectMapper;

    public PublicationTest() {
        this.objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .enable(SerializationFeature.INDENT_OUTPUT)
                .enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Test
    void test() throws IOException {

        UUID publicationIdentifier = UUID.randomUUID();
        UUID fileIdentifier = UUID.randomUUID();
        Instant now = Instant.now();

        Publication publication = new Publication.Builder()
                .withIdentifier(publicationIdentifier)
                .withCreatedDate(now)
                .withModifiedDate(now)
                .withHandle(URI.create("http://example.org/handle/123"))
                .withLink(URI.create("http://example.org/link"))
                .withStatus(PublicationStatus.DRAFT)
                .withPublisher(new Organization.Builder()
                        .withId(URI.create("http://example.org/org/123"))
                        .withLabels(Collections.singletonMap("no", "Eksempelforlaget"))
                        .build()
                )
                .withFileSet(new FileSet.Builder()
                        .withFiles(Collections.singletonList(new File.Builder()
                                .withIdentifier(fileIdentifier)
                                .withMimeType("application/pdf")
                                .withSize(2L)
                                .withName("new document(1)")
                                .withLicense(new License.Builder()
                                        .withIdentifier("NTNU-CC-BY-4.0")
                                        .withLink(URI.create("http://example.org/license/123"))
                                        .withLabels(Collections.singletonMap("no", "CC-BY 4.0"))
                                        .build()
                                )
                                .build()
                        ))
                        .build()
                )
                .withLicense(new License.Builder()
                        .withIdentifier("NTNU-CC-BY-4.0")
                        .withLink(URI.create("http://example.org/license/123"))
                        .withLabels(Collections.singletonMap("no", "CC-BY 4.0"))
                        .build()
                )
                .withEntityDescription(new EntityDescription.Builder()
                        .withMainTitle("Hovedtittelen")
                        .withLanguage(URI.create("http://example.org/norsk"))
                        .withAlternativeTitles(Collections.singletonMap("en", "English title"))
                        .withDate(new PublicationDate.Builder()
                                .withYear("2020")
                                .withMonth("4")
                                .withDay("7")
                                .build()
                        )
                        .withPublicationType(PublicationType.JOURNAL_ARTICLE.getValue())
                        .withContributors(Collections.singletonList(new Contributor.Builder()
                                .withSequence(0)
                                .withRole(Role.CREATOR)
                                .withAffiliation(Collections.singletonList(new Organization.Builder()
                                        .withLabels(Collections.singletonMap("no", "Affiliasjonen"))
                                        .build()
                                ))
                                .withIdentity(new Identity.Builder()
                                        .withId(URI.create("http://example.org/person/123"))
                                        .withArpId("arp123")
                                        .withOrcId("orc123")
                                        .withName("Navnesen, Navn")
                                        .withNameType(NameType.PERSONAL)
                                        .build()
                                )
                                .build()
                        ))
                        .build()
                )
                .withOwner("eier@example.org")
                .build();

        JsonNode document = objectMapper.readTree(objectMapper.writeValueAsString(publication));
        JsonNode context = objectMapper.readTree(new FileInputStream("src/main/resources/publicationContext.json"));
        ContextUtil.injectContext(document, context);
        objectMapper.writeValue(System.out, document);

        // Start test of Json-Ld framing
        Object input = JsonUtils.fromString(objectMapper.writeValueAsString(document));
        Object frame = JsonUtils.fromInputStream(new FileInputStream("src/main/resources/publicationFrame.json"));
        JsonLdOptions options = new JsonLdOptions();
        options.setOmitGraph(true);
        options.setPruneBlankNodeIdentifiers(true);
        Object framed = JsonLdProcessor.frame(input, frame, options);
        System.out.println(JsonUtils.toPrettyString(framed));
        // End test of Json-Ld framing

        Publication publicationFromJson = objectMapper.readValue(objectMapper.writeValueAsString(document), Publication.class);
        Assertions.assertEquals(publication, publicationFromJson);
    }

}
