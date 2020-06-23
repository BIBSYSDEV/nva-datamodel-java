package no.unit.nva.model.instancetypes;

import no.unit.nva.model.pages.Pages;

import java.rmi.UnexpectedException;

public abstract class NonPeerReviewed<P extends Pages> implements PublicationInstance<P> {
    public static final String PEER_REVIEWED_ERROR_TEMPLATE = "%s is assumed not to be peer-reviewed";

    /**
     * In cases where data is inserted with a value peerReviewed = true, an Unexpected exception will be thrown,
     * otherwise the value is ignored as the class always returns false.
     *
     * @param peerReviewed a boolean value.
     * @throws UnexpectedException thrown if the boolean equals true.
     */
    public final void setPeerReviewed(boolean peerReviewed) throws UnexpectedException {
        if (peerReviewed) {
            throw new UnexpectedException(String.format(PEER_REVIEWED_ERROR_TEMPLATE, this.getClass().getSimpleName()));
        }
    }

    @Override
    public final boolean isPeerReviewed() {
        return false;
    }
}
