package no.unit.nva.model.instancetypes.book;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.instancetypes.PeerReviewedMonograph;
import no.unit.nva.model.pages.MonographPages;
import nva.commons.core.JacocoGenerated;

import java.util.Objects;

import static java.util.Objects.isNull;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class BookMonograph extends PeerReviewedMonograph {

    public static final String PAGES_FIELD = "pages";
    private static final String CONTENT_TYPE_FIELD = "contentType";
    public static final String ORIGINAL_RESEARCH_FIELD = "originalResearch";
    public static final String PEER_REVIEWED_FIELD = "peerReviewed";
    private final boolean originalResearch;

    public BookMonograph(@JsonProperty(PAGES_FIELD) MonographPages pages,
                         @JsonProperty(ORIGINAL_RESEARCH_FIELD) boolean originalResearch,
                         @JsonProperty(PEER_REVIEWED_FIELD) boolean peerReviewed) {
        super(pages, peerReviewed);
        this.originalResearch = originalResearch;
    }

    @JsonCreator
    public static BookMonograph fromJson(@JsonProperty(PAGES_FIELD) MonographPages pages,
                                         @JsonProperty(CONTENT_TYPE_FIELD) BookMonographContentType contentType,
                                         @JsonProperty(ORIGINAL_RESEARCH_FIELD) boolean originalResearch,
                                         @JsonProperty(PEER_REVIEWED_FIELD) boolean peerReviewed) {
        if (BookMonographContentType.ACADEMIC_MONOGRAPH.equals(contentType)) {
            return new AcademicMonograph(pages, peerReviewed);
        } else if (BookMonographContentType.ENCYCLOPEDIA.equals(contentType)) {
            return new Encyclopedia(pages, peerReviewed);
        } else if (BookMonographContentType.EXHIBITION_CATALOG.equals(contentType)) {
            return new ExhibitionCatalog(pages, peerReviewed);
        } else if (BookMonographContentType.NON_FICTION_MONOGRAPH.equals(contentType)) {
            return new NonFictionMonograph(pages, peerReviewed);
        } else if (BookMonographContentType.POPULAR_SCIENCE_MONOGRAPH.equals(contentType)) {
            return new PopularScienceMonograph(pages, peerReviewed);
        } else if (BookMonographContentType.TEXTBOOK.equals(contentType)) {
            return new Textbook(pages, peerReviewed);
        } else if (isNull(contentType)) {
            return new AcademicMonograph(pages, peerReviewed);
        } else {
            throw new UnsupportedOperationException("The Book Monograph subtype is unknown");
        }
    }

    public boolean isOriginalResearch() {
        return originalResearch;
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
        if (!super.equals(o)) {
            return false;
        }
        BookMonograph that = (BookMonograph) o;
        return isOriginalResearch() == that.isOriginalResearch();
    }

    @Override
    @JacocoGenerated
    public int hashCode() {
        return Objects.hash(super.hashCode(), isOriginalResearch());
    }
}
