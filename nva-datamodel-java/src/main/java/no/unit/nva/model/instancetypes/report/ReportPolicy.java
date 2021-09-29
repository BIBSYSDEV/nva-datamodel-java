package no.unit.nva.model.instancetypes.report;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.instancetypes.NonPeerReviewedMonograph;
import no.unit.nva.model.pages.MonographPages;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class ReportPolicy extends NonPeerReviewedMonograph {

    public ReportPolicy() {
        super();
    }

    private ReportPolicy(Builder builder) {
        super(builder.pages);
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
