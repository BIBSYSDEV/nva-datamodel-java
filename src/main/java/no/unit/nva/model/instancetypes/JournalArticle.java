package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.exceptions.InvalidPageTypeException;
import no.unit.nva.model.pages.Range;
import nva.commons.utils.JacocoGenerated;

import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class JournalArticle extends ChapterArticle implements PublicationInstance {
    private String volume;
    private String issue;
    private String articleNumber;

    @JacocoGenerated
    public JournalArticle() {
        super();
    }

    private JournalArticle(Builder builder) throws InvalidPageTypeException {
        super();
        setVolume(builder.volume);
        setIssue(builder.issue);
        setArticleNumber(builder.articleNumber);
        setPages(builder.pages);
        setPeerReviewed(builder.peerReviewed);
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(String articleNumber) {
        this.articleNumber = articleNumber;
    }

    public static final class Builder {
        private String volume;
        private String issue;
        private String articleNumber;
        private Range pages;
        private boolean peerReviewed;

        public Builder() {
        }

        public Builder withVolume(String volume) {
            this.volume = volume;
            return this;
        }

        public Builder withIssue(String issue) {
            this.issue = issue;
            return this;
        }

        public Builder withArticleNumber(String articleNumber) {
            this.articleNumber = articleNumber;
            return this;
        }

        public Builder withPages(Range pages) {
            this.pages = pages;
            return this;
        }

        public Builder withPeerReviewed(boolean peerReviewed) {
            this.peerReviewed = peerReviewed;
            return this;
        }

        public JournalArticle build() throws InvalidPageTypeException {
            return new JournalArticle(this);
        }
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JournalArticle)) {
            return false;
        }
        JournalArticle that = (JournalArticle) o;
        return isPeerReviewed() == that.isPeerReviewed()
                && Objects.equals(getVolume(), that.getVolume())
                && Objects.equals(getIssue(), that.getIssue())
                && Objects.equals(getArticleNumber(), that.getArticleNumber())
                && Objects.equals(getPages(), that.getPages());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getVolume(), getIssue(), getArticleNumber(), getPages(), isPeerReviewed());
    }
}
