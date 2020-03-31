package no.unit.nva.model.exceptions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class MissingLicenseExceptionTest {
    public static final String THE_MESSAGE = "The message";

    @DisplayName("The MissingLicenseException can be thrown and has a message")
    @Test
    void missingLicenseExceptionIsThrownAndHasMessage() {
        MissingLicenseException exception = assertThrows(MissingLicenseException.class, () -> {
            throw new MissingLicenseException(THE_MESSAGE);
        });

        assertEquals(THE_MESSAGE, exception.getMessage());
    }
}
