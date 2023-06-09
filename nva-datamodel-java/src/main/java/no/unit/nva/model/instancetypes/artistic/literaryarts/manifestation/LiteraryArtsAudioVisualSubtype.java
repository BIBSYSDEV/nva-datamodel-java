package no.unit.nva.model.instancetypes.artistic.literaryarts.manifestation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import nva.commons.core.JacocoGenerated;

import java.util.Objects;

public class LiteraryArtsAudioVisualSubtype {

    public static final String TYPE_FIELD = "type";
    public static final String DESCRIPTION_FIELD = "description";
    @JsonProperty(TYPE_FIELD)
    private final LiteraryArtsAudioVisualSubtypeEnum type;

    public static LiteraryArtsAudioVisualSubtype createOther(String description) {
        return new LiteraryArtsAudioVisualSubtypeOther(LiteraryArtsAudioVisualSubtypeEnum.OTHER, description);
    }

    @JacocoGenerated
    @JsonCreator
    public static LiteraryArtsAudioVisualSubtype fromJson(@JsonProperty(TYPE_FIELD) LiteraryArtsAudioVisualSubtypeEnum type,
                                                          @JsonProperty(DESCRIPTION_FIELD) String description) {
        if (LiteraryArtsAudioVisualSubtypeEnum.OTHER.equals(type)) {
            return createOther(description);
        }
        return new LiteraryArtsAudioVisualSubtype(type);
    }

    public static LiteraryArtsAudioVisualSubtype create(LiteraryArtsAudioVisualSubtypeEnum type) {
        return new LiteraryArtsAudioVisualSubtype(type);
    }

    protected LiteraryArtsAudioVisualSubtype(LiteraryArtsAudioVisualSubtypeEnum type) {
        this.type = type;
    }

    public LiteraryArtsAudioVisualSubtypeEnum getType() {
        return type;
    }

    @Override
    @JacocoGenerated
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LiteraryArtsAudioVisualSubtype that)) {
            return false;
        }
        return getType() == that.getType();
    }

    @Override
    @JacocoGenerated
    public int hashCode() {
        return Objects.hash(getType());
    }
}
