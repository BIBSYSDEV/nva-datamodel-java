package no.unit.nva.model.contexttypes;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.Level;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(name = "Journal", value = Journal.class),
        @JsonSubTypes.Type(name = "Book", value = Book.class),
        @JsonSubTypes.Type(name = "Report", value = Report.class),
        @JsonSubTypes.Type(name = "Degree", value = Degree.class)
})
public interface PublicationContext {

    Level getLevel();

    void setLevel(Level level);

    boolean isOpenAccess();

    void setOpenAccess(boolean openAccess);

    boolean isPeerReviewed();

    void setPeerReviewed(boolean peerReviewed);
}
