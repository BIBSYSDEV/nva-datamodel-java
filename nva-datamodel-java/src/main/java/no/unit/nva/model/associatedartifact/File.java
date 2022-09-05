package no.unit.nva.model.associatedartifact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import nva.commons.core.JacocoGenerated;

import java.net.URI;
import java.util.Objects;

/**
 * A file hosted by NVA.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class File implements AssociatedArtifact {
    public static final String ID_FIELD = "id";

    @JsonProperty(ID_FIELD)
    private final URI id;

    public File(@JsonProperty(ID_FIELD) URI id) {
        this.id = id;
    }

    @Override
    public URI getId() {
        return id;
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof File)) {
            return false;
        }
        File file = (File) o;
        return Objects.equals(getId(), file.getId());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
