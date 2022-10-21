package no.unit.nva.api;

import static no.unit.nva.hamcrest.DoesNotHaveEmptyValues.doesNotHaveEmptyValuesIgnoringFields;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.Set;
import no.unit.nva.model.Publication;
import no.unit.nva.model.associatedartifacts.InvalidAssociatedArtifactsException;
import no.unit.nva.model.testing.PublicationGenerator;
import org.junit.jupiter.api.Test;

class PublicationResponseTest {

    @Test
    void staticConstructorShouldReturnPublicationResponseWithoutUnexpectedLossOfInformation()
            throws InvalidAssociatedArtifactsException {
        Publication publication = PublicationGenerator.randomPublication();
        assertThat(publication,doesNotHaveEmptyValuesIgnoringFields(Set.of("doiRequest")));
        PublicationResponse publicationResponse = PublicationResponse.fromPublication(publication);
        assertThat(publicationResponse,doesNotHaveEmptyValuesIgnoringFields(Set.of("doiRequest")));
    }

}