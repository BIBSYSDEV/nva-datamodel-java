package no.unit.nva.model.contexttypes.utils;

import no.unit.nva.model.exceptions.InvalidIssnException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IssnUtilTest {

    @DisplayName("IssnUtil can validate valid ISSNs")
    @ParameterizedTest(name = "IssnUtil validates valid issn {0}")
    @ValueSource(strings = {"0363-6941", "1945-662X"})
    void issnUtilReturnsInputWhenInputIssnIsValid(String input) throws InvalidIssnException {
        String actual = IssnUtil.checkIssn(input);
        assertEquals(input, actual);
    }

    @DisplayName("IssnUtil rejects invalid ISSNs")
    @ParameterizedTest(name = "IssnUtil throws error for invalid issn {0}")
    @ValueSource(strings = {"03636941", "1945662X"})
    void issnUtilInvalidIssnExceptionWhenInputIssnIsInalid(String input) {
        assertThrows(InvalidIssnException.class, () -> IssnUtil.checkIssn(input));
    }

    @DisplayName("IssnUtil returns null if input is null or empty")
    @ParameterizedTest(name = "IssnUtil returns null when input is {0}")
    @NullAndEmptySource
    void issnUtilReturnsNullWhenInputIsNullOrEmpty(String input) throws InvalidIssnException {
        String actual = IssnUtil.checkIssn(input);
        assertNull(actual);
    }
}
