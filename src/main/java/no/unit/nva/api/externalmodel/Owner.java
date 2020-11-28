package no.unit.nva.api.externalmodel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import no.unit.nva.model.Organization;

public class Owner {
    private final String identity;
    private final Organization institution;

    @JsonCreator
    public Owner(@JsonProperty("owner") String owner, @JsonProperty("institution")Organization institution) {
        this.identity = owner;
        this.institution = institution;
    }

    public String getIdentity() {
        return identity;
    }

    public Organization getInstitution() {
        return institution;
    }
}
