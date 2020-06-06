package no.unit.nva.model.instancetypes;

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
     * @param peerReviewed  the value is always ignored.
     */
    @JsonCreator
    public JournalLeader(
            @JsonProperty("volume") String volume,
            @JsonProperty("issue") String issue,
            @JsonProperty("articleNumber") String articleNumber,
            @JsonProperty("pages") Range pages,
            @JsonProperty("peerReviewed") boolean peerReviewed
    ) {
        super(volume, issue, articleNumber, pages, peerReviewed);
    }
}
