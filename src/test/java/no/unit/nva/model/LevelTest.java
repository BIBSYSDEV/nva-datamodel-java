package no.unit.nva.model;

import static no.unit.nva.utils.RandomData.randomString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.fasterxml.jackson.core.JsonProcessingException;
import nva.commons.core.JsonUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

class LevelTest {

    public static final String DOUBLE_QUOTES = "\"";

    @ParameterizedTest(name = "ObjectMapper serializes Level {0} as: {0}")
    @EnumSource(Level.class)
    public void objectMapperReturnsEnumNamesInSerialization(Level level) throws JsonProcessingException {
        String expectedName = level.name();
        String actualName = JsonUtils.objectMapper.writeValueAsString(level);
        String expectedValue = DOUBLE_QUOTES + expectedName + DOUBLE_QUOTES;
        assertThat(actualName, is(equalTo(expectedValue)));
    }

    @ParameterizedTest(name = "Level is parsed correctly when using one of the aliases")
    @ValueSource(strings = {
        "level1", "level2", "level0",
        "Level1", "Level2", "Level0",
        "level_1", "level_2", "leVel_0",
        "LEVEL1", "LEVEL2", "LEVEL0",
        "LEVEL_1", "LEVEL_2", "LEVEL_0",
        "NO_LEVEL","no_level"
    })
    public void objectMapperParsesLevelWhenInputIsEqualToOneOfTheAliases(String alias) throws JsonProcessingException {
        Level expectedLevel = calculateExpectedLevel(alias);
        String jsonString = DOUBLE_QUOTES + alias + DOUBLE_QUOTES;
        Level actualLevel = parseLevel(jsonString);
        assertThat(actualLevel, is(equalTo(expectedLevel)));
    }

    @Test
    public void objectMapperThrowsExceptionContainingTheInvalidInputWhenInputIsInvalid() {
        String invalidInput = DOUBLE_QUOTES + randomString() + DOUBLE_QUOTES;
        Executable action = () -> parseLevel(invalidInput);
        Exception exception = assertThrows(Exception.class, action);
        assertThat(exception.getMessage(), containsString(invalidInput));
    }

    private Level parseLevel(String jsonString) throws JsonProcessingException {
        return JsonUtils.objectMapper.readValue(jsonString, Level.class);
    }

    private Level calculateExpectedLevel(String alias) {
        if (alias.contains("1")) {
            return Level.LEVEL_1;
        } else if (alias.contains("2")) {
            return Level.LEVEL_2;
        } else if (alias.contains("0")) {
            return Level.LEVEL_0;
        } else if (alias.equalsIgnoreCase("no_level")){
            return Level.NO_LEVEL;
        }
        else throw new RuntimeException("Level not found for alias "+alias);
    }
}