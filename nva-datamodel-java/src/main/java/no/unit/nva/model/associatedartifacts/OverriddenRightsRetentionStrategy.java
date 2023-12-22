package no.unit.nva.model.associatedartifacts;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.Objects;
import nva.commons.core.JacocoGenerated;

@JsonTypeInfo(use = Id.NAME, property = "type")
@JsonTypeName(OverriddenRightsRetentionStrategy.TYPE_NAME)
public final class OverriddenRightsRetentionStrategy implements RightsRetentionStrategy {

    public static final String TYPE_NAME = "OverriddenRightsRetentionStrategy";
    public static final String LEGAL_NOTE_FIELD = "legalNote";
    private final String legalNote;

    @JsonCreator
    private OverriddenRightsRetentionStrategy(@JsonProperty(LEGAL_NOTE_FIELD) String legalNote) {
        this.legalNote = legalNote;
    }

    public static OverriddenRightsRetentionStrategy create(String legalNote) {
        return new OverriddenRightsRetentionStrategy(legalNote);
    }

    public static OverriddenRightsRetentionStrategy create() {
        return new OverriddenRightsRetentionStrategy(null);
    }

    public String getLegalNote() {
        return legalNote;
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
        OverriddenRightsRetentionStrategy that = (OverriddenRightsRetentionStrategy) o;
        return Objects.equals(legalNote, that.legalNote);
    }
}
