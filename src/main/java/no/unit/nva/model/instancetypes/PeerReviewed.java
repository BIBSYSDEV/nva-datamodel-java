package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import no.unit.nva.model.pages.Pages;

@SuppressWarnings("PMD.AvoidFieldNameMatchingTypeName")
public abstract class PeerReviewed<P extends Pages> implements PublicationInstance<P> {

    @JsonProperty("peerReviewed")
    private boolean peerReviewed;

    protected PeerReviewed() {
    }

    protected PeerReviewed(boolean peerReviewed) {
        setPeerReviewed(peerReviewed);
    }

    @Override
    public final boolean isPeerReviewed() {
        return peerReviewed;
    }

    public final void setPeerReviewed(boolean peerReviewed) {
        this.peerReviewed = peerReviewed;
    }


}
