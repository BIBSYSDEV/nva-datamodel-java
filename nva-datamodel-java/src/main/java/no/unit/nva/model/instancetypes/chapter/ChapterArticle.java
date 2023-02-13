package no.unit.nva.model.instancetypes.chapter;

import no.unit.nva.model.instancetypes.PublicationInstance;
import no.unit.nva.model.pages.Range;
import nva.commons.core.JacocoGenerated;

import java.util.Objects;

public class ChapterArticle implements PublicationInstance<Range> {

    public static final String PAGES_FIELD = "pages";
    private final Range pages;

    public ChapterArticle(Range pages) {
        this.pages = pages;
    }

    @Override
    public Range getPages() {
        return pages;
    }

    @Override
    @JacocoGenerated
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChapterArticle)) {
            return false;
        }
        ChapterArticle that = (ChapterArticle) o;
        return Objects.equals(getPages(), that.getPages());
    }

    @Override
    @JacocoGenerated
    public int hashCode() {
        return Objects.hash(getPages());
    }
}
