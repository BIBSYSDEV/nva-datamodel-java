package no.unit.nva.model.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MalformedContributorExceptionTest {

    public static final String THE_MESSAGE = "The message";

    @DisplayName("The MalformedContributorException can be thrown and has a message")
    @Test
    void malformedContributorExceptionIsThrownAndHasMessage() {
        MalformedContributorException exception = assertThrows(MalformedContributorException.class, () -> {
            throw new MalformedContributorException(THE_MESSAGE);
        });

        assertEquals(THE_MESSAGE, exception.getMessage());
    }
}
