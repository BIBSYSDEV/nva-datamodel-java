package no.unit.nva.model;

import static java.util.Collections.emptySet;

import java.util.Set;

/**
 * NVA's DOI Registration Agency Process statuses.
 */
public enum DoiRegistrationAgencyProcessStatus {
    /**
     * Our registration with a Registration Agency (RA) has not started.
     */
    NOT_STARTED,
    /**
     * The DOI is registered as a draft with the Registration Agency (RA).
     */
    DRAFT,
    /**
     * The DOI is registered as a findable with the Registration Agency (RA).
     */
    FINDABLE,
    /**
     * The DOI is registered as a `archived` (REGISTERD, read deregistered, deleted). with the Registration Agency (RA).
     */
    ARCHIVED;
    protected static final Set<DoiRegistrationAgencyProcessStatus> validStatusChangeForInProcessDatacite = Set.of(
        DRAFT, FINDABLE, ARCHIVED);
    protected static final Set<DoiRegistrationAgencyProcessStatus> validStatusChangesForNotStarted = Set.of(
        DRAFT, FINDABLE);
    protected static final Set<DoiRegistrationAgencyProcessStatus> validDefaultStatusChanges = emptySet();
    public static final String ERROR_MESSAGE_NOT_ALLOWED_TO_CHANGE_STATUS_FROM_S_TO_S =
        "Not allowed to change status from %s to %s";
    protected static final Set<DoiRegistrationAgencyProcessStatus> validStatusChangesForDraft = Set.of(
        ARCHIVED, FINDABLE);
    protected static final Set<DoiRegistrationAgencyProcessStatus> validStatusChangeForFindable = Set.of(
        ARCHIVED);
    protected static final Set<DoiRegistrationAgencyProcessStatus> validStatusChangesForArchived = emptySet();


    public boolean isValidStatusChange(DoiRegistrationAgencyProcessStatus requestedStatusChange) {
        return getValidTransitions(this).contains(requestedStatusChange);
    }

    /**
     * Translates our enum to expected datacite status name.
     * @return translated enum name.
     */
    public String toDataciteStatusName() {
        if (this.equals(ARCHIVED)) {
            return "REGISTERED";
        }
        return this.toString();
    }

    /**
     * Changes status for a DoiRegistrationAgencyProcessStatus change. It will return the new
     * DoiRegistrationAgencyProcessStatus if the transition is valid.
     *
     * @param requestedStatusChange requested DOIRequestStatus to transform to.
     * @return New DoiRequestStatus.
     * @throws IllegalArgumentException requestedStatusChange is not valid to change into.
     */
    public DoiRegistrationAgencyProcessStatus changeStatus(DoiRegistrationAgencyProcessStatus requestedStatusChange) {
        if (isValidStatusChange(requestedStatusChange)) {
            return requestedStatusChange;
        }
        throw new IllegalArgumentException(getErrorMessageForNotAllowedStatusChange(requestedStatusChange));
    }

    protected String getErrorMessageForNotAllowedStatusChange(
        DoiRegistrationAgencyProcessStatus requestedStatusChange) {
        return String.format(ERROR_MESSAGE_NOT_ALLOWED_TO_CHANGE_STATUS_FROM_S_TO_S, this, requestedStatusChange);
    }

    private Set<DoiRegistrationAgencyProcessStatus> getValidTransitions(
        DoiRegistrationAgencyProcessStatus fromRequestStatus) {
        switch (fromRequestStatus) {
            case NOT_STARTED:
                return validStatusChangesForNotStarted;
            case DRAFT:
                return validStatusChangesForDraft;
            case FINDABLE:
                return validStatusChangeForFindable;
            case ARCHIVED:
                return validStatusChangesForArchived;
            default:
                return validDefaultStatusChanges;
        }
    }
}
