package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.net.URI;
import java.util.Objects;
import nva.commons.core.JacocoGenerated;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Grant {
    private String source;
    private URI id;

    public Grant() {

    }

    private Grant(Builder builder) {
        setSource(builder.source);
        setId(builder.id);
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public URI getId() {
        return id;
    }

    public void setId(URI id) {
        this.id = id;
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Grant)) {
            return false;
        }
        Grant grant = (Grant) o;
        return Objects.equals(getSource(), grant.getSource())
                && Objects.equals(getId(), grant.getId());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getSource(), getId());
    }

    public static final class Builder {
        private String source;
        private URI id;

        public Builder() {
        }

        public Builder withSource(String source) {
            this.source = source;
            return this;
        }

        public Builder withId(URI id) {
            this.id = id;
            return this;
        }

        public Grant build() {
            return new Grant(this);
        }
    }
}
