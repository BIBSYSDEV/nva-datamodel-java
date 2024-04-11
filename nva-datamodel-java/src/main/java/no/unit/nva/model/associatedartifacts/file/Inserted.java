package no.unit.nva.model.associatedartifacts.file;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.Instant;
import java.util.Objects;
import no.unit.nva.model.Username;
import nva.commons.core.JacocoGenerated;

@JsonTypeInfo(use = Id.NAME, property = "type")
@JsonTypeName(PublishedFile.TYPE)
public class Inserted {

    @JsonProperty("insertedBy")
    private final Username insertedBy;

    @JsonProperty("insertedDate")
    private final Instant insertedDate;

    /**
     * Constructor for no.unit.nva.file.model.Inserted.
     *
     * @param insertedBy        The person or job that inserted the file into the system
     * @param insertedDate      The date which the file was inserted into the system
     */
    @JsonCreator
    public Inserted(@JsonProperty("insertedBy") Username insertedBy,
                    @JsonProperty("insertedDate") Instant insertedDate) {
        this.insertedBy = insertedBy;
        this.insertedDate = insertedDate;
    }

    @JacocoGenerated
    public Username getInsertedBy() {
        return insertedBy;
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Inserted inserted = (Inserted) o;
        return Objects.equals(insertedBy, inserted.insertedBy) && Objects.equals(insertedDate,
                                                                                 inserted.insertedDate);
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(insertedBy, insertedDate);
    }
}
