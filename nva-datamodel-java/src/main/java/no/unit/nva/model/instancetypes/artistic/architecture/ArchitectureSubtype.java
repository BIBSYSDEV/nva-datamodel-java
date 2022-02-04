package no.unit.nva.model.instancetypes.artistic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import nva.commons.core.JacocoGenerated;

import java.util.Objects;

public class ArchitectureSubtype {
    public static final String TYPE = "type";
    public static final String DESCRIPTION = "description";
    @JsonProperty(TYPE)
    private final ArchitectureSubtypeEnum type;

    public static ArchitectureSubtype createOther(String description) {
        return new ArchitectureSubtypeOther(ArchitectureSubtypeEnum.OTHER, description);
    }

    @JacocoGenerated
    @JsonCreator
    public static ArchitectureSubtype fromJson(@JsonProperty(TYPE) ArchitectureSubtypeEnum type,
                                               @JsonProperty(DESCRIPTION) String description) {
        if (ArchitectureSubtypeEnum.OTHER.equals(type)) {
            return createOther(description);
        }
        return new ArchitectureSubtype(type);
    }

    public static ArchitectureSubtype create(ArchitectureSubtypeEnum type) {
        return new ArchitectureSubtype(type);
    }

    protected ArchitectureSubtype(ArchitectureSubtypeEnum type) {
        this.type = type;
    }

    public ArchitectureSubtypeEnum getType() {
        return type;
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ArchitectureSubtype)) {
            return false;
        }
        ArchitectureSubtype architectureSubtype = (ArchitectureSubtype) o;
        return getType() == architectureSubtype.getType();
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getType());
    }
}
