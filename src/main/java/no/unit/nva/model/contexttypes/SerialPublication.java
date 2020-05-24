package no.unit.nva.model.contexttypes;

import no.unit.nva.model.exceptions.InvalidIssnException;

public interface SerialPublication {
    void setOnlineIssn(String issn) throws InvalidIssnException;

    void setPrintIssn(String issn) throws InvalidIssnException;
}
