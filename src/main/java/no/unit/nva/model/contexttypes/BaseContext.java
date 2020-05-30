package no.unit.nva.model.contexttypes;

import no.unit.nva.model.Level;

public interface BaseContext extends PublicationContext {

    Level getLevel();

    void setLevel(Level level);

    boolean isOpenAccess();

    void setOpenAccess(boolean openAccess);

    boolean isPeerReviewed();

    void setPeerReviewed(boolean peerReviewed);
}
