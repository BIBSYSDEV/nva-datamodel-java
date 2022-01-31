package no.unit.nva.model.instancetypes.artistic.realization;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.instancetypes.artistic.ArchitectureOutput;
import no.unit.nva.model.time.Time;
import nva.commons.core.JacocoGenerated;

import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Competition implements ArchitectureOutput {

    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String TIME = "time";

    @JsonProperty(NAME)
    private final String name;
    @JsonProperty(DESCRIPTION)
    private final String description;
    @JsonProperty(TIME)
    private final Time time;
    @JsonProperty(SEQUENCE)
    private final int sequence;

    public Competition(@JsonProperty(NAME) String name,
                       @JsonProperty(DESCRIPTION) String description,
                       @JsonProperty(TIME) Time time,
                       @JsonProperty(SEQUENCE) int sequence) {
        this.name = name;
        this.description = description;
        this.time = time;
        this.sequence = sequence;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Time getTime() {
        return time;
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
        if (!(o instanceof Competition)) {
            return false;
        }
        Competition that = (Competition) o;
        return Objects.equals(getName(), that.getName())
                && Objects.equals(getDescription(), that.getDescription())
                && Objects.equals(getTime(), that.getTime());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDescription(), getTime());
    }
}
