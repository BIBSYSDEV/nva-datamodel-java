package no.unit.nva.model.instancetypes.artistic.literaryarts;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import nva.commons.core.JacocoGenerated;

import java.util.Objects;

public class LiteraryArtsSubtype {
    public static final String TYPE = "type";
    public static final String DESCRIPTION = "description";
    @JsonProperty(TYPE)
    private final LiteraryArtsSubtypeEnum type;

    public static LiteraryArtsSubtype createOther(String description) {
        return new LiteraryArtsSubtypeOther(LiteraryArtsSubtypeEnum.OTHER, description);
    }

    @JacocoGenerated
    @JsonCreator
    public static LiteraryArtsSubtype fromJson(@JsonProperty(TYPE) LiteraryArtsSubtypeEnum type,
                                               @JsonProperty(DESCRIPTION) String description) {
        if (LiteraryArtsSubtypeEnum.OTHER.equals(type)) {
            return createOther(description);
        }
        return new LiteraryArtsSubtype(type);
    }

    public static LiteraryArtsSubtype create(LiteraryArtsSubtypeEnum type) {
        return new LiteraryArtsSubtype(type);
    }

    protected LiteraryArtsSubtype(LiteraryArtsSubtypeEnum type) {
        this.type = type;
    }

    public LiteraryArtsSubtypeEnum getType() {
        return type;
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LiteraryArtsSubtype)) {
            return false;
        }
        LiteraryArtsSubtype that = (LiteraryArtsSubtype) o;
        return getType() == that.getType();
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getType());
    }
}
