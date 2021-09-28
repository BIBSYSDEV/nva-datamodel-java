package no.unit.nva.model.instancetypes.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.pages.MonographPages;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Lecture extends ConferenceLecture {
    public Lecture(@JsonProperty("pages") MonographPages pages) {
        super(pages);
    }
}
