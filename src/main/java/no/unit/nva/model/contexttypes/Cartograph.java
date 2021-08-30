package no.unit.nva.model.contexttypes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import nva.commons.core.JacocoGenerated;

import java.net.URI;
import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Cartograph implements Partitive {

    private URI partOf;

    @JsonCreator
    public Cartograph(@JsonProperty("partOf") URI partOf) {
        this.partOf = partOf;
    }

    private Cartograph(Builder builder) {
        this(builder.partOf);
    }

    @Override
    public URI getPartOf() {
        return partOf;
    }

    @Override
    public void setPartOf(URI partOf) {
        this.partOf = partOf;
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
        return Objects.equals(getPartOf(), that.getPartOf());
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

        public Cartograph build() {
            return new Cartograph(this);
        }
    }
}
