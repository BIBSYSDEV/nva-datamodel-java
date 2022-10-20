package no.unit.nva.model.associatedartifacts;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(name = "File", value = AssociatedFile.class),
    @JsonSubTypes.Type(name = "PublishedFile", value = AssociatedFile.class),
    @JsonSubTypes.Type(name = "UnpublishedFile", value = AssociatedFile.class),
    @JsonSubTypes.Type(name = "UnpublishableFile", value = AssociatedFile.class)
})
public interface AssociatedArtifact {

    String getType();
}
