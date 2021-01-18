package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.instancetypes.book.BookAbstracts;
import no.unit.nva.model.instancetypes.book.BookAnthology;
import no.unit.nva.model.instancetypes.book.BookMonograph;
import no.unit.nva.model.instancetypes.cartographic.CartographicMap;
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
import no.unit.nva.model.instancetypes.report.ReportBasic;
import no.unit.nva.model.instancetypes.report.ReportPolicy;
import no.unit.nva.model.instancetypes.report.ReportResearch;
import no.unit.nva.model.instancetypes.report.ReportWorkingPaper;
import no.unit.nva.model.pages.Pages;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(name = "FeatureArticle", value = FeatureArticle.class),
        @JsonSubTypes.Type(name = "JournalArticle", value = JournalArticle.class),
        @JsonSubTypes.Type(name = "JournalCorrigendum", value = JournalCorrigendum.class),
        @JsonSubTypes.Type(name = "JournalInterview", value = JournalInterview.class),
        @JsonSubTypes.Type(name = "JournalLetter", value = JournalLetter.class),
        @JsonSubTypes.Type(name = "JournalLeader", value = JournalLeader.class),
        @JsonSubTypes.Type(name = "JournalReview", value = JournalReview.class),
        @JsonSubTypes.Type(name = "JournalShortCommunication", value = JournalShortCommunication.class),
        @JsonSubTypes.Type(name = "BookAbstracts", value = BookAbstracts.class),
        @JsonSubTypes.Type(name = "BookMonograph", value = BookMonograph.class),
        @JsonSubTypes.Type(name = "BookAnthology", value = BookAnthology.class),
        @JsonSubTypes.Type(name = "DegreeBachelor", value = DegreeBachelor.class),
        @JsonSubTypes.Type(name = "DegreeMaster", value = DegreeMaster.class),
        @JsonSubTypes.Type(name = "DegreePhd", value = DegreePhd.class),
        @JsonSubTypes.Type(name = "ReportBasic", value = ReportBasic.class),
        @JsonSubTypes.Type(name = "ReportPolicy", value = ReportPolicy.class),
        @JsonSubTypes.Type(name = "ReportResearch", value = ReportResearch.class),
        @JsonSubTypes.Type(name = "ReportWorkingPaper", value = ReportWorkingPaper.class),
        @JsonSubTypes.Type(name = "ChapterArticle", value = ChapterArticle.class),
        @JsonSubTypes.Type(name = "OtherStudentWork", value = OtherStudentWork.class),
        @JsonSubTypes.Type(name = "CartographicMap", value = CartographicMap.class),
        @JsonSubTypes.Type(name = "MusicNotation", value = MusicNotation.class)
})
public interface PublicationInstance<P extends Pages> {

    P getPages();

    void setPages(P pages);

    boolean isPeerReviewed();

}
