package no.unit.nva.model.time;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(name = "Period", value = Period.class),
    @JsonSubTypes.Type(name = "Instant", value = Instant.class)
})
public interface Time {
}
