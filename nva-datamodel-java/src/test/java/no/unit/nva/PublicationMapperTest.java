package no.unit.nva;

import static no.unit.nva.DatamodelConfig.dataModelObjectMapper;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import no.unit.nva.api.PublicationResponse;
import no.unit.nva.api.UpdatePublicationRequest;
import no.unit.nva.identifiers.SortableIdentifier;
import no.unit.nva.model.Publication;
import no.unit.nva.model.testing.PublicationGenerator;
import org.junit.jupiter.api.Test;

public class PublicationMapperTest {


    public static final ObjectNode SOME_CONTEXT = dataModelObjectMapper.createObjectNode();


    @Test
    public void canMapUpdatePublicationRequestToExistingPublication() {
        Publication sampleData = PublicationGenerator.randomPublication();
        UpdatePublicationRequest request = createUpdateRequest(sampleData);
        Publication existingPublication =
            PublicationGenerator.randomPublication(
                sampleData.getEntityDescription().getReference().getPublicationInstance().getClass()
            );
        Publication publication = PublicationMapper.toExistingPublication(request, existingPublication);

        assertNotNull(publication);
        assertNotEquals(publication.getCreatedDate(), publication.getModifiedDate());
        assertEquals(sampleData.getSubjects(), publication.getSubjects());
    }

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



    @Test
    public void convertValueReturnsUpdatePublicationRequestWhenInputIsValidPublication() {
        Publication publication = PublicationGenerator.randomPublication();

        UpdatePublicationRequest request = PublicationMapper
            .convertValue(publication, UpdatePublicationRequest.class);

        assertNotNull(request);
        assertNotNull(request.getContext());
        assertEquals(publication.getFileSet(), request.getFileSet());
        assertEquals(publication.getProjects(), request.getProjects());
        assertEquals(publication.getEntityDescription(), request.getEntityDescription());
    }

    private UpdatePublicationRequest createUpdateRequest(Publication sampleData) {
        UpdatePublicationRequest request = new UpdatePublicationRequest();
        request.setIdentifier(SortableIdentifier.next());
        request.setEntityDescription(sampleData.getEntityDescription());
        request.setProjects(sampleData.getProjects());
        request.setFileSet(sampleData.getFileSet());
        request.setSubjects(sampleData.getSubjects());
        request.setContext(SOME_CONTEXT);
        return request;
    }

    private void assertThatIdIsPresent(PublicationResponse response) throws JsonProcessingException {
        String string = dataModelObjectMapper.writeValueAsString(response);
        ObjectNode json = (ObjectNode) dataModelObjectMapper.readTree(string);
        assertThat(json.has("id"), is(equalTo(true)));
    }
}

