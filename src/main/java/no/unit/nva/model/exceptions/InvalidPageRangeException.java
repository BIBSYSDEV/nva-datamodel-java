package no.unit.nva.model.exceptions;

public class InvalidPageRangeException extends Exception {

    public static final String ERROR_TEMPLATE = "The supplied page range \"%s-%s\" is not valid";

    public InvalidPageRangeException(String begin, String end) {
        super(String.format(ERROR_TEMPLATE, begin, end));
    }
}
