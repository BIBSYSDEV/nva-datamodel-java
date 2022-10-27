package no.unit.nva.model.testing;

import static no.unit.nva.hamcrest.DoesNotHaveEmptyValues.doesNotHaveEmptyValues;
import static no.unit.nva.model.testing.PublicationInstanceBuilder.randomPublicationInstanceType;
import static no.unit.nva.testutils.RandomDataGenerator.randomElement;
import static no.unit.nva.testutils.RandomDataGenerator.randomInstant;
import static no.unit.nva.testutils.RandomDataGenerator.randomString;
import static org.hamcrest.MatcherAssert.assertThat;
import com.github.javafaker.Faker;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Set;
import no.unit.nva.identifiers.SortableIdentifier;
import no.unit.nva.model.AdditionalIdentifier;
import no.unit.nva.model.Approval;
import no.unit.nva.model.ApprovalStatus;
import no.unit.nva.model.ApprovalsBody;
import no.unit.nva.model.EntityDescription;
import no.unit.nva.model.Grant;
import no.unit.nva.model.Organization;
import no.unit.nva.model.Publication;
import no.unit.nva.model.Publication.Builder;
import no.unit.nva.model.PublicationStatus;
import no.unit.nva.model.ResearchProject;
import no.unit.nva.model.ResourceOwner;
import nva.commons.core.JacocoGenerated;

@JacocoGenerated
public final class PublicationGenerator {

    private static final Faker FAKER = Faker.instance();

    @JacocoGenerated
    private PublicationGenerator() {

    }

    // This method is in use in api-tests
    @JacocoGenerated
    public static Publication publicationWithIdentifier() {
        return randomPublication();
    }

    // This method is in use in api-tests
    @JacocoGenerated
    public static Publication publicationWithoutIdentifier() {
        var publication = randomPublication();
        publication.setIdentifier(null);
        return publication;
    }



    public static URI randomUri() {
        String uriString = "https://www.example.org/" + randomWord() + randomWord();
        return URI.create(uriString);
    }

    public static Publication randomPublication() {
        return randomPublication(randomPublicationInstanceType());
    }

    public static Publication randomPublication(Class<?> publicationInstanceClass) {

        var publication = buildRandomPublicationFromInstance(publicationInstanceClass);

        assertThat(publication, doesNotHaveEmptyValues());
        return publication;
    }

    public static EntityDescription randomEntityDescription(Class<?> publicationInstanceClass) {
        return EntityDescriptionBuilder.randomEntityDescription(publicationInstanceClass);
    }

    public static URI randomDoi() {
        return URI.create("https://doi.org/10.1234/" + randomWord());
    }

    public static List<ResearchProject> randomProjects() {
        return List.of(randomResearchProject());
    }

    public static ResearchProject randomResearchProject() {
        return new ResearchProject.Builder()
            .withId(randomUri())
            .withName(randomString())
            .withApprovals(randomApprovals())
            .withGrants(randomGrants())
            .build();
    }

    public static List<Grant> randomGrants() {
        return List.of(randomGrant());
    }

    public static Grant randomGrant() {
        return new Grant.Builder()
            .withId(randomString())
            .withSource(randomString())
            .build();
    }

    public static List<Approval> randomApprovals() {
        return List.of(randomApproval());
    }

    public static Approval randomApproval() {

        return new Approval.Builder()
            .withApprovalStatus(randomElement(ApprovalStatus.values()))
            .withDate(randomInstant())
            .withApplicationCode(randomString())
            .withApprovedBy(randomElement(ApprovalsBody.values()))
            .build();
    }

    public static AdditionalIdentifier randomAdditionalIdentifier() {
        return new AdditionalIdentifier(randomString(), randomString());
    }

    public static Organization randomOrganization() {
        return new Organization.Builder()
            .withId(randomUri())
            .withLabels(Map.of(randomString(), randomString()))
            .build();
    }

    private static Publication buildRandomPublicationFromInstance(Class<?> publicationInstanceClass) {
        return new Builder()
                .withIdentifier(SortableIdentifier.next())
                .withPublisher(randomOrganization())
                .withSubjects(List.of(randomUri()))
                .withStatus(randomElement(PublicationStatus.values()))
                .withPublishedDate(randomInstant())
                .withModifiedDate(randomInstant())
                .withAdditionalIdentifiers(Set.of(randomAdditionalIdentifier()))
                .withProjects(randomProjects())
                .withResourceOwner(randomResourceOwner())
                .withLink(randomUri())
                .withIndexedDate(randomInstant())
                .withHandle(randomUri())
                .withDoi(randomDoi())
                .withCreatedDate(randomInstant())
                .withEntityDescription(randomEntityDescription(publicationInstanceClass))
                .withAssociatedArtifacts(AssociatedArtifactsGenerator.randomAssociatedArtifacts())
                .build();
    }

    private static ResourceOwner randomResourceOwner() {
        return new ResourceOwner(randomString(),randomUri());
    }

    private static String randomWord() {
        return FAKER.lorem().word();
    }
}
