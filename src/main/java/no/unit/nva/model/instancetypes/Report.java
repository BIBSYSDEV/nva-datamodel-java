package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.pages.MonographPages;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Report extends ReportContent {

    /**
     * Constructor for Report.
     *
     * @param pages the Pages of the PublicationInstance.
     */
    @JsonCreator
    public Report(@JsonProperty("pages") MonographPages pages) {
        super(pages);
    }

    public static final class Builder {
        private MonographPages pages;

        public Builder() {
        }

        public Report.Builder withPages(MonographPages pages) {
            this.pages = pages;
            return this;
        }

        public Report build() {
            return new Report(this.pages);
        }
    }

}
