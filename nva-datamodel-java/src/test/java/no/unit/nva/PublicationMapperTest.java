package no.unit.nva;

import static no.unit.nva.DatamodelConfig.dataModelObjectMapper;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import no.unit.nva.api.PublicationResponse;
import no.unit.nva.model.Publication;
import no.unit.nva.model.testing.PublicationGenerator;
import org.junit.jupiter.api.Test;

public class PublicationMapperTest {


    public static final ObjectNode SOME_CONTEXT = dataModelObjectMapper.createObjectNode();

    @Test
    public void canMapPublicationAndContextToPublicationResponse() throws Exception {
        Publication publication = PublicationGenerator.randomPublication();

        PublicationResponse response = PublicationMapper
            .convertValue(publication, SOME_CONTEXT, PublicationResponse.class);

        assertNotNull(response);
        assertThat(response.getId(), notNullValue());
        assertThatIdIsPresent(response);
    }

    @Test
    public void canMapPublicationToPublicationResponse() {
        Publication publication = PublicationGenerator.randomPublication();

        PublicationResponse response = PublicationMapper
            .convertValue(publication, PublicationResponse.class);

        assertNotNull(response);
        assertThat(response.getId(), notNullValue());
    }


    private void assertThatIdIsPresent(PublicationResponse response) throws JsonProcessingException {
        String string = dataModelObjectMapper.writeValueAsString(response);
        ObjectNode json = (ObjectNode) dataModelObjectMapper.readTree(string);
        assertThat(json.has("id"), is(equalTo(true)));
    }
}

