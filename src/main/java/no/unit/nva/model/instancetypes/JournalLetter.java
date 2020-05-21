package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.exceptions.InvalidPageTypeException;
import no.unit.nva.model.pages.Pages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class JournalLetter extends JournalArticle implements PublicationInstance {

    private static final Logger logger = LoggerFactory.getLogger(JournalLetter.class);
    public static final String SETTING_PEER_REVIEWED_FALSE =
            "Setting peerReviewed to false as {} is assumed to not be peer-reviewed";

    /**
     * This constructor ensures that the peerReviewed value is always false.
     *
     * @param volume the volume of the PublicationInstance.
     * @param issue the issue of the PublicationInstance.
     * @param articleNumber the article number of the PublicationInstance.
     * @param pages the Pages of the PublicationInstance.
     * @param peerReviewed the value is always ignored.
     * @throws InvalidPageTypeException if the type of Pages is incompatible with the PublicationInstance type.
     */
    @JsonCreator
    public JournalLetter(
            @JsonProperty("volume") String volume,
            @JsonProperty("issue") String issue,
            @JsonProperty("articleNumber") String articleNumber,
            @JsonProperty("pages") Pages pages,
            @JsonProperty("peerReviewed") boolean peerReviewed
    ) throws InvalidPageTypeException {
        super();
        setVolume(volume);
        setIssue(issue);
        setArticleNumber(articleNumber);
        setPages(pages);
        if (peerReviewed) {
            logger.warn(SETTING_PEER_REVIEWED_FALSE, JournalLetter.class.getSimpleName());
        }
        setPeerReviewed(false);
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

        public JournalLetter build() throws InvalidPageTypeException {
            return new JournalLetter(this.volume, this.issue, this.articleNumber, this.pages, false);
        }
    }
}
