package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.pages.MonographPages;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Report extends NonPeerReviewedMonograph {

    /**
     * The constructor allows setting of pages and open access status, and sets peer-reviewed status to false.
     *
     * @param pages A {@link MonographPages} object.
     */
    public Report(@JsonProperty("pages") MonographPages pages) {
        super(pages);
    }

    private Report(Builder builder) {
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

        public Report build() {
            return new Report(this);
        }
    }

}
