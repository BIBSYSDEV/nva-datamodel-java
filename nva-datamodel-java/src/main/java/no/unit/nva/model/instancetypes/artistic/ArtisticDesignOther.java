package no.unit.nva.model.instancetypes.artistic;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import nva.commons.core.JacocoGenerated;

import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class ArtisticDesignOther extends ArtisticDesignLightDesign {
    public static final String TYPE_DESCRIPTION = "typeDescription";

    @JsonProperty(TYPE_DESCRIPTION)
    private final String typeDescription;

    public ArtisticDesignOther(@JsonProperty(DESCRIPTION) String description,
                               @JsonProperty(TYPE_DESCRIPTION) String typeDescription) {
        super(description);
        this.typeDescription = typeDescription;
    }

    public String getTypeDescription() {
        return typeDescription;
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ArtisticDesignOther)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        ArtisticDesignOther that = (ArtisticDesignOther) o;
        return Objects.equals(getTypeDescription(), that.getTypeDescription());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTypeDescription());
    }
}
