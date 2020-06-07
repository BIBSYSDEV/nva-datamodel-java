package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.pages.MonographPages;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class DegreePhd extends Report {
    public DegreePhd(@JsonProperty("pages") MonographPages pages,
                     @JsonProperty("peerReviewed") boolean peerReviewed) {
        super(pages, peerReviewed);
    }

    private DegreePhd(Builder builder) {
        super(builder.pages, false);
    }


    public static final class Builder {
        private MonographPages pages;

        public Builder() {
        }

        public Builder withPages(MonographPages pages) {
            this.pages = pages;
            return this;
        }

        public DegreePhd build() {
            return new DegreePhd(this);
        }
    }
}
