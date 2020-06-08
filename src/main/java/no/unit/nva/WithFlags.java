package no.unit.nva;

public interface WithFlags {

    /**
     * Get flag telling Publication API to create a DOI Request for this Publication (if one does not exist).
     *
     * @return  doiRequested
     */
    Boolean getDoiRequested();

    /**
     * Set flag telling Publication API to create a DOI Request for this Publication (if one does not exist).
     *
     * @param doiRequested  doiRequested
     */
    void setDoiRequest(Boolean doiRequested);

}
