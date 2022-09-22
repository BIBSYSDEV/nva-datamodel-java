package no.unit.nva.model.instancetypes.media;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import java.util.Objects;
import no.unit.nva.model.instancetypes.PublicationInstance;
import no.unit.nva.model.pages.NullPages;

public class MediaBase implements PublicationInstance<NullPages> {

    public static final String PAGES_FIELD = "pages";

    @JsonCreator
    public MediaBase() {
    }

    @JsonGetter(PAGES_FIELD)
    @Override
    public NullPages getPages() {
        return NullPages.NULL_PAGES;
    }

    @JsonSetter(PAGES_FIELD)
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
