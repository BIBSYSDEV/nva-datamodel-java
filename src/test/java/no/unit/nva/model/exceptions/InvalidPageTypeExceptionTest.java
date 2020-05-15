package no.unit.nva.model.exceptions;

import no.unit.nva.model.Journal;
import no.unit.nva.model.instancetypes.JournalArticle;
import no.unit.nva.model.pages.MonographPages;
import no.unit.nva.model.pages.Range;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InvalidPageTypeExceptionTest {

    @DisplayName("The InvalidPageTypeException is thrown")
    @Test
    void testInvalidPageTypeExceptionIsThrown() {
        assertThrows(InvalidPageTypeException.class, () -> {
            throw new InvalidPageTypeException(JournalArticle.class, Range.class, MonographPages.class);
        });
    }

    @DisplayName("The InvalidPageTypeException has formatted message")
    @Test
    void testInvalidPageTypeExceptionHasFormattedMessage() {
        InvalidPageTypeException exception = assertThrows(InvalidPageTypeException.class, () -> {
            throw new InvalidPageTypeException(JournalArticle.class, Range.class, MonographPages.class);
        });
        String message = String.format(InvalidPageTypeException.INVALID_CLASS_MESSAGE,
                JournalArticle.class.getTypeName(), Range.class.getTypeName(), MonographPages.class.getTypeName());
        assertEquals(message, exception.getMessage());
    }
}