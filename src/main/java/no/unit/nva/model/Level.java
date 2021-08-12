package no.unit.nva.model;

import static java.util.Objects.nonNull;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import no.unit.nva.model.exceptions.InvalidNpiLevelException;
import nva.commons.core.SingletonCollector;

public enum Level {
    LEVEL_2(2, "level2", "level_2"),
    LEVEL_1(1, "level1", "level_1"),
    LEVEL_0(0, "level0", "level_0"),
    NO_LEVEL(null, "no_level");

    public static final String ERROR_TEMPLATE = "The specified level \"%s\" is not a legal value (%s)";
    private List<String> aliases;
    private Integer level;

    Level(Integer level, String... aliases) {
        if (nonNull(level)) {
            this.level = level;
        }
        this.aliases = Arrays.asList(aliases);
    }

    @JsonCreator
    public static Level fromString(String level) {
        return Arrays.stream(values())
            .filter(enumValue -> enumValue.aliases.contains(level.toLowerCase(Locale.ROOT)))
            .collect(SingletonCollector.tryCollect())
            .orElseThrow(fail -> new InvalidNpiLevelException(errorMessage(level)));
    }

    /**
     * In cases where an integer is supplied, it must be checked that the level is correct.
     *
     * @param integer The level value as integer
     * @return Level value
     * @throws InvalidNpiLevelException In cases where the input level is not a valid level
     */
    public static Level getLevel(Integer integer) {
        return Arrays.stream(values())
            .filter(levelInteger -> levelInteger.level.equals(integer)).findFirst()
            .orElseThrow(() -> new InvalidNpiLevelException(errorMessage(integer.toString())));
    }

    @JsonValue
    public String toJsonString() {
        return this.toString();
    }

    private static String errorMessage(String input) {
        return String.format(ERROR_TEMPLATE, input, Arrays.toString(values()));
    }
}
