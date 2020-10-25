package no.unit.nva.model.instancetypes.musicalcontent;

import no.unit.nva.model.instancetypes.musicalcontent.exception.InvalidIsmnException;
import no.unit.nva.model.pages.Range;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static no.unit.nva.model.instancetypes.musicalcontent.Ismn.INVALID_ISMN_TEMPLATE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MusicNotationTest {
    private static final String VALID_ISMN_10 = "M-2306-7118-7";
    private static final String VALID_ISMN_13 = "979-0-9016791-7-7";
    private static final String INVALID_CHECKBIT_ISMN = "979-0-9016791-72";
    private static final String INVALID_SHORT_LENGTH_ISMN = "979-0-9016791";
    private static final String INVALID_LONG_LENGTH_ISMN = "979-0-9016791-1233";
    private static final String INVALID_PREFIX_ISMN = "929-0-9016791-7";
    private static final Range RANGE = generateRange();

    @Test
    void musicNotationCanBeCreatedWhenInputIsValidIsmn13() {
        assertDoesNotThrow(() -> new MusicNotation(RANGE, VALID_ISMN_13));
    }

    @Test
    void musicNotationCanBeCreatedWhenInputIsValidIsmn10() {
        assertDoesNotThrow(() -> new MusicNotation(RANGE, VALID_ISMN_10));
    }

    @ParameterizedTest(name = "ISMN10s like {0} throw InvalidIsmnException")
    @ValueSource(strings = {
            INVALID_PREFIX_ISMN,
            INVALID_SHORT_LENGTH_ISMN,
            INVALID_LONG_LENGTH_ISMN,
            "X-981-1-1234-1",
            "9876-1234-1",
            "111",
            "M-100",
            "M-1101010101000",
            "Madmadworl",
            "M is a letter",
            "979-1-32111-122-1"
    })
    void musicNotationThrowsExceptionWhenIsmn10IsBadlyFormatted(String candidate) {
        Executable executable = () -> new MusicNotation(RANGE, candidate);
        var exception = assertThrows(InvalidIsmnException.class, executable);
        var expectedMessage = String.format(INVALID_ISMN_TEMPLATE,
                candidate);
        assertThat(exception.getMessage(), equalTo(expectedMessage));
    }

    @Test
    void musicNotationThrowsExceptionWhenIsmnHasInvalidCheckBit() {
        Executable executable = () -> new MusicNotation(RANGE, INVALID_CHECKBIT_ISMN);
        Exception exception = assertThrows(InvalidIsmnException.class, executable);
        var expectedMessage = String.format(INVALID_ISMN_TEMPLATE, INVALID_CHECKBIT_ISMN);
        assertThat(exception.getMessage(), containsString(expectedMessage));
    }

    @ParameterizedTest(name = "Music notation getIsmn reformats {0} correctly")
    @ValueSource(strings = {
            "M-001-12050-0",
            "M-004-16663-5",
            "M-049-05851-3",
            "M-051-66073-5",
            "M-2306-2632-3",
            "M-706871-19-6",
            "M-708010-34-0",
            "M-9001001-2-2",
            "979-0-001-16094-0",
            "979-0-001-16093-3",
            "979-0-008-00281-6",
            "979-0-035-22568-4",
            "979-0-047-30105-5",
            "979-0-2152-1558-0",
            "979-0-2312-0112-3",
            "979-0-3007-5976-0",
            "979-0-50057-152-0",
            "979-0-50224-227-5",
            "979-0-56005-291-5",
            "979-0-69795-559-2",
            "979-0-708146-03-2",
            "979-0-708024-09-5",
            "979-0-800059-00-1",
            "979-0-9002305-4-6",
            "979-0-9002013-3-1"
    })
    void musicNotationGetFormattedIsmnReturnsCorrectlyFormattedIsmn(String ismn) throws InvalidIsmnException {
        MusicNotation musicNotation = new MusicNotation(RANGE, ismn);
        assertThat(musicNotation.getFormattedIsmn(), equalTo(ismn));
    }

    @ParameterizedTest(name = "Null or empty value fails")
    @EmptySource
    @ValueSource(strings = {"", " ", "  ", "           ", "             "})
    void musicNotationThrowsExceptionWhenIsmnIsEmpty(String candidate) {
        Executable executable = () -> new MusicNotation(RANGE, candidate);
        var exception = assertThrows(InvalidIsmnException.class, executable);
        var expectedMessage = String.format(INVALID_ISMN_TEMPLATE,
                candidate);
        assertThat(exception.getMessage(), equalTo(expectedMessage));
    }

    @Test
    void musicNotationReturnsEmptyIsmnWhenIsmnIsNull() throws InvalidIsmnException {
        MusicNotation musicNotation = new MusicNotation(RANGE, null);
        assertThat(musicNotation.getFormattedIsmn(), nullValue());
    }

    private static Range generateRange() {
        return new Range.Builder()
                .withBegin("1")
                .withEnd("5")
                .build();
    }
}