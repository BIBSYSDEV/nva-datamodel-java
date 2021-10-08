package no.unit.nva.model;

import static no.unit.nva.hamcrest.DoesNotHaveEmptyValues.doesNotHaveEmptyValues;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import org.junit.jupiter.api.Test;

class DoiRequestTest {

    public static final Instant CREATION_TIME = Instant.now();
    public static final Instant LATER_THAN_CREATION_TIME = CREATION_TIME.plus(Duration.ofDays(1));
    public static final String SOME_AUTHOR = "SomeAuthor";
    public static final String SOME_TEXT = "SomeText";

    @Test
    public void copyReturnsABuilderWithAllDataCopied() {
        DoiRequest doiRequest = sampleDoiRequest();

        DoiRequest copy = doiRequest.copy().build();

        assertThat(doiRequest, doesNotHaveEmptyValues());
        assertThat(copy, is(equalTo(doiRequest)));
        assertThat(copy, is(not(sameInstance(doiRequest))));
    }

    private DoiRequest sampleDoiRequest() {
        DoiRequestMessage message = new DoiRequestMessage.Builder()
            .withAuthor(SOME_AUTHOR)
            .withText(SOME_TEXT)
            .withTimestamp(LATER_THAN_CREATION_TIME)
            .build();
        return new DoiRequest.Builder()
            .withStatus(DoiRequestStatus.REQUESTED)
            .withModifiedDate(CREATION_TIME)
            .withCreatedDate(CREATION_TIME)
            .withMessages(Collections.singletonList(message))
            .build();
    }
}