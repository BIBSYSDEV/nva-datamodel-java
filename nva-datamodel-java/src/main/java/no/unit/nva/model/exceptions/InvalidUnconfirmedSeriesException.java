package no.unit.nva.model.exceptions;

import nva.commons.core.JacocoGenerated;

public class InvalidUnconfirmedSeriesException extends Exception {

    public static final String ERROR_MESSAGE = "The series defines a seriesTitle string and an unconfirmed "
            + "series object that have different values. These values should either match, "
            + "or the seriesTitle should be removed.";

    @JacocoGenerated
    public InvalidUnconfirmedSeriesException() {
        super(ERROR_MESSAGE);
    }
}
