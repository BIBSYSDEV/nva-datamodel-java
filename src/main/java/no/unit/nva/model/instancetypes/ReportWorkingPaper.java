package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.pages.MonographPages;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class ReportWorkingPaper extends Report {

    /**
     * Constructor for ReportWorkingPaper.
     * @param pages {@link MonographPages} object for page count
     * @param peerReviewed Should always be false, if not is set to false with logger warning.
     */
    @JsonCreator
    public ReportWorkingPaper(@JsonProperty("pages") MonographPages pages,
                              @JsonProperty("peerReviewed") boolean peerReviewed) {
        super(pages, false);
        if (peerReviewed) {
            logger.warn(PEER_REVIEWED_FALSE, this.getClass().getSimpleName());
        }
    }

    private ReportWorkingPaper(Builder builder) {
        this(builder.pages, false);
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
