package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.pages.MonographPages;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class DegreeMaster extends NonPeerReviewedMonograph {

    public DegreeMaster(@JsonProperty("pages") MonographPages pages) {
        super(pages);
    }

    private DegreeMaster(Builder builder) {
        super(builder.pages);
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
