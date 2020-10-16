package no.unit.nva.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.MalformedURLException;
import java.net.URL;
import no.unit.nva.JsonHandlingTest;
import no.unit.nva.model.contexttypes.Book;
import no.unit.nva.model.contexttypes.Chapter;
import no.unit.nva.model.contexttypes.Degree;
import no.unit.nva.model.contexttypes.Journal;
import no.unit.nva.model.contexttypes.LinkedContext;
import no.unit.nva.model.contexttypes.PublicationContext;
import no.unit.nva.model.contexttypes.Report;
import no.unit.nva.model.exceptions.InvalidIsbnException;
import no.unit.nva.model.exceptions.InvalidIssnException;
import no.unit.nva.model.exceptions.MalformedContributorException;
import no.unit.nva.model.instancetypes.PublicationInstance;
import no.unit.nva.model.instancetypes.book.BookAnthology;
import no.unit.nva.model.instancetypes.book.BookMonograph;
import no.unit.nva.model.instancetypes.chapter.ChapterArticle;
import no.unit.nva.model.instancetypes.degree.DegreeBachelor;
import no.unit.nva.model.instancetypes.degree.DegreeMaster;
import no.unit.nva.model.instancetypes.degree.DegreePhd;
import no.unit.nva.model.instancetypes.journal.JournalArticle;
import no.unit.nva.model.instancetypes.journal.JournalLeader;
import no.unit.nva.model.instancetypes.journal.JournalLetter;
import no.unit.nva.model.instancetypes.journal.JournalReview;
import no.unit.nva.model.instancetypes.journal.JournalShortCommunication;
import no.unit.nva.model.instancetypes.report.ReportPolicy;
import no.unit.nva.model.instancetypes.report.ReportResearch;
import no.unit.nva.model.instancetypes.report.ReportWorkingPaper;
import no.unit.nva.model.pages.MonographPages;
import no.unit.nva.model.pages.Pages;
import no.unit.nva.model.pages.Range;
import nva.commons.utils.JsonUtils;

import java.net.URI;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class ModelTest implements JsonHandlingTest {
    public static final String QUOTE = "\"";
    public static final String EMPTY_STRING = "";
    public static final String COMMA_SPACE = ", ";
    public static final String COMMA = ",";
    public static final String KEY_VALUE_STRING_PAIR_TEMPLATE = "  \"%s\" : \"%s\"";
    public static final String KEY_VALUE_BOOLEAN_PAIR_TEMPLATE = "  \"%s\" : %s";
    public static final String KEY_VALUE_LIST_PAIR_TEMPLATE = "  \"%s\" : [ %s ]";
    public static final String NEWLINE = "\n";
    public static final String PROLOGUE = "{\n";
    public static final String EPILOGUE = "\n}";
    public static final String TYPE = "type";
    public static final String SERIES_TITLE = "seriesTitle";
    public static final String SERIES_NUMBER = "seriesNumber";
    public static final String PUBLISHER = "publisher";
    public static final String LEVEL = "level";
    public static final String OPEN_ACCESS = "openAccess";
    public static final String PEER_REVIEWED = "peerReviewed";
    public static final String ISBN_LIST = "isbnList";
    public static final String ONLINE_ISSN = "onlineIssn";
    public static final String PRINT_ISSN = "printIssn";
    public static final String EMPTY_ISBN_LIST = "  \"" + ISBN_LIST + "\" : [ ]";
    public static final String EXAMPLE_EMAIL = "nn@example.org";

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

    protected static Reference generateChapterArticle() {
        PublicationInstance<Range> chapterArticle = new ChapterArticle.Builder()
                .withPages(generateRange())
                .withPeerReviewed(true)
                .withTextbookContent(true)
                .build();
        return generateReference(generateChapterContext(), chapterArticle);
    }

    protected static Reference generateDegreeBachelor() throws InvalidIsbnException, MalformedURLException {
        PublicationInstance<MonographPages> degreeBachelor = new DegreeBachelor.Builder()
                .withPages(generateMonographPages())
                .build();
        return generateReference(generateDegreeContext(), degreeBachelor);
    }

    protected static Reference generateDegreeMaster() throws InvalidIsbnException, MalformedURLException {
        PublicationInstance<MonographPages> degreeMaster = new DegreeMaster.Builder()
                .withPages(generateMonographPages())
                .build();
        return generateReference(generateDegreeContext(), degreeMaster);
    }

    protected static Reference generateDegreePhd() throws InvalidIsbnException, MalformedURLException {
        PublicationInstance<MonographPages> degreePhd = new DegreePhd.Builder()
                .withPages(generateMonographPages())
                .build();
        return generateReference(generateDegreeContext(), degreePhd);
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

    protected static Reference generateReportPolicy()
        throws InvalidIssnException, InvalidIsbnException, MalformedURLException {
        PublicationInstance<MonographPages> reportPolicy = new ReportPolicy.Builder()
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
                .withPeerReviewed(false)
                .withOpenAccess(true)
                .withLevel(Level.LEVEL_0)
                .withIsbnList(List.of("9780201309515"))
                .withPublisher("People's Socialist Republic of South Lakeland")
                .withSeriesNumber("58100117")
                .withSeriesTitle("Report of the people's agricultural commission on ovine husbandry")
                .withUrl(new URL(("http://example.org/report/1")))
                .build();
    }

    private static PublicationContext generateJournalContext() throws InvalidIssnException, MalformedURLException {
        return new Journal.Builder()
                .withLevel(Level.LEVEL_0)
                .withOnlineIssn("1111-1119")
                .withPrintIssn("2222-2227")
                .withOpenAccess(true)
                .withPeerReviewed(true)
                .withTitle("The journal of mechanically separated meats")
                .withUrl(new URL(("http://example.org/journal/1")))
                .build();
    }

    private static PublicationContext generateDegreeContext() throws InvalidIsbnException, MalformedURLException {
        return new Degree.Builder()
                .withPublisher("Some university publisher")
                .withSeriesNumber("8")
                .withSeriesTitle("Degrees of this type series")
                .withIsbnList(List.of("9780201309515"))
                .withUrl(new URL(("http://example.org/degree/1")))
                .build();
    }

    private static LinkedContext generateChapterContext() {
        return new Chapter.Builder()
                .withLinkedContext(URI.create("https://example.org/linkedContext"))
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
                    .withLevel(Level.LEVEL_0)
                    .withOpenAccess(true)
                    .withPeerReviewed(false)
                    .withPublisher("Organic publishing AS")
                    .withSeriesNumber("1")
                    .withSeriesTitle("Bloop and how to grow 'em")
                    .withUrl(new URL(("http://example.org/book/1")))
                    .build();
    }

    protected DoiRequest generateDoiRequest() {
        Instant now = Instant.now();
        return new DoiRequest.Builder()
                .withDate(now)
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

    protected static ResearchProject generateProject() {
        return new ResearchProject.Builder()
                .withId(URI.create("http://link.to.cristin.example.org/123"))
                .withName("Det gode prosjektet")
                .withApprovals(generateApprovals())
                .withGrants(generateGrants())
                .build();
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
                                                 String level,
                                                 boolean openAccess,
                                                 boolean peerReviewed,
                                                 List<String> isbnList,
                                                 String onlineIssn,
                                                 String printIssn) {

        String processedLevel = isNull(level) || level.isEmpty() ? level : Level.valueOf(level).name();
        List<String> body = new ArrayList<>();
        body.add(generateKeyValuePair(TYPE, type));
        body.add(generateKeyValuePair(SERIES_TITLE, seriesTitle));
        body.add(generateKeyValuePair(SERIES_NUMBER, seriesNumber));
        body.add(generateKeyValuePair(PUBLISHER, publisher));
        body.add(generateKeyValuePair(LEVEL, processedLevel));
        body.add(generateKeyValuePair(OPEN_ACCESS, openAccess));
        body.add(generateKeyValuePair(PEER_REVIEWED, peerReviewed));
        body.add(generateKeyValueListPair(isbnList));
        body.add(generateKeyValuePair(PRINT_ISSN, printIssn));
        body.add(generateKeyValuePair(ONLINE_ISSN, onlineIssn));

        body.removeIf(s -> s.equals(EMPTY_STRING));

        return PROLOGUE
                + String.join(COMMA + NEWLINE, body)
                + EPILOGUE;
    }


    private static String generateKeyValuePair(String key, Object value) {
        if (nonNull(value) && value instanceof String) {
            return String.format(KEY_VALUE_STRING_PAIR_TEMPLATE, key, value);
        }
        if (nonNull(value) && value instanceof Boolean) {
            return String.format(KEY_VALUE_BOOLEAN_PAIR_TEMPLATE, key, value);
        }
        return EMPTY_STRING;
    }

    private static String generateKeyValueListPair(List<String> value) {
        if (nonNull(value) && !value.isEmpty()) {
            String isbnListString = value.stream()
                    .map(isbn -> QUOTE + isbn + QUOTE)
                    .collect(Collectors.joining(COMMA_SPACE));
            return String.format(KEY_VALUE_LIST_PAIR_TEMPLATE, ISBN_LIST, isbnListString);
        }
        return EMPTY_ISBN_LIST;
    }
}
