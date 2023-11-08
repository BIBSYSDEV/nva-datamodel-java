package no.unit.nva.model.associatedartifacts;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.Objects;
import java.util.Optional;
import nva.commons.core.JacocoGenerated;

@JsonTypeInfo(use = Id.NAME, property = "type")
@JsonTypeName(NullRightsRetentionStrategy.TYPE_NAME)
public class NullRightsRetentionStrategy implements RightsRetentionStrategy {
    public static final String TYPE_NAME = "NullRightsRetentionStrategy";
    public static final String LEGAL_NOTE_FIELD = "legalNote";

    @JsonProperty(LEGAL_NOTE_FIELD)
    private final Optional<String> legalNote;

    @JsonCreator
    public NullRightsRetentionStrategy(@JsonProperty(LEGAL_NOTE_FIELD) String legalNote) {
        this.legalNote = Optional.ofNullable(legalNote);
    }

    public Optional<String> getLegalNote() {
        return legalNote;
    }

    @Override
    @JacocoGenerated
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NullRightsRetentionStrategy)) {
            return false;
        }
        NullRightsRetentionStrategy that = (NullRightsRetentionStrategy) o;
        return Objects.equals(getLegalNote(), that.getLegalNote());
    }

    @Override
    @JacocoGenerated
    public int hashCode() {
        return Objects.hash(getLegalNote());
    }
}
