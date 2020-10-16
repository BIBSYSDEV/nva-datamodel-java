package no.unit.nva.model.instancetypes.other;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.instancetypes.NonPeerReviewed;
import no.unit.nva.model.pages.Range;
import nva.commons.utils.JacocoGenerated;

import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Other extends NonPeerReviewed<Range> {

    private Range pages;

    @JsonCreator
    public Other(@JsonProperty("pages") Range pages) {
        super();
        this.pages = pages;
    }

    private Other(Builder builder) {
        this(builder.pages);
    }

    @Override
    public Range getPages() {
        return pages;
    }

    @Override
    public void setPages(Range pages) {
        this.pages = pages;
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Other)) {
            return false;
        }
        Other other = (Other) o;
        return Objects.equals(getPages(), other.getPages());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getPages());
    }


    public static final class Builder {
        private Range pages;

        public Builder() {
        }

        public Builder withPages(Range pages) {
            this.pages = pages;
            return this;
        }

        public Other build() {
            return new Other(this);
        }
    }
}
