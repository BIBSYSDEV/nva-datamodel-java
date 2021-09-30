package no.unit.nva.model.instancetypes.artistic;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class ArtisticDesignExhibition extends ArtisticDesignLightDesign {
    public ArtisticDesignExhibition(@JsonProperty(DESCRIPTION) String description) {
        super(description);
    }
}
