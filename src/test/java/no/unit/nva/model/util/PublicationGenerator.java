package no.unit.nva.model.util;

import java.net.URI;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import no.unit.nva.model.Approval;
import no.unit.nva.model.ApprovalStatus;
import no.unit.nva.model.ApprovalsBody;
import no.unit.nva.model.Contributor;
import no.unit.nva.model.EntityDescription;
import no.unit.nva.model.File;
import no.unit.nva.model.FileSet;
import no.unit.nva.model.Grant;
import no.unit.nva.model.Identity;
import no.unit.nva.model.Journal;
import no.unit.nva.model.Level;
import no.unit.nva.model.License;
import no.unit.nva.model.NameType;
import no.unit.nva.model.Organization;
import no.unit.nva.model.Publication;
import no.unit.nva.model.PublicationContext;
import no.unit.nva.model.PublicationDate;
import no.unit.nva.model.PublicationStatus;
import no.unit.nva.model.Reference;
import no.unit.nva.model.ResearchProject;
import no.unit.nva.model.Role;
import no.unit.nva.model.exceptions.InvalidIssnException;
import no.unit.nva.model.exceptions.InvalidPageTypeException;
import no.unit.nva.model.exceptions.MalformedContributorException;
import no.unit.nva.model.instancetypes.JournalArticle;
import no.unit.nva.model.instancetypes.PublicationInstance;
import no.unit.nva.model.pages.Range;

@SuppressWarnings("missingjavadocmethod")
public class PublicationGenerator {

    public static final String EXAMPLE_EMAIL = "nn@example.org";
    public static final URI SOME_URI = URI.create("https://123123/213123.com");

    private PublicationGenerator() {

    }

    public static Publication getPublication()
        throws InvalidIssnException, MalformedContributorException, InvalidPageTypeException {
        return getPublication(UUID.randomUUID(), UUID.randomUUID(), Instant.now());
    }

    public static Publication getPublication(UUID publicationIdentifier, UUID fileIdentifier, Instant now)
        throws MalformedContributorException, InvalidIssnException, InvalidPageTypeException {
        return new Publication.Builder()
            .withIdentifier(publicationIdentifier)
            .withCreatedDate(now)
            .withModifiedDate(now)
            .withHandle(URI.create("http://example.org/handle/123"))
            .withLink(URI.create("http://example.org/link"))
            .withStatus(PublicationStatus.DRAFT)
            .withPublisher(getOrganization())
            .withFileSet(getFileSet(fileIdentifier))
            .withEntityDescription(getEntityDescription())
            .withOwner("eier@example.org")
            .withProject(getProject())
            .build();
    }

    public static ResearchProject getProject() {
        return new ResearchProject.Builder()
            .withId(URI.create("http://link.to.cristin.example.org/123"))
            .withName("Det gode prosjektet")
            .withApprovals(getApprovals())
            .withGrants(getGrants())
            .build();
    }

    public static List<Grant> getGrants() {
        return Collections.singletonList(new Grant.Builder()
            .withId("123123")
            .withSource("Norsk rødt felaget")
            .build());
    }

    public static List<Approval> getApprovals() {
        return Collections.singletonList(new Approval.Builder()
            .withApplicationCode("123123")
            .withApprovedBy(ApprovalsBody.REK)
            .withDate(Instant.now())
            .withApprovalStatus(ApprovalStatus.APPLIED)
            .build());
    }

    public static EntityDescription getEntityDescription() throws MalformedContributorException, InvalidIssnException,
                                                            InvalidPageTypeException {
        return new EntityDescription.Builder()
            .withMainTitle("Hovedtittelen")
            .withLanguage(URI.create("http://example.org/norsk"))
            .withAlternativeTitles(Collections.singletonMap("en", "English title"))
            .withDate(getPublicationDate())
            .withContributors(Collections.singletonList(getContributor()))
            .withAbstract("En lang streng som beskriver innholdet i dokumentet metadataene omtaler.")
            .withNpiSubjectHeading("010")
            .withTags(Arrays.asList("dokumenter", "publikasjoner"))
            .withDescription("En streng som beskriver innholdet i dokumentet på en annen måte enn abstrakt")
            .withReference(getJournalReference())
            .withMetadataSource(URI.create("https://example.org/doi?doi=123/123"))
            .build();
    }

    public static Reference getJournalReference() throws InvalidIssnException, InvalidPageTypeException {
        return new Reference.Builder()
            .withPublishingContext(getPublishingContext())
            .withDoi(SOME_URI)
            .withPublicationInstance(getPublicationInstance())
            .build();
    }

    public static PublicationInstance getPublicationInstance() throws InvalidPageTypeException {
        return new JournalArticle.Builder()
            .withArticleNumber("1234456")
            .withIssue("2")
            .withVolume("24")
            .withPages(getPages())
            .withPeerReviewed(true)
            .build();
    }

    public static PublicationContext getPublishingContext() throws InvalidIssnException {
        return new Journal.Builder()
            .withLevel(Level.LEVEL_1)
            .withTitle("Tim's lovely publishing house")
            .withPeerReviewed(true)
            .withOpenAccess(true)
            .withOnlineIssn("1111-1119")
            .withPrintIssn("2222-2227")
            .build();
    }

    public static Contributor getContributor() throws MalformedContributorException {
        return new Contributor.Builder()
            .withSequence(0)
            .withRole(Role.CREATOR)
            .withAffiliations(Collections.singletonList(getOrganization()))
            .withIdentity(getIdentity())
            .withCorrespondingAuthor(true)
            .withEmail(EXAMPLE_EMAIL)
            .build();
    }

    public static Identity getIdentity() {
        return new Identity.Builder()
            .withId(URI.create("http://example.org/person/123"))
            .withArpId("arp123")
            .withOrcId("orc123")
            .withName("Navnesen, Navn")
            .withNameType(NameType.PERSONAL)
            .build();
    }

    public static PublicationDate getPublicationDate() {
        return new PublicationDate.Builder()
            .withYear("2020")
            .withMonth("4")
            .withDay("7")
            .build();
    }

    public static License getLicense() {
        return new License.Builder()
            .withIdentifier("NTNU-CC-BY-4.0")
            .withLink(URI.create("http://example.org/license/123"))
            .withLabels(Collections.singletonMap("no", "CC-BY 4.0"))
            .build();
    }

    public static FileSet getFileSet(UUID fileIdentifier) {
        return new FileSet.Builder()
            .withFiles(Collections.singletonList(getFile(fileIdentifier)))
            .build();
    }

    public static File getFile(UUID fileIdentifier) {
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

    public static Organization getOrganization() {
        return new Organization.Builder()
            .withId(URI.create("http://example.org/org/123"))
            .withLabels(Collections.singletonMap("no", "Eksempelforlaget"))
            .build();
    }

    public static Range getPages() {
        return new Range.Builder()
            .withBegin("1")
            .withEnd("15")
            .build();
    }
}