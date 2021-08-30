package no.unit.nva.model.contexttypes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import nva.commons.core.JacocoGenerated;

import java.net.URI;
import java.util.Objects;

public class MusicalContent implements LinkedContext {
    private URI linkedContext;

    @JsonCreator
    public MusicalContent(@JsonProperty("linkedContext") URI linkedContext) {
        this.linkedContext = linkedContext;
    }

    private MusicalContent(Builder builder) {
        this(builder.linkedContext);
    }

    @Override
    public URI getLinkedContext() {
        return linkedContext;
    }

    @Override
    public void setLinkedContext(URI linkedContext) {
        this.linkedContext = linkedContext;
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MusicalContent)) {
            return false;
        }
        MusicalContent that = (MusicalContent) o;
        return Objects.equals(getLinkedContext(), that.getLinkedContext());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getLinkedContext());
    }

    public static final class Builder {
        private URI linkedContext;

        public Builder() {
        }

        public Builder withLinkedContext(URI linkedContext) {
            this.linkedContext = linkedContext;
            return this;
        }

        public MusicalContent build() {
            return new MusicalContent(this);
        }
    }
}
