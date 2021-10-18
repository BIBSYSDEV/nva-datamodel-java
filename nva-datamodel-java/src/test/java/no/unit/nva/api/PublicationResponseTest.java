package no.unit.nva.api;

import static no.unit.nva.hamcrest.DoesNotHaveEmptyValues.doesNotHaveEmptyValues;
import static no.unit.nva.hamcrest.DoesNotHaveEmptyValues.doesNotHaveEmptyValuesIgnoringFields;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.Set;
import no.unit.nva.model.Publication;
import no.unit.nva.model.exceptions.InvalidIsbnException;
import no.unit.nva.model.exceptions.InvalidIssnException;
import no.unit.nva.model.exceptions.InvalidUnconfirmedSeriesException;
import no.unit.nva.model.util.PublicationGenerator;
import org.junit.jupiter.api.Test;

class PublicationResponseTest {

    @Test
    void staticConstructorShouldReturnPublicationResponseWithoutUnexpectedLossOfInformation()
        throws InvalidIssnException, InvalidIsbnException, InvalidUnconfirmedSeriesException {
        Publication publication = PublicationGenerator.generatePublication("JournalArticle");
        assertThat(publication,doesNotHaveEmptyValues());
        PublicationResponse publicationResponse = PublicationResponse.fromPublication(publication);
        assertThat(publicationResponse,doesNotHaveEmptyValues());
    }

}