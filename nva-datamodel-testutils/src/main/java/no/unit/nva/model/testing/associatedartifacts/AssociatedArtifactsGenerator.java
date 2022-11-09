package no.unit.nva.model.testing.associatedartifacts;

import no.unit.nva.model.associatedartifacts.AssociatedArtifact;
import no.unit.nva.model.associatedartifacts.AssociatedArtifactList;
import no.unit.nva.model.associatedartifacts.AssociatedLink;
import nva.commons.core.JacocoGenerated;

import java.util.List;

import static no.unit.nva.testutils.RandomDataGenerator.randomString;
import static no.unit.nva.testutils.RandomDataGenerator.randomUri;

@JacocoGenerated
public final class AssociatedArtifactsGenerator {

    private AssociatedArtifactsGenerator() {
        // NO-OP
    }

    public static List<AssociatedArtifact> randomAssociatedArtifacts() {
        return new AssociatedArtifactList(PublishedFileGenerator.random(),randomAssociatedLink());
    }

    public static AssociatedLink randomAssociatedLink() {
        return new AssociatedLink(randomUri(), randomString(), randomString());
    }
}
