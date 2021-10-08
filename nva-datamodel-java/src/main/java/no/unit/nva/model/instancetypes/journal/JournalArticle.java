package no.unit.nva.model.instancetypes.journal;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.Objects;
import no.unit.nva.model.instancetypes.PeerReviewedPaper;
import no.unit.nva.model.pages.Range;
import nva.commons.core.JacocoGenerated;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class JournalArticle extends PeerReviewedPaper {
    private String volume;
    private String issue;
    private String articleNumber;

    @JsonAlias("content") //for backwards compatibility.
    private JournalArticleContentType contentType;

    @JacocoGenerated
    public JournalArticle() {
        super();
    }

    private JournalArticle(Builder builder) {
        super();
        setVolume(builder.volume);
        setIssue(builder.issue);
        setArticleNumber(builder.articleNumber);
        setPages(builder.pages);
        setPeerReviewed(builder.peerReviewed);
        setContentType(builder.content);
        setOriginalResearch(builder.originalResearch);
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

    public JournalArticleContentType getContentType() {
        return contentType;
    }

    public void setContentType(JournalArticleContentType contentType) {
        this.contentType = contentType;
    }

    @Override
    public void setOriginalResearch(boolean originalResearch) {
        if (isOriginalResearchCandidate()) {
            this.originalResearch = originalResearch;
        }
    }

    private boolean isOriginalResearchCandidate() {
        return contentType == null
               || contentType == JournalArticleContentType.RESEARCH_ARTICLE
               || contentType == JournalArticleContentType.REVIEW_ARTICLE;
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
        if (!super.equals(o)) {
            return false;
        }
        JournalArticle that = (JournalArticle) o;
        return Objects.equals(getVolume(), that.getVolume())
                && Objects.equals(getIssue(), that.getIssue())
                && Objects.equals(getArticleNumber(), that.getArticleNumber())
                && Objects.equals(getContentType(), that.getContentType())
                && this.isOriginalResearch() == that.isOriginalResearch();
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getVolume(), getIssue(), getArticleNumber(), getContentType());
    }

    public static final class Builder {
        private String volume;
        private String issue;
        private String articleNumber;
        private Range pages;
        private boolean peerReviewed;
        private JournalArticleContentType content;
        private boolean originalResearch;

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

        public Builder withContent(JournalArticleContentType content) {
            this.content = content;
            return this;
        }

        public Builder withOriginalResearch(boolean originalResearch) {
            this.originalResearch = originalResearch;
            return this;
        }

        public JournalArticle build() {
            return new JournalArticle(this);
        }
    }
}
