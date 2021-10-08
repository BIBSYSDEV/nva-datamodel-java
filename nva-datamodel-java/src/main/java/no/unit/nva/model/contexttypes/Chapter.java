package no.unit.nva.model.contexttypes;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.net.URI;
import java.util.Objects;
import nva.commons.core.JacocoGenerated;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Chapter implements Partitive {

    public static final String ERROR_TEMPLATE = "The URI <%s> is an invalid context for a Chapter";
    private URI partOf;

    public Chapter() {
    }

    private Chapter(Builder builder) {
        partOf = builder.partOf;
    }

    @Override
    public URI getPartOf() {
        return partOf;
    }

    @Override
    public void setPartOf(URI partOf) {
        validateUri(partOf);
        this.partOf = partOf;
    }

    @Override
    public String getErrorTemplate() {
        return ERROR_TEMPLATE;
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
        return Objects.equals(getPartOf(), chapter.getPartOf());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getPartOf());
    }

    public static final class Builder {
        private URI partOf;

        public Builder() {
        }

        public Builder withPartOf(URI partOf) {
            this.partOf = partOf;
            return this;
        }

        public Chapter build() {
            return new Chapter(this);
        }
    }
}
