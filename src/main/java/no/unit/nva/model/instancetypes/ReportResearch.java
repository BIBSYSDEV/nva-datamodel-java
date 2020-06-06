package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.pages.MonographPages;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class ReportResearch extends ReportContent {

    /**
     * Constructor for ReportResearch.
     *
     * @param pages the Pages of the PublicationInstance.
     */
    @JsonCreator
    public ReportResearch(@JsonProperty("pages") MonographPages pages) {
        super(pages);
    }

    public static final class Builder {
        private MonographPages pages;

        public Builder() {
        }

        public ReportResearch.Builder withPages(MonographPages pages) {
            this.pages = pages;
            return this;
        }

        public ReportResearch build() {
            return new ReportResearch(this.pages);
        }
    }
}
