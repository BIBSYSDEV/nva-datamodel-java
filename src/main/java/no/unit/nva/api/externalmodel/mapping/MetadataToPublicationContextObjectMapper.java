package no.unit.nva.api.externalmodel.mapping;

import no.unit.nva.api.externalmodel.Metadata;
import no.unit.nva.api.externalmodel.SeriesDto;
import no.unit.nva.model.Level;
import no.unit.nva.model.contexttypes.Book;
import no.unit.nva.model.contexttypes.Chapter;
import no.unit.nva.model.contexttypes.Degree;
import no.unit.nva.model.contexttypes.Journal;
import no.unit.nva.model.contexttypes.PublicationContext;
import no.unit.nva.model.contexttypes.Report;
import no.unit.nva.model.exceptions.InvalidIsbnException;
import no.unit.nva.model.exceptions.InvalidIssnException;

import java.net.URI;
import java.net.URL;
import java.util.List;

import static java.util.Objects.nonNull;

public class MetadataToPublicationContextObjectMapper {

    protected static PublicationContext getPublicationContext(String type, Metadata metadata)
            throws InvalidIsbnException, InvalidIssnException {
        switch (type) {
            case "BookAnthology":
            case "BookMonograph":
                return getPublicationContextForBook(metadata);
            case "CartographicMap": // unsupported, as yet this does nothing
                break;
            case "ChapterArticle":
            case "MusicNotation":
                return getPublicationContextForLinkedContext(metadata);
            case "DegreeBachelor":
            case "DegreeMaster":
            case "DegreePhd":
            case "OtherStudentWork":
                return getPublicationContextForDegree(metadata);
            case "FeatureArticle":
            case "JournalArticle":
            case "JournalCorrigendum":
            case "JournalLeader":
            case "JournalLetter":
            case "JournalReview":
            case "JournalShortCommunication":
                return getPublicationContextForJournal(metadata);
            case "ReportPolicy":
            case "ReportResearch":
            case "ReportWorkingPaper":
                return getPublicationContextForReport(metadata);
            default:
                throw new RuntimeException("Unknown instance type: " + type);
        }
        return null;
    }

    private static Report getPublicationContextForReport(Metadata metadata)
            throws InvalidIssnException, InvalidIsbnException {
        return new Report.Builder()
                .withIsbnList(extractIsbn(metadata))
                .withLevel(extractLevel(metadata))
                .withOpenAccess(extractOpenAccess(metadata))
                .withPeerReviewed(extractPeerReviewed(metadata))
                .withPublisher(extractPublisherName(metadata))
                .withSeriesNumber(extractSeriesNumber(metadata))
                .withSeriesTitle(extractSeriesTitle(metadata))
                .withUrl(extractPublisherUrl(metadata))
                .withOnlineIssn(extractOnlineIssn(metadata))
                .withPrintIssn(extractPrintIssn(metadata))
                .build();
    }


    private static Journal getPublicationContextForJournal(Metadata metadata) throws InvalidIssnException {
        return new Journal.Builder()
                .withLevel(extractLevel(metadata))
                .withOnlineIssn(extractOnlineIssn(metadata))
                .withOpenAccess(extractOpenAccess(metadata))
                .withPeerReviewed(extractPeerReviewed(metadata))
                .withPrintIssn(extractPrintIssn(metadata))
                .withTitle(extractPublisherName(metadata))
                .withUrl(extractPublisherUrl(metadata))
                .build();
    }

    private static String extractSeriesNumber(Metadata metadata) {
        var series = extractSeries(metadata);
        return nonNull(series) ? series.getNumber() : null;
    }

    private static String extractSeriesTitle(Metadata metadata) {
        var series = extractSeries(metadata);
        return nonNull(series) ? series.getTitle() : null;
    }

    private static String extractPrintIssn(Metadata metadata) {
        return metadata.getReference().getPublisher().getPrintIssn();
    }

    private static String extractOnlineIssn(Metadata metadata) {
        return metadata.getReference().getPublisher().getOnlineIssn();
    }

    private static Degree getPublicationContextForDegree(Metadata metadata) throws InvalidIsbnException {
        return new Degree.Builder()
                .withPublisher(extractPublisherName(metadata))
                .withSeriesNumber(extractSeriesNumber(metadata))
                .withSeriesTitle(extractSeriesTitle(metadata))
                .withUrl(extractPublisherUrl(metadata))
                .withIsbnList(extractIsbn(metadata))
                .build();
    }

    private static Chapter getPublicationContextForLinkedContext(Metadata metadata) {
        return new Chapter.Builder()
                .withLinkedContext(extractLinkedContext(metadata))
                .build();
    }

    private static URI extractLinkedContext(Metadata metadata) {
        return metadata.getReference().getLinkedContext();
    }

    private static PublicationContext getPublicationContextForBook(Metadata metadata) throws InvalidIsbnException {
        return new Book.Builder()
                .withLevel(extractLevel(metadata))
                .withOpenAccess(extractOpenAccess(metadata))
                .withPeerReviewed(extractPeerReviewed(metadata))
                .withPublisher(extractPublisherName(metadata))
                .withSeriesNumber(extractSeriesNumber(metadata))
                .withSeriesTitle(extractSeriesTitle(metadata))
                .withUrl(extractPublisherUrl(metadata))
                .withIsbnList(extractIsbn(metadata))
                .build();
    }

    private static boolean extractPeerReviewed(Metadata metadata) {
        return metadata.getReference().getPublisher().isPeerReviewed();
    }

    private static SeriesDto extractSeries(Metadata metadata) {
        return metadata.getReference().getSeries();
    }

    private static List<String> extractIsbn(Metadata metadata) {
        return metadata.getReference().getIsbn();
    }

    private static URL extractPublisherUrl(Metadata metadata) {
        return metadata.getReference().getPublisher().getUrl();
    }

    private static String extractPublisherName(Metadata metadata) {
        return metadata.getReference().getPublisher().getName();
    }

    private static boolean extractOpenAccess(Metadata metadata) {
        return metadata.getReference().getPublisher().isOpenAccess();
    }

    private static Level extractLevel(Metadata metadata) {
        return metadata.getReference().getPublisher().getLevel();
    }

}
