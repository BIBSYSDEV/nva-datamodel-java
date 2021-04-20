package no.unit.nva.model.instancetypes.report;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.instancetypes.NonPeerReviewedMonograph;
import no.unit.nva.model.pages.MonographPages;
import nva.commons.core.JacocoGenerated;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class ReportBasic extends NonPeerReviewedMonograph {

    @JacocoGenerated
    public ReportBasic() {
        super();
    }

    private ReportBasic(Builder builder) {
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

        public ReportBasic build() {
            return new ReportBasic(this);
        }
    }

}
