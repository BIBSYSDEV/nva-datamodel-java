package no.unit.nva.model;

import static java.util.Collections.emptySet;

import java.util.Set;

public enum DoiRequestStatus {
    REQUESTED,
    APPROVED,
    REJECTED;

    public boolean isValidStatusChange(DoiRequestStatus requestedStatusChange) {
        return getValidTransitions(this).contains(requestedStatusChange);
    }

    /**
     * Transition a DoiRequestStatus change
     * <p>
     * It will return the new DoiRequestStatus if the transition is valid.
     *
     * @param requestedStatusChange requested DOIRequestStatus to transform to.
     * @return New or existing DoiRequestStatus.
     * @see #isValidStatusChange(DoiRequestStatus) to check if a transition is allowed or not, and you need to know
     *     before performing it.
     */
    public DoiRequestStatus transition(DoiRequestStatus requestedStatusChange) {
        if (requestedStatusChange.getValidTransitions(this).contains(requestedStatusChange)) {
            return requestedStatusChange;
        }
        return this;
    }

    private Set<DoiRequestStatus> getValidTransitions(DoiRequestStatus fromRequestStatus) {
        switch (fromRequestStatus) {
            case REQUESTED:
                return Set.of(APPROVED, REJECTED);
            case REJECTED:
                return Set.of(APPROVED);
            default:
                return emptySet();
        }
    }
}
