package no.unit.nva.model.associatedartifacts;

import static no.unit.nva.model.associatedartifacts.OverriddenRightsRetentionStrategy.LEGAL_NOTE_FIELD;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.Objects;
import nva.commons.core.JacocoGenerated;

@JsonTypeInfo(use = Id.NAME, property = "type")
@JsonTypeName(NullRightsRetentionStrategy.TYPE_NAME)
public final class NullRightsRetentionStrategy implements RightsRetentionStrategy {

    public static final String TYPE_NAME = "NullRightsRetentionStrategy";
    public static final String LEGAL_NOTE_FIELD = "legalNote";
    private final String legalNote;

    @JsonCreator
    private NullRightsRetentionStrategy(@JsonProperty(LEGAL_NOTE_FIELD) String legalNote) {
        this.legalNote = legalNote;
    }

    public static NullRightsRetentionStrategy create() {
        return new NullRightsRetentionStrategy(null);
    }

    public static NullRightsRetentionStrategy create(String legalNote) {
        return new NullRightsRetentionStrategy(legalNote);
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(legalNote);
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
        NullRightsRetentionStrategy that = (NullRightsRetentionStrategy) o;
        return Objects.equals(legalNote, that.legalNote);
    }

    public String getLegalNote() {
        return legalNote;
    }
}
