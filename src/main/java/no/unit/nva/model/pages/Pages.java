package no.unit.nva.model.pages;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(name = "Range", value = Range.class),
    @JsonSubTypes.Type(name = "MonographPages", value = MonographPages.class),
    @JsonSubTypes.Type(name = "TemporalExtent", value = TemporalExtent.class)
})
public interface Pages {
    // A marker pattern interface, it may be useful later, at the moment it remains empty.
}
