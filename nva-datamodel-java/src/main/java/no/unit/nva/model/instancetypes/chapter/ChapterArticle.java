package no.unit.nva.model.instancetypes.chapter;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.instancetypes.PeerReviewedPaper;
import no.unit.nva.model.pages.Range;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class ChapterArticle extends PeerReviewedPaper {

    private ChapterArticleContentType contentType;

    public ChapterArticle() {
        super();
    }

    private ChapterArticle(Builder builder) {
        super(builder.pages, builder.peerReviewed, builder.originalResearch);
        setContentType(builder.contentType);
    }

    public void setContentType(ChapterArticleContentType contentType) {
        this.contentType = contentType;
    }

    public ChapterArticleContentType getContentType() {
        return contentType;
    }

    public static final class Builder {
        private boolean peerReviewed;
        private Range pages;
        private boolean originalResearch;
        private ChapterArticleContentType contentType;

        public Builder() {
        }

        public Builder withPeerReviewed(boolean peerReviewed) {
            this.peerReviewed = peerReviewed;
            return this;
        }

        public Builder withPages(Range pages) {
            this.pages = pages;
            return this;
        }

        public Builder withOriginalResearch(boolean originalResearch) {
            this.originalResearch = originalResearch;
            return this;
        }

        public Builder withContentType(ChapterArticleContentType contentType) {
            this.contentType = contentType;
            return this;
        }

        public ChapterArticle build() {
            return new ChapterArticle(this);
        }
    }
}
