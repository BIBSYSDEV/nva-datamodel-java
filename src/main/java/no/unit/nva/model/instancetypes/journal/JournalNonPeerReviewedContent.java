package no.unit.nva.model.instancetypes.journal;

import no.unit.nva.model.instancetypes.PublicationInstance;
import no.unit.nva.model.pages.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JournalNonPeerReviewedContent extends JournalArticle implements PublicationInstance<Range> {
    public static final String SETTING_PEER_REVIEWED_FALSE =
            "Setting peerReviewed to false as {} is assumed to not be peer-reviewed";
    protected static final Logger logger = LoggerFactory.getLogger(JournalLetter.class);

    /**
     * This constructor ensures that the peerReviewed value is always false.
     *
     * @param volume        the volume of the PublicationInstance.
     * @param issue         the issue of the PublicationInstance.
     * @param articleNumber the article number of the PublicationInstance.
     * @param pages         the Pages of the PublicationInstance.
     * @param peerReviewed  the value is always ignored.
     */
    public JournalNonPeerReviewedContent(
            String volume,
            String issue,
            String articleNumber,
            Range pages,
            boolean peerReviewed
    ) {
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
}
