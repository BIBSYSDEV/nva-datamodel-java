package no.unit.nva.api.externalmodel.mapping;

import no.unit.nva.api.externalmodel.Metadata;
import no.unit.nva.model.instancetypes.PublicationInstance;
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
import no.unit.nva.model.instancetypes.journal.JournalLeader;
import no.unit.nva.model.instancetypes.journal.JournalLetter;
import no.unit.nva.model.instancetypes.journal.JournalReview;
import no.unit.nva.model.instancetypes.journal.JournalShortCommunication;
import no.unit.nva.model.instancetypes.musicalcontent.MusicNotation;
import no.unit.nva.model.instancetypes.musicalcontent.exception.InvalidIsmnException;
import no.unit.nva.model.instancetypes.report.ReportPolicy;
import no.unit.nva.model.instancetypes.report.ReportResearch;
import no.unit.nva.model.instancetypes.report.ReportWorkingPaper;
import no.unit.nva.model.pages.MonographPages;
import no.unit.nva.model.pages.Pages;
import no.unit.nva.model.pages.Range;

@SuppressWarnings("PMD.CouplingBetweenObjects")
public class MetadataToPublicationInstanceMapperUtil {

    protected static PublicationInstance<?> getPublicationInstanceByName(String type, Metadata metadata)
            throws InvalidIsmnException {
        switch (type) {
            case "BookAnthology":
                return createPublicationInstanceBookAnthology(metadata);
            case "BookMonograph":
                return createPublicationInstanceBookMonograph(metadata);
            case "CartographicMap":
                // NO-OP
                break;
            case "ChapterArticle":
                return createPublicationInstanceChapterArticle(metadata);
            case "DegreeBachelor":
                return createPublicationInstanceDegreeBachelor(metadata);
            case "DegreeMaster":
                return createPublicationInstanceDegreeMaster(metadata);
            case "DegreePhd":
                return createPublicationInstanceDegreePhd(metadata);
            case "FeatureArticle":
                return createPublicationInstanceFeatureArticle(metadata);
            case "JournalArticle":
                return createPublicationInstanceJournalArticle(metadata);
            case "JournalCorrigendum":
                return createPublicationInstanceJournalCorrigendum(metadata);
            case "JournalLeader":
                return createPublicationInstanceJournalLeader(metadata);
            case "JournalLetter":
                return createPublicationInstanceJournalLetter(metadata);
            case "JournalReview":
                return createPublicationInstanceJournalReview(metadata);
            case "JournalShortCommunication":
                return createPublicationInstanceJournalShortCommunication(metadata);
            case "MusicNotation":
                return createPublicationInstanceMusicNotation(metadata);
            case "OtherStudentWork":
                return createPublicationInstanceOtherStudentWork(metadata);
            case "ReportPolicy":
                return createPublicationInstanceReportPolicy(metadata);
            case "ReportResearch":
                return createPublicationInstanceReportResearch(metadata);
            case "ReportWorkingPaper":
                return createPublicationInstanceReportWorkingPaper(metadata);
            default:
                throw new RuntimeException("Unknown instance type: " + type);
        }
        return null;
    }

    private static ReportWorkingPaper createPublicationInstanceReportWorkingPaper(Metadata metadata) {
        return new ReportWorkingPaper.Builder()
                .withPages((MonographPages) extractPages(metadata))
                .build();
    }

    private static ReportResearch createPublicationInstanceReportResearch(Metadata metadata) {
        return new ReportResearch.Builder()
                .withPages((MonographPages) extractPages(metadata))
                .build();
    }

    private static ReportPolicy createPublicationInstanceReportPolicy(Metadata metadata) {
        return new ReportPolicy.Builder()
                .withPages((MonographPages) extractPages(metadata))
                .build();
    }

    private static OtherStudentWork createPublicationInstanceOtherStudentWork(Metadata metadata) {
        return new OtherStudentWork.Builder()
                .withPages((MonographPages) extractPages(metadata))
                .build();
    }

    private static JournalReview createPublicationInstanceJournalReview(Metadata metadata) {
        return new JournalReview.Builder()
                .withArticleNumber(extractArticleNumber(metadata))
                .withIssue(extractIssue(metadata))
                .withPages((Range) extractPages(metadata))
                .withVolume(extractVolume(metadata))
                .build();
    }

    private static MusicNotation createPublicationInstanceMusicNotation(Metadata metadata) throws InvalidIsmnException {
        return new MusicNotation.Builder()
                .withIsmn(extractIsmn(metadata))
                .withPages((Range) extractPages(metadata))
                .build();
    }

    private static JournalShortCommunication createPublicationInstanceJournalShortCommunication(Metadata metadata) {
        return new JournalShortCommunication.Builder()
                .withArticleNumber(extractArticleNumber(metadata))
                .withIssue(extractIssue(metadata))
                .withPages((Range) extractPages(metadata))
                .withVolume(extractVolume(metadata))
                .build();
    }

    private static JournalLetter createPublicationInstanceJournalLetter(Metadata metadata) {
        return new JournalLetter.Builder()
                .withArticleNumber(extractArticleNumber(metadata))
                .withIssue(extractIssue(metadata))
                .withPages((Range) extractPages(metadata))
                .withVolume(extractVolume(metadata))
                .build();
    }

    private static JournalLeader createPublicationInstanceJournalLeader(Metadata metadata) {
        return new JournalLeader.Builder()
                .withArticleNumber(extractArticleNumber(metadata))
                .withIssue(extractIssue(metadata))
                .withPages((Range) extractPages(metadata))
                .withVolume(extractVolume(metadata))
                .build();
    }

    private static JournalCorrigendum createPublicationInstanceJournalCorrigendum(Metadata metadata) {
        return new JournalCorrigendum.Builder()
                .withArticleNumber(extractArticleNumber(metadata))
                .withIssue(extractIssue(metadata))
                .withPages((Range) extractPages(metadata))
                .withVolume(extractVolume(metadata))
                .build();
    }

    private static JournalArticle createPublicationInstanceJournalArticle(Metadata metadata) {
        return new JournalArticle.Builder()
                .withArticleNumber(extractArticleNumber(metadata))
                .withIssue(extractIssue(metadata))
                .withPages((Range) extractPages(metadata))
                .withPeerReviewed(extractPeerReviewed(metadata))
                .withVolume(extractVolume(metadata))
                .build();
    }

    private static boolean extractPeerReviewed(Metadata metadata) {
        return metadata.getReference().isPeerReviewed();
    }

    private static FeatureArticle createPublicationInstanceFeatureArticle(Metadata metadata) {
        return new FeatureArticle.Builder()
                .withArticleNumber(extractArticleNumber(metadata))
                .withIssue(extractIssue(metadata))
                .withPages((Range) extractPages(metadata))
                .withVolume(extractVolume(metadata))
                .build();
    }

    private static DegreePhd createPublicationInstanceDegreePhd(Metadata metadata) {
        return new DegreePhd.Builder()
                .withPages((MonographPages) extractPages(metadata))
                .build();
    }

    private static DegreeMaster createPublicationInstanceDegreeMaster(Metadata metadata) {
        return new DegreeMaster.Builder()
                .withPages((MonographPages) extractPages(metadata))
                .build();
    }

    private static DegreeBachelor createPublicationInstanceDegreeBachelor(Metadata metadata) {
        return new DegreeBachelor.Builder()
                .withPages((MonographPages) extractPages(metadata))
                .build();
    }

    private static ChapterArticle createPublicationInstanceChapterArticle(Metadata metadata) {
        return new ChapterArticle.Builder()
                .withTextbookContent(extractTextbook(metadata))
                .withPages((Range) extractPages(metadata))
                .withPeerReviewed(extractPeerReviewed(metadata))
                .build();
    }

    private static BookMonograph createPublicationInstanceBookMonograph(Metadata metadata) {
        return new BookMonograph.Builder()
                .withPages((MonographPages) extractPages(metadata))
                .withPeerReviewed(extractPeerReviewed(metadata))
                .withTextbookContent(extractTextbook(metadata))
                .build();
    }

    private static BookAnthology createPublicationInstanceBookAnthology(Metadata metadata) {
        return new BookAnthology.Builder()
                .withPages((MonographPages) extractPages(metadata))
                .withPeerReviewed(extractPeerReviewed(metadata))
                .build();
    }

    private static String extractIsmn(Metadata metadata) {
        return metadata.getReference().getIsmn();
    }

    private static String extractVolume(Metadata metadata) {
        return metadata.getReference().getVolume();
    }

    private static String extractIssue(Metadata metadata) {
        return metadata.getReference().getIssue();
    }

    private static String extractArticleNumber(Metadata metadata) {
        return metadata.getReference().getArticleNumber();
    }

    private static boolean extractTextbook(Metadata metadata) {
        return metadata.isTextbook();
    }

    private static Pages extractPages(Metadata metadata) {
        return metadata.getReference().getPages();
    }
}
