package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.pages.MonographPages;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class ReportWorkingPaper extends ReportContent {

    /**
     * Constructor for ReportWorkingPaper.
     *
     * @param pages the Pages of the PublicationInstance.
     */
    @JsonCreator
    public ReportWorkingPaper(@JsonProperty("pages") MonographPages pages) {
        super(pages);
    }

    public static final class Builder {
        private MonographPages pages;

        public Builder() {
        }

        public ReportWorkingPaper.Builder withPages(MonographPages pages) {
            this.pages = pages;
            return this;
        }

        public ReportWorkingPaper build() {
            return new ReportWorkingPaper(this.pages);
        }
    }
}
