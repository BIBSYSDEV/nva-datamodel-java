package no.unit.nva.model.instancetypes;

import no.unit.nva.model.pages.Pages;

public abstract class NonPeerReviewed<P extends Pages> implements PublicationInstance<P> {
    public static final String PEER_REVIEWED_ERROR_TEMPLATE = "%s is assumed not to be peer-reviewed";

    /**
     * In cases where data is inserted with a value peerReviewed = true, an Unexpected exception will be thrown,
     * otherwise the value is ignored as the class always returns false.
     *
     * @param peerReviewed a boolean value.
     */
    public final void setPeerReviewed(boolean peerReviewed) {
        if (peerReviewed) {
            throw new IllegalArgumentException(String.format(PEER_REVIEWED_ERROR_TEMPLATE,
                this.getClass().getSimpleName()));
        }
    }

    @Override
    public final boolean isPeerReviewed() {
        return false;
    }
}
