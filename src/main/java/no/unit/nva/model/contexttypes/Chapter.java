package no.unit.nva.model.contexttypes;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import nva.commons.utils.JacocoGenerated;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Chapter implements LinkedContext {

    public static final String ERROR_TEMPLATE = "The URI <%s> is an invalid context for a Chapter";
    private URI linkedContext;

    public Chapter() {
    }

    private Chapter(Builder builder) {
        linkedContext = builder.linkedContext;
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
        if (!(o instanceof Chapter)) {
            return false;
        }
        Chapter chapter = (Chapter) o;
        return Objects.equals(getLinkedContext(), chapter.getLinkedContext());
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

        public Chapter build() {
            return new Chapter(this);
        }
    }
}
