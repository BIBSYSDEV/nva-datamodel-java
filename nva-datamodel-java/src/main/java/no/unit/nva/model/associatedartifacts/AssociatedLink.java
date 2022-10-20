package no.unit.nva.model.associatedartifacts;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import nva.commons.core.JacocoGenerated;
import nva.commons.core.SingletonCollector;

import java.net.URI;
import java.util.Arrays;
import java.util.Objects;

import static java.util.Objects.nonNull;

public class AssociatedLink implements AssociatedArtifact {

    public static final String ID_FIELD = "id";
    public static final String NAME_FIELD = "name";
    public static final String DESCRIPTION_FIELD = "description";
    public static final String TYPE_FIELD = "type";
    @JsonProperty(ID_FIELD)
    private final URI id;
    @JsonProperty(NAME_FIELD)
    private final String name;
    @JsonProperty(DESCRIPTION_FIELD)
    private final String description;
    @JsonProperty(TYPE_FIELD)
    private final RelationType type;

    @JsonCreator
    public AssociatedLink(@JsonProperty(ID_FIELD) URI id,
                          @JsonProperty(NAME_FIELD) String name,
                          @JsonProperty(DESCRIPTION_FIELD) String description,
                          @JsonProperty(TYPE_FIELD) RelationType type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = nonNull(type) ? type : RelationType.GENERIC_LINKED_RESOURCE;
    }

    public URI getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type.getType();
    }

    @Override
    @JacocoGenerated
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AssociatedLink)) {
            return false;
        }
        AssociatedLink that = (AssociatedLink) o;
        return Objects.equals(getId(), that.getId())
                && Objects.equals(getName(), that.getName())
                && Objects.equals(getDescription(), that.getDescription())
                && getType() == that.getType();
    }

    @Override
    @JacocoGenerated
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getType());
    }

    public enum RelationType {
        GENERIC_LINKED_RESOURCE("GenericLinkedResource");

        private final String type;

        RelationType(String type) {

            this.type = type;
        }

        @JsonValue
        public String getType() {
            return type;
        }

        @JsonCreator
        public RelationType lookup(String candidate) {
            return Arrays.stream(RelationType.values())
                .filter(item -> item.getType().equals(candidate))
                .collect(SingletonCollector.collect());
        }
    }
}
