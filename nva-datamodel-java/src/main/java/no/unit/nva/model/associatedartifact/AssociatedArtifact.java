package no.unit.nva.model.associatedartifact;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.net.URI;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(name = "File", value = File.class),
    @JsonSubTypes.Type(name = "Link", value = Link.class)
})
public interface AssociatedArtifact {
    URI getId();
}
