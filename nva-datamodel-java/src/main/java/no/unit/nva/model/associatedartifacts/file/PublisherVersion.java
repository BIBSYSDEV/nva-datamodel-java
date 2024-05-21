package no.unit.nva.model.associatedartifacts.file;

import static java.lang.String.format;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import com.fasterxml.jackson.annotation.JsonValue;
import no.unit.nva.model.Revision;
import nva.commons.core.StringUtils;

public enum PublisherVersion {
    PUBLISHED_VERSION("PublishedVersion"),
    DRAFT("Draft"),
    SUBMITTED_VERSION("SubmittedVersion"),
    UPDATED_VERSION("UpdatedVersion"),
    ACCEPTED_VERSION("AcceptedVersion");

    public static final String ERROR_MESSAGE_TEMPLATE = "%s not a valid PublisherVersion, expected one of: %s";
    public static final String DELIMITER = ", ";

    private final String value;

    PublisherVersion(String value) {
        this.value = value;
    }

    public static PublisherVersion parse(String candidate) {
        if (StringUtils.isBlank(candidate)) {
            return null;
        }
        return parseFromString(candidate);
    }

    public static PublisherVersion parseFromString(String candidate) {
        return stream(values())
                   .filter(revision -> revision.getValue().equalsIgnoreCase(candidate))
                   .findAny()
                   .orElseThrow(() -> new IllegalArgumentException(
                       format(ERROR_MESSAGE_TEMPLATE, candidate,
                              stream(Revision.values())
                                  .map(Revision::toString).collect(joining(DELIMITER)))));
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
