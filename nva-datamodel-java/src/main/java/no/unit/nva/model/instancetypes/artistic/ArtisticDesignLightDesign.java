package no.unit.nva.model.instancetypes.artistic;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.instancetypes.PublicationInstance;
import no.unit.nva.model.pages.NullPages;
import nva.commons.core.JacocoGenerated;

import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class ArtisticDesignLightDesign implements PublicationInstance<NullPages>, ArtisticDescription {
    public static final String DESCRIPTION = "description";

    @JsonProperty(DESCRIPTION)
    private final String description;

    public ArtisticDesignLightDesign(@JsonProperty(DESCRIPTION) String description) {
        this.description = description;
    }

    @Override
    public NullPages getPages() {
        return new NullPages();
    }

    @Override
    public void setPages(NullPages pages) {

    }

    @Override
    public boolean isPeerReviewed() {
        return false;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ArtisticDesignLightDesign)) {
            return false;
        }
        ArtisticDesignLightDesign that = (ArtisticDesignLightDesign) o;
        return Objects.equals(getDescription(), that.getDescription());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getDescription());
    }
}
