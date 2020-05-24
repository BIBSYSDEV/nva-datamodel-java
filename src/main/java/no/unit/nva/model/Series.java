package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(name = "Series", value = SeriesImpl.class),
        @JsonSubTypes.Type(name = "NullSeries", value = NullSeries.class)
})
public interface Series {
    String getTitle();
}
