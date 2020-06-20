package no.unit.nva.model.instancetypes;

import java.rmi.UnexpectedException;

public class NonPeerReviewed {
    public static final String PEER_REVIEWED_ERROR_TEMPLATE = "%s is assumed not to be peer-reviewed";

    /**
     * In cases where data is inserted with a value peerReviewed = true, an Unexpected exception will be thrown,
     * otherwise the value is ignored as the class always returns false.
     *
     * @param peerReviewed a boolean value.
     * @throws UnexpectedException thrown if the boolean equals true.
     */
    public void setPeerReviewed(boolean peerReviewed) throws UnexpectedException {
        if (peerReviewed) {
            throw new UnexpectedException(String.format(PEER_REVIEWED_ERROR_TEMPLATE, this.getClass().getSimpleName()));
        }
    }
}
