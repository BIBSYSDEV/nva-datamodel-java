package no.unit.nva.model.util;

import com.github.javafaker.Faker;
import java.net.URI;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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
import no.unit.nva.model.contexttypes.Chapter;
import no.unit.nva.model.contexttypes.Degree;
import no.unit.nva.model.contexttypes.PublicationContext;
import no.unit.nva.model.contexttypes.Report;
import no.unit.nva.model.contexttypes.UnconfirmedJournal;
import no.unit.nva.model.contexttypes.UnconfirmedPublisher;
import no.unit.nva.model.contexttypes.UnconfirmedSeries;
import no.unit.nva.model.exceptions.InvalidIsbnException;
import no.unit.nva.model.exceptions.InvalidIssnException;
import no.unit.nva.model.exceptions.InvalidUnconfirmedSeriesException;
import no.unit.nva.model.exceptions.MalformedContributorException;
import no.unit.nva.model.instancetypes.PublicationInstance;
import no.unit.nva.model.instancetypes.book.BookAbstracts;
import no.unit.nva.model.instancetypes.book.BookAnthology;
import no.unit.nva.model.instancetypes.book.BookMonograph;
import no.unit.nva.model.instancetypes.book.BookMonographContentType;
import no.unit.nva.model.instancetypes.chapter.ChapterArticle;
import no.unit.nva.model.instancetypes.chapter.ChapterArticleContentType;
import no.unit.nva.model.instancetypes.degree.DegreeBachelor;
import no.unit.nva.model.instancetypes.degree.DegreeMaster;
import no.unit.nva.model.instancetypes.degree.DegreePhd;
import no.unit.nva.model.instancetypes.degree.OtherStudentWork;
import no.unit.nva.model.instancetypes.journal.FeatureArticle;
import no.unit.nva.model.instancetypes.journal.JournalArticle;
import no.unit.nva.model.instancetypes.journal.JournalArticleContentType;
import no.unit.nva.model.instancetypes.journal.JournalCorrigendum;
import no.unit.nva.model.instancetypes.journal.JournalInterview;
import no.unit.nva.model.instancetypes.journal.JournalLeader;
import no.unit.nva.model.instancetypes.journal.JournalLetter;
import no.unit.nva.model.instancetypes.journal.JournalReview;
import no.unit.nva.model.instancetypes.journal.JournalShortCommunication;
import no.unit.nva.model.instancetypes.report.ReportBasic;
import no.unit.nva.model.instancetypes.report.ReportPolicy;
import no.unit.nva.model.instancetypes.report.ReportResearch;
import no.unit.nva.model.instancetypes.report.ReportWorkingPaper;
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
                                                                      InvalidIsbnException,
                                                                      InvalidUnconfirmedSeriesException {
        Reference reference;
        switch (type) {
            case "BookAbstracts":
                reference = generateReference(getPublishingContextBook(), getBookAbstractsInstance());
                break;
            case "BookAnthology":
                reference = generateReference(getPublishingContextBook(), getBookAnthologyInstance());
                break;
            case "BookMonograph":
                reference = generateReference(getPublishingContextBook(), getBookMonographInstance());
                break;
            case "ChapterArticle":
                reference = generateReference(getPublishingContextChapter(), getChapterArticleInstance());
                break;
            case "DegreeBachelor":
                reference = generateReference(getPublishingContextDegree(), getDegreeBachelorInstance());
                break;
            case "DegreeMaster":
                reference = generateReference(getPublishingContextDegree(), getDegreeMasterInstance());
                break;
            case "DegreePhd":
                reference = generateReference(getPublishingContextDegree(), getDegreePhdInstance());
                break;
            case "FeatureArticle":
                reference = generateReference(getPublishingContextJournal(), getFeatureArticleInstance());
                break;
            case "JournalArticle":
                reference = generateReference(getPublishingContextJournal(), getJournalArticleInstance());
                break;
            case "JournalCorrigendum":
                reference = generateReference(getPublishingContextJournal(), getJournalCorrigendumInstance());
                break;
            case "JournalInterview":
                reference = generateReference(getPublishingContextJournal(), getJournalInterviewInstance());
                break;
            case "JournalLeader":
                reference = generateReference(getPublishingContextJournal(), getJournalLeaderInstance());
                break;
            case "JournalLetter":
                reference = generateReference(getPublishingContextJournal(), getJournalLetterInstance());
                break;
            case "JournalReview":
                reference = generateReference(getPublishingContextJournal(), getJournalReviewInstance());
                break;
            case "JournalShortCommunication":
                reference = generateReference(getPublishingContextJournal(), getJournalShortCommunicationInstance());
                break;
            case "OtherStudentWork":
                reference = generateReference(getPublishingContextDegree(), getOtherStudentWorkInstance());
                break;
            case "ReportBasic":
                reference = generateReference(getPublishingContextReport(), getReportBasicInstance());
                break;
            case "ReportPolicy":
                reference = generateReference(getPublishingContextReport(), getReportPolicyInstance());
                break;
            case "ReportResearch":
                reference = generateReference(getPublishingContextReport(), getReportResearchInstance());
                break;
            case "ReportWorkingPaper":
                reference = generateReference(getPublishingContextReport(), getReportWorkingPaperInstance());
                break;
            default:
                throw new RuntimeException("Unrecognized instance-type");
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
            .withSubjects(List.of(URI.create("http://example.org/subject/123")))
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

    public static Publication generateBookMonographWithUnconfirmedSeriesTitleString()
        throws InvalidIsbnException, InvalidUnconfirmedSeriesException, MalformedContributorException {

        var context = new Book(null,
                               "Some wild series title",
                               "2",
                               new UnconfirmedPublisher("Hansome publishing cowpoke"),
                               Collections.emptyList());
        var instance = new BookMonograph.Builder()
            .withPages(generateMonographPages())
            .withPeerReviewed(false)
            .withContentType(BookMonographContentType.ACADEMIC_MONOGRAPH)
            .withOriginalResearch(true)
            .withTextbookContent(false)
            .build();
        var reference = new Reference.Builder()
            .withPublishingContext(context)
            .withPublicationInstance(instance)
            .withDoi(SOME_URI)
            .build();

        var entityDescription = new EntityDescription.Builder()
            .withReference(reference)
            .withDescription("Yes, a description")
            .withAbstract("Irrelevant abstract")
            .withAlternativeTitles(Map.of("en", "Alternative title", "nb", "Alternativ tittel"))
            .withContributors(List.of(generateContributor()))
            .withDate(getPublicationDate())
            .withMainTitle("A funky main title")
            .withLanguage(SOME_URI)
            .withMetadataSource(SOME_URI)
            .withTags(List.of("tiny", "happy", "trolls"))
            .withNpiSubjectHeading("Soulfulness")
            .build();

        return generatePublication(SortableIdentifier.next(), UUID.randomUUID(), Instant.now(), entityDescription);
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
            .withContent(JournalArticleContentType.PROFESSIONAL_ARTICLE)
            .build();
    }

    public static BasicContext getPublishingContextJournal() throws InvalidIssnException {
        return new UnconfirmedJournal("Tim's lovely publishing house", "2222-2227", "1111-1119");
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

    public static String randomInvalidIssn() {
        return IssnGenerator.randomInvalidIssn();
    }

    public static String randomIssn() {
        return IssnGenerator.randomIssn();
    }

    private static Publication generatePublicationWithReference(Reference reference)
        throws MalformedContributorException {
        return generatePublication(SortableIdentifier.next(),
                                   UUID.randomUUID(),
                                   Instant.now(),
                                   generateEntityDescription(reference)
        );
    }

    private static BookAbstracts getBookAbstractsInstance() {
        return new BookAbstracts.Builder()
            .withPages(generateMonographPages("i", "xx", "221", true))
            .build();
    }

    private static BookAnthology getBookAnthologyInstance() {
        return new BookAnthology.Builder()
            .withPages(generateMonographPages("i", "xx", "221", true))
            .withPeerReviewed(true)
            .build();
    }

    private static BookMonograph getBookMonographInstance() {
        return new BookMonograph.Builder()
            .withPages(generateMonographPages("i", "xx", "221", true))
            .withPeerReviewed(true)
            .build();
    }

    private static ChapterArticle getChapterArticleInstance() {
        return new ChapterArticle.Builder()
            .withPages(getPages())
            .withPeerReviewed(true)
            .withContentType(ChapterArticleContentType.ACADEMIC_CHAPTER)
            .withOriginalResearch(true)
            .build();
    }

    private static DegreeBachelor getDegreeBachelorInstance() {
        return new DegreeBachelor.Builder()
            .withPages(generateMonographPages("i", "xx", "221", true))
            .withSubmittedDate(getPublicationDate())
            .build();
    }

    private static DegreeMaster getDegreeMasterInstance() {
        return new DegreeMaster.Builder()
            .withPages(generateMonographPages("i", "xx", "221", true))
            .withSubmittedDate(getPublicationDate())
            .build();
    }

    private static DegreePhd getDegreePhdInstance() {
        return new DegreePhd.Builder()
            .withPages(generateMonographPages("i", "xx", "221", true))
            .withSubmittedDate(getPublicationDate())
            .build();
    }

    private static FeatureArticle getFeatureArticleInstance() {
        return new FeatureArticle.Builder()
            .withArticleNumber("11111")
            .withIssue("1")
            .withPages(getPages())
            .withVolume("2")
            .build();
    }

    private static JournalArticle getJournalArticleInstance() {
        return new JournalArticle.Builder()
            .withArticleNumber("11111")
            .withIssue("1")
            .withPages(getPages())
            .withPeerReviewed(true)
            .withContent(JournalArticleContentType.REVIEW_ARTICLE)
            .withVolume("2")
            .withOriginalResearch(true)
            .build();
    }

    private static JournalCorrigendum getJournalCorrigendumInstance() {
        return new JournalCorrigendum.Builder()
            .withIssue("1")
            .withPages(getPages())
            .withCorrigendumFor(SOME_URI)
            .withArticleNumber("11111")
            .withVolume("2")
            .build();
    }

    private static JournalInterview getJournalInterviewInstance() {
        return new JournalInterview.Builder()
            .withArticleNumber("11111")
            .withPages(getPages())
            .withVolume("2")
            .withIssue("1")
            .build();
    }

    private static JournalLeader getJournalLeaderInstance() {
        return new JournalLeader.Builder()
            .withArticleNumber("213222")
            .withIssue("5")
            .withVolume("27")
            .withPages(getPages())
            .build();
    }

    private static JournalLetter getJournalLetterInstance() {
        return new JournalLetter.Builder()
            .withArticleNumber("213222")
            .withIssue("5")
            .withVolume("27")
            .withPages(getPages())
            .build();
    }

    private static PublicationInstance<Range> getJournalReviewInstance() {
        return new JournalReview.Builder()
            .withArticleNumber("213222")
            .withIssue("5")
            .withVolume("27")
            .withPages(getPages())
            .build();
    }

    private static JournalShortCommunication getJournalShortCommunicationInstance() {
        return new JournalShortCommunication.Builder()
            .withArticleNumber("213222")
            .withIssue("5")
            .withVolume("27")
            .withPages(getPages())
            .build();
    }

    private static OtherStudentWork getOtherStudentWorkInstance() {
        return new OtherStudentWork.Builder()
            .withPages(generateMonographPages())
            .withSubmittedDate(getPublicationDate())
            .build();
    }

    private static ReportBasic getReportBasicInstance() {
        return new ReportBasic.Builder()
            .withPages(generateMonographPages())
            .build();
    }

    private static ReportPolicy getReportPolicyInstance() {
        return new ReportPolicy.Builder()
            .withPages(generateMonographPages())
            .build();
    }

    private static ReportResearch getReportResearchInstance() {
        return new ReportResearch.Builder()
            .withPages(generateMonographPages())
            .build();
    }

    private static ReportWorkingPaper getReportWorkingPaperInstance() {
        return new ReportWorkingPaper.Builder()
            .withPages(generateMonographPages())
            .build();
    }

    private static Reference generateReference(PublicationContext context, PublicationInstance<?> instance) {
        return new Reference.Builder()
            .withDoi(SOME_URI)
            .withPublishingContext(context)
            .withPublicationInstance(instance)
            .build();
    }

    private static EntityDescription generateEntityDescriptionBookMonograph() throws MalformedContributorException,
                                                                                     InvalidIsbnException {
        return getEntityDescription(getBookMonographReference());
    }

    private static Reference getBookMonographReference() throws InvalidIsbnException {
        return new Reference.Builder()
            .withPublishingContext(getPublishingContextBook())
            .withDoi(SOME_URI)
            .withPublicationInstance(null)
            .build();
    }

    private static BasicContext getPublishingContextBook() throws InvalidIsbnException {
        return new Book.BookBuilder()
            .withIsbnList(List.of("9780201309515"))
            .withPublisher(new UnconfirmedPublisher("My publisher dot com"))
            .withSeriesNumber("123")
            .withSeries(UnconfirmedSeries.fromTitle("Explorations in ego"))
            .build();
    }

    private static Chapter getPublishingContextChapter() {
        return new Chapter.Builder()
            .withPartOf(SOME_URI)
            .build();
    }

    private static BasicContext getPublishingContextDegree() throws InvalidIsbnException,
                                                                    InvalidUnconfirmedSeriesException {
        return new Degree.Builder()
            .withIsbnList(List.of("9780201309515"))
            .withPublisher(new UnconfirmedPublisher("My publisher dot com"))
            .withSeriesNumber("123")
            .withSeries(UnconfirmedSeries.fromTitle("Explorations in ego"))
            .build();
    }

    private static Report getPublishingContextReport() throws InvalidIssnException, InvalidIsbnException,
                                                              InvalidUnconfirmedSeriesException {
        return new Report.Builder()
            .withIsbnList(List.of("9780201309515"))
            .withPublisher(new UnconfirmedPublisher("Hello cheesy world of anaemic flavours publishing"))
            .withSeries(UnconfirmedSeries.fromTitle("Strøsand and Muck in context"))
            .withSeriesNumber("221")
            .withOnlineIssn("1111-1119")
            .withPrintIssn("2222-2227")
            .build();
    }

    private static String randomWord() {
        return FAKER.lorem().word() + FAKER.lorem().word();
    }
}
