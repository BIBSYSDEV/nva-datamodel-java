package no.unit.nva.model.instancetypes.cartographic;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class CartographicMap {
}
