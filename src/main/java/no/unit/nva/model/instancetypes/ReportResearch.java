package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.pages.MonographPages;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class ReportResearch extends NonPeerReviewedMonograph {

    public ReportResearch(@JsonProperty("pages") MonographPages pages) {
        super(pages);
    }

    private ReportResearch(Builder builder) {
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

        public ReportResearch build() {
            return new ReportResearch(this);
        }
    }
}
