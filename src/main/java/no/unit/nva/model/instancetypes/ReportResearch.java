package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.pages.MonographPages;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class ReportResearch extends Report {

    /**
     * Constructor for ReportResearch.
     *
     * @param pages the Pages of the PublicationInstance.
     */
    @JsonCreator
    public ReportResearch(@JsonProperty("pages") MonographPages pages,
                          @JsonProperty("peerReviewed") boolean peerReviewed) {
        super(pages, false);
        if (peerReviewed) {
            logger.warn(PEER_REVIEWED_FALSE, this.getClass().getSimpleName());
        }
    }

    private ReportResearch(Builder builder) {
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

        public ReportResearch build() {
            return new ReportResearch(this);
        }
    }
}
