package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.pages.MonographPages;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class ReportWorkingPaper extends NonPeerReviewedMonograph {

    /**
     * Constructor for ReportWorkingPaper.
     * @param pages {@link MonographPages} object for page count
     */
    public ReportWorkingPaper(@JsonProperty("pages") MonographPages pages) {
        super(pages);
    }

    private ReportWorkingPaper(Builder builder) {
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

        public ReportWorkingPaper build() {
            return new ReportWorkingPaper(this);
        }
    }
}
