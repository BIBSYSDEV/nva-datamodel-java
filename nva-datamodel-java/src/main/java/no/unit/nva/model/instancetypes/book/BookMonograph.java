package no.unit.nva.model.instancetypes.book;

import no.unit.nva.model.pages.MonographPages;
import no.unit.nva.model.instancetypes.PublicationInstance;
import nva.commons.core.JacocoGenerated;

import java.util.Objects;

public class BookMonograph implements PublicationInstance<MonographPages> {

    public static final String PAGES_FIELD = "pages";
    private final MonographPages pages;

    public BookMonograph(MonographPages pages) {
        this.pages = pages;
    }

    @Override
    public MonographPages getPages() {
        return pages;
    }

    @Override
    @JacocoGenerated
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BookMonograph)) {
            return false;
        }
        BookMonograph that = (BookMonograph) o;
        return Objects.equals(getPages(), that.getPages());
    }

    @Override
    @JacocoGenerated
    public int hashCode() {
        return Objects.hash(getPages());
    }
}
