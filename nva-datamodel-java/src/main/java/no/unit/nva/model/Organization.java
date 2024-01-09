package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.net.URI;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import nva.commons.core.JacocoGenerated;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Organization extends Corporation {

    @JsonProperty("id")
    private URI id;
    @JsonProperty("labels")
    private Map<String, String> labels;

    public Organization() {
    }

    private Organization(Builder builder) {
        setId(builder.id);
        setLabels(builder.labels);
    }

    public URI getId() {
        return id;
    }

    public void setId(URI id) {
        this.id = id;
    }

    public Map<String, String> getLabels() {
        return Objects.nonNull(labels) ? labels : Collections.emptyMap();
    }

    public void setLabels(Map<String, String> labels) {
        this.labels = labels;
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Organization that = (Organization) o;
        return Objects.equals(getId(), that.getId())
               && Objects.equals(getLabels(), that.getLabels());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLabels());
    }

    public static final class Builder {

        private URI id;
        private Map<String, String> labels;

        public Builder() {
        }

        public Builder withId(URI id) {
            this.id = id;
            return this;
        }

        public Builder withLabels(Map<String, String> labels) {
            this.labels = labels;
            return this;
        }

        public Organization build() {
            return new Organization(this);
        }
    }
}
