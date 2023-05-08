package no.unit.nva.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import no.unit.nva.model.testing.PublicationGenerator;
import org.junit.jupiter.api.Test;

public class ImportCandidateTest {

    @Test
    void shouldCreatePublicationFromImportCandidate() {
        var randomImportCandidate = PublicationGenerator.randomImportCandidate();
        var expectedPublication = createExpectedPublication(randomImportCandidate);
        var actualImportedPublication = randomImportCandidate.toPublication();
        assertThat(actualImportedPublication, is(equalTo(expectedPublication)));

    }

    private Publication createExpectedPublication(ImportCandidate randomImportCandidate) {
        return new Publication.Builder()
                   .withStatus(PublicationStatus.PUBLISHED)
                   .withAssociatedArtifacts(randomImportCandidate.getAssociatedArtifacts())
                   .withEntityDescription(randomImportCandidate.getEntityDescription())
                   .withAdditionalIdentifiers(randomImportCandidate.getAdditionalIdentifiers())
                   .withCreatedDate(randomImportCandidate.getCreatedDate())
                   .withDoi(randomImportCandidate.getDoi())
                   .withFundings(randomImportCandidate.getFundings())
                   .withSubjects(randomImportCandidate.getSubjects())
                   .withIdentifier(randomImportCandidate.getIdentifier())
                   .withLink(randomImportCandidate.getLink())
                   .withModifiedDate(randomImportCandidate.getModifiedDate())
                   .withProjects(randomImportCandidate.getProjects())
                   .withPublisher(randomImportCandidate.getPublisher())
                   .withResourceOwner(randomImportCandidate.getResourceOwner())
                   .withRightsHolder(randomImportCandidate.getRightsHolder())
                   .withHandle(randomImportCandidate.getHandle())
                   .withIndexedDate(randomImportCandidate.getIndexedDate())
                   .withPublishedDate(randomImportCandidate.getPublishedDate())
                   .build();
    }
}
