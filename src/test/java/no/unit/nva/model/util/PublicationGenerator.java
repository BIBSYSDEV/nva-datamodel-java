package no.unit.nva.model.util;

import static nva.commons.core.attempt.Try.attempt;
import com.github.javafaker.Faker;
import java.net.URI;
import java.net.URL;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import no.unit.nva.identifiers.SortableIdentifier;
import no.unit.nva.model.AdditionalIdentifier;
import no.unit.nva.model.Approval;
import no.unit.nva.model.ApprovalStatus;
import no.unit.nva.model.ApprovalsBody;
import no.unit.nva.model.DoiRequest;
import no.unit.nva.model.DoiRequestMessage;
import no.unit.nva.model.DoiRequestStatus;
import no.unit.nva.model.EntityDescription;
import no.unit.nva.model.File;
import no.unit.nva.model.FileSet;
import no.unit.nva.model.Grant;
import no.unit.nva.model.Level;
import no.unit.nva.model.License;
import no.unit.nva.model.ModelTest;
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
import no.unit.nva.model.exceptions.MalformedContributorException;
import no.unit.nva.model.instancetypes.PublicationInstance;
import no.unit.nva.model.instancetypes.book.BookAnthology;
import no.unit.nva.model.instancetypes.journal.JournalArticle;
import no.unit.nva.model.instancetypes.journal.JournalLeader;
import no.unit.nva.model.instancetypes.journal.JournalLetter;
import no.unit.nva.model.instancetypes.journal.JournalReview;
import no.unit.nva.model.instancetypes.journal.JournalShortCommunication;
import no.unit.nva.model.pages.MonographPages;
import no.unit.nva.model.pages.Range;
import nva.commons.core.StringUtils;

@SuppressWarnings("MissingJavadocMethod")
public class PublicationGenerator extends ModelTest {

    public static final URI SOME_URI = URI.create("https://123123/213123.com");
    public static final String SEPARATOR = "\\|";
    public static final String QUOTE = "\"";
    public static final String EMPTY_STRING = "";
    public static final Faker FAKER = Faker.instance();

    private PublicationGenerator() {
    }

    /**
     * Generates publication.
     *
     * @param type publication type.
     * @return a publication
     * @throws InvalidIssnException          when Issn is invalid.
     * @throws MalformedContributorException when contributor is malformed
     * @throws InvalidIsbnException          with Isbn is invalid.
     */
    public static Publication generatePublication(String type) throws InvalidIssnException,
                                                                      MalformedContributorException,
                                                                      InvalidIsbnException {
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
        return generatePublicationWithReference(reference);
    }

    /**
     * Generates publication.
     *
     * @param publicationIdentifier the publication identifier
     * @param fileIdentifier        the file identifier.
     * @param now                   the current date and time.
     * @param entityDescription     the entity description.
     * @return a publication.
     */
    public static Publication generatePublication(SortableIdentifier publicationIdentifier,
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
                   .withProjects(getProjects())
                   .withDoiRequest(getDoiRequest(now))
                   .withPublishedDate(now)
                   .withDoi(URI.create("http://example.org/doi/1231/98765"))
                   .withIndexedDate(now)
                   .withAdditionalIdentifiers(generateAdditionalIdentifiers())
                   .build();
    }

    public static Publication generateJournalArticlePublication() throws InvalidIssnException,
                                                                         MalformedContributorException {
        return generatePublication(SortableIdentifier.next(), UUID.randomUUID(), Instant.now(),
                                   generateEntityDescriptionJournalArticle());
    }

    public static Publication generateBookMonographPublication() throws MalformedContributorException,
                                                                        InvalidIsbnException {
        return generatePublication(SortableIdentifier.next(), UUID.randomUUID(), Instant.now(),
                                   generateEntityDescriptionBookMonograph());
    }

    public static List<ResearchProject> getProjects() {
        return List.of(new ResearchProject.Builder()
                           .withId(URI.create("http://link.to.cristin.example.org/123"))
                           .withName("Det gode prosjektet")
                           .withApprovals(getApprovals())
                           .withGrants(getGrants())
                           .build());
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

    public static DoiRequest getDoiRequest(Instant now) {
        DoiRequestMessage message = new DoiRequestMessage.Builder()
                                        .withTimestamp(now)
                                        .withText("Some Text")
                                        .withAuthor("SomeAuthor")
                                        .build();

        return new DoiRequest.Builder()
                   .withStatus(DoiRequestStatus.REQUESTED)
                   .withModifiedDate(now)
                   .withCreatedDate(now)
                   .withMessages(Collections.singletonList(message))
                   .build();
    }

    public static EntityDescription generateEntityDescriptionJournalArticle() throws InvalidIssnException,
                                                                                     MalformedContributorException {
        return getEntityDescription(getJournalArticleReference());
    }

    public static EntityDescription generateEntityDescription(Reference reference) throws
                                                                                   MalformedContributorException {
        return getEntityDescription(reference);
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

    public static Reference getJournalArticleReference() throws InvalidIssnException {
        return new Reference.Builder()
                   .withPublishingContext(getPublishingContextJournal())
                   .withDoi(SOME_URI)
                   .withPublicationInstance(getPublicationInstanceJournalArticle())
                   .build();
    }

    public static PublicationInstance<Range> getPublicationInstanceJournalArticle() {
        return new JournalArticle.Builder()
                   .withArticleNumber("1234456")
                   .withIssue("2")
                   .withVolume("24")
                   .withPages(getPages())
                   .withPeerReviewed(true)
                   .build();
    }

    public static BasicContext getPublishingContextJournal() throws InvalidIssnException {
        URL randomUrl = randomUrl();
        return new Journal.Builder()
                   .withLevel(Level.LEVEL_1)
                   .withTitle("Tim's lovely publishing house")
                   .withPeerReviewed(true)
                   .withOpenAccess(true)
                   .withOnlineIssn("1111-1119")
                   .withPrintIssn("2222-2227")
                   .withUrl(randomUrl)
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

    public static List<String> convertIsbnStringToList(String isbnList) {
        if (StringUtils.isBlank(isbnList)) {
            return Collections.emptyList();
        } else {
            String unquoted = isbnList.replaceAll(QUOTE, EMPTY_STRING);
            String[] split = unquoted.split(SEPARATOR);
            return new ArrayList<>(Arrays.asList(split));
        }
    }

    public static Set<AdditionalIdentifier> generateAdditionalIdentifiers() {
        AdditionalIdentifier identifier = new AdditionalIdentifier(randomWord(), UUID.randomUUID().toString());
        return Set.of(identifier);
    }

    private static Publication generatePublicationWithReference(Reference reference)
        throws MalformedContributorException {
        return generatePublication(SortableIdentifier.next(),
                                   UUID.randomUUID(),
                                   Instant.now(),
                                   generateEntityDescription(reference)
        );
    }

    private static PublicationInstance<MonographPages> getBookAnthologyInstance() {
        return new BookAnthology.Builder()
                   .withPages(generateMonographPages("i", "xx", "221", true))
                   .withPeerReviewed(true)
                   .build();
    }

    private static Reference getBookAnthologyReference() throws InvalidIsbnException {
        return new Reference.Builder()
                   .withDoi(SOME_URI)
                   .withPublishingContext(getPublishingContextBook())
                   .withPublicationInstance(getBookAnthologyInstance())
                   .build();
    }

    private static EntityDescription generateEntityDescriptionBookMonograph() throws MalformedContributorException,
                                                                                     InvalidIsbnException {
        return getEntityDescription(getBookMonographReference());
    }

    private static Reference getJournalLeaderReference() throws InvalidIssnException {
        return new Reference.Builder()
                   .withPublishingContext(getPublishingContextJournal())
                   .withDoi(SOME_URI)
                   .withPublicationInstance(getPublicationInstanceJournalLeader())
                   .build();
    }

    private static Reference getJournalLetterReference() throws InvalidIssnException {
        return new Reference.Builder()
                   .withPublishingContext(getPublishingContextJournal())
                   .withDoi(SOME_URI)
                   .withPublicationInstance(getPublicationInstanceJournalLetter())
                   .build();
    }

    private static Reference getJournalReviewReference() throws InvalidIssnException {
        return new Reference.Builder()
                   .withPublishingContext(getPublishingContextJournal())
                   .withDoi(SOME_URI)
                   .withPublicationInstance(getPublicationInstanceJournalReview())
                   .build();
    }

    private static Reference getJournalShortCommunicationReference() throws InvalidIssnException {
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

    private static PublicationInstance<Range> getPublicationInstanceJournalLeader() {
        return new JournalLeader.Builder()
                   .withArticleNumber("213222")
                   .withIssue("5")
                   .withVolume("27")
                   .withPages(getPages())
                   .build();
    }

    private static PublicationInstance<Range> getPublicationInstanceJournalLetter() {
        return new JournalLetter.Builder()
                   .withArticleNumber("213222")
                   .withIssue("5")
                   .withVolume("27")
                   .withPages(getPages())
                   .build();
    }

    private static PublicationInstance<Range> getPublicationInstanceJournalReview() {
        return new JournalReview.Builder()
                   .withArticleNumber("213222")
                   .withIssue("5")
                   .withVolume("27")
                   .withPages(getPages())
                   .build();
    }

    private static PublicationInstance<Range> getPublicationInstanceJournalShortCommunication() {
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

    private static URL randomUrl() {
        return attempt(() -> URI.create("https://www.example.org/" + randomWord()).toURL()).orElseThrow();
    }

    private static String randomWord() {
        return FAKER.lorem().word() + FAKER.lorem().word();
    }
}
