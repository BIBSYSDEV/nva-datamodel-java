package no.unit.nva.model.instancetypes.journal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.pages.Range;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class JournalLeader extends JournalNonPeerReviewedContent {
    /**
     * This constructor ensures that the peerReviewed value is always false.
     *
     * @param volume        the volume of the PublicationInstance.
     * @param issue         the issue of the PublicationInstance.
     * @param articleNumber the article number of the PublicationInstance.
     * @param pages         the Pages of the PublicationInstance.
     */
    @JsonCreator
    public JournalLeader(
            @JsonProperty("volume") String volume,
            @JsonProperty("issue") String issue,
            @JsonProperty("articleNumber") String articleNumber,
            @JsonProperty("pages") Range pages
    ) {
        super(volume, issue, articleNumber, pages);
    }

    public static final class Builder {
        private Range pages;
        private String volume;
        private String issue;
        private String articleNumber;

        public Builder() {
        }

        public Builder withPages(Range pages) {
            this.pages = pages;
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

        public JournalLeader build() {
            return new JournalLeader(volume, issue, articleNumber, pages);
        }
    }
}
