package no.unit.nva.model.contexttypes;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * PublicationContext provides a common root object for contexts of type
 * {@link Partitive} and {@link BasicContext}.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(name = "Book", value = Book.class),
    @JsonSubTypes.Type(name = "Report", value = Report.class),
    @JsonSubTypes.Type(name = "Degree", value = Degree.class),
    @JsonSubTypes.Type(name = "Chapter", value = Chapter.class),
    @JsonSubTypes.Type(name = "MusicalContent", value = MusicalContent.class),
    @JsonSubTypes.Type(name = "Cartograph", value = Cartograph.class),
    @JsonSubTypes.Type(name = "Journal", value = Journal.class),
    @JsonSubTypes.Type(name = "UnconfirmedJournal", value = UnconfirmedJournal.class)
})
public interface PublicationContext {
}
