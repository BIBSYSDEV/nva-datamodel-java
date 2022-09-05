package no.unit.nva.model.associatedartifact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import nva.commons.core.JacocoGenerated;

import java.util.List;
import java.util.Objects;

import static java.util.Collections.emptyList;
import static java.util.Objects.nonNull;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class AssociatedArtifactList implements AssociatedArtifactCollection {

    public static final String ARTIFACTS_FIELD = "artifacts";

    @JsonProperty(ARTIFACTS_FIELD)
    private final List<AssociatedArtifact> artifacts;

    public AssociatedArtifactList(@JsonProperty(ARTIFACTS_FIELD) List<AssociatedArtifact> artifacts) {
        this.artifacts = artifacts;
    }

    @Override
    public List<AssociatedArtifact> getArtifacts() {
        return nonNull(artifacts) ? artifacts : emptyList();
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AssociatedArtifactList)) {
            return false;
        }
        AssociatedArtifactList that = (AssociatedArtifactList) o;
        return Objects.equals(getArtifacts(), that.getArtifacts());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getArtifacts());
    }
}
