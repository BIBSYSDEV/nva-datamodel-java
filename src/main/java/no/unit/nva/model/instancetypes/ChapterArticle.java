package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.exceptions.InvalidPageTypeException;
import no.unit.nva.model.pages.Pages;
import no.unit.nva.model.pages.Range;
import nva.commons.utils.JacocoGenerated;

import java.util.Objects;

import static java.util.Objects.nonNull;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class ChapterArticle implements PublicationInstance {

    private Pages pages;
    private boolean peerReviewed;

    public ChapterArticle() {
    }

    private ChapterArticle(Builder builder) throws InvalidPageTypeException {
        setPages(builder.pages);
        setPeerReviewed(builder.peerReviewed);
    }

    @Override
    public Pages getPages() {
        return pages;
    }

    @Override
    public void setPages(Pages pages) throws InvalidPageTypeException {
        if (nonNull(pages) && !(pages instanceof  Range)) {
            throw new InvalidPageTypeException(this.getClass(), Range.class, pages.getClass());
        }
        this.pages = pages;
    }

    @Override
    public void setPeerReviewed(boolean peerReviewed) {
        this.peerReviewed = peerReviewed;
    }

    @Override
    public boolean isPeerReviewed() {
        return peerReviewed;
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
        private Pages pages;
        private boolean peerReviewed;

        public Builder() {
        }

        public Builder withPages(Pages pages) {
            this.pages = pages;
            return this;
        }

        public Builder withPeerReviewed(boolean peerReviewed) {
            this.peerReviewed = peerReviewed;
            return this;
        }

        public ChapterArticle build() throws InvalidPageTypeException {
            return new ChapterArticle(this);
        }
    }
}
