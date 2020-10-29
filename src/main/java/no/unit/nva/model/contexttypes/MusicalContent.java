package no.unit.nva.model.contexttypes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import nva.commons.utils.JacocoGenerated;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

public class MusicalContent implements LinkedContext {
    public static final String ERROR_TEMPLATE = "The URI <%s> is an invalid context for MusicalContent";

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
    public void setLinkedContext(String linkedContext) {
        try {
            this.linkedContext = new URL(linkedContext).toURI();
        } catch (URISyntaxException | MalformedURLException e) {
            throw new IllegalArgumentException(String.format(ERROR_TEMPLATE, linkedContext));
        }
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
