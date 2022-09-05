package no.unit.nva.model.testing;

import no.unit.nva.model.associatedartifact.AssociatedArtifactCollection;
import no.unit.nva.model.associatedartifact.AssociatedArtifactList;
import no.unit.nva.model.associatedartifact.File;
import no.unit.nva.model.associatedartifact.Link;

import java.net.URI;
import java.util.List;

public class AssociatedArtifactListGenerator {

    public static AssociatedArtifactCollection random() {
        // Since we are checking for empty fields/values in some tests, we cannot reliably test for the
        // explicitly empty NullObject NullAssociatedArtifactList, so the null object remains untested.
        return randomNonNullAssociatedArtifactList();
    }

    public static AssociatedArtifactCollection randomNonNullAssociatedArtifactList() {
        return new AssociatedArtifactList(List.of(generateAssociatedFile(), generateAssociatedLink()));
    }

    private static Link generateAssociatedLink() {
        return new Link(URI.create("https://example.org/link"), "My Link", "A nice document description");
    }

    private static File generateAssociatedFile() {
        return new File(URI.create("https://example.org/file"));
    }
}
