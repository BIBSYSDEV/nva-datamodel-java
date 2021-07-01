package no.unit.nva.model.instancetypes.book;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.instancetypes.PeerReviewedMonograph;
import no.unit.nva.model.pages.MonographPages;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class BookMonograph extends PeerReviewedMonograph {

    private BookMonographContentType contentType;

    public BookMonographContentType getContentType() {
        return contentType;
    }

    public void setContentType(BookMonographContentType contentType) {
        this.contentType = contentType;
    }

    public BookMonograph() {
        super();
    }

    private BookMonograph(Builder builder) {
        super(builder.pages, builder.peerReviewed, builder.textbookContent);
        setContentType(builder.contentType);
    }

    public static final class Builder {
        private boolean peerReviewed;
        private MonographPages pages;
        private boolean textbookContent;
        private BookMonographContentType contentType;

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

        public Builder withContentType(BookMonographContentType contentType) {
            this.contentType = contentType;
            return this;
        }


        public BookMonograph build() {
            return new BookMonograph(this);
        }
    }
}
