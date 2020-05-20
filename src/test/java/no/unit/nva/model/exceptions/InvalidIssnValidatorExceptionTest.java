package no.unit.nva.model.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InvalidIssnValidatorExceptionTest {

    @DisplayName("InvalidIssnException exists, can be thrown and has message")
    @Test
    void invalidIssnExceptionThrowsAndHasMessage() {
        String invalidIssn = "123";
        String expected = String.format("The ISSN \"%s\" is invalid", invalidIssn);

        InvalidIssnException exception = assertThrows(InvalidIssnException.class, () -> {
            throw new InvalidIssnException(invalidIssn);
        });

        assertEquals(expected, exception.getMessage());
    }

}