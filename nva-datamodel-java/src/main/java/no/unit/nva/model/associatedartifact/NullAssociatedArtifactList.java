package no.unit.nva.model.associatedartifact;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;

import static java.util.Collections.emptyList;

/**
 * When a publication has no associated resources at a semantic level, this type is used.
 * Care should be exercised as this is not the same as an empty set of resources; i.e. this class represents
 * an active statement that the publication has zero resources associated with it, rather than it is unknown
 * whether the publication has any associated with it at the current time. The latter case is an empty set.
 **/
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public final class NullAssociatedArtifactList implements AssociatedArtifactCollection {

    @Override
    public List<AssociatedArtifact> getArtifacts() {
        return emptyList();
    }
}
