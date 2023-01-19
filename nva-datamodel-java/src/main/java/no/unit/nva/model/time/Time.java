package no.unit.nva.model.time;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(name = "Period", value = Period.class),
    @JsonSubTypes.Type(name = "Instant", value = Instant.class)
})
public interface Time {

    // The conversion methods should be removed following migration
    String UNIVERSAL_TIMEZONE = "Z";
    String ZEROED_MILLISECONDS_IN_TZ = ".000000Z";

    @Deprecated
    static java.time.Instant parseLocalDate(String candidate) {
        return LocalDateTime.parse(candidate).toInstant(ZoneOffset.UTC).plus(12, ChronoUnit.HOURS);
    }

    @Deprecated
    static boolean isInstant(String candidate) {
        return candidate.endsWith(UNIVERSAL_TIMEZONE);
    }

    static java.time.Instant convertToInstant(String candidate) {
        return Time.isInstant(candidate)
                ? java.time.Instant.parse(candidate)
                : java.time.Instant.parse(candidate + ZEROED_MILLISECONDS_IN_TZ);
    }
}
