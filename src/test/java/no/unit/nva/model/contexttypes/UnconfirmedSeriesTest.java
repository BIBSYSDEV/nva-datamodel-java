package no.unit.nva.model.contexttypes;

import static no.unit.nva.model.util.PublicationGenerator.randomInvalidIssn;
import static no.unit.nva.model.util.PublicationGenerator.randomIssn;
import static no.unit.nva.utils.RandomData.randomString;
import static nva.commons.core.JsonUtils.objectMapperWithEmpty;
import static nva.commons.core.attempt.Try.attempt;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import no.unit.nva.model.exceptions.InvalidIssnException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class UnconfirmedSeriesTest {

    @Test
    public void constructorReturnsUnconfirmedSeriesWhenInputContainsValidIssn() throws InvalidIssnException {
        String randomIssn = randomIssn();
        assertDoesNotThrow(() -> new UnconfirmedSeries(randomString(), randomIssn,randomIssn));
        UnconfirmedSeries series = new UnconfirmedSeries(randomString(), randomIssn,randomIssn);
        assertThat(series.getIssn(), is(equalTo(randomIssn)));
    }

    @Test
    public void jsonCreatorReturnsUnconfirmedSeriesWhenInputContainsValidIssnString() {
        String json = attempt(() -> new UnconfirmedSeries(randomString(), randomIssn(),randomIssn()))
            .map(objectMapperWithEmpty::writeValueAsString)
            .orElseThrow();

        assertDoesNotThrow(() -> objectMapperWithEmpty.readValue(json, UnconfirmedSeries.class));
    }

    @Test
    public void constructorThrowsExceptionWhenInputContainsInvalidIssn() {
        String invalidIssn = randomInvalidIssn();
        Executable action = () -> new UnconfirmedSeries(randomString(), invalidIssn,randomIssn());
        InvalidIssnException exception = assertThrows(InvalidIssnException.class, action);
        assertThat(exception.getMessage(), containsString(invalidIssn));
    }

    @Test
    public void constructorThrowsExceptionWhenInputContainsInvalidOnlineIssn() {
        String invalidIssn = randomInvalidIssn();
        Executable action = () -> new UnconfirmedSeries(randomString(), randomIssn(),invalidIssn);
        InvalidIssnException exception = assertThrows(InvalidIssnException.class, action);
        assertThat(exception.getMessage(), containsString(invalidIssn));
    }
}