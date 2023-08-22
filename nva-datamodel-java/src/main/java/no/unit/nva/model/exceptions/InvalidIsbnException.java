package no.unit.nva.model.exceptions;

import java.util.List;
import nva.commons.core.JacocoGenerated;

public class InvalidIsbnException extends Exception {

    public static final String ERROR_TEMPLATE = "The provided ISBN(s) %s is/are invalid";

    @JacocoGenerated
    public InvalidIsbnException(List<String> isbnList) {
        super(String.format(ERROR_TEMPLATE, String.join(", ", isbnList)));
    }
}
