package no.unit.nva.model.instancetypes.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class ConferencePoster extends ConferenceLecture {
    @JsonCreator
    public ConferencePoster() {
        super();
    }
}
