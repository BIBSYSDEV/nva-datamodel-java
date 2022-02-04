package no.unit.nva.model.instancetypes.artistic;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ArtisticDesignSubtypeEnum {
    CLOTHING_DESIGN("ClothingDesign"),
    EXHIBITION("Exhibition"),
    GRAPHIC_DESIGN("GraphicDesign"),
    ILLUSTRATION("Illustration"),
    INTERACTION_DESIGN("InteractionDesign"),
    INTERIOR_DESIGN("InteriorDesign"),
    LIGHT_DESIGN("LightDesign"),
    OTHER("Other"),
    PRODUCT_DESIGN("ProductDesign"),
    SERVICE_DESIGN("ServiceDesign"),
    WEB_DESIGN("WebDesign");

    @JsonValue
    private final String type;

    ArtisticDesignSubtypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
