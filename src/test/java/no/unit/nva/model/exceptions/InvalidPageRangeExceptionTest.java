package no.unit.nva.model.exceptions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InvalidPageRangeExceptionTest {

    @DisplayName("InvalidPageRangeException can be thrown and has message")
    @Test
    void invalidPageRangeExceptionHasMessageWhenThrown() {
        String end = "somethingElse";
        Executable executable = () -> {
            throw new InvalidPageRangeException(null, end);
        };
        InvalidPageRangeException exception = assertThrows(InvalidPageRangeException.class, executable);
        String expectedError = String.format(InvalidPageRangeException.ERROR_TEMPLATE, null, end);
        assertEquals(expectedError, exception.getMessage());
    }
}
