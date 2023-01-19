package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.net.URI;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

import nva.commons.core.JacocoGenerated;

import static java.util.Objects.isNull;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Organization implements Agent {

    public static final String ID_FIELD = "id";
    public static final String LABELS_FIELD = "labels";
    @JsonProperty(ID_FIELD)
    private final URI id;
    @JsonProperty(LABELS_FIELD)
    private final Map<String, String> labels;

    @Deprecated
    @JsonCreator
    public static Organization fromJson(@JsonProperty(ID_FIELD) URI id,
                                        @JsonProperty(LABELS_FIELD) Map<String, String> labels) {
        if (isNull(id)) {
            return null;
        }
        return new Organization(id, labels);
    }

    public Organization(@JsonProperty(ID_FIELD) URI id,
                        @JsonProperty(LABELS_FIELD) Map<String, String> labels) {
        this.id = validateId(id);
        this.labels = labels;
    }

    public URI getId() {
        return id;
    }

    public Map<String, String> getLabels() {
        return Objects.nonNull(labels) ? labels : Collections.emptyMap();
    }

    private URI validateId(URI candidate) {
        if (isNull(candidate)) {
            throw new IllegalArgumentException("The id of an Organization can not be null");
        }
        return candidate;
    }

    @Override
    @JacocoGenerated
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Organization)) {
            return false;
        }
        Organization that = (Organization) o;
        return Objects.equals(getId(), that.getId())
                && Objects.equals(getLabels(), that.getLabels());
    }

    @Override
    @JacocoGenerated
    public int hashCode() {
        return Objects.hash(getId(), getLabels());
    }
}
