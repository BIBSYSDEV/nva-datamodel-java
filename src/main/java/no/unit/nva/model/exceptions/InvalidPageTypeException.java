package no.unit.nva.model.exceptions;

import no.unit.nva.model.instancetypes.PublicationInstance;
import no.unit.nva.model.pages.Pages;

public class InvalidPageTypeException extends Exception {
    public static final String INVALID_CLASS_MESSAGE =
            "Class %s expects a Pages object of type %s, while an object of type %s was provided";

    /**
     * This exception is thrown when a Pages object is used with a class that expects a specific Pages type.
     * For example, JournalArticle is compatible with a Pages object of type Range.
     *
     * @param publicationInstance The publication instance.
     * @param expectedPages The expected Pages type.
     * @param actualPages The actual Pages type.
     */
    public InvalidPageTypeException(Class<? extends PublicationInstance> publicationInstance,
                                    Class<? extends Pages> expectedPages,
                                    Class<? extends Pages> actualPages) {
        super(String.format(INVALID_CLASS_MESSAGE,
                publicationInstance.getTypeName(),
                expectedPages.getTypeName(),
                actualPages.getTypeName()));
    }
}
