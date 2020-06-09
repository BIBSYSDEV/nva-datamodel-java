package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.pages.MonographPages;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class BookAnthology extends PeerReviewedMonograph {

    public BookAnthology(@JsonProperty("pages") MonographPages pages,
                         @JsonProperty("peerReviewed") boolean peerReviewed) {
        super(pages, peerReviewed);
    }

    private BookAnthology(Builder builder) {
        super(builder.pages, builder.peerReviewed);
    }

    public static final class Builder {
        private MonographPages pages;
        private boolean peerReviewed;

        public Builder() {
        }

        public Builder withPages(MonographPages pages) {
            this.pages = pages;
            return this;
        }

        public Builder withPeerReviewed(boolean peerReviewed) {
            this.peerReviewed = peerReviewed;
            return this;
        }

        public BookAnthology build() {
            return new BookAnthology(this);
        }
    }
}
