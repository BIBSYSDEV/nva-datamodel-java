package no.unit.nva.api;

import static no.unit.nva.hamcrest.DoesNotHaveEmptyValues.doesNotHaveEmptyValuesIgnoringFields;
import static no.unit.nva.model.PublicationTest.AGENT_TOP_LEVEL_ORG_FIELD;
import static no.unit.nva.model.PublicationTest.ALLOWED_OPERATIONS_FIELD;
import static no.unit.nva.model.PublicationTest.BOOK_REVISION_FIELD;
import static no.unit.nva.model.PublicationTest.PUBLISHER_TOP_LEVEL_ORG_FIELD;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import java.util.Set;
import no.unit.nva.model.PublicationOperation;
import no.unit.nva.model.Publication;
import no.unit.nva.model.testing.PublicationGenerator;
import org.junit.jupiter.api.Test;

class PublicationResponseTest {

    @Test
    void staticConstructorShouldReturnPublicationResponseWithoutUnexpectedLossOfInformation() {
        Publication publication = PublicationGenerator.randomPublication();
        var ignoreFields = Set.of(BOOK_REVISION_FIELD,
                                  ALLOWED_OPERATIONS_FIELD, AGENT_TOP_LEVEL_ORG_FIELD, PUBLISHER_TOP_LEVEL_ORG_FIELD);
        assertThat(publication, doesNotHaveEmptyValuesIgnoringFields(ignoreFields));
        PublicationResponse publicationResponse = PublicationResponse.fromPublication(publication);
        assertThat(publicationResponse,
                   doesNotHaveEmptyValuesIgnoringFields(ignoreFields));
    }

    @Test
    void staticConstructorWithAllowedOperationsShouldReturnPublicationResponseWithoutUnexpectedLossOfInformation() {
        Publication publication = PublicationGenerator.randomPublication();
        var operation = PublicationOperation.UPDATE;
        PublicationResponse publicationResponse = PublicationResponse.fromPublicationWithAllowedOperations(publication,
                                                                                                           Set.of(
                                                                                                               operation));
        assertThat(publicationResponse.getAllowedOperations(), is(equalTo(Set.of(operation))));
    }
}