package no.unit.nva.model.instancetypes.artistic.music;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import nva.commons.core.JacocoGenerated;

public class ConcertProgramme {

    public static final String TITLE = "title";
    public static final String COMPOSER = "composer";
    public static final String PREMIERE = "premiere";
    @JsonProperty(TITLE)
    private final String title;
    @JsonProperty(COMPOSER)
    private final String composer;
    @JsonProperty(PREMIERE)
    private final boolean premiere;

    @JsonCreator
    public ConcertProgramme(@JsonProperty(TITLE) String title,
                            @JsonProperty(COMPOSER) String composer,
                            @JsonProperty(PREMIERE) boolean premiere) {
        this.title = title;
        this.composer = composer;
        this.premiere = premiere;
    }

    public String getTitle() {
        return title;
    }

    public String getComposer() {
        return composer;
    }

    public boolean isPremiere() {
        return premiere;
    }

    @Override
    @JacocoGenerated
    public int hashCode() {
        return Objects.hash(getTitle(), getComposer(), isPremiere());
    }

    @Override
    @JacocoGenerated
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ConcertProgramme)) {
            return false;
        }
        ConcertProgramme that = (ConcertProgramme) o;
        return isPremiere() == that.isPremiere()
               && Objects.equals(getTitle(), that.getTitle())
               && Objects.equals(getComposer(), that.getComposer());
    }
}
