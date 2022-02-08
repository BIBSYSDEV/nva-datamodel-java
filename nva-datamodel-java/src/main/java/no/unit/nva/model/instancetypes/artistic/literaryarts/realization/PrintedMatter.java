package no.unit.nva.model.instancetypes.artistic.literaryarts.realization;

import static java.util.Collections.emptyList;
import static java.util.Objects.nonNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import java.util.List;
import java.util.Objects;
import no.unit.nva.model.contexttypes.PublishingHouse;
import no.unit.nva.model.contexttypes.UnconfirmedPublisher;
import no.unit.nva.model.exceptions.InvalidIsbnException;
import no.unit.nva.model.time.Instant;
import no.unit.nva.model.time.Time;
import nva.commons.core.JacocoGenerated;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = As.EXISTING_PROPERTY, property = "type", visible = true)
public class PrintedMatter implements LiteraryArtsOutput, WithIsbn {

    public static final String PUBLISHER = "publisher";
    public static final String DATE = "date";
    public static final String ISBN = "isbns";
    public static final String PAGES = "pages";
    public static final String TYPE_VALUE = "PrintedMatter";

    @JsonProperty(PUBLISHER)
    private final PublishingHouse publisher;
    @JsonProperty(DATE)
    private final Time date;
    @JsonProperty(ISBN)
    private final List<String> isbns;
    @JsonProperty(PAGES)
    private final String pages;
    @JsonProperty(TYPE)
    private final String type;

    public PrintedMatter(@JsonProperty(PUBLISHER) UnconfirmedPublisher publisher,
                         @JsonProperty(DATE) Instant date,
                         @JsonProperty(ISBN) List<String> isbns,
                         @JsonProperty(PAGES) String pages,
                         @JsonProperty(TYPE) String type) throws
                                                          InvalidIsbnException {
        this.publisher = publisher;
        this.date = date;
        this.isbns = nonNull(isbns) ? validateIsbn(isbns) : emptyList();
        this.pages = pages;
        this.type = validateType(type);
    }

    public String getType() {
        return type;
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

    @Override
    @JacocoGenerated
    public int hashCode() {
        return Objects.hash(getPublisher(), getDate(), getIsbns(), getPages(), getType());
    }

    @Override
    @JacocoGenerated
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
               && Objects.equals(getPages(), that.getPages())
               && Objects.equals(getType(), that.getType());
    }

    private String validateType(String type) {
        if (TYPE_VALUE.equalsIgnoreCase(type)) {
            return TYPE_VALUE;
        }
        throw new IllegalArgumentException("Illegal type" + type);
    }
}
