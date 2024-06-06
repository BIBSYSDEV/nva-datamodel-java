package no.unit.nva.model.testing;

import static java.util.function.Predicate.not;
import static no.unit.nva.model.testing.PublicationInstanceBuilder.randomPublicationInstanceType;
import static no.unit.nva.model.testing.RandomCurrencyUtil.randomCurrency;
import static no.unit.nva.model.testing.RandomUtils.randomLabels;
import static no.unit.nva.testutils.RandomDataGenerator.randomElement;
import static no.unit.nva.testutils.RandomDataGenerator.randomInstant;
import static no.unit.nva.testutils.RandomDataGenerator.randomInteger;
import static no.unit.nva.testutils.RandomDataGenerator.randomString;
import java.net.URI;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import net.datafaker.providers.base.BaseFaker;
import no.unit.nva.identifiers.SortableIdentifier;
import no.unit.nva.model.AdditionalIdentifier;
import no.unit.nva.model.Approval;
import no.unit.nva.model.ApprovalStatus;
import no.unit.nva.model.ApprovalsBody;
import no.unit.nva.model.EntityDescription;
import no.unit.nva.model.Organization;
import no.unit.nva.model.Publication;
import no.unit.nva.model.Publication.Builder;
import no.unit.nva.model.PublicationNote;
import no.unit.nva.model.PublicationNoteBase;
import no.unit.nva.model.PublicationStatus;
import no.unit.nva.model.ResearchProject;
import no.unit.nva.model.ResourceOwner;
import no.unit.nva.model.UnpublishingNote;
import no.unit.nva.model.Username;
import no.unit.nva.model.funding.Funding;
import no.unit.nva.model.funding.FundingBuilder;
import no.unit.nva.model.funding.MonetaryAmount;
import no.unit.nva.model.instancetypes.degree.DegreeBachelor;
import no.unit.nva.model.instancetypes.degree.DegreeBase;
import no.unit.nva.model.instancetypes.degree.DegreeLicentiate;
import no.unit.nva.model.instancetypes.degree.DegreeMaster;
import no.unit.nva.model.instancetypes.degree.DegreePhd;
import no.unit.nva.model.testing.associatedartifacts.AssociatedArtifactsGenerator;
import nva.commons.core.JacocoGenerated;

@JacocoGenerated
public final class PublicationGenerator {

    private static final BaseFaker FAKER = new BaseFaker();

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
        String uriString = "https://www.example.org/" + UUID.randomUUID();
        return URI.create(uriString);
    }

    public static Publication randomPublication() {
        return randomPublication(randomPublicationInstanceType());
    }

    public static Publication randomPublication(Class<?> publicationInstanceClass) {

        return buildRandomPublicationFromInstance(publicationInstanceClass);
    }

    /*
    This method is used to generate test data for tests regarding special protection of degree thesis. Use
    randomPublicationNonDegreeThesis() for this purpose.
     */
    @Deprecated(since = "0.21.25", forRemoval = true)
    public static Publication randomPublicationNonDegree() {
        var nonDegrees = PublicationInstanceBuilder.listPublicationInstanceTypes()
                             .stream()
                             .filter(not(PublicationGenerator::isDegree))
                             .toList();
        return randomPublication(randomElement(nonDegrees));
    }

    public static Publication randomPublicationNonDegreeThesis() {
        var nonDegreeTheses = PublicationInstanceBuilder.listPublicationInstanceTypes()
                             .stream()
                             .filter(not(PublicationGenerator::isDegreeThesis))
                             .toList();
        return randomPublication(randomElement(nonDegreeTheses));
    }

    public static Publication randomPublicationDegreeThesis() {
        var degreeTheses = PublicationInstanceBuilder.listPublicationInstanceTypes()
                             .stream()
                             .filter(PublicationGenerator::isDegreeThesis)
                             .toList();
        return randomPublication(randomElement(degreeTheses));
    }

    private static boolean isDegree(Class<?> subClass) {
        return DegreeBase.class.isAssignableFrom(subClass);
    }

    private static boolean isDegreeThesis(Class<?> subClass) {
        return subClass.equals(DegreePhd.class) ||
               subClass.equals(DegreeMaster.class) ||
               subClass.equals(DegreeBachelor.class) ||
               subClass.equals(DegreeLicentiate.class);
    }

    public static Publication randomPublicationWithEmptyValues(Class<?> publicationInstanceClass) {
        return buildRandomPublicationFromInstance(publicationInstanceClass);
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

    public static List<Funding> randomFundings() {
        return List.of(randomUnconfirmedFunding(), randomConfirmedFunding());
    }

    public static Funding randomConfirmedFunding() {
        var activeFrom = randomInstant();

        return new FundingBuilder()
                   .withId(randomUri())
                   .withSource(randomUri())
                   .withIdentifier(randomString())
                   .withLabels(randomLabels())
                   .withFundingAmount(randomMonetaryAmount())
                   .withActiveFrom(activeFrom)
                   .withActiveTo(randomInstant(activeFrom))
                   .build();
    }

    public static Funding randomUnconfirmedFunding() {
        var activeFrom = randomInstant();

        return new FundingBuilder()
                   .withSource(randomUri())
                   .withIdentifier(randomString())
                   .withLabels(randomLabels())
                   .withFundingAmount(randomMonetaryAmount())
                   .withActiveFrom(activeFrom)
                   .withActiveTo(randomInstant(activeFrom))
                   .build();
    }

    private static MonetaryAmount randomMonetaryAmount() {
        var monetaryAmount = new MonetaryAmount();

        monetaryAmount.setCurrency(randomCurrency());
        monetaryAmount.setAmount(randomInteger().longValue());

        return monetaryAmount;
    }

    public static ResearchProject randomResearchProject() {
        return new ResearchProject.Builder()
                   .withId(randomUri())
                   .withName(randomString())
                   .withApprovals(randomApprovals())
                   .build();
    }

    public static List<Approval> randomApprovals() {
        return List.of(randomApproval());
    }

    public static Approval randomApproval() {

        return new Approval.Builder()
                   .withApprovalStatus(randomElement(ApprovalStatus.values()))
                   .withApprovalDate(randomInstant())
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
                   .build();
    }

    private static Publication buildRandomPublicationFromInstance(Class<?> publicationInstanceClass) {
        return new Builder()
                   .withIdentifier(SortableIdentifier.next())
                   .withRightsHolder(randomString())
                   .withPublisher(randomOrganization())
                   .withSubjects(List.of(randomUri()))
                   .withStatus(randomElement(PublicationStatus.values()))
                   .withPublishedDate(randomInstant())
                   .withModifiedDate(randomInstant())
                   .withAdditionalIdentifiers(Set.of(randomAdditionalIdentifier()))
                   .withProjects(randomProjects())
                   .withFundings(randomFundings())
                   .withResourceOwner(randomResourceOwner())
                   .withLink(randomUri())
                   .withIndexedDate(randomInstant())
                   .withHandle(randomUri())
                   .withDoi(randomDoi())
                   .withCreatedDate(randomInstant())
                   .withEntityDescription(randomEntityDescription(publicationInstanceClass))
                   .withAssociatedArtifacts(AssociatedArtifactsGenerator.randomAssociatedArtifacts())
                   .withPublicationNotes(List.of(randomPublicationNote(), randomUnpublishingNote()))
                   .withDuplicateOf(randomUri())
                   .withCuratingInstitutions(Set.of(randomUri()))
                   .build();
    }

    private static PublicationNoteBase randomPublicationNote() {
        return new PublicationNote(randomString());
    }

    private static PublicationNoteBase randomUnpublishingNote() {
        return new UnpublishingNote(randomString(), new Username(randomString()), randomInstant());
    }

    private static ResourceOwner randomResourceOwner() {
        return new ResourceOwner(new Username(randomString()), randomUri());
    }

    private static String randomWord() {
        return FAKER.lorem().word();
    }
}
