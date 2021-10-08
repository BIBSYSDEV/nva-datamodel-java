package no.unit.nva.model;

import static no.unit.nva.DatamodelConfig.dataModelObjectMapper;
import static no.unit.nva.hamcrest.DoesNotHaveEmptyValues.doesNotHaveEmptyValues;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import com.fasterxml.jackson.databind.JsonNode;
import java.lang.reflect.Field;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class DoiRequestMessageTest {

    public static final String SOME_TEXT = "Some comment";
    public static final String SOME_AUTHOR = "Some Author";
    public static final Instant someTimestamp = Instant.now();


    @Test
    public void doiRequestContainsDoiRequestMessages() {
        DoiRequestMessage message = sampleDoiRequestMessage();
        DoiRequest doiRequest = new DoiRequest.Builder()
                .addMessage(message)
                .withStatus(DoiRequestStatus.REQUESTED)
                .withModifiedDate(someTimestamp)
                .withCreatedDate(someTimestamp)
                .build();

        assertThat(doiRequest.getMessages(), contains(message));

    }

    @Test
    public void doiRequestMessageCanBeSerialized() {
        DoiRequestMessage message = sampleDoiRequestMessage();
        JsonNode json = dataModelObjectMapper.convertValue(message, JsonNode.class);

        List<String> fieldNames = extractFieldNames();

        assertThat(message, doesNotHaveEmptyValues());
        assertThatAllFieldsAreSerialized(json, fieldNames);
    }


    private DoiRequestMessage sampleDoiRequestMessage() {
        return new DoiRequestMessage.Builder()
                .withText(SOME_TEXT)
                .withAuthor(SOME_AUTHOR)
                .withTimestamp(someTimestamp)
                .build();
    }

    private void assertThatAllFieldsAreSerialized(JsonNode json, List<String> fieldNames) {
        fieldNames.forEach(field -> assertThat(json.hasNonNull(field), is(equalTo(true))));
    }


    private List<String> extractFieldNames() {
        return Arrays.stream((DoiRequestMessage.class.getDeclaredFields()))
                .map(Field::getName).collect(Collectors.toList());


    }

}