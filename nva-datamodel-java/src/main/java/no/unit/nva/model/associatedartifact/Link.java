package no.unit.nva.model.associatedartifact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import nva.commons.core.JacocoGenerated;

import java.net.URI;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

/**
 * Allows the representation of a web resource that is associated with a publication in NVA.
 * Typically, these understood in data viewing context as hyperlinks
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Link implements AssociatedArtifact {

    private static final Set<String> acceptedSchemes = Set.of("http", "https");
    public static final String ID_FIELD = "id";
    public static final String NAME_FIELD = "name";
    public static final String DESCRIPTION_FIELD = "description";

    @JsonProperty(ID_FIELD)
    private final URI id;
    @JsonProperty(NAME_FIELD)
    private final String name;
    @JsonProperty(DESCRIPTION_FIELD)
    private final String description;

    public Link(@JsonProperty(ID_FIELD) URI uri,
                @JsonProperty(NAME_FIELD) String name,
                @JsonProperty(DESCRIPTION_FIELD) String description) {
        this.id = enforceQualifiedUris(uri);
        this.name = name;
        this.description = description;
    }

    private URI enforceQualifiedUris(URI uri) {
        if (isNotWebLink(uri)) {
            throw new UnsupportedOperationException("Absolute URIs are required");
        }
        return uri;
    }

    private static boolean isNotWebLink(URI uri) {
        return !uri.isAbsolute() || !acceptedSchemes.contains(uri.getScheme().toLowerCase(Locale.getDefault()));
    }

    @Override
    public URI getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Link)) {
            return false;
        }
        Link link = (Link) o;
        return Objects.equals(getId(), link.getId())
                && Objects.equals(getName(), link.getName())
                && Objects.equals(getDescription(), link.getDescription());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription());
    }
}
