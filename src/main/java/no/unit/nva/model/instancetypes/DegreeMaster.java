package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.pages.MonographPages;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class DegreeMaster extends Report {
    public DegreeMaster(@JsonProperty("pages") MonographPages pages,
                        @JsonProperty("peerReviewed") boolean peerReviewed) {
        super(pages, peerReviewed);
    }

    private DegreeMaster(Builder builder) {
        super(builder.pages, false);
        setPages(builder.pages);
    }

    public static final class Builder {
        private MonographPages pages;

        public Builder() {
        }

        public Builder withPages(MonographPages pages) {
            this.pages = pages;
            return this;
        }

        public DegreeMaster build() {
            return new DegreeMaster(this);
        }
    }
}
