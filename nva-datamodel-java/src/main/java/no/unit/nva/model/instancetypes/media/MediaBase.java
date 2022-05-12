package no.unit.nva.model.instancetypes.media;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import java.util.Objects;
import no.unit.nva.model.instancetypes.PublicationInstance;
import no.unit.nva.model.pages.NullPages;

public class MediaBase implements PublicationInstance<NullPages> {

    @JsonProperty("pages")
    public static final NullPages pages = new NullPages();

    @JsonCreator
    public MediaBase() {
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
        return Objects.hash(super.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj instanceof MediaBase;
    }
}
