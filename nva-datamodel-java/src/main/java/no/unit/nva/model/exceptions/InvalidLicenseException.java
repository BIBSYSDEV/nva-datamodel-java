package no.unit.nva.model.exceptions;

public class InvalidLicenseException extends RuntimeException {

    public static final String MESSAGE = "The specified URI is not a valid license URI: %s";

    public InvalidLicenseException(String seriesUri) {
        super(String.format(MESSAGE,seriesUri));
    }
}
