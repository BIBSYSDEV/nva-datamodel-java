package no.unit.nva.model.instancetypes.book;

import static java.lang.String.format;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import com.fasterxml.jackson.annotation.JsonValue;

public enum BookMonographContentType {
    ACADEMIC_MONOGRAPH("Academic Monograph"),
    NON_FICTION_MONOGRAPH("Non-fiction Monograph"),
    POPULAR_SCIENCE_MONOGRAPH("Popular Science Monograph"),
    TEXTBOOK("Textbook"),
    ENCYCLOPEDIA("Encyclopedia");

    public static final String ERROR_MESSAGE_TEMPLATE = "%s not a valid BookMonographContentType, expected one of: %s";
    public static final String DELIMITER = ", ";

    private final String value;

    BookMonographContentType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    /**
     * Lookup enum by value.
     *
     * @param value value
     * @return enum
     */
    public static BookMonographContentType lookup(String value) {
        return stream(values())
                .filter(nameType -> nameType.getValue().equalsIgnoreCase(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(
                        format(ERROR_MESSAGE_TEMPLATE, value, stream(BookMonographContentType.values())
                                .map(BookMonographContentType::toString).collect(joining(DELIMITER)))));
    }
}
