package no.unit.nva.model.associatedartifact;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(name = "NullAssociatedArtifactList", value = NullAssociatedArtifactList.class),
        @JsonSubTypes.Type(name = "AssociatedArtifactList", value = AssociatedArtifactList.class)
})
public interface AssociatedArtifactCollection {
    List<AssociatedArtifact> getArtifacts();
}
