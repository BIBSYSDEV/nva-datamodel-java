package no.unit.nva.model.instancetypes.journal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.instancetypes.PeerReviewedPaper;
import no.unit.nva.model.pages.Range;
import nva.commons.core.JacocoGenerated;

import java.util.Objects;

import static java.util.Objects.isNull;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class JournalArticle extends PeerReviewedPaper {
    public static final String PAGES_FIELD = "pages";
    public static final String PEER_REVIEWED_FIELD = "peerReviewed";
    public static final String ORIGINAL_RESEARCH_FIELD = "originalResearch";
    public static final String TYPE = "type";
    public static final String VOLUME_FIELD = "volume";
    public static final String ISSUE_FIELD = "issue";
    public static final String ARTICLE_NUMBER_FIELD = "articleNumber";
    public static final String CONTENT_TYPE_FIELD = "contentType";
    private String volume;
    private String issue;
    private String articleNumber;

    protected JournalArticle(Range pages, boolean peerReviewed, boolean originalResearch, String volume, String issue, String articleNumber) {
        super(pages, peerReviewed, originalResearch);
        this.volume = volume;
        this.issue = issue;
        this.articleNumber = articleNumber;
    }

    @JsonCreator
    public static JournalArticle fromJson(@JsonProperty(PAGES_FIELD) Range pages,
                                          @JsonProperty(PEER_REVIEWED_FIELD) boolean peerReviewed,
                                          @JsonProperty(ORIGINAL_RESEARCH_FIELD) boolean originalResearch,
                                          @JsonProperty(VOLUME_FIELD) String volume,
                                          @JsonProperty(ISSUE_FIELD) String issue,
                                          @JsonProperty(ARTICLE_NUMBER_FIELD) String articleNumber,
                                          @JsonProperty(CONTENT_TYPE_FIELD) JournalArticleContentType contentType) {
        if (JournalArticleContentType.ACADEMIC_ARTICLE.equals(contentType)) {
            return new AcademicArticle(pages, peerReviewed, volume, issue, articleNumber);
        } else if (JournalArticleContentType.ACADEMIC_LITERATURE_REVIEW.equals(contentType)) {
            return new AcademicLiteratureReview(pages, peerReviewed, volume, issue, articleNumber);
        } else if (JournalArticleContentType.CASE_REPORT.equals(contentType)) {
            return new CaseReport(pages, peerReviewed, volume, issue, articleNumber);
        } else if (JournalArticleContentType.POPULAR_SCIENCE_ARTICLE.equals(contentType)) {
            return new PopularScienceArticle(pages, peerReviewed, volume, issue, articleNumber);
        } else if (JournalArticleContentType.PROFESSIONAL_ARTICLE.equals(contentType)) {
            return new ProfessionalArticle(pages, peerReviewed, volume, issue, articleNumber);
        } else if (JournalArticleContentType.STUDY_PROTOCOL.equals(contentType)) {
            return new StudyProtocol(pages, peerReviewed, volume, issue, articleNumber);
        } else if (isNull(contentType)) {
            return new AcademicArticle(pages, peerReviewed, volume, issue, articleNumber);
        } else {
            throw new UnsupportedOperationException("The Journal article subtype is unknown");
        }
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

    public static final class Builder {
        private boolean peerReviewed;
        private boolean originalResearch;
        private Range pages;
        private JournalArticleContentType type;
        private String volume;
        private String issue;
        private String articleNumber;

        public Builder() {
        }

        public Builder withPeerReviewed(boolean peerReviewed) {
            this.peerReviewed = peerReviewed;
            return this;
        }

        public Builder withOriginalResearch(boolean originalResearch) {
            this.originalResearch = originalResearch;
            return this;
        }

        public Builder withPages(Range pages) {
            this.pages = pages;
            return this;
        }

        public Builder withType(JournalArticleContentType type) {
            this.type = type;
            return this;
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

        public JournalArticle build() {
            return new JournalArticle(pages, peerReviewed, originalResearch, volume,
                    issue, articleNumber);
        }
    }
}
