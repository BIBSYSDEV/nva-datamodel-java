package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.pages.Range;
import nva.commons.utils.JacocoGenerated;

import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class ChapterArticle implements PublicationInstance<Range> {

    private Range pages;
    private boolean peerReviewed;

    public ChapterArticle() {
        super();
    }

    private ChapterArticle(Builder builder) {
        setPages(builder.pages);
        setPeerReviewed(builder.peerReviewed);
    }

    @Override
    public Range getPages() {
        return pages;
    }

    @Override
    public void setPages(Range pages) {
        this.pages = pages;
    }

    @Override
    public boolean isPeerReviewed() {
        return peerReviewed;
    }

    public void setPeerReviewed(boolean peerReviewed) {
        this.peerReviewed = peerReviewed;
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChapterArticle)) {
            return false;
        }
        ChapterArticle that = (ChapterArticle) o;
        return isPeerReviewed() == that.isPeerReviewed()
                && Objects.equals(getPages(), that.getPages());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getPages(), isPeerReviewed());
    }

    public static final class Builder {
        private Range pages;
        private boolean peerReviewed;

        public Builder() {
        }

        public Builder withPages(Range pages) {
            this.pages = pages;
            return this;
        }

        public Builder withPeerReviewed(boolean peerReviewed) {
            this.peerReviewed = peerReviewed;
            return this;
        }

        public ChapterArticle build() {
            return new ChapterArticle(this);
        }
    }
}
