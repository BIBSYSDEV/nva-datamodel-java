package no.unit.nva.model.instancetypes.artistic.literaryarts.manifestation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.PublicationDate;
import no.unit.nva.model.contexttypes.PublishingHouse;
import no.unit.nva.model.pages.MonographPages;
import nva.commons.core.JacocoGenerated;

import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class LiteraryArtsMonograph implements LiteraryArtsManifestation{
    public static final String PUBLISHER_FIELD = "publisher";
    public static final String DATE_FIELD = "publicationDate";
    public static final String ISBN_FIELD = "isbn";
    public static final String PAGES_FIELD = "pages";
    @JsonProperty(PUBLISHER_FIELD) private final PublishingHouse publisher;
    @JsonProperty(DATE_FIELD) private final PublicationDate publicationDate;
    @JsonProperty(ISBN_FIELD) private final String isbn;
    @JsonProperty(PAGES_FIELD) private final MonographPages pages;

    @JsonCreator
    public LiteraryArtsMonograph(@JsonProperty(PUBLISHER_FIELD) PublishingHouse publisher,
                                 @JsonProperty(DATE_FIELD) PublicationDate publicationDate,
                                 @JsonProperty(ISBN_FIELD) String isbn,
                                 @JsonProperty(PAGES_FIELD) MonographPages pages) {
        this.publisher = publisher;
        this.publicationDate = publicationDate;
        this.isbn = isbn;
        this.pages = pages;
    }

    public PublishingHouse getPublisher() {
        return publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public MonographPages getPages() {
        return pages;
    }

    @Override
    public PublicationDate getPublicationDate() {
        return publicationDate;
    }

    @Override
    @JacocoGenerated
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LiteraryArtsMonograph)) {
            return false;
        }
        LiteraryArtsMonograph that = (LiteraryArtsMonograph) o;
        return Objects.equals(getPublisher(), that.getPublisher())
                && Objects.equals(getPublicationDate(), that.getPublicationDate())
                && Objects.equals(getIsbn(), that.getIsbn())
                && Objects.equals(getPages(), that.getPages());
    }

    @Override
    @JacocoGenerated
    public int hashCode() {
        return Objects.hash(getPublisher(), getPublicationDate(), getIsbn(), getPages());
    }
}
