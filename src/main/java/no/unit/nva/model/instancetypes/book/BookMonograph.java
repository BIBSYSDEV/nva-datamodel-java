package no.unit.nva.model.instancetypes.book;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.instancetypes.PeerReviewedMonograph;
import no.unit.nva.model.pages.MonographPages;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class BookMonograph extends PeerReviewedMonograph {

    private BookMonographContentType contentType;
    private boolean originalResearch;

    public BookMonographContentType getContentType() {
        return contentType;
    }

    public void setContentType(BookMonographContentType contentType) {
        this.contentType = contentType;
    }

    public boolean isOriginalResearch() {
        return originalResearch;
    }

    public void setOriginalResearch(boolean originalResearch) {
        if (isOriginalResearchCandidate()) {
            this.originalResearch = originalResearch;
        }
    }

    private boolean isOriginalResearchCandidate() {
        return contentType == null || contentType == BookMonographContentType.ACADEMIC_MONOGRAPH;
    }


    public BookMonograph() {
        super();
    }

    private BookMonograph(Builder builder) {
        super(builder.pages, builder.peerReviewed, builder.textbookContent);
        setContentType(builder.contentType);
        setOriginalResearch(builder.originalResearch);
    }

    public static final class Builder {
        private boolean peerReviewed;
        private MonographPages pages;
        private boolean textbookContent;
        private BookMonographContentType contentType;
        private boolean originalResearch;

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

        public Builder withOriginalResearch(boolean originalResearch) {
            this.originalResearch = originalResearch;
            return this;
        }

        public BookMonograph build() {
            return new BookMonograph(this);
        }
    }
}
