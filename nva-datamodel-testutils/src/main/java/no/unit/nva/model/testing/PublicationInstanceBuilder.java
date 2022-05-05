package no.unit.nva.model.testing;

import static no.unit.nva.model.testing.RandomUtils.randomPublicationDate;
import static no.unit.nva.testutils.RandomDataGenerator.randomBoolean;
import static no.unit.nva.testutils.RandomDataGenerator.randomElement;
import static no.unit.nva.testutils.RandomDataGenerator.randomInteger;
import static no.unit.nva.testutils.RandomDataGenerator.randomLocalDateTime;
import static no.unit.nva.testutils.RandomDataGenerator.randomString;
import static no.unit.nva.testutils.RandomDataGenerator.randomUri;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import no.unit.nva.model.contexttypes.Journal;
import no.unit.nva.model.contexttypes.UnconfirmedPublisher;
import no.unit.nva.model.contexttypes.place.UnconfirmedPlace;
import no.unit.nva.model.instancetypes.PublicationInstance;
import no.unit.nva.model.instancetypes.artistic.architecture.Architecture;
import no.unit.nva.model.instancetypes.artistic.architecture.ArchitectureOutput;
import no.unit.nva.model.instancetypes.artistic.architecture.ArchitectureSubtype;
import no.unit.nva.model.instancetypes.artistic.architecture.ArchitectureSubtypeEnum;
import no.unit.nva.model.instancetypes.artistic.architecture.realization.Award;
import no.unit.nva.model.instancetypes.artistic.architecture.realization.Competition;
import no.unit.nva.model.instancetypes.artistic.architecture.realization.Exhibition;
import no.unit.nva.model.instancetypes.artistic.architecture.realization.MentionInPublication;
import no.unit.nva.model.instancetypes.artistic.design.ArtisticDesign;
import no.unit.nva.model.instancetypes.artistic.design.ArtisticDesignSubtype;
import no.unit.nva.model.instancetypes.artistic.design.ArtisticDesignSubtypeEnum;
import no.unit.nva.model.instancetypes.artistic.design.realization.Venue;
import no.unit.nva.model.instancetypes.artistic.film.MovingPicture;
import no.unit.nva.model.instancetypes.artistic.film.MovingPictureSubtype;
import no.unit.nva.model.instancetypes.artistic.film.MovingPictureSubtypeEnum;
import no.unit.nva.model.instancetypes.artistic.film.realization.Broadcast;
import no.unit.nva.model.instancetypes.artistic.film.realization.CinematicRelease;
import no.unit.nva.model.instancetypes.artistic.film.realization.MovingPictureOutput;
import no.unit.nva.model.instancetypes.artistic.film.realization.OtherRelease;
import no.unit.nva.model.instancetypes.artistic.performingarts.PerformingArts;
import no.unit.nva.model.instancetypes.artistic.performingarts.PerformingArtsSubtype;
import no.unit.nva.model.instancetypes.artistic.performingarts.PerformingArtsSubtypeEnum;
import no.unit.nva.model.instancetypes.artistic.performingarts.realization.PerformingArtsOutput;
import no.unit.nva.model.instancetypes.artistic.performingarts.realization.PerformingArtsVenue;
import no.unit.nva.model.instancetypes.book.BookAbstracts;
import no.unit.nva.model.instancetypes.book.BookAnthology;
import no.unit.nva.model.instancetypes.book.BookMonograph;
import no.unit.nva.model.instancetypes.book.BookMonographContentType;
import no.unit.nva.model.instancetypes.chapter.ChapterArticle;
import no.unit.nva.model.instancetypes.chapter.ChapterArticleContentType;
import no.unit.nva.model.instancetypes.degree.DegreeBachelor;
import no.unit.nva.model.instancetypes.degree.DegreeLicentiate;
import no.unit.nva.model.instancetypes.degree.DegreeMaster;
import no.unit.nva.model.instancetypes.degree.DegreePhd;
import no.unit.nva.model.instancetypes.degree.OtherStudentWork;
import no.unit.nva.model.instancetypes.event.ConferenceLecture;
import no.unit.nva.model.instancetypes.event.ConferencePoster;
import no.unit.nva.model.instancetypes.event.Lecture;
import no.unit.nva.model.instancetypes.event.OtherPresentation;
import no.unit.nva.model.instancetypes.journal.ConferenceAbstract;
import no.unit.nva.model.instancetypes.journal.FeatureArticle;
import no.unit.nva.model.instancetypes.journal.JournalArticle;
import no.unit.nva.model.instancetypes.journal.JournalArticleContentType;
import no.unit.nva.model.instancetypes.journal.JournalCorrigendum;
import no.unit.nva.model.instancetypes.journal.JournalInterview;
import no.unit.nva.model.instancetypes.journal.JournalIssue;
import no.unit.nva.model.instancetypes.journal.JournalLeader;
import no.unit.nva.model.instancetypes.journal.JournalLetter;
import no.unit.nva.model.instancetypes.journal.JournalReview;
import no.unit.nva.model.instancetypes.report.ReportBasic;
import no.unit.nva.model.instancetypes.report.ReportBookOfAbstract;
import no.unit.nva.model.instancetypes.report.ReportPolicy;
import no.unit.nva.model.instancetypes.report.ReportResearch;
import no.unit.nva.model.instancetypes.report.ReportWorkingPaper;
import no.unit.nva.model.pages.MonographPages;
import no.unit.nva.model.pages.Pages;
import no.unit.nva.model.pages.Range;
import no.unit.nva.model.time.Instant;
import no.unit.nva.model.time.Period;
import nva.commons.core.JacocoGenerated;

@JacocoGenerated
@SuppressWarnings({"PMD.CouplingBetweenObjects", "PMD.GodClass"})
public class PublicationInstanceBuilder {

    public static final String OTHER = "Other";

    public static PublicationInstance<? extends Pages> randomPublicationInstance() {
        Class<?> randomType = randomPublicationInstanceType();
        return randomPublicationInstance(randomType);
    }

    @SuppressWarnings("PMD.NcssCount")
    public static PublicationInstance<? extends Pages> randomPublicationInstance(Class<?> randomType) {
        var typeName = randomType.getSimpleName();

        switch (typeName) {
            case "PerformingArts":
                return generatePerformingArts();
            case "MovingPicture":
                return generateRandomMotionPicture();
            case "Architecture":
                return generateRandomArchitecture();
            case "ArtisticDesign":
                return generateRandomArtisticDesign();
            case "FeatureArticle":
                return generateFeatureArticle();
            case "JournalCorrigendum":
                return generateJournalCorrigendum();
            case "JournalArticle":
                return generateJournalArticle();
            case "BookAnthology":
                return generateBookAnthology();
            case "ChapterArticle":
                return generateChapterArticle();
            case "JournalInterview":
                return generateJournalInterview();
            case "JournalLetter":
                return generateJournalLetter();
            case "JournalLeader":
                return generateJournalLeader();
            case "JournalReview":
                return generateJournalReview();
            case "BookAbstracts":
                return generateBookAbstracts();
            case "BookMonograph":
                return generateBookMonograph();
            case "DegreeBachelor":
                return generateDegreeBachelor();
            case "DegreeMaster":
                return generateDegreeMaster();
            case "DegreePhd":
                return generateDegreePhd();
            case "DegreeLicentiate":
                return generateDegreeLicentiate();
            case "ReportBasic":
                return generateReportBasic();
            case "ReportPolicy":
                return generateReportPolicy();
            case "ReportResearch":
                return generateReportResearch();
            case "ReportWorkingPaper":
                return generateReportWorkingPaper();
            case "ReportBookOfAbstract":
                return generateReportBookOfAbstract();
            case "OtherStudentWork":
                return generateOtherStudentWork();
            case "ConferenceLecture":
                return generateConferenceLecture();
            case "ConferencePoster":
                return generateConferencePoster();
            case "Lecture":
                return generateLecture();
            case "OtherPresentation":
                return generateOtherPresentation();
            case "JournalIssue":
                return generateJournalIssue();
            case "ConferenceAbstract":
                return generateConferenceAbstract();
            default:
                throw new UnsupportedOperationException("Publication instance not supported: " + typeName);
        }
    }

    public static Class<?> randomPublicationInstanceType() {
        List<Class<?>> publicationInstanceClasses = listPublicationInstanceTypes();
        return randomElement(publicationInstanceClasses);
    }

    public static Stream<Class<?>> journalArticleInstanceTypes() {
        return listPublicationInstanceTypes().stream()
            .map(PublicationInstanceBuilder::getPublicationContext)
            .filter(contextAndInstanceTuple -> contextAndInstanceTuple.getContext() instanceof Journal)
            .map(ContextAndInstanceTuple::getInstanceType);
    }

    public static List<Class<?>> listPublicationInstanceTypes() {
        JsonSubTypes[] annotations = PublicationInstance.class.getAnnotationsByType(JsonSubTypes.class);
        Type[] types = annotations[0].value();
        return Arrays.stream(types).map(Type::value).collect(Collectors.toList());
    }

    private static PublicationInstance<? extends Pages> generateJournalIssue() {
        return new JournalIssue.Builder()
            .withArticleNumber(randomArticleNumber())
            .withIssue(randomIssue())
            .withPages(randomRange())
            .withVolume(randomVolume())
            .build();
    }

    private static PublicationInstance<? extends Pages> generateConferenceAbstract() {
        return new ConferenceAbstract.Builder()
            .withArticleNumber(randomArticleNumber())
            .withIssue(randomIssue())
            .withPages(randomRange())
            .withVolume(randomVolume())
            .build();
    }

    private static ContextAndInstanceTuple getPublicationContext(Class<?> instanceType) {
        var context = PublicationContextBuilder.randomPublicationContext(instanceType);
        return new ContextAndInstanceTuple(context, instanceType);
    }

    private static OtherPresentation generateOtherPresentation() {
        return new OtherPresentation();
    }

    private static Lecture generateLecture() {
        return new Lecture();
    }

    private static ConferencePoster generateConferencePoster() {
        return new ConferencePoster();
    }

    private static ConferenceLecture generateConferenceLecture() {
        return new ConferenceLecture();
    }

    private static OtherStudentWork generateOtherStudentWork() {
        return new OtherStudentWork.Builder()
            .withPages(randomMonographPages())
            .withSubmittedDate(randomPublicationDate())
            .build();
    }

    private static ReportWorkingPaper generateReportWorkingPaper() {
        return new ReportWorkingPaper.Builder()
            .withPages(randomMonographPages())
            .build();
    }

    private static ReportResearch generateReportResearch() {
        return new ReportResearch.Builder()
            .withPages(randomMonographPages())
            .build();
    }

    private static ReportPolicy generateReportPolicy() {
        return new ReportPolicy.Builder()
            .withPages(randomMonographPages())
            .build();
    }

    private static ReportBasic generateReportBasic() {
        return new ReportBasic.Builder()
            .withPages(randomMonographPages())
            .build();
    }

    private static ReportBookOfAbstract generateReportBookOfAbstract() {
        return new ReportBookOfAbstract.Builder()
            .withPages(randomMonographPages())
            .build();
    }

    private static DegreePhd generateDegreePhd() {
        return new DegreePhd.Builder()
            .withPages(randomMonographPages())
            .withSubmittedDate(randomPublicationDate())
            .build();
    }

    private static DegreeLicentiate generateDegreeLicentiate() {
        return new DegreeLicentiate.Builder()
            .withPages(randomMonographPages())
            .withSubmittedDate(randomPublicationDate())
            .build();
    }

    private static DegreeMaster generateDegreeMaster() {
        return new DegreeMaster.Builder()
            .withPages(randomMonographPages())
            .withSubmittedDate(randomPublicationDate())
            .build();
    }

    private static DegreeBachelor generateDegreeBachelor() {
        return new DegreeBachelor.Builder()
            .withSubmittedDate(randomPublicationDate())
            .withPages(randomMonographPages())
            .build();
    }

    private static BookMonograph generateBookMonograph() {
        return new BookMonograph.Builder()
            .withPages(randomMonographPages())
            .withContentType(randomElement(BookMonographContentType.values()))
            .withOriginalResearch(randomBoolean())
            .withPeerReviewed(randomBoolean())
            .build();
    }

    private static BookAbstracts generateBookAbstracts() {
        return new BookAbstracts.Builder()
            .withPages(randomMonographPages())
            .build();
    }

    private static JournalReview generateJournalReview() {
        return new JournalReview.Builder()
            .withArticleNumber(randomArticleNumber())
            .withIssue(randomIssue())
            .withPages(randomRange())
            .withVolume(randomVolume())
            .build();
    }

    private static JournalLeader generateJournalLeader() {
        return new JournalLeader.Builder()
            .withArticleNumber(randomArticleNumber())
            .withIssue(randomIssue())
            .withPages(randomRange())
            .withVolume(randomVolume())
            .build();
    }

    private static JournalLetter generateJournalLetter() {
        return new JournalLetter.Builder()
            .withArticleNumber(randomArticleNumber())
            .withIssue(randomIssue())
            .withPages(randomRange())
            .withVolume(randomVolume())
            .build();
    }

    private static JournalInterview generateJournalInterview() {
        return new JournalInterview.Builder()
            .withArticleNumber(randomArticleNumber())
            .withIssue(randomIssue())
            .withPages(randomRange())
            .withVolume(randomVolume())
            .build();
    }

    private static ChapterArticle generateChapterArticle() {
        return new ChapterArticle.Builder()
            .withPages(randomRange())
            .withPeerReviewed(randomBoolean())
            .withOriginalResearch(randomBoolean())
            .withContentType(randomElement(ChapterArticleContentType.values()))
            .build();
    }

    private static BookAnthology generateBookAnthology() {
        return new BookAnthology.Builder()
            .withPages(randomMonographPages())
            .withPeerReviewed(randomBoolean())
            .build();
    }

    private static MonographPages randomMonographPages() {
        return new MonographPages.Builder()
            .withPages(randomPagesString())
            .withIllustrated(randomBoolean())
            .withIntroduction(randomRange())
            .build();
    }

    private static Range randomRange() {
        return new Range.Builder().withBegin(randomPagesString()).withEnd(randomPagesString()).build();
    }

    private static String randomPagesString() {
        return randomString();
    }

    private static JournalCorrigendum generateJournalCorrigendum() {
        return new JournalCorrigendum.Builder()
            .withArticleNumber(randomArticleNumber())
            .withIssue(randomIssue())
            .withVolume(randomVolume())
            .withPages(randomRange())
            .withCorrigendumFor(randomUri())
            .build();
    }

    private static JournalArticle generateJournalArticle() {
        return new JournalArticle.Builder()
            .withContent(randomContent())
            .withArticleNumber(randomArticleNumber())
            .withPages(randomRange())
            .withIssue(randomIssue())
            .withVolume(randomVolume())
            .withOriginalResearch(randomElement(true, false))
            .withPeerReviewed(randomElement(true, false))
            .build();
    }

    private static String randomArticleNumber() {
        return randomString();
    }

    private static String randomIssue() {
        return randomString();
    }

    private static JournalArticleContentType randomContent() {
        return randomElement(JournalArticleContentType.values());
    }

    private static FeatureArticle generateFeatureArticle() {
        return new FeatureArticle.Builder()
            .withArticleNumber(randomArticleNumber())
            .withIssue(randomIssue())
            .withPages(randomRange())
            .withVolume(randomVolume())
            .build();
    }

    private static String randomVolume() {
        return randomString();
    }

    private static PublicationInstance<? extends Pages> generatePerformingArts() {
        var subtype = randomElement(PerformingArtsSubtypeEnum.values());
        return performingArts(subtype);
    }

    private static PublicationInstance<? extends Pages> generateRandomMotionPicture() {
        var subtype = randomElement(MovingPictureSubtypeEnum.values());
        return movingPicture(subtype);
    }

    private static PublicationInstance<? extends Pages> generateRandomArchitecture() {
        var subtype = randomElement(ArchitectureSubtypeEnum.values());
        return architecture(subtype);
    }

    private static PublicationInstance<? extends Pages> performingArts(PerformingArtsSubtypeEnum subtype) {
        PerformingArtsSubtype performingArtsSubtype = performingArtsSubtype(subtype);
        return new PerformingArts(performingArtsSubtype, randomString(), randomPerformingArtsOutputs());
    }

    private static PerformingArtsSubtype performingArtsSubtype(PerformingArtsSubtypeEnum subtype) {
        return OTHER.equals(subtype.getType())
                   ? PerformingArtsSubtype.createOther(randomString())
                   : PerformingArtsSubtype.create(subtype);
    }

    private static List<PerformingArtsOutput> randomPerformingArtsOutputs() {
        return List.of(performingArtsVenue());
    }

    private static PerformingArtsVenue performingArtsVenue() {
        return new PerformingArtsVenue(randomUnconfirmedPlace(), randomNvaPeriod(), randomInteger());
    }

    private static PublicationInstance<? extends Pages> movingPicture(MovingPictureSubtypeEnum subtype) {
        return new MovingPicture(getMovingPictureSubtype(subtype), randomString(), randomMovingPictureOutputs());
    }

    private static MovingPictureSubtype getMovingPictureSubtype(MovingPictureSubtypeEnum subtype) {
        return OTHER.equals(subtype.getType())
                   ? MovingPictureSubtype.createOther(randomString())
                   : MovingPictureSubtype.create(subtype);
    }

    private static List<MovingPictureOutput> randomMovingPictureOutputs() {
        return List.of(streaming(), cinematicRelease(), otherRelease());
    }

    private static OtherRelease otherRelease() {
        return new OtherRelease(randomString(), randomUnconfirmedPlace(), new UnconfirmedPublisher(randomString()),
                                randomNvaInstant(), randomInteger());
    }

    private static CinematicRelease cinematicRelease() {
        return new CinematicRelease(randomUnconfirmedPlace(), randomNvaInstant(), randomInteger());
    }

    private static Broadcast streaming() {
        return new Broadcast(new UnconfirmedPublisher(randomString()), randomNvaInstant(), randomInteger());
    }

    private static PublicationInstance<? extends Pages> architecture(ArchitectureSubtypeEnum subtype) {
        return new Architecture(architectureSubtype(subtype), randomString(), randomArchitectureOutputs());
    }

    private static ArchitectureSubtype architectureSubtype(ArchitectureSubtypeEnum subtype) {
        return OTHER.equals(subtype.getType())
                   ? ArchitectureSubtype.createOther(randomString())
                   : ArchitectureSubtype.create(subtype);
    }

    private static List<ArchitectureOutput> randomArchitectureOutputs() {
        return List.of(randomCompetition(), randomMentionInPublication(), randomAward(), randomExhibition());
    }

    private static ArchitectureOutput randomAward() {
        return new Award(randomString(), randomString(), randomNvaInstant(), randomInteger(),
                         randomString(), randomInteger());
    }

    private static ArchitectureOutput randomCompetition() {
        return new Competition(randomString(), randomString(), randomNvaInstant(), randomInteger());
    }

    private static Exhibition randomExhibition() {
        return new Exhibition(randomString(), randomUnconfirmedPlace(), randomString(), randomNvaPeriod(),
                              randomString(), randomInteger());
    }

    private static ArchitectureOutput randomMentionInPublication() {
        return new MentionInPublication(randomString(), randomIssue(), randomNvaInstant(),
                                        randomString(), randomInteger());
    }

    private static ArtisticDesign generateRandomArtisticDesign() {
        var subtype = randomElement(ArtisticDesignSubtypeEnum.values());
        return artisticDesign(subtype);
    }

    private static ArtisticDesign artisticDesign(ArtisticDesignSubtypeEnum subtype) {
        return new ArtisticDesign(ArtisticDesignSubtype.create(subtype), randomString(), randomVenues());
    }

    private static List<Venue> randomVenues() {
        return List.of(randomVenue(), randomVenue());
    }

    private static Venue randomVenue() {
        return new Venue(randomUnconfirmedPlace(), randomNvaPeriod(), randomInteger());
    }

    private static UnconfirmedPlace randomUnconfirmedPlace() {
        return new UnconfirmedPlace(randomString(), randomString());
    }

    private static Instant randomNvaInstant() {
        return new Instant(randomLocalDateTime());
    }

    private static Period randomNvaPeriod() {
        var localDateTime = randomLocalDateTime();
        return new Period(localDateTime, randomLocalDateTime(localDateTime));
    }
}
