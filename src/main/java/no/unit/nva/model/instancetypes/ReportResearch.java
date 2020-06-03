package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.exceptions.InvalidPageTypeException;
import no.unit.nva.model.pages.Pages;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class ReportResearch extends ReportContent {

    /**
     * This constructor ensures that the peerReviewed value is always false.
     *
     * @param pages         the Pages of the PublicationInstance.
     * @param peerReviewed  the value is always ignored.
     * @throws InvalidPageTypeException if the type of Pages is incompatible with the PublicationInstance type.
     */
    @JsonCreator
    public ReportResearch(
            @JsonProperty("pages") Pages pages,
            @JsonProperty("peerReviewed") boolean peerReviewed
    ) throws InvalidPageTypeException {
        super(pages, peerReviewed);
    }

    public static final class Builder {
        private Pages pages;

        public Builder() {
        }

        public ReportResearch.Builder withPages(Pages pages) {
            this.pages = pages;
            return this;
        }

        public ReportResearch build() throws InvalidPageTypeException {
            return new ReportResearch(this.pages, false);
        }
    }
}