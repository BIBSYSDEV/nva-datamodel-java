package no.unit.nva.model.contexttypes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(name = "Series", value = Series.class),
    @JsonSubTypes.Type(name = "UnconfirmedSeries", value = UnconfirmedSeries.class)
})
public interface BookSeries {

    @JsonIgnore
    boolean isConfirmed();
}
