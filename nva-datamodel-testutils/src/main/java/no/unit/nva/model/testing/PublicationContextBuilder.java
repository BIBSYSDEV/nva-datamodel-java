package no.unit.nva.model.testing;

import static no.unit.nva.model.testing.RandomUtils.randomLabel;
import static no.unit.nva.model.testing.RandomUtils.randomLabels;
import static no.unit.nva.testutils.RandomDataGenerator.randomElement;
import static no.unit.nva.testutils.RandomDataGenerator.randomInstant;
import static no.unit.nva.testutils.RandomDataGenerator.randomIsbn13;
import static no.unit.nva.testutils.RandomDataGenerator.randomString;
import static no.unit.nva.testutils.RandomDataGenerator.randomUri;
import static nva.commons.core.attempt.Try.attempt;
import java.net.URI;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import no.unit.nva.model.Agent;
import no.unit.nva.model.Organization;
import no.unit.nva.model.contexttypes.Artistic;
import no.unit.nva.model.contexttypes.Book;
import no.unit.nva.model.contexttypes.Chapter;
import no.unit.nva.model.contexttypes.Degree;
import no.unit.nva.model.contexttypes.Event;
import no.unit.nva.model.contexttypes.GeographicalContent;
import no.unit.nva.model.contexttypes.Journal;
import no.unit.nva.model.contexttypes.MediaContribution;
import no.unit.nva.model.contexttypes.PublicationContext;
import no.unit.nva.model.contexttypes.Publisher;
import no.unit.nva.model.contexttypes.PublishingHouse;
import no.unit.nva.model.contexttypes.Report;
import no.unit.nva.model.contexttypes.ResearchData;
import no.unit.nva.model.contexttypes.Series;
import no.unit.nva.model.contexttypes.media.MediaFormat;
import no.unit.nva.model.contexttypes.media.MediaSubType;
import no.unit.nva.model.contexttypes.media.MediaSubTypeEnum;
import no.unit.nva.model.contexttypes.media.MediaSubTypeOther;
import no.unit.nva.model.contexttypes.media.SeriesEpisode;
import no.unit.nva.model.contexttypes.place.Place;
import no.unit.nva.model.contexttypes.place.UnconfirmedPlace;
import no.unit.nva.model.exceptions.InvalidIsbnException;
import no.unit.nva.model.exceptions.InvalidIssnException;
import no.unit.nva.model.exceptions.InvalidUnconfirmedSeriesException;
import no.unit.nva.model.time.Period;
import no.unit.nva.model.time.Time;
import nva.commons.core.JacocoGenerated;

@SuppressWarnings("PMD.CouplingBetweenObjects")
@JacocoGenerated
public class PublicationContextBuilder {

    @SuppressWarnings("PMD.NcssCount")
    public static PublicationContext randomPublicationContext(Class<?> publicationInstance) {
        String className = publicationInstance.getSimpleName();
        switch (className) {
            case "Architecture":
            case "ArtisticDesign":
            case "MovingPicture":
            case "PerformingArts":
            case "MusicPerformance":
            case "VisualArts":
            case "LiteraryArts":
                return randomArtistic();
            case "JournalIssue":
            case "ConferenceAbstract":
            case "FeatureArticle":
            case "JournalCorrigendum":
            case "JournalArticle":
            case "JournalInterview":
            case "JournalLetter":
            case "JournalLeader":
            case "JournalReview":
                return randomJournal();
            case "BookAnthology":
            case "BookAbstracts":
            case "BookMonograph":
            case "OtherStudentWork":
                return attempt(PublicationContextBuilder::randomBook).orElseThrow();
            case "DegreeBachelor":
            case "DegreeMaster":
            case "DegreePhd":
            case "DegreeLicentiate":
                return attempt(PublicationContextBuilder::randomDegree).orElseThrow();
            case "ChapterArticle":
            case "ChapterConferenceAbstract":
            case "ChapterInReport":
                return randomChapter();
            case "ReportBasic":
            case "ReportPolicy":
            case "ReportResearch":
            case "ReportWorkingPaper":
            case "ReportBookOfAbstract":
                return attempt(PublicationContextBuilder::randomReport).orElseThrow();
            case "ConferenceLecture":
            case "ConferencePoster":
            case "Lecture":
            case "OtherPresentation":
                return randomPresentation();
            case "MediaFeatureArticle":
            case "MediaBlogPost":
            case "MediaInterview":
            case "MediaParticipationInRadioOrTv":
            case "MediaPodcast":
            case "MediaReaderOpinion":
                return randomMediaContribution();
            case "DataManagementPlan":
            case "DataSet":
                return randomResearchData();
            case "Map":
                return randomGeographicalContent();
            default:
                throw new UnsupportedOperationException("Publication instance not supported: " + className);
        }
    }

    private static GeographicalContent randomGeographicalContent() {
        return new GeographicalContent(randomPublishingHouse());
    }

    private static ResearchData randomResearchData() {
        return new ResearchData(randomPublishingHouse());
    }

    private static MediaContribution randomMediaContribution() {
        return new MediaContribution.Builder()
            .withMedium(generateRandomMedium())
            .withFormat(generateRandomMediaFormat())
            .withDisseminationChannel(randomString())
            .withPartOf(generateRandomSeriesEpisode())
            .build();
    }

    private static SeriesEpisode generateRandomSeriesEpisode() {
        return new SeriesEpisode(randomString(), randomString());
    }

    private static MediaFormat generateRandomMediaFormat() {
        return randomElement(MediaFormat.values());
    }

    private static MediaSubType generateRandomMedium() {
        var type = randomElement(MediaSubTypeEnum.values());
        return MediaSubTypeEnum.OTHER == type
                ? MediaSubTypeOther.createOther(randomString())
                : MediaSubType.create(type);
    }

    private static Degree randomDegree() throws InvalidIsbnException, InvalidUnconfirmedSeriesException {
        return new Degree.Builder()
            .withSeriesNumber(randomSeriesNumber())
            .withSeries(randomBookSeries())
            .withIsbnList(randomIsbnList())
            .withPublisher(randomPublishingHouse())
            .build();
    }

    private static Event randomPresentation() {
        return new Event.Builder()
            .withAgent(randomAgent())
            .withLabel(randomLabel())
            .withPlace(randomPlace())
            .withProduct(randomUri())
            .withTime(randomTime())
            .build();
    }

    private static Time randomTime() {
        Instant instant = randomInstant();
        LocalDateTime from = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

        return Math.random() <= 0.5
                   ? new no.unit.nva.model.time.Instant(from)
                   : randomPeriod(instant, from);
    }

    private static Period randomPeriod(Instant fromInstant, LocalDateTime from) {
        LocalDateTime to = LocalDateTime.ofInstant(randomInstant(fromInstant), ZoneId.systemDefault());
        return new Period(from, to);
    }

    private static Agent randomAgent() {
        return new Organization.Builder()
            .withId(randomUri())
            .withLabels(randomLabels())
            .build();
    }

    private static Report randomReport()
        throws InvalidIssnException, InvalidIsbnException, InvalidUnconfirmedSeriesException {
        return new Report.Builder()
            .withSeriesNumber(randomSeriesNumber())
            .withSeries(randomBookSeries())
            .withIsbnList(randomIsbnList())
            .withPublisher(randomPublishingHouse())
            .build();
    }

    private static String randomSeriesNumber() {
        return randomString();
    }

    private static Chapter randomChapter() {
        return new Chapter.Builder()
            .withPartOf(randomUri())
            .build();
    }

    private static Book randomBook() throws InvalidIsbnException {
        return new Book.BookBuilder()
            .withIsbnList(randomIsbnList())
            .withPublisher(randomPublishingHouse())
            .withSeries(randomBookSeries())
            .withSeriesNumber(randomSeriesNumber())
            .build();
    }

    private static Series randomBookSeries() {
        return new Series(randomPublicationChannelsUri());
    }

    private static PublishingHouse randomPublishingHouse() {
        return new Publisher(randomPublicationChannelsUri());
    }

    private static List<String> randomIsbnList() {
        return List.of(randomIsbn13());
    }

    private static Journal randomJournal() {
        return new Journal(randomPublicationChannelsUri().toString());
    }

    private static URI randomPublicationChannelsUri() {
        return URI.create("https://api.dev.nva.aws.unit.no/publication-channels/" + randomString());
    }

    private static Artistic randomArtistic() {
        return new Artistic();
    }

    private static Place randomPlace() {
        return new UnconfirmedPlace(randomString(), randomString());
    }
}
