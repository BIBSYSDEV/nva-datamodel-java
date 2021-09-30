package no.unit.nva.model.instancetypes.artistic;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class ArtisticDesignWebDesign extends ArtisticDesignLightDesign {
    public ArtisticDesignWebDesign(@JsonProperty(DESCRIPTION) String description) {
        super(description);
    }
}
