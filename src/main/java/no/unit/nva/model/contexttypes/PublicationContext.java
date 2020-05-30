package no.unit.nva.model.contexttypes;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(name = "Journal", value = Journal.class),
        @JsonSubTypes.Type(name = "Book", value = Book.class),
        @JsonSubTypes.Type(name = "Report", value = Report.class),
        @JsonSubTypes.Type(name = "Degree", value = Degree.class),
        @JsonSubTypes.Type(name = "Chapter", value = Chapter.class)
})
public interface PublicationContext {
}
