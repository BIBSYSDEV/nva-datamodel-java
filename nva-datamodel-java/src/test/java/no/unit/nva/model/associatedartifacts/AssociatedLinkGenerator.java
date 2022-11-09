package no.unit.nva.model.associatedartifacts;

import static no.unit.nva.model.testing.PublicationGenerator.randomUri;
import static no.unit.nva.testutils.RandomDataGenerator.randomString;

public class AssociatedLinkGenerator {
    private AssociatedLinkGenerator() {
        // NO-OP
    }


    public static AssociatedLink random() {
        return new AssociatedLink(randomUri(), randomString(), randomString());
    }
}
