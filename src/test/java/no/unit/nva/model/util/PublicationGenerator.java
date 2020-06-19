package no.unit.nva.model.util;

import no.unit.nva.ModelTest;
import no.unit.nva.model.Approval;
import no.unit.nva.model.ApprovalStatus;
import no.unit.nva.model.ApprovalsBody;
import no.unit.nva.model.DoiRequest;
import no.unit.nva.model.DoiRequestStatus;
import no.unit.nva.model.EntityDescription;
import no.unit.nva.model.File;
import no.unit.nva.model.FileSet;
import no.unit.nva.model.Grant;
import no.unit.nva.model.Level;
import no.unit.nva.model.License;
import no.unit.nva.model.Organization;
import no.unit.nva.model.Publication;
import no.unit.nva.model.PublicationDate;
import no.unit.nva.model.PublicationStatus;
import no.unit.nva.model.Reference;
import no.unit.nva.model.ResearchProject;
import no.unit.nva.model.contexttypes.BasicContext;
import no.unit.nva.model.contexttypes.Book;
import no.unit.nva.model.contexttypes.Journal;
import no.unit.nva.model.exceptions.InvalidIsbnException;
import no.unit.nva.model.exceptions.InvalidIssnException;
import no.unit.nva.model.exceptions.InvalidPageRangeException;
import no.unit.nva.model.exceptions.MalformedContributorException;
import no.unit.nva.model.instancetypes.BookAnthology;
import no.unit.nva.model.instancetypes.JournalArticle;
import no.unit.nva.model.instancetypes.JournalLeader;
import no.unit.nva.model.instancetypes.JournalLetter;
import no.unit.nva.model.instancetypes.JournalReview;
import no.unit.nva.model.instancetypes.JournalShortCommunication;
import no.unit.nva.model.instancetypes.PublicationInstance;
import no.unit.nva.model.pages.MonographPages;
import no.unit.nva.model.pages.Range;

import java.net.URI;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static java.util.Objects.isNull;

@SuppressWarnings("missingjavadocmethod")
public class PublicationGenerator extends ModelTest {

    public static final URI SOME_URI = URI.create("https://123123/213123.com");
    public static final String SEPARATOR = "\\|";
    public static final String QUOTE = "\"";
    public static final String EMPTY_STRING = "";

    private PublicationGenerator() {
    }

    public static Publication generatePublication(String type) throws InvalidIssnException,
            MalformedContributorException, InvalidPageRangeException, InvalidIsbnException {
        Reference reference;
        switch (type) {
            case "BookAnthology":
                reference = getBookAnthologyReference();
                break;
            case "JournalLeader":
                reference = getJournalLeaderReference();
                break;
            case "JournalLetter":
                reference = getJournalLetterReference();
                break;
            case "JournalReview":
                reference = getJournalReviewReference();
                break;
            case "JournalShortCommunication":
                reference = getJournalShortCommunicationReference();
                break;
            case "JournalArticle":
            default:
                reference = getJournalArticleReference();
                break;
        }
        return generatePublication(reference);
    }

    private static Publication generatePublication(Reference reference) throws MalformedContributorException {
        return generatePublication(UUID.randomUUID(), UUID.randomUUID(), Instant.now(),
                generateEntityDescription(reference));
    }

    public static Publication generatePublication(UUID publicationIdentifier,
                                                  UUID fileIdentifier,
                                                  Instant now,
                                                  EntityDescription entityDescription) {
        return new Publication.Builder()
                .withIdentifier(publicationIdentifier)
                .withCreatedDate(now)
                .withModifiedDate(now)
                .withHandle(URI.create("http://example.org/handle/123"))
                .withLink(URI.create("http://example.org/link"))
                .withStatus(PublicationStatus.DRAFT)
                .withPublisher(getOrganization())
                .withFileSet(getFileSet(fileIdentifier))
                .withEntityDescription(entityDescription)
                .withOwner("eier@example.org")
                .withProject(getProject())
                .withDoiRequest(getDoiRequest())
                .withPublishedDate(now)
                .withDoi(URI.create("http://example.org/doi/1231/98765"))
                .withIndexedDate(now)
                .build();
    }

    private static PublicationInstance<MonographPages> getBookAnthologyInstance() throws InvalidPageRangeException {
        return new BookAnthology.Builder()
                .withPages(generateMonographPages("i", "xx", "221", true))
                .withPeerReviewed(true)
                .build();
    }

    private static Reference getBookAnthologyReference() throws InvalidIsbnException, InvalidPageRangeException {
        return new Reference.Builder()
                .withDoi(SOME_URI)
                .withPublishingContext(getPublishingContextBook())
                .withPublicationInstance(getBookAnthologyInstance())
                .build();
    }

    public static Publication generateJournalArticlePublication() throws InvalidIssnException,
            MalformedContributorException, InvalidPageRangeException {
        return generatePublication(UUID.randomUUID(), UUID.randomUUID(), Instant.now(),
                generateEntityDescriptionJournalArticle());
    }

    public static Publication generateBookMonographPublication() throws MalformedContributorException,
            InvalidIsbnException {
        return generatePublication(UUID.randomUUID(), UUID.randomUUID(), Instant.now(),
                generateEntityDescriptionBookMonograph());
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

    public static DoiRequest getDoiRequest() {
        return new DoiRequest.Builder()
                .withStatus(DoiRequestStatus.REQUESTED)
                .withDate(Instant.now())
                .build();
    }

    public static EntityDescription generateEntityDescriptionJournalArticle() throws InvalidIssnException,
            MalformedContributorException, InvalidPageRangeException {
        return getEntityDescription(getJournalArticleReference());
    }

    public static EntityDescription generateEntityDescription(Reference reference) throws
            MalformedContributorException {
        return getEntityDescription(reference);
    }

    private static EntityDescription generateEntityDescriptionBookMonograph() throws MalformedContributorException,
            InvalidIsbnException {
        return getEntityDescription(getBookMonographReference());
    }

    public static EntityDescription getEntityDescription(Reference reference) throws MalformedContributorException {
        return new EntityDescription.Builder()
                .withMainTitle("Hovedtittelen")
                .withLanguage(URI.create("http://example.org/norsk"))
                .withAlternativeTitles(Collections.singletonMap("en", "English title"))
                .withDate(getPublicationDate())
                .withContributors(Collections.singletonList(generateContributor()))
                .withAbstract("En lang streng som beskriver innholdet i dokumentet metadataene omtaler.")
                .withNpiSubjectHeading("010")
                .withTags(Arrays.asList("dokumenter", "publikasjoner"))
                .withDescription("En streng som beskriver innholdet i dokumentet på en annen måte enn abstrakt")
                .withReference(reference)
                .withMetadataSource(URI.create("https://example.org/doi?doi=123/123"))
                .build();
    }


    public static Reference getJournalArticleReference() throws InvalidIssnException, InvalidPageRangeException {
        return new Reference.Builder()
                .withPublishingContext(getPublishingContextJournal())
                .withDoi(SOME_URI)
                .withPublicationInstance(getPublicationInstanceJournalArticle())
                .build();
    }

    private static Reference getJournalLeaderReference() throws InvalidIssnException, InvalidPageRangeException {
        return new Reference.Builder()
                .withPublishingContext(getPublishingContextJournal())
                .withDoi(SOME_URI)
                .withPublicationInstance(getPublicationInstanceJournalLeader())
                .build();
    }

    private static Reference getJournalLetterReference() throws InvalidIssnException, InvalidPageRangeException {
        return new Reference.Builder()
                .withPublishingContext(getPublishingContextJournal())
                .withDoi(SOME_URI)
                .withPublicationInstance(getPublicationInstanceJournalLetter())
                .build();
    }

    private static Reference getJournalReviewReference() throws InvalidIssnException, InvalidPageRangeException {
        return new Reference.Builder()
                .withPublishingContext(getPublishingContextJournal())
                .withDoi(SOME_URI)
                .withPublicationInstance(getPublicationInstanceJournalReview())
                .build();
    }

    private static Reference getJournalShortCommunicationReference() throws InvalidIssnException,
            InvalidPageRangeException {
        return new Reference.Builder()
                .withPublishingContext(getPublishingContextJournal())
                .withDoi(SOME_URI)
                .withPublicationInstance(getPublicationInstanceJournalShortCommunication())
                .build();
    }

    private static Reference getBookMonographReference() throws InvalidIsbnException {
        return new Reference.Builder()
                .withPublishingContext(getPublishingContextBook())
                .withDoi(SOME_URI)
                .withPublicationInstance(null)
                .build();
    }

    public static PublicationInstance<Range> getPublicationInstanceJournalArticle() throws InvalidPageRangeException {
        return new JournalArticle.Builder()
                .withArticleNumber("1234456")
                .withIssue("2")
                .withVolume("24")
                .withPages(getPages())
                .withPeerReviewed(true)
                .build();
    }

    private static PublicationInstance<Range> getPublicationInstanceJournalLeader() throws InvalidPageRangeException {
        return new JournalLeader.Builder()
                .withArticleNumber("213222")
                .withIssue("5")
                .withVolume("27")
                .withPages(getPages())
                .build();
    }

    private static PublicationInstance<Range> getPublicationInstanceJournalLetter() throws InvalidPageRangeException {
        return new JournalLetter.Builder()
                .withArticleNumber("213222")
                .withIssue("5")
                .withVolume("27")
                .withPages(getPages())
                .build();
    }

    private static PublicationInstance<Range> getPublicationInstanceJournalReview() throws InvalidPageRangeException {
        return new JournalReview.Builder()
                .withArticleNumber("213222")
                .withIssue("5")
                .withVolume("27")
                .withPages(getPages())
                .build();
    }

    private static PublicationInstance<Range> getPublicationInstanceJournalShortCommunication() throws
            InvalidPageRangeException {
        return new JournalShortCommunication.Builder()
                .withArticleNumber("213222")
                .withIssue("5")
                .withVolume("27")
                .withPages(getPages())
                .build();
    }

    private static BasicContext getPublishingContextBook() throws InvalidIsbnException {
        return new Book.Builder()
                .withIsbnList(List.of("9780201309515"))
                .withLevel(Level.LEVEL_0)
                .withOpenAccess(false)
                .withPeerReviewed(true)
                .withPublisher("My publisher dot com")
                .withSeriesNumber("123")
                .withSeriesTitle("Explorations in ego")
                .build();
    }

    public static BasicContext getPublishingContextJournal() throws InvalidIssnException {
        return new Journal.Builder()
                .withLevel(Level.LEVEL_1)
                .withTitle("Tim's lovely publishing house")
                .withPeerReviewed(true)
                .withOpenAccess(true)
                .withOnlineIssn("1111-1119")
                .withPrintIssn("2222-2227")
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

    public static Range getPages() throws InvalidPageRangeException {
        return new Range.Builder()
                .withBegin("1")
                .withEnd("15")
                .build();
    }

    public static List<String> convertIsbnStringToList(String isbnList) {
        if (isNull(isbnList)) {
            return Collections.emptyList();
        }
        String unquoted = isbnList.replaceAll(QUOTE, EMPTY_STRING);
        String[] split = unquoted.split(SEPARATOR);
        return new ArrayList<>(Arrays.asList(split));
    }
}
