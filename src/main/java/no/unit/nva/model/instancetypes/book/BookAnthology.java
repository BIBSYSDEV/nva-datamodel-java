package no.unit.nva.model.instancetypes.book;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.instancetypes.PeerReviewedMonograph;
import no.unit.nva.model.pages.MonographPages;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class BookAnthology extends PeerReviewedMonograph {

    public BookAnthology() {
        super();
    }

    private BookAnthology(Builder builder) {
        super(builder.pages, builder.peerReviewed, builder.textbookContent);
    }

    public static final class Builder {
        private boolean peerReviewed;
        private MonographPages pages;
        private boolean textbookContent;

        public Builder() {
        }

        public Builder withPeerReviewed(boolean peerReviewed) {
            this.peerReviewed = peerReviewed;
            return this;
        }

        public Builder withPages(MonographPages pages) {
            this.pages = pages;
            return this;
        }

        public Builder withTextbookContent(boolean textbookContent) {
            this.textbookContent = textbookContent;
            return this;
        }

        public BookAnthology build() {
            return new BookAnthology(this);
        }
    }
}
