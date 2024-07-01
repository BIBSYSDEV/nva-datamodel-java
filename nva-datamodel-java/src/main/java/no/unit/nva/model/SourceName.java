package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * A record representing a source name.
 *
 * @param system the system name of the source. e.g. "brage"
 * @param instanceName the instance name of the source. e.g. "194.0.0.0"
 */
public record SourceName(String system, String instanceName) {

    @JsonValue
    @Override
    public String toString() {
        return system == null ? instanceName : system + "@" + instanceName;
    }

    @SuppressWarnings("PMD.NullAssignment")
    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public SourceName(String sourceName) {
        this(sourceName.contains("@") ? sourceName.split("@")[0] : null,
             sourceName.contains("@") ? sourceName.split("@")[1] : sourceName);
    }
}
