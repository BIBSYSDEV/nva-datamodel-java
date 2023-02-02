package no.unit.nva.model.instancetypes.journal;

import no.unit.nva.model.instancetypes.PeerReviewedPaper;
import no.unit.nva.model.pages.Range;
import nva.commons.core.JacocoGenerated;

import java.util.Objects;

public class JournalArticle extends PeerReviewedPaper {
    public static final String PAGES_FIELD = "pages";
    public static final String PEER_REVIEWED_FIELD = "peerReviewed";
    public static final String TYPE = "type";
    public static final String VOLUME_FIELD = "volume";
    public static final String ISSUE_FIELD = "issue";
    public static final String ARTICLE_NUMBER_FIELD = "articleNumber";
    private String volume;
    private String issue;
    private String articleNumber;

    protected JournalArticle(Range pages,
                             boolean peerReviewed,
                             boolean originalResearch,
                             String volume,
                             String issue,
                             String articleNumber) {
        super(pages, peerReviewed, originalResearch);
        this.volume = volume;
        this.issue = issue;
        this.articleNumber = articleNumber;
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


    @Override
    public void setOriginalResearch(boolean originalResearch) {
        if (isOriginalResearchCandidate()) {
            this.originalResearch = originalResearch;
        }
    }

    private boolean isOriginalResearchCandidate() {
        return originalResearch;
    }

    @Override
    @JacocoGenerated
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
                && Objects.equals(getArticleNumber(), that.getArticleNumber());
    }

    @Override
    @JacocoGenerated
    public int hashCode() {
        return Objects.hash(super.hashCode(), getVolume(), getIssue(), getArticleNumber());
    }
}
