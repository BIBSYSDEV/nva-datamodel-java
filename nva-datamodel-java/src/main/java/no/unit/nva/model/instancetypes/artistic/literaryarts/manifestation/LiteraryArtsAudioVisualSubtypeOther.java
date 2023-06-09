package no.unit.nva.model.instancetypes.artistic.literaryarts.manifestation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import nva.commons.core.JacocoGenerated;

import java.util.Objects;

public class LiteraryArtsAudioVisualSubtypeOther extends LiteraryArtsAudioVisualSubtype {
    @JsonProperty(DESCRIPTION_FIELD)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String description;

    @JsonCreator
    public LiteraryArtsAudioVisualSubtypeOther(@JsonProperty(TYPE_FIELD) LiteraryArtsAudioVisualSubtypeEnum type,
                                               @JsonProperty(DESCRIPTION_FIELD) String description) {
        super(type);
        this.description = description;
    }

    @Override
    @JacocoGenerated
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LiteraryArtsAudioVisualSubtypeOther that)) {
            return false;
        }
        return Objects.equals(description, that.description);
    }

    @Override
    @JacocoGenerated
    public int hashCode() {
        return Objects.hash(description);
    }
}
