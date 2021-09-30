package no.unit.nva.model.instancetypes.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.instancetypes.PublicationInstance;
import no.unit.nva.model.pages.NullPages;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class ConferenceLecture implements PublicationInstance<NullPages> {

    @JsonProperty("pages")
    public static final NullPages pages = new NullPages();

    @JsonCreator
    public ConferenceLecture() {
    }

    @JsonGetter("pages")
    @Override
    public NullPages getPages() {
        return pages;
    }

    @JsonSetter("pages")
    @Override
    public void setPages(NullPages pages) {

    }

    @Override
    public boolean isPeerReviewed() {
        return false;
    }

    @Override
    public int hashCode() {
        return 222_222_222; // Implemented manually due to field-less class.
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        return o instanceof ConferenceLecture;
    }
}
