package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.pages.MonographPages;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class ReportPolicy extends Report {

    /**
     * Constructor for ReportPolicy.
     *
     * @param pages the Pages of the PublicationInstance.
     */
    public ReportPolicy(@JsonProperty("pages") MonographPages pages) {
        super(pages);
    }

    private ReportPolicy(Builder builder) {
        this(builder.pages);
    }

    public static final class Builder {
        private MonographPages pages;

        public Builder() {
        }

        public Builder withPages(MonographPages pages) {
            this.pages = pages;
            return this;
        }

        public ReportPolicy build() {
            return new ReportPolicy(this);
        }
    }
}
