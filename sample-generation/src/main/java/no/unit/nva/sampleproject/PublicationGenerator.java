package no.unit.nva.sampleproject;

import static no.unit.nva.testutils.RandomDataGenerator.randomString;
import static no.unit.nva.testutils.RandomDataGenerator.randomUri;
import java.util.List;
import java.util.Map;
import no.unit.nva.identifiers.SortableIdentifier;
import no.unit.nva.model.Organization;
import no.unit.nva.model.Publication;

public class PublicationGenerator {

    public static Publication createPublication() {
        return new Publication.Builder()
            .withIdentifier(SortableIdentifier.next())
            .withSubjects(List.of(randomUri()))
            .withPublisher(randomOrganization())
            .build();
    }

    private static Organization randomOrganization() {
        return new Organization.Builder()
            .withId(randomUri())
            .withLabels(Map.of(randomString(), randomString()))
            .build();
    }
}
