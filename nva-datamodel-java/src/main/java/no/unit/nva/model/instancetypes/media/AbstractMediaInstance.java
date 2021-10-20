package no.unit.nva.model.instancetypes.media;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import no.unit.nva.model.instancetypes.PublicationInstance;
import no.unit.nva.model.pages.NullPages;
import nva.commons.core.JacocoGenerated;

public abstract class AbstractMediaInstance implements PublicationInstance<NullPages> {

    public static final String PAGES = "pages";

    @JsonProperty(PAGES)
    private static final NullPages pages = new NullPages();

    @JsonGetter(PAGES)
    @Override
    public NullPages getPages() {
        return pages;
    }

    @JsonSetter(PAGES)
    @Override
    public abstract void setPages(NullPages pages);

    @Override
    public boolean isPeerReviewed() {
        return false;
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return 100_201_314; // Implemented manually due to field-less class.
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        return o instanceof AbstractMediaInstance;
    }
}
