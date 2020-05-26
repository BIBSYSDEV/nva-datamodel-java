package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.exceptions.InvalidPageTypeException;
import no.unit.nva.model.pages.Pages;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(name = "JournalArticle", value = JournalArticle.class),
        @JsonSubTypes.Type(name = "JournalLetter", value = JournalLetter.class),
        @JsonSubTypes.Type(name = "JournalLeader", value = JournalLeader.class),
        @JsonSubTypes.Type(name = "JournalReview", value = JournalReview.class),
        @JsonSubTypes.Type(name = "JournalShortCommunication", value = JournalShortCommunication.class),
        @JsonSubTypes.Type(name = "Report", value = Report.class),
        @JsonSubTypes.Type(name = "ReportPolicy", value = ReportPolicy.class),
        @JsonSubTypes.Type(name = "ReportResearch", value = ReportResearch.class),
        @JsonSubTypes.Type(name = "ReportWorkingPaper", value = ReportWorkingPaper.class)
})
public interface PublicationInstance {

    Pages getPages();

    void setPages(Pages pages) throws InvalidPageTypeException;

    void setPeerReviewed(boolean peerReviewed);

    boolean isPeerReviewed();
}
