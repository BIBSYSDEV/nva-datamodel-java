package no.unit.nva.model.instancetypes.artistic.literaryarts.realization;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.contexttypes.PublishingHouse;
import no.unit.nva.model.contexttypes.UnconfirmedPublisher;
import no.unit.nva.model.exceptions.InvalidIsbnException;
import no.unit.nva.model.time.Instant;
import no.unit.nva.model.time.Time;
import nva.commons.core.JacocoGenerated;

import java.util.List;
import java.util.Objects;

import static java.util.Collections.emptyList;
import static java.util.Objects.nonNull;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class PrintedMatter implements LiteraryArtsOutput, WithIsbn {
    public static final String PUBLISHER = "publisher";
    public static final String DATE = "date";
    public static final String ISBN = "isbns";
    public static final String PAGES = "pages";

    @JsonProperty(PUBLISHER)
    private final PublishingHouse publisher;
    @JsonProperty(DATE)
    private final Time date;
    @JsonProperty(ISBN)
    private final List<String> isbns;
    @JsonProperty(PAGES)
    private final String pages;

    public PrintedMatter(@JsonProperty(PUBLISHER) UnconfirmedPublisher publisher,
                         @JsonProperty(DATE) Instant date,
                         @JsonProperty(ISBN) List<String> isbns,
                         @JsonProperty(PAGES) String pages) throws
            InvalidIsbnException {
        this.publisher = publisher;
        this.date = date;
        this.isbns = nonNull(isbns) ? validateIsbn(isbns) : emptyList();
        this.pages = pages;
    }

    public PublishingHouse getPublisher() {
        return publisher;
    }

    public Time getDate() {
        return date;
    }

    @Override
    public List<String> getIsbns() {
        return isbns;
    }

    public String getPages() {
        return pages;
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PrintedMatter)) {
            return false;
        }
        PrintedMatter that = (PrintedMatter) o;
        return Objects.equals(getPublisher(), that.getPublisher())
                && Objects.equals(getDate(), that.getDate())
                && Objects.equals(getIsbns(), that.getIsbns())
                && Objects.equals(getPages(), that.getPages());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getPublisher(), getDate(), getIsbns(), getPages());
    }
}
