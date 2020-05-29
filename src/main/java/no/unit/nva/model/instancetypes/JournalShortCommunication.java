package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.exceptions.InvalidPageTypeException;
import no.unit.nva.model.pages.Pages;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class JournalShortCommunication extends JournalNonPeerReviewedContent {

    /**
     * This constructor ensures that the peerReviewed value is always false.
     *
     * @param volume        the volume of the PublicationInstance.
     * @param issue         the issue of the PublicationInstance.
     * @param articleNumber the article number of the PublicationInstance.
     * @param pages         the Pages of the PublicationInstance.
     * @param peerReviewed  the value is always ignored.
     * @throws InvalidPageTypeException if the type of Pages is incompatible with the PublicationInstance type.
     */
    @JsonCreator
    public JournalShortCommunication(
            @JsonProperty("volume") String volume,
            @JsonProperty("issue") String issue,
            @JsonProperty("articleNumber") String articleNumber,
            @JsonProperty("pages") Pages pages,
            @JsonProperty("peerReviewed") boolean peerReviewed
    ) throws InvalidPageTypeException {
        super(volume, issue, articleNumber, pages, peerReviewed);
    }

    public static final class Builder {
        private String volume;
        private String issue;
        private String articleNumber;
        private Pages pages;

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

        public JournalShortCommunication build() throws InvalidPageTypeException {
            return new JournalShortCommunication(this.volume, this.issue, this.articleNumber, this.pages, false);
        }
    }
}