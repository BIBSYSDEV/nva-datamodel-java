package no.unit.nva.model.instancetypes.chapter;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.net.URI;
import no.unit.nva.model.instancetypes.PeerReviewedPaper;
import no.unit.nva.model.pages.Range;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class ChapterArticle extends PeerReviewedPaper {

    private ChapterArticleContentType contentType;
    private URI partOf;

    public ChapterArticle() {
        super();
    }

    public ChapterArticleContentType getContentType() {
        return contentType;
    }

    public void setContentType(ChapterArticleContentType contentType) {
        this.contentType = contentType;
    }

    public URI getPartOf() {
        return partOf;
    }

    public void setPartOf(URI partOf) {
        this.partOf = partOf;
    }

    public Builder copy() {
        return new Builder()
            .withContentType(getContentType())
            .withOriginalResearch(isOriginalResearch())
            .withPeerReviewed(isPeerReviewed())
            .withPartOf(getPartOf())
            .withPages(getPages());
    }

    public static final class Builder {

        private final ChapterArticle chapterArticle;

        public Builder() {
            chapterArticle = new ChapterArticle();
        }

        public Builder withPeerReviewed(boolean peerReviewed) {
            chapterArticle.setPeerReviewed(peerReviewed);
            return this;
        }

        public Builder withOriginalResearch(boolean originalResearch) {
            chapterArticle.setOriginalResearch(originalResearch);
            return this;
        }

        public Builder withPages(Range pages) {
            chapterArticle.setPages(pages);
            return this;
        }

        public Builder withContentType(ChapterArticleContentType contentType) {
            chapterArticle.setContentType(contentType);
            return this;
        }

        public Builder withPartOf(URI partOf) {
            chapterArticle.setPartOf(partOf);
            return this;
        }

        public ChapterArticle build() {
            return chapterArticle;
        }
    }
}
