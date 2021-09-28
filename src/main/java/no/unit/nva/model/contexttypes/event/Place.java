package no.unit.nva.model.contexttypes.event;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(name = "UnconfirmedPlace", value = UnconfirmedPlace.class)
})
public interface Place {
    // This class is used to allow extension
}
