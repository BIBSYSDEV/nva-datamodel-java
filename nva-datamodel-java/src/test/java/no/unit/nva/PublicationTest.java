package no.unit.nva;

import static no.unit.nva.DatamodelConfig.dataModelObjectMapper;
import static no.unit.nva.hamcrest.DoesNotHaveEmptyValues.doesNotHaveEmptyValues;
import static no.unit.nva.model.PublicationStatus.DRAFT;
import static no.unit.nva.model.PublicationStatus.DRAFT_FOR_DELETION;
import static no.unit.nva.model.PublicationStatus.NEW;
import static no.unit.nva.model.PublicationStatus.PUBLISHED;
import static no.unit.nva.model.file.FileModelTest.buildAdministrativeAgreement;
import static no.unit.nva.model.file.FileModelTest.buildNonAdministrativeAgreement;
import static no.unit.nva.model.file.FileModelTest.randomLegacyFile;
import static no.unit.nva.model.testing.PublicationGenerator.randomPublication;
import static no.unit.nva.model.testing.PublicationGenerator.randomUri;
import static no.unit.nva.model.testing.associatedartifacts.AssociatedArtifactsGenerator.randomAssociatedLink;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import no.unit.nva.commons.json.JsonUtils;
import no.unit.nva.identifiers.SortableIdentifier;
import no.unit.nva.model.AdditionalIdentifier;
import no.unit.nva.model.Publication;
import no.unit.nva.model.PublicationStatus;
import no.unit.nva.model.associatedartifacts.AssociatedArtifactList;
import no.unit.nva.model.associatedartifacts.InvalidAssociatedArtifactsException;
import no.unit.nva.model.associatedartifacts.NullAssociatedArtifact;
import no.unit.nva.model.associatedartifacts.file.AdministrativeAgreement;
import no.unit.nva.model.associatedartifacts.file.LegacyFile;
import no.unit.nva.model.associatedartifacts.file.PublishedFile;
import no.unit.nva.model.associatedartifacts.file.UnpublishedFile;
import no.unit.nva.model.exceptions.InvalidPublicationStatusTransitionException;
import no.unit.nva.model.testing.PublicationGenerator;
import no.unit.nva.model.testing.PublicationInstanceBuilder;
import no.unit.nva.model.testing.associatedartifacts.AdministrativeAgreementGenerator;
import no.unit.nva.model.testing.associatedartifacts.AssociatedLinkGenerator;
import no.unit.nva.model.testing.associatedartifacts.PublishedFileGenerator;
import no.unit.nva.model.testing.associatedartifacts.UnpublishedFileGenerator;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

public class PublicationTest {

    public static final String DOCUMENTATION_PATH_TEMPLATE = "../documentation/%s.json";
    public static final SortableIdentifier REPLACEMENT_IDENTIFIER_1 =
        new SortableIdentifier("c443030e-9d56-43d8-afd1-8c89105af555");
    public static final Javers JAVERS = JaversBuilder.javers().build();

    public static Stream<Class<?>> publicationInstanceProvider() {
        return PublicationInstanceBuilder.listPublicationInstanceTypes().stream();
    }

    public static Stream<Publication> publishablePublicationProvider() {

        var publication = randomPublication();
        publication.setStatus(DRAFT);

        return Stream.of(
            publicationWithOriginalDoi(),
            publicationWithAdministrativeAgreementAndUnpublishedFile(),
            publicationWithAdministrativeAgreementAndPublishedFile(),
            publicationWithAdministrativeAgreementAndLink()
        );
    }

    public static Stream<Publication> unpublishablePublicationProvider() {
        return Stream.of(
            randomDraftForDeletion(),
            publicationWithoutTitle(),
            publicationWithOnlyAdministrativeAgreement(),
            publicationWithoutEntityDescription(),
            publicationWithNoAssociatedArtifactsOrOriginalDoi()
        );
    }

    @ParameterizedTest(name = "Test that publication with InstanceType {0} can be round-tripped to and from JSON")
    @MethodSource("publicationInstanceProvider")
    void publicationReturnsValidPublicationWhenInputIsValid(Class<?> instanceType) throws Exception {
        Publication expected = randomPublication(instanceType);

        String publication = dataModelObjectMapper.writeValueAsString(expected);
        Publication roundTripped = dataModelObjectMapper.readValue(publication, Publication.class);
        Diff diff = JAVERS.compare(expected, roundTripped);
        assertThatPublicationDoesNotHaveEmptyFields(expected);
        assertEquals(expected, roundTripped);
        assertThat(diff.prettyPrint(), roundTripped, is(equalTo(expected)));

        writePublicationToFile(instanceType, expected);
    }

    @ParameterizedTest(name = "Test that publication with InstanceType {0} can be copied without loss of data")
    @MethodSource("publicationInstanceProvider")
    void copyReturnsBuilderWithAllDataOfAPublication(Class<?> referenceInstanceType) {
        Publication publication = randomPublication(referenceInstanceType);
        Publication copy = publication.copy().build();
        assertThatPublicationDoesNotHaveEmptyFields(publication);
        Diff diff = compareAsObjectNodes(publication, copy);
        assertThat(diff.prettyPrint(), copy, is(equalTo(publication)));
        assertThat(copy, is(not(sameInstance(publication))));
    }

    @Test
    void copyShouldCreateDeepCopyOfPublicationWithoutOverridingOriginalPublicationValuesWhenModifyingCopy() {
        Publication publication = randomPublication();
        Publication copy = publication.copy().build();
        copy.setLink(randomUri());
        Diff diff = compareAsObjectNodes(publication, copy);
        assertThat(diff.prettyPrint(), copy.getLink(), not(is(equalTo(publication.getLink()))));
        assertThat(copy, is(not(sameInstance(publication))));
    }

    @ParameterizedTest(name = "Test that publication with InstanceType {0} can be round-tripped to and from JSON")
    @MethodSource("publicationInstanceProvider")
    void projectsAreSetAsListsWhenInputIsSingleProject(Class<?> instanceType) {
        Publication expected = randomPublication(instanceType);
        assertThat(expected.getProjects(), instanceOf(List.class));
    }

    @ParameterizedTest
    @EnumSource(value = PublicationStatus.class, names = {"DRAFT_FOR_DELETION", "PUBLISHED"})
    void updateStatusForDraftPublication(PublicationStatus target) throws Exception {
        Publication publication = randomPublication();
        publication.setStatus(DRAFT);
        publication.updateStatus(target);

        assertThat(publication.getStatus(), is(equalTo(target)));
    }

    @Test
    void updateStatusThrowsExceptionForInvalidStatusTransition() {
        Publication publication = randomPublication();
        publication.setStatus(NEW);

        InvalidPublicationStatusTransitionException exception =
            assertThrows(InvalidPublicationStatusTransitionException.class, () -> publication.updateStatus(PUBLISHED));

        String expectedError = String.format(InvalidPublicationStatusTransitionException.ERROR_MSG_TEMPLATE,
                                             NEW, PUBLISHED);
        assertThat(exception.getMessage(), is(equalTo(expectedError)));
    }

    //TODO: This is a temporary fix. type "File" for files should not be acceptable
    @Test
    void shouldReturnPublicationWithLegacyFileWhenInputIsPublicationWithLegacyFile()
        throws JsonProcessingException {
        var legacyFile = randomLegacyFile();
        var publication = randomPublication()
                              .copy()
                              .withAssociatedArtifacts(List.of(legacyFile))
                              .build();
        var deserialized = serializeDeserialize(publication);
        assertThat(deserialized.getAssociatedArtifacts().get(0), is(instanceOf(LegacyFile.class)));
    }

    @Test
    void shouldConvertPublishableArtifactToPublishedUponRequest() {
        var legacyFile = buildNonAdministrativeAgreement().buildLegacyFile();
        var unpublishedFile = buildNonAdministrativeAgreement().buildUnpublishedFile();
        var publishedFile = buildNonAdministrativeAgreement().buildPublishedFile();
        assertThat(legacyFile.toPublishedFile(), is(instanceOf(PublishedFile.class)));
        assertThat(unpublishedFile.toPublishedFile(), is(instanceOf(PublishedFile.class)));
        assertThat(publishedFile.toPublishedFile(), is(instanceOf(PublishedFile.class)));
    }

    @Test
    void shouldConvertPublishableArtifactToUnpublishedUponRequest() {
        var legacyFile = buildNonAdministrativeAgreement().buildLegacyFile();
        var unpublishedFile = buildNonAdministrativeAgreement().buildUnpublishedFile();
        var publishedFile = buildNonAdministrativeAgreement().buildPublishedFile();
        assertThat(legacyFile.toUnpublishedFile(), is(instanceOf(UnpublishedFile.class)));
        assertThat(unpublishedFile.toUnpublishedFile(), is(instanceOf(UnpublishedFile.class)));
        assertThat(publishedFile.toUnpublishedFile(), is(instanceOf(UnpublishedFile.class)));
    }

    @Test
    void shouldConvertLegacyAndUnpublishableArtifactsToUnpublishableUponRequest() {
        var legacyFile = buildAdministrativeAgreement().buildLegacyFile();
        var unpublishableFile = buildAdministrativeAgreement().buildUnpublishableFile();
        assertThat(legacyFile.toUnpublishableFile(), is(instanceOf(AdministrativeAgreement.class)));
        assertThat(unpublishableFile.toUnpublishableFile(), is(instanceOf(AdministrativeAgreement.class)));
    }

    @Test
    void shouldNotConvertUnPublishableArtifactToPublishableArtifacts() {
        var unpublishableFile = buildAdministrativeAgreement().buildUnpublishableFile();
        assertThrows(IllegalStateException.class, unpublishableFile::toPublishedFile);
        assertThrows(IllegalStateException.class, unpublishableFile::toUnpublishedFile);
        assertThrows(IllegalStateException.class, unpublishableFile::toUnpublishedFile);
    }

    // This test is included because of a bizarre error.
    @Test
    void initializingPublicationShouldNotThrowException() {
        assertDoesNotThrow(Publication::new);
    }

    @Test
    void shouldThrowExceptionWhenCreatingAssociatedArtifactsWithNullArtifactsAndOtherArtifacts() {
        Executable executable = () -> new AssociatedArtifactList(randomAssociatedLink(),
                                                                 new NullAssociatedArtifact());
        assertThrows(InvalidAssociatedArtifactsException.class, executable);
    }

    @ParameterizedTest(name = "Publication can be published when basic data is OK and associated files is OK")
    @MethodSource("publishablePublicationProvider")
    void shouldMarkPublicationAsPublishableWhenPublicationHasRequiredData(Publication publication) {
        assertThat(publication.isPublishable(), is(equalTo(true)));
    }

    @ParameterizedTest(name = "Publication cannot be published when basic data is not in place")
    @MethodSource("unpublishablePublicationProvider")
    void shouldMarkPublicationAsWhenDataIsNotCompliantWithPublicationRequirements(Publication publication) {
        assertThat(publication.isPublishable(), is(equalTo(false)));
    }

    private static Publication publicationWithOnlyAdministrativeAgreement() {
        var publication = PublicationGenerator.randomPublication();
        publication.setStatus(DRAFT);
        publication.getEntityDescription().getReference().setDoi(null);
        publication.setAssociatedArtifacts(new AssociatedArtifactList(AdministrativeAgreementGenerator.random()));
        return publication;
    }

    private static Publication publicationWithoutTitle() {
        var publication = PublicationGenerator.randomPublication();
        publication.setStatus(DRAFT);
        publication.getEntityDescription().setMainTitle(null);
        return publication;
    }

    private static Publication randomDraftForDeletion() {
        var publication = PublicationGenerator.randomPublication();
        publication.setStatus(DRAFT_FOR_DELETION);
        return publication;
    }

    private static Publication publicationWithoutEntityDescription() {
        var publication = publicationWithoutTitle();
        publication.setEntityDescription(null);
        return publication;
    }

    private static Publication publicationWithNoAssociatedArtifactsOrOriginalDoi() {
        var publication = randomPublication();
        publication.setStatus(DRAFT);
        publication.setAssociatedArtifacts(null);
        publication.getEntityDescription().getReference().setDoi(null);
        return publication;
    }

    private static Publication publicationWithAdministrativeAgreementAndLink() {
        var administrativeAgreement = AdministrativeAgreementGenerator.random();
        var link = AssociatedLinkGenerator.random();
        return publicationWithAssociatedArtifact(new AssociatedArtifactList(List.of(administrativeAgreement, link)));
    }

    private static Publication publicationWithAdministrativeAgreementAndUnpublishedFile() {
        var unpublishedFile = UnpublishedFileGenerator.random();
        var administrativeAgreement = AdministrativeAgreementGenerator.random();
        return publicationWithAssociatedArtifact(new AssociatedArtifactList(List.of(administrativeAgreement,
                                                                                    unpublishedFile)));
    }

    private static Publication publicationWithAdministrativeAgreementAndPublishedFile() {
        var publishedFile = PublishedFileGenerator.random();
        var administrativeAgreement = AdministrativeAgreementGenerator.random();
        return publicationWithAssociatedArtifact(new AssociatedArtifactList(List.of(administrativeAgreement,
                                                                                    publishedFile)));
    }

    private static Publication publicationWithOriginalDoi() {
        var publication = randomPublication();
        publication.setStatus(DRAFT);
        publication.setAssociatedArtifacts(null);
        return publication;
    }

    private static Publication publicationWithAssociatedArtifact(AssociatedArtifactList associatedArtifacts) {
        var publication = randomPublication();
        publication.setStatus(DRAFT);
        publication.getEntityDescription().getReference().setDoi(null);
        publication.setAssociatedArtifacts(associatedArtifacts);
        return publication;
    }

    private Publication serializeDeserialize(Publication publication) throws JsonProcessingException {
        var json = JsonUtils.dtoObjectMapper.writeValueAsString(publication);
        return JsonUtils.dtoObjectMapper.readValue(json, Publication.class);
    }

    private void assertThatPublicationDoesNotHaveEmptyFields(Publication expected) {
        assertThat(expected, doesNotHaveEmptyValues());
    }

    private Diff compareAsObjectNodes(Publication publication, Publication copy) {
        var publicationObjectNode = dataModelObjectMapper.convertValue(publication, ObjectNode.class);
        var copyObjectNode = dataModelObjectMapper.convertValue(copy, ObjectNode.class);
        return JAVERS.compare(publicationObjectNode, copyObjectNode);
    }

    private void writePublicationToFile(Class<?> instanceType, Publication publication) throws IOException {
        publication.setIdentifier(REPLACEMENT_IDENTIFIER_1);
        publication.setAdditionalIdentifiers(Set.of(new AdditionalIdentifier("fakesource", "1234")));
        String path = String.format(DOCUMENTATION_PATH_TEMPLATE, instanceType.getSimpleName());
        var publicationJson = dataModelObjectMapper.writeValueAsString(publication);
        Files.write(Paths.get(path), publicationJson.getBytes());
    }
}
