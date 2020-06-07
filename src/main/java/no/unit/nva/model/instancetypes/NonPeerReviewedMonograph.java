package no.unit.nva.model.instancetypes;

import no.unit.nva.model.pages.MonographPages;
import nva.commons.utils.JacocoGenerated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NonPeerReviewedMonograph extends Monograph {
    public static final String PEER_REVIEWED_FALSE =
            "peerReviewed is always false as {} is assumed to not be peer-reviewed";
    protected static final Logger logger = LoggerFactory.getLogger(NonPeerReviewedMonograph.class);

    protected NonPeerReviewedMonograph(MonographPages pages, boolean peerReviewed) {
        super();
        if (peerReviewed) {
            logger.warn(PEER_REVIEWED_FALSE, this.getClass().getSimpleName());
        }
        setPages(pages);
        this.peerReviewed = false;
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
