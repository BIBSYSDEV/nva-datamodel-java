package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonCreator;


/**
 * A record representing a source name.
 *
 * @param system the system name of the source. e.g. "brage"
 * @param instanceName the instance name of the source. e.g. "194.0.0.0"
 */
public record SourceName(String system, String instanceName) {

    @Override
    public String toString() {
        return system == null ? instanceName : system + "@" + instanceName;
    }

    @SuppressWarnings("PMD.NullAssignment")
    @JsonCreator
    public SourceName(String sourceName) {
        this(sourceName.contains("@") ? sourceName.split("@")[0] : null,
             sourceName.contains("@") ? sourceName.split("@")[1] : sourceName);
    }
}
