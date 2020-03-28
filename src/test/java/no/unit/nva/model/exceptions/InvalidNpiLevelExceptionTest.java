package no.unit.nva.model.exceptions;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InvalidNpiLevelExceptionTest {

    public static final String THE_MESSAGE = "The message";

    @DisplayName("The InvalidNpiLevelException can be thrown and has a message")
    @Test
    void invalidNpiLevelExceptionIsThrownAndHasMessage() {
        InvalidNpiLevelException exception = assertThrows(InvalidNpiLevelException.class, () -> {
            throw new InvalidNpiLevelException(THE_MESSAGE);
        });

        assertEquals(THE_MESSAGE, exception.getMessage());
    }
}
