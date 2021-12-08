package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.net.URI;
import java.util.Objects;
import nva.commons.core.JacocoGenerated;

public class ResourceOwner {

    public static final String OWNER_AFFILIATION = "ownerAffiliation";
    public static final String OWNER = "owner";

    @JsonProperty(OWNER)
    private final String owner;
    @JsonProperty(OWNER_AFFILIATION)
    private final URI ownerAffiliation;

    @JsonCreator
    public ResourceOwner(@JsonProperty(OWNER) String owner,
                         @JsonProperty(OWNER_AFFILIATION) URI ownerAffiliation) {
        this.owner = owner;
        this.ownerAffiliation = ownerAffiliation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOwner(), getOwnerAffiliation());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ResourceOwner)) {
            return false;
        }
        ResourceOwner that = (ResourceOwner) o;
        return Objects.equals(getOwner(), that.getOwner()) && Objects.equals(getOwnerAffiliation(),
                                                                             that.getOwnerAffiliation());
    }

    @JacocoGenerated
    public String getOwner() {
        return owner;
    }

    @JacocoGenerated
    public URI getOwnerAffiliation() {
        return ownerAffiliation;
    }
}
