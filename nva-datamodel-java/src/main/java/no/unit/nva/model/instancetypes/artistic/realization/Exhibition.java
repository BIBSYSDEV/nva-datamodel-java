package no.unit.nva.model.instancetypes.artistic.realization;

import com.fasterxml.jackson.annotation.JsonProperty;
import no.unit.nva.model.instancetypes.artistic.ArchitectureOutput;
import no.unit.nva.model.time.Period;
import nva.commons.core.JacocoGenerated;

import java.util.Objects;

public class Exhibition implements ArchitectureOutput {
    public static final String NAME = "name";
    public static final String PLACE = "place";
    public static final String ORGANIZER = "organizer";
    public static final String TIME = "time";
    public static final String OTHER_INFORMATION = "otherInformation";

    @JsonProperty(NAME)
    private final String name;
    @JsonProperty(PLACE)
    private final String place;
    @JsonProperty(ORGANIZER)
    private final String organizer;
    @JsonProperty(TIME)
    private final Period time;
    @JsonProperty(OTHER_INFORMATION)
    private final String otherInformation;
    @JsonProperty(SEQUENCE)
    private final int sequence;

    public Exhibition(@JsonProperty(NAME) String name,
                      @JsonProperty(PLACE) String place,
                      @JsonProperty(ORGANIZER) String organizer,
                      @JsonProperty(TIME) Period time,
                      @JsonProperty(OTHER_INFORMATION) String otherInformation,
                      @JsonProperty(SEQUENCE) int sequence) {
        this.name = name;
        this.place = place;
        this.organizer = organizer;
        this.time = time;
        this.otherInformation = otherInformation;
        this.sequence = sequence;
    }

    public String getName() {
        return name;
    }

    public String getPlace() {
        return place;
    }

    public String getOrganizer() {
        return organizer;
    }

    public Period getTime() {
        return time;
    }

    public String getOtherInformation() {
        return otherInformation;
    }

    @Override
    public int getSequence() {
        return sequence;
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Exhibition)) {
            return false;
        }
        Exhibition that = (Exhibition) o;
        return Objects.equals(getName(), that.getName())
                && Objects.equals(getPlace(), that.getPlace())
                && Objects.equals(getOrganizer(), that.getOrganizer())
                && Objects.equals(getTime(), that.getTime())
                && Objects.equals(getOtherInformation(), that.getOtherInformation());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPlace(), getOrganizer(), getTime(), getOtherInformation());
    }
}
