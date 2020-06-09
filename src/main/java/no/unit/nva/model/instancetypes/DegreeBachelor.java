package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.pages.MonographPages;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class DegreeBachelor extends NonPeerReviewedMonograph {

    public DegreeBachelor(@JsonProperty("pages") MonographPages pages) {
        super(pages);
    }

    private DegreeBachelor(Builder builder) {
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

        public DegreeBachelor build() {
            return new DegreeBachelor(this);
        }
    }
}
