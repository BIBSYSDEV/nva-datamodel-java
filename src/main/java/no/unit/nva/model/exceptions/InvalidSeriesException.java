package no.unit.nva.model.exceptions;

public class InvalidSeriesException extends RuntimeException {

    public static final String MESSAGE = "The specified URI is not a valid series URI:";

    public InvalidSeriesException(String seriesUri) {
        super(MESSAGE + seriesUri);
    }
}
