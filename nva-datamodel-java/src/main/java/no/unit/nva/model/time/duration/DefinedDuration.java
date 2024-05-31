package no.unit.nva.model.time.duration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.Objects;
import nva.commons.core.JacocoGenerated;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public final class DefinedDuration implements Duration {

    public static final String MINUTES_FIELD = "minutes";
    public static final String HOURS_FIELD = "hours";
    public static final String DAYS_FIELD = "days";
    public static final String WEEKS_FIELD = "weeks";
    private final String minutes;
    private final String hours;
    private final String days;
    private final String weeks;

    @JsonCreator
    private DefinedDuration(@JsonProperty(MINUTES_FIELD) String minutes, @JsonProperty(HOURS_FIELD) String hours,
                            @JsonProperty(DAYS_FIELD) String days, @JsonProperty(WEEKS_FIELD) String weeks) {
        this.minutes = minutes;
        this.hours = hours;
        this.days = days;
        this.weeks = weeks;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getMinutes() {
        return minutes;
    }

    public String getHours() {
        return hours;
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(minutes, hours, days, weeks);
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DefinedDuration that)) {
            return false;
        }
        return Objects.equals(minutes, that.minutes)
               && Objects.equals(hours, that.hours)
               && Objects.equals(days, that.days)
               && Objects.equals(weeks, that.weeks);
    }

    public String getDays() {
        return days;
    }

    public String getWeeks() {
        return weeks;
    }

    public static final class Builder {

        private String minutes;
        private String hours;
        private String days;
        private String weeks;

        private Builder() {
        }

        public Builder withMinutes(String minutes) {
            this.minutes = minutes;
            return this;
        }

        public Builder withHours(String hours) {
            this.hours = hours;
            return this;
        }

        public Builder withDays(String days) {
            this.days = days;
            return this;
        }

        public Builder withWeeks(String weeks) {
            this.weeks = weeks;
            return this;
        }

        public DefinedDuration build() {
            return new DefinedDuration(minutes, hours, days, weeks);
        }
    }
}
