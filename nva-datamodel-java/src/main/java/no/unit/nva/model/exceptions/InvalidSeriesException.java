package no.unit.nva.model.exceptions;

import nva.commons.core.JacocoGenerated;

public class InvalidSeriesException extends RuntimeException {

    public static final String MESSAGE = "The specified URI is not a valid series URI: %s";

    @JacocoGenerated
    public InvalidSeriesException(String seriesUri) {
        super(String.format(MESSAGE,seriesUri));
    }
}
