package no.unit.nva.model.associatedartifacts.file;

import static java.lang.String.format;
import static java.util.Arrays.stream;
import static java.util.Objects.isNull;
import static java.util.stream.Collectors.joining;
import com.fasterxml.jackson.annotation.JsonValue;
import no.unit.nva.model.Revision;

public enum PublisherVersion {
    PUBLISHED_VERSION("PublishedVersion"),
    ACCEPTED_VERSION("AcceptedVersion");

    public static final String ERROR_MESSAGE_TEMPLATE = "%s not a valid PublisherVersion, expected one of: %s";
    public static final String ERROR_MESSAGE_CANNOT_PARSE_THIS_OBJECT = "%s not a valid object, expected either "
                                                                        + "String or boolean";
    public static final String DELIMITER = ", ";

    private final String value;

    PublisherVersion(String value) {
        this.value = value;
    }

    public static PublisherVersion parse(Object candidate) {
        if (candidate instanceof Boolean publisherAuthority) {
            return parseFromBoolean(publisherAuthority);
        } else if (candidate instanceof String stringCandidate) {
            return parseFromString(stringCandidate);
        } else if (isNull(candidate)) {
            return null;
        } else if (candidate instanceof PublisherVersion publisherVersion) {
            return publisherVersion;
        }
        throw new UnsupportedOperationException(format(ERROR_MESSAGE_CANNOT_PARSE_THIS_OBJECT,
                                                       candidate.getClass().getSimpleName()));
    }

    public static PublisherVersion parseFromBoolean(boolean candidate) {
        return candidate ? PUBLISHED_VERSION : ACCEPTED_VERSION;
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
