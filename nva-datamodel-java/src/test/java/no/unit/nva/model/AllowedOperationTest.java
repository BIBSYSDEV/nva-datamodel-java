package no.unit.nva.model;

import static no.unit.nva.testutils.RandomDataGenerator.randomString;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class AllowedOperationTest {

    @Test
    void shouldLookupEnumWhenStatusIsKnown() {
        assertEquals(AllowedOperation.UPDATE, AllowedOperation.lookup("update"));
    }

    @Test
    void shouldThrowExceptionWhenCannotParseStatus() {
        assertThrows(IllegalArgumentException.class, () -> AllowedOperation.lookup(randomString()));
    }
}