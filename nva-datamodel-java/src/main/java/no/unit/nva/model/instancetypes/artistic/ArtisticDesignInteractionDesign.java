package no.unit.nva.model.instancetypes.artistic;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class ArtisticDesignInteractionDesign extends ArtisticDesignLightDesign {
    public ArtisticDesignInteractionDesign(@JsonProperty(DESCRIPTION) String description) {
        super(description);
    }
}
