package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.net.URI;
import java.util.Map;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public class Organization {

    private URI id;
    private Map<String,String> labels;

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
        return labels;
    }

    public void setLabels(Map<String, String> labels) {
        this.labels = labels;
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
