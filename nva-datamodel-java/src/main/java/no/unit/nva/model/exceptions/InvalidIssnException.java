package no.unit.nva.model.exceptions;

import nva.commons.core.JacocoGenerated;

public class InvalidIssnException extends Exception {

    public static final String MESSAGE_TEMPLATE = "The ISSN \"%s\" is invalid";

    @JacocoGenerated
    public InvalidIssnException(String issn) {
        super(String.format(MESSAGE_TEMPLATE, issn));
    }
}
