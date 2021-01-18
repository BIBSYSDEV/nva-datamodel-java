package no.unit.nva.model.contexttypes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import nva.commons.core.JacocoGenerated;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Cartograph implements LinkedContext {
    public static final String ERROR_TEMPLATE = "The URI <%s> is an invalid context for a Cartograph";

    private URI linkedContext;

    @JsonCreator
    public Cartograph(@JsonProperty("linkedContext") URI linkedContext) {
        this.linkedContext = linkedContext;
    }

    private Cartograph(Builder builder) {
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
        if (!(o instanceof Cartograph)) {
            return false;
        }
        Cartograph that = (Cartograph) o;
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

        public Cartograph build() {
            return new Cartograph(this);
        }
    }
}
