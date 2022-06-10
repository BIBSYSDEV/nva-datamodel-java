package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.instancetypes.artistic.architecture.Architecture;
import no.unit.nva.model.instancetypes.artistic.design.ArtisticDesign;
import no.unit.nva.model.instancetypes.artistic.film.MovingPicture;
import no.unit.nva.model.instancetypes.artistic.music.MusicPerformance;
import no.unit.nva.model.instancetypes.artistic.performingarts.PerformingArts;
import no.unit.nva.model.instancetypes.book.BookAbstracts;
import no.unit.nva.model.instancetypes.book.BookAnthology;
import no.unit.nva.model.instancetypes.book.BookMonograph;
import no.unit.nva.model.instancetypes.chapter.ChapterArticle;
import no.unit.nva.model.instancetypes.chapter.ChapterConferenceAbstract;
import no.unit.nva.model.instancetypes.chapter.ChapterInReport;
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
import no.unit.nva.model.instancetypes.journal.JournalCorrigendum;
import no.unit.nva.model.instancetypes.journal.JournalInterview;
import no.unit.nva.model.instancetypes.journal.JournalIssue;
import no.unit.nva.model.instancetypes.journal.JournalLeader;
import no.unit.nva.model.instancetypes.journal.JournalLetter;
import no.unit.nva.model.instancetypes.journal.JournalReview;
import no.unit.nva.model.instancetypes.media.MediaBlogPost;
import no.unit.nva.model.instancetypes.media.MediaFeatureArticle;
import no.unit.nva.model.instancetypes.media.MediaInterview;
import no.unit.nva.model.instancetypes.media.MediaParticipationInRadioOrTv;
import no.unit.nva.model.instancetypes.media.MediaPodcast;
import no.unit.nva.model.instancetypes.media.MediaReaderOpinion;
import no.unit.nva.model.instancetypes.report.ReportBasic;
import no.unit.nva.model.instancetypes.report.ReportBookOfAbstract;
import no.unit.nva.model.instancetypes.report.ReportPolicy;
import no.unit.nva.model.instancetypes.report.ReportResearch;
import no.unit.nva.model.instancetypes.report.ReportWorkingPaper;
import no.unit.nva.model.pages.Pages;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(name = "Architecture", value = Architecture.class),
    @JsonSubTypes.Type(name = "ArtisticDesign", value = ArtisticDesign.class),
    @JsonSubTypes.Type(name = "FeatureArticle", value = FeatureArticle.class),
    @JsonSubTypes.Type(name = "MovingPicture", value = MovingPicture.class),
    @JsonSubTypes.Type(name = "PerformingArts", value = PerformingArts.class),
    @JsonSubTypes.Type(name = "JournalArticle", value = JournalArticle.class),
    @JsonSubTypes.Type(name = "JournalCorrigendum", value = JournalCorrigendum.class),
    @JsonSubTypes.Type(name = "JournalInterview", value = JournalInterview.class),
    @JsonSubTypes.Type(name = "JournalLetter", value = JournalLetter.class),
    @JsonSubTypes.Type(name = "JournalLeader", value = JournalLeader.class),
    @JsonSubTypes.Type(name = "JournalReview", value = JournalReview.class),
    @JsonSubTypes.Type(name = "BookAbstracts", value = BookAbstracts.class),
    @JsonSubTypes.Type(name = "BookMonograph", value = BookMonograph.class),
    @JsonSubTypes.Type(name = "BookAnthology", value = BookAnthology.class),
    @JsonSubTypes.Type(name = "DegreeBachelor", value = DegreeBachelor.class),
    @JsonSubTypes.Type(name = "DegreeMaster", value = DegreeMaster.class),
    @JsonSubTypes.Type(name = "DegreePhd", value = DegreePhd.class),
    @JsonSubTypes.Type(name = "DegreeLicentiate", value = DegreeLicentiate.class),
    @JsonSubTypes.Type(name = "ReportBasic", value = ReportBasic.class),
    @JsonSubTypes.Type(name = "ReportPolicy", value = ReportPolicy.class),
    @JsonSubTypes.Type(name = "ReportResearch", value = ReportResearch.class),
    @JsonSubTypes.Type(name = "ReportWorkingPaper", value = ReportWorkingPaper.class),
    @JsonSubTypes.Type(name = "ReportBookOfAbstract", value = ReportBookOfAbstract.class),
    @JsonSubTypes.Type(name = "ChapterArticle", value = ChapterArticle.class),
    @JsonSubTypes.Type(name = "ChapterConferenceAbstract", value = ChapterConferenceAbstract.class),
    @JsonSubTypes.Type(name = "ChapterInReport", value = ChapterInReport.class),
    @JsonSubTypes.Type(name = "OtherStudentWork", value = OtherStudentWork.class),
    @JsonSubTypes.Type(name = "ConferenceLecture", value = ConferenceLecture.class),
    @JsonSubTypes.Type(name = "ConferencePoster", value = ConferencePoster.class),
    @JsonSubTypes.Type(name = "Lecture", value = Lecture.class),
    @JsonSubTypes.Type(name = "OtherPresentation", value = OtherPresentation.class),
    @JsonSubTypes.Type(name = "JournalIssue", value = JournalIssue.class),
    @JsonSubTypes.Type(name = "ConferenceAbstract", value = ConferenceAbstract.class),
    @JsonSubTypes.Type(name = "MediaFeatureArticle", value = MediaFeatureArticle.class),
    @JsonSubTypes.Type(name = "MediaBlogPost", value = MediaBlogPost.class),
    @JsonSubTypes.Type(name = "MediaInterview", value = MediaInterview.class),
    @JsonSubTypes.Type(name = "MediaParticipationInRadioOrTv", value = MediaParticipationInRadioOrTv.class),
    @JsonSubTypes.Type(name = "MediaPodcast", value = MediaPodcast.class),
    @JsonSubTypes.Type(name = "MediaReaderOpinion", value = MediaReaderOpinion.class),
    @JsonSubTypes.Type(name = "MusicPerformance", value = MusicPerformance.class)
})
public interface PublicationInstance<P extends Pages> {

    P getPages();

    void setPages(P pages);

    boolean isPeerReviewed();

    @JsonIgnore
    default String getInstanceType() {
        return this.getClass().getSimpleName();
    }
}
