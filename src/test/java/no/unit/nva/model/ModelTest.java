package no.unit.nva.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.net.MalformedURLException;
import java.util.Map;
import no.unit.nva.JsonHandlingTest;
import no.unit.nva.model.contexttypes.Book;
import no.unit.nva.model.contexttypes.Cartograph;
import no.unit.nva.model.contexttypes.Chapter;
import no.unit.nva.model.contexttypes.Degree;
import no.unit.nva.model.contexttypes.Journal;
import no.unit.nva.model.contexttypes.Partitive;
import no.unit.nva.model.contexttypes.MusicalContent;
import no.unit.nva.model.contexttypes.PublicationContext;
import no.unit.nva.model.contexttypes.Report;
import no.unit.nva.model.exceptions.InvalidIsbnException;
import no.unit.nva.model.exceptions.InvalidIssnException;
import no.unit.nva.model.exceptions.MalformedContributorException;
import no.unit.nva.model.instancetypes.PublicationInstance;
import no.unit.nva.model.instancetypes.book.BookAbstracts;
import no.unit.nva.model.instancetypes.book.BookAnthology;
import no.unit.nva.model.instancetypes.book.BookMonograph;
import no.unit.nva.model.instancetypes.chapter.ChapterArticle;
import no.unit.nva.model.instancetypes.degree.DegreeBachelor;
import no.unit.nva.model.instancetypes.degree.DegreeMaster;
import no.unit.nva.model.instancetypes.degree.DegreePhd;
import no.unit.nva.model.instancetypes.degree.OtherStudentWork;
import no.unit.nva.model.instancetypes.journal.FeatureArticle;
import no.unit.nva.model.instancetypes.journal.JournalArticle;
import no.unit.nva.model.instancetypes.journal.JournalCorrigendum;
import no.unit.nva.model.instancetypes.journal.JournalInterview;
import no.unit.nva.model.instancetypes.journal.JournalLeader;
import no.unit.nva.model.instancetypes.journal.JournalLetter;
import no.unit.nva.model.instancetypes.journal.JournalReview;
import no.unit.nva.model.instancetypes.journal.JournalShortCommunication;
import no.unit.nva.model.instancetypes.musicalcontent.MusicNotation;
import no.unit.nva.model.instancetypes.musicalcontent.exception.InvalidIsmnException;
import no.unit.nva.model.instancetypes.report.ReportBasic;
import no.unit.nva.model.instancetypes.report.ReportPolicy;
import no.unit.nva.model.instancetypes.report.ReportResearch;
import no.unit.nva.model.instancetypes.report.ReportWorkingPaper;
import no.unit.nva.model.pages.MonographPages;
import no.unit.nva.model.pages.Pages;
import no.unit.nva.model.pages.Range;
import nva.commons.core.JsonUtils;

import java.net.URI;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static nva.commons.core.attempt.Try.attempt;

public class ModelTest implements JsonHandlingTest {

    public static final String EMPTY_STRING = "";
    public static final String TYPE = "type";
    public static final String SERIES_TITLE = "seriesTitle";
    public static final String SERIES_NUMBER = "seriesNumber";
    public static final String PUBLISHER = "publisher";
    public static final String ISBN_LIST = "isbnList";
    public static final String ONLINE_ISSN = "onlineIssn";
    public static final String PRINT_ISSN = "printIssn";
    public static final String EXAMPLE_EMAIL = "nn@example.org";
    public static final String PART_OF = "partOf";

    public static final URI SAMPLE_PART_OF = URI.create("https://example.org/partof");

    public final ObjectMapper objectMapper = JsonUtils.objectMapper;

    protected static MonographPages generateMonographPages(String introductionBegin,
                                                           String introductionEnd,
                                                           String pages,
                                                           boolean illustrated) {
        return new MonographPages.Builder()
            .withPages(pages)
            .withIllustrated(illustrated)
            .withIntroduction(generateRange(introductionBegin, introductionEnd))
            .build();
    }

    protected static MonographPages generateMonographPages() {
        return generateMonographPages("i", "ix", "321", true);
    }

    protected static Range generateRange(String begin, String end) {
        return new Range.Builder()
            .withBegin(begin)
            .withEnd(end)
            .build();
    }

    protected static Range generateRange() {
        return generateRange("1", "23");
    }

    protected Reference generateBookAbstracts() throws MalformedURLException, InvalidIsbnException {
        PublicationInstance<MonographPages> bookAbstracts = new BookAbstracts.Builder()
                .withPages(generateMonographPages())
                .build();
        return generateReference(generateBookContext(), bookAbstracts);
    }

    protected static Reference generateBookAnthology() throws InvalidIsbnException, MalformedURLException {
        PublicationInstance<MonographPages> bookAnthology = new BookAnthology.Builder()
            .withPages(generateMonographPages())
            .withPeerReviewed(true)
            .withTextbookContent(true)
            .build();

        return generateReference(generateBookContext(), bookAnthology);
    }

    protected static Reference generateBookMonograph() throws InvalidIsbnException, MalformedURLException {
        PublicationInstance<MonographPages> bookMonograph = new BookMonograph.Builder()
            .withPages(generateMonographPages())
            .withPeerReviewed(true)
            .withTextbookContent(true)
            .build();
        return generateReference(generateBookContext(), bookMonograph);
    }

    protected static Reference generateCartographicMap() {
        PublicationInstance<Range> chapterArticle = new ChapterArticle.Builder()
            .build();
        return generateReference(generateCartographicContext(), chapterArticle);
    }

    protected static Reference generateChapterArticle() {
        PublicationInstance<Range> chapterArticle = new ChapterArticle.Builder()
            .withPages(generateRange())
            .withPeerReviewed(true)
            .build();
        return generateReference(generateChapterContext(), chapterArticle);
    }

    protected static Reference generateDegreeBachelor() throws InvalidIsbnException, MalformedURLException {
        PublicationInstance<MonographPages> degreeBachelor = new DegreeBachelor.Builder()
            .withPages(generateMonographPages())
            .withSubmittedDate(generatePublicationDate())
            .build();
        return generateReference(generateDegreeContext(), degreeBachelor);
    }

    protected static Reference generateDegreeMaster() throws InvalidIsbnException, MalformedURLException {
        PublicationInstance<MonographPages> degreeMaster = new DegreeMaster.Builder()
            .withPages(generateMonographPages())
            .withSubmittedDate(generatePublicationDate())
            .build();
        return generateReference(generateDegreeContext(), degreeMaster);
    }

    protected static Reference generateDegreePhd() throws InvalidIsbnException, MalformedURLException {
        PublicationInstance<MonographPages> degreePhd = new DegreePhd.Builder()
            .withPages(generateMonographPages())
            .withSubmittedDate(generatePublicationDate())
            .build();
        return generateReference(generateDegreeContext(), degreePhd);
    }

    protected static Reference generateOtherStudentWork() throws MalformedURLException, InvalidIsbnException {
        PublicationInstance<MonographPages> otherStudentWork = new OtherStudentWork.Builder()
            .withPages(generateMonographPages())
            .withSubmittedDate(generatePublicationDate())
            .build();
        return generateReference(generateDegreeContext(), otherStudentWork);
    }

    protected static Reference generateFeatureArticle() throws InvalidIssnException, MalformedURLException {
        PublicationInstance<Range> featureArticle = new FeatureArticle.Builder()
            .withArticleNumber("4321")
            .withIssue("1")
            .withVolume("27")
            .withPages(generateRange())
            .build();
        return generateReference(generateJournalContext(), featureArticle);
    }

    protected static Reference generateJournalArticle() throws InvalidIssnException, MalformedURLException {
        PublicationInstance<Range> journalArticle = new JournalArticle.Builder()
            .withArticleNumber("123321")
            .withIssue("1")
            .withVolume("27")
            .withPages(generateRange())
            .withPeerReviewed(true)
            .build();
        return generateReference(generateJournalContext(), journalArticle);
    }

    protected static Reference generateJournalCorrigendum() throws InvalidIssnException, MalformedURLException {
        PublicationInstance<Range> journalCorrigendum = new JournalCorrigendum.Builder()
            .withArticleNumber("42311")
            .withIssue("5")
            .withVolume("2")
            .withPages(generateRange())
            .withCorrigendumFor(URI.create("https://example.org/article/42310"))
            .build();
        return generateReference(generateJournalContext(), journalCorrigendum);
    }


    protected Reference generateJournalInterview() throws InvalidIssnException, MalformedURLException {
        PublicationInstance<Range> journalInterview = new JournalInterview.Builder()
                .withArticleNumber("3126")
                .withIssue("22")
                .withVolume("200")
                .withPages(generateRange())
                .build();
        return generateReference(generateJournalContext(), journalInterview);
    }

    protected static Reference generateJournalLeader() throws InvalidIssnException, MalformedURLException {
        PublicationInstance<Range> journalLeader = new JournalLeader.Builder()
            .withArticleNumber("8764")
            .withIssue("22")
            .withVolume("9")
            .withPages(generateRange())
            .build();
        return generateReference(generateJournalContext(), journalLeader);
    }

    protected static Reference generateJournalLetter() throws InvalidIssnException, MalformedURLException {
        PublicationInstance<Range> journalLetter = new JournalLetter.Builder()
            .withArticleNumber("8764")
            .withIssue("22")
            .withVolume("9")
            .withPages(generateRange())
            .build();
        return generateReference(generateJournalContext(), journalLetter);
    }

    protected static Reference generateJournalReview() throws InvalidIssnException, MalformedURLException {
        PublicationInstance<Range> journalReview = new JournalReview.Builder()
            .withArticleNumber("75492")
            .withIssue("27")
            .withVolume("12")
            .withPages(generateRange())
            .build();
        return generateReference(generateJournalContext(), journalReview);
    }

    protected static Reference generateJournalShortCommunication() throws InvalidIssnException, MalformedURLException {
        PublicationInstance<Range> journalShortCommunication = new JournalShortCommunication.Builder()
            .withArticleNumber("311")
            .withIssue("1")
            .withVolume("2")
            .withPages(generateRange())
            .build();
        return generateReference(generateJournalContext(), journalShortCommunication);
    }

    protected static Reference generateMusicNotation() throws InvalidIsmnException {
        PublicationInstance<Range> musicNotation = new MusicNotation.Builder()
            .withPages(generateRange())
            .withIsmn("979-0-9016791-7-7")
            .build();
        return generateReference(generateMusicalContentContext(), musicNotation);
    }

    protected static Reference generateReportPolicy()
        throws InvalidIssnException, InvalidIsbnException, MalformedURLException {
        PublicationInstance<MonographPages> reportPolicy = new ReportPolicy.Builder()
            .withPages(generateMonographPages())
            .build();
        return generateReference(generateReportContext(), reportPolicy);
    }

    protected Reference generateReportBasic() throws InvalidIssnException, InvalidIsbnException, MalformedURLException {
        PublicationInstance<MonographPages> reportPolicy = new ReportBasic.Builder()
            .withPages(generateMonographPages())
            .build();
        return generateReference(generateReportContext(), reportPolicy);
    }

    protected static Reference generateReportResearch() throws InvalidIssnException,
                                                               InvalidIsbnException, MalformedURLException {
        PublicationInstance<MonographPages> reportResearch = new ReportResearch.Builder()
            .withPages(generateMonographPages())
            .build();
        return generateReference(generateReportContext(), reportResearch);
    }

    protected static Reference generateReportWorkingPaper() throws InvalidIssnException,
                                                                   InvalidIsbnException, MalformedURLException {
        PublicationInstance<MonographPages> reportWorkingPaper = new ReportWorkingPaper.Builder()
            .withPages(generateMonographPages())
            .build();
        return generateReference(generateReportContext(), reportWorkingPaper);
    }

    private static PublicationContext generateReportContext()
        throws InvalidIssnException, InvalidIsbnException, MalformedURLException {
        return new Report.Builder()
            .withOnlineIssn("1111-1119")
            .withPrintIssn("2222-2227")
            .withIsbnList(List.of("9780201309515"))
            .withPublisher("People's Socialist Republic of South Lakeland")
            .withSeriesNumber("58100117")
            .withSeriesTitle("Report of the people's agricultural commission on ovine husbandry")
            .build();
    }

    private static PublicationContext generateJournalContext() throws InvalidIssnException, MalformedURLException {
        return new Journal.Builder()
            .withOnlineIssn("1111-1119")
            .withPrintIssn("2222-2227")
            .withTitle("The journal of mechanically separated meats")
            .build();
    }

    private static PublicationContext generateMusicalContentContext() {
        return new MusicalContent.Builder()
            .withPartOf(SAMPLE_PART_OF)
            .build();
    }

    private static PublicationContext generateDegreeContext() throws InvalidIsbnException, MalformedURLException {
        return new Degree.Builder()
            .withPublisher("Some university publisher")
            .withSeriesNumber("8")
            .withSeriesTitle("Degrees of this type series")
            .withIsbnList(List.of("9780201309515"))
            .build();
    }

    private static Partitive generateChapterContext() {
        return new Chapter.Builder()
            .withPartOf(SAMPLE_PART_OF)
            .build();
    }

    private static Partitive generateCartographicContext() {
        return new Cartograph.Builder()
            .withPartOf(SAMPLE_PART_OF)
            .build();
    }

    private static Reference generateReference(PublicationContext publicationContext,
                                               PublicationInstance<? extends Pages> publicationInstance) {
        return new Reference.Builder()
            .withDoi(URI.create("https://example.org/doi/12313/2313"))
            .withPublicationInstance(publicationInstance)
            .withPublishingContext(publicationContext)
            .build();
    }

    private static Book generateBookContext() throws InvalidIsbnException, MalformedURLException {
        return new Book.Builder()
            .withIsbnList(List.of("9780201309515"))
            .withPublisher("Organic publishing AS")
            .withSeriesNumber("1")
            .withSeriesTitle("Bloop and how to grow 'em")
            .build();
    }

    protected DoiRequest generateDoiRequest(Instant now) {
        return new DoiRequest.Builder()
            .withCreatedDate(now)
            .withModifiedDate(now)
            .withStatus(DoiRequestStatus.APPROVED)
            .build();
    }

    protected static EntityDescription generateEntityDescription(Reference reference) throws
                                                                                      MalformedContributorException {
        return new EntityDescription.Builder()
            .withMainTitle("Hovedtittelen")
            .withLanguage(URI.create("http://example.org/norsk"))
            .withAlternativeTitles(Collections.singletonMap("en", "English title"))
            .withDate(generatePublicationDate())
            .withContributors(Collections.singletonList(generateContributor()))
            .withAbstract("En lang streng som beskriver innholdet i dokumentet metadataene omtaler.")
            .withNpiSubjectHeading("010")
            .withTags(Arrays.asList("dokumenter", "publikasjoner"))
            .withDescription("En streng som beskriver innholdet i dokumentet på en annen måte enn abstrakt")
            .withReference(reference)
            .withMetadataSource(URI.create("https://example.org/doi?doi=123/123"))
            .build();
    }

    protected static Contributor generateContributor() throws MalformedContributorException {
        return new Contributor.Builder()
            .withSequence(0)
            .withRole(Role.CREATOR)
            .withAffiliations(Collections.singletonList(generateOrganization()))
            .withIdentity(generateIdentity())
            .withCorrespondingAuthor(true)
            .withEmail(EXAMPLE_EMAIL)
            .build();
    }

    protected static Identity generateIdentity() {
        return new Identity.Builder()
            .withId(URI.create("http://example.org/person/123"))
            .withArpId("arp123")
            .withOrcId("orc123")
            .withName("Navnesen, Navn")
            .withNameType(NameType.PERSONAL)
            .build();
    }

    protected static PublicationDate generatePublicationDate() {
        return new PublicationDate.Builder()
            .withYear("2020")
            .withMonth("4")
            .withDay("7")
            .build();
    }

    protected static List<ResearchProject> generateProject() {
        return List.of(new ResearchProject.Builder()
            .withId(URI.create("http://link.to.cristin.example.org/123"))
            .withName("Det gode prosjektet")
            .withApprovals(generateApprovals())
            .withGrants(generateGrants())
            .build());
    }

    protected static List<Grant> generateGrants() {
        return Collections.singletonList(new Grant.Builder()
            .withId("123123")
            .withSource("Norsk rødt felaget")
            .build());
    }

    protected static List<Approval> generateApprovals() {
        return Collections.singletonList(new Approval.Builder()
            .withApplicationCode("123123")
            .withApprovedBy(ApprovalsBody.REK)
            .withDate(Instant.now())
            .withApprovalStatus(ApprovalStatus.APPLIED)
            .build());
    }

    protected static FileSet generateFileSet() {
        return new FileSet.Builder()
            .withFiles(Collections.singletonList(generateFile()))
            .build();
    }

    protected static File generateFile() {
        return new File.Builder()
            .withIdentifier(UUID.randomUUID())
            .withMimeType("application/pdf")
            .withSize(2L)
            .withName("new document(1)")
            .withLicense(generateLicense())
            .withAdministrativeAgreement(true)
            .withPublisherAuthority(true)
            .withEmbargoDate(Instant.now())
            .build();
    }

    protected static License generateLicense() {
        return new License.Builder()
            .withIdentifier("NTNU-CC-BY-4.0")
            .withLink(URI.create("http://example.org/license/123"))
            .withLabels(Collections.singletonMap("no", "CC-BY 4.0"))
            .build();
    }

    protected static Organization generateOrganization() {
        return new Organization.Builder()
            .withId(URI.create("http://example.org/org/123"))
            .withLabels(Collections.singletonMap("no", "Eksempelforlaget"))
            .build();
    }

    protected static String generatePublicationJson(String type,
                                                    String seriesTitle,
                                                    String seriesNumber,
                                                    String publisher,
                                                    List<String> isbnList,
                                                    String onlineIssn,
                                                    String printIssn,
                                                    URI partOf) {

        ObjectNode jsonNode = JsonUtils.objectMapper.createObjectNode();
        jsonNode.put(TYPE, type);

        jsonNode.put(SERIES_TITLE, seriesTitle);
        jsonNode.put(SERIES_NUMBER, seriesNumber);
        jsonNode.put(PUBLISHER, publisher);
        jsonNode.set(ISBN_LIST, arrayNode(isbnList));
        if (nonNull(partOf)) {
            jsonNode.put(PART_OF, partOf.toString());
        }
        jsonNode.put(PRINT_ISSN, printIssn);
        jsonNode.put(ONLINE_ISSN, onlineIssn);

        return attempt(() -> JsonUtils.objectMapper.writeValueAsString(jsonNode))
            .map(content -> JsonUtils.objectMapper.readValue(content, Map.class))
            .map(JsonUtils.objectMapper::writeValueAsString)
            .orElseThrow();
    }

    private static JsonNode arrayNode(List<String> isbnList) {
        if (isNull(isbnList)) {
            return JsonUtils.objectMapper.nullNode();
        }
        ArrayNode arrayNode = JsonUtils.objectMapper.createArrayNode();
        isbnList.forEach(arrayNode::add);
        return arrayNode;
    }
}
