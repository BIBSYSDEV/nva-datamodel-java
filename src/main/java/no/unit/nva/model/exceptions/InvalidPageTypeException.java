package no.unit.nva.model.exceptions;

import no.unit.nva.model.instancetypes.PublicationInstance;
import no.unit.nva.model.pages.Pages;

public class InvalidPageTypeException extends Exception {
    public static final String INVALID_CLASS_MESSAGE =
            "Class %s expects a Pages object of type %s, while an object of type %s was provided";
    public InvalidPageTypeException(Class<? extends PublicationInstance> publicationInstance,
                                    Class<? extends Pages> expectedPages,
                                    Class<? extends Pages> actualPages) {
        super(String.format(INVALID_CLASS_MESSAGE,
                publicationInstance.getTypeName(),
                expectedPages.getTypeName(),
                actualPages.getTypeName()));
    }
}
