package no.unit.nva.model.associatedartifacts;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

/**
 * This class represents a state for publications that do not and cannot have
 * associated artifacts.
 */
@JsonTypeInfo(use = Id.NAME, property = "type")
public class NullAssociatedArtifact implements AssociatedArtifact {

    public NullAssociatedArtifact() {
        // NO-OP
    }
}
