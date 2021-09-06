package no.unit.nva.model;

import com.fasterxml.jackson.databind.JsonNode;
import no.unit.nva.model.contexttypes.Book;
import no.unit.nva.model.contexttypes.BookSeries;
import no.unit.nva.model.contexttypes.UnconfirmedSeries;
import no.unit.nva.model.exceptions.InvalidIsbnException;
import no.unit.nva.model.exceptions.InvalidUnconfirmedSeriesException;
import no.unit.nva.model.exceptions.MalformedContributorException;
import no.unit.nva.model.util.PublicationGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static no.unit.nva.model.util.PublicationGenerator.generateBookMonographWithUnconfirmedSeriesTitleString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

public class BookPublicationTest extends PublicationTest {

    @DisplayName("Book publications can be created")
    @Test
    void publicationReturnPublicationWhenInputIsValid() throws MalformedContributorException, IOException,
            InvalidIsbnException {
        Publication bookPublication = PublicationGenerator.generateBookMonographPublication();
        JsonNode document = toPublicationWithContext(bookPublication);

        Publication publicationFromJson = objectMapper.readValue(objectMapper.writeValueAsString(document),
                Publication.class);

        assertThat(bookPublication, is(equalTo(publicationFromJson)));
    }

    @Test
    void publicationReturnsSeriesWhenInputIsSeriesTitle() throws MalformedContributorException, InvalidIsbnException,
            InvalidUnconfirmedSeriesException {
        Publication publication = generateBookMonographWithUnconfirmedSeriesTitleString();
        BookSeries actual = ((Book) publication.getEntityDescription()
                .getReference()
                .getPublicationContext())
                .getSeries();

        assertThat(actual, instanceOf(UnconfirmedSeries.class));
    }
}
