package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.exceptions.InvalidPageTypeException;
import no.unit.nva.model.pages.Pages;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class JournalLetter extends JournalArticle {

    public static final String NON_PEER_REVIEW_ERROR = "Letters to the editor cannot be peer reviewed";

    public JournalLetter() {
    }

    private JournalLetter(Builder builder) throws InvalidPageTypeException {
        setVolume(builder.volume);
        setIssue(builder.issue);
        setArticleNumber(builder.articleNumber);
        setPages(builder.pages);
        setPeerReviewed(builder.peerReviewed);
    }

    @Override
    public boolean isPeerReviewed() {
        return false;
    }

    @Override
    @JsonSetter("peerReviewed")
    public void setPeerReviewed(boolean peerReview) {
        if (peerReview) {
            throw new IllegalArgumentException(NON_PEER_REVIEW_ERROR);
        }
    }

    public static final class Builder {
        private String volume;
        private String issue;
        private String articleNumber;
        private Pages pages;
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

        public Builder withPages(Pages pages) {
            this.pages = pages;
            return this;
        }

        public Builder withPeerReviewed(boolean peerReviewed) {
            this.peerReviewed = peerReviewed;
            return this;
        }

        public JournalLetter build() throws InvalidPageTypeException {
            return new JournalLetter(this);
        }
    }
}
