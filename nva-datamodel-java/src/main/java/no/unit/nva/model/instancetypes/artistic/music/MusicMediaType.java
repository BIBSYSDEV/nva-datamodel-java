package no.unit.nva.model.instancetypes.artistic.music;

import static java.lang.String.format;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MusicMediaType {

    COMPACT_DISC("CompactDisc"),
    DVD("DVD"),
    STREAMING("Streaming"),
    DIGITAL_FILE("DigitalFile"),
    VINYL("Vinyl"),
    OTHER("Other");

    public static final String ERROR_MESSAGE_TEMPLATE = "%s not a valid MusicMediaType, expected one of: %s";
    public static final String DELIMITER = ", ";
    private final String value;

    MusicMediaType(String value) {
        this.value = value;
    }

    public static MusicMediaType lookup(String candidate) {
        return stream(values())
                   .filter(value -> value.getValue().equalsIgnoreCase(candidate))
                   .findAny()
                   .orElseThrow(() -> new IllegalArgumentException(
                       format(ERROR_MESSAGE_TEMPLATE, candidate, stream(MusicMediaType.values())
                                                                     .map(MusicMediaType::toString)
                                                                     .collect(joining(DELIMITER)))));
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
