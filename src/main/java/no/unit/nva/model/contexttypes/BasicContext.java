package no.unit.nva.model.contexttypes;

import no.unit.nva.model.Level;

/**
 * BasicContexts are contexts that are expressed in full in the data model.
 */
public interface BasicContext extends PublicationContext {

    Level getLevel();

    void setLevel(Level level);

    boolean isOpenAccess();

    void setOpenAccess(boolean openAccess);

    boolean isPeerReviewed();

    void setPeerReviewed(boolean peerReviewed);
}
