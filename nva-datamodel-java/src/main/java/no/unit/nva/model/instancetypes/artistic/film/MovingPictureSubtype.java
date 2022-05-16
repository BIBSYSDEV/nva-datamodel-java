package no.unit.nva.model.instancetypes.artistic.film;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import nva.commons.core.JacocoGenerated;

import java.util.Objects;

public class MovingPictureSubtype {
    public static final String TYPE = "type";
    @JsonProperty(TYPE)
    private final MovingPictureSubtypeEnum type;

    public static MovingPictureSubtype createOther(String description) {
        return new MovingPictureSubtypeOther(MovingPictureSubtypeEnum.OTHER, description);
    }

    @JacocoGenerated
    @JsonCreator
    public static MovingPictureSubtype fromJson(@JsonProperty(TYPE) MovingPictureSubtypeEnum type,
                                                 @JsonProperty("description") String description) {
        if (MovingPictureSubtypeEnum.OTHER.equals(type)) {
            return createOther(description);
        }
        return new MovingPictureSubtype(type);
    }

    public static MovingPictureSubtype create(MovingPictureSubtypeEnum type) {
        return new MovingPictureSubtype(type);
    }

    protected MovingPictureSubtype(MovingPictureSubtypeEnum type) {
        this.type = type;
    }

    public MovingPictureSubtypeEnum getType() {
        return type;
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MovingPictureSubtype)) {
            return false;
        }
        MovingPictureSubtype movingPictureSubtype = (MovingPictureSubtype) o;
        return getType() == movingPictureSubtype.getType();
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getType());
    }
}