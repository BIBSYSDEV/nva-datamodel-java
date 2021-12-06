package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResourceOwner {

    public static final String OWNER_AFFILIATION = "ownerAffiliation";
    public static final String OWNER = "owner";

    @JsonProperty(OWNER)
    private final String owner;
    @JsonProperty(OWNER_AFFILIATION)
    private final CristinUnitIdentifier ownerAffiliation;

    @JsonCreator
    public ResourceOwner(@JsonProperty(OWNER) String owner,
                         @JsonProperty(OWNER_AFFILIATION) CristinUnitIdentifier ownerAffiliation) {
        this.owner = owner;
        this.ownerAffiliation = ownerAffiliation;
    }

    public String getOwner() {
        return owner;
    }

    public CristinUnitIdentifier getOwnerAffiliation() {
        return ownerAffiliation;
    }
}
