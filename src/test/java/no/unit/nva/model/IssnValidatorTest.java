package no.unit.nva.model;

import no.unit.nva.model.validator.IssnValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IssnValidatorTest {

    @DisplayName("The valid ISSNs pass")
    @ParameterizedTest
    @ValueSource(strings = {"1476-4687", "0363-6941", "1945-662X", "0025-5629"})
    void issnCreatesIssnWithValidInput(String candidate) {
        assertTrue(IssnValidator.validate(candidate));
    }

    @DisplayName("Invalid ISSNs throw exception")
    @ParameterizedTest
    @ValueSource(strings = {"123-123", "B123-1234", "0000-000X", "1233-1111", "12331111", "1476 4687"})
    void issnThrowsExceptionWhenInputIsNotValid(String candidate) {
        assertFalse(IssnValidator.validate(candidate));
    }
}
