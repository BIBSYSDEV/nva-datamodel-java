package no.unit.nva.model;

import no.unit.nva.model.exceptions.InvalidIssnException;
import no.unit.nva.model.validator.IssnValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IssnValidatorTest {

    @DisplayName("The valid ISSNs pass")
    @Test
    void issnCreatesIssnWithValidInput() {
        List<String> validIssns = List.of("1476-4687", "0363-6941", "1945-662X", "0025-5629");
        for (String issn : validIssns) {
            assertTrue(IssnValidator.validate(issn));
        }
    }

    @DisplayName("Invalid ISSNs throw exception")
    @Test
    void issnThrowsExceptionWhenInputIsNotValid() {
        List<String> invalidIssns = List.of("", "123-123", "B123-1234",
                "0000-000X", "1233-1111", "12331111", "1476 4687");
        for (String issn : invalidIssns) {
            assertFalse(IssnValidator.validate(issn));
        }

    }

}