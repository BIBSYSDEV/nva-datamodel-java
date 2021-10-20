package no.unit.nva;

import static no.unit.nva.DatamodelConfig.dataModelObjectMapper;
import static no.unit.nva.model.util.PublicationGenerator.generateEntityDescriptionJournalArticle;
import static no.unit.nva.model.util.PublicationGenerator.getFileSet;
import static no.unit.nva.model.util.PublicationGenerator.getOrganization;
import static no.unit.nva.model.util.PublicationGenerator.getProjects;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import no.unit.nva.api.CreatePublicationRequest;
import no.unit.nva.api.PublicationResponse;
import no.unit.nva.api.UpdatePublicationRequest;
import no.unit.nva.identifiers.SortableIdentifier;
import no.unit.nva.model.Publication;
import no.unit.nva.model.util.PublicationGenerator;
import org.junit.jupiter.api.Test;

public class PublicationMapperTest {

    public static final URI SOME_URI = URI.create("http://example.org");
    public static final String SOME_OWNER = "owner";
    public static final ObjectNode SOME_CONTEXT = dataModelObjectMapper.createObjectNode();

    @Test
    public void canMapCreatePublicationRequestToNewPublication() throws Exception {

        CreatePublicationRequest request = new CreatePublicationRequest();
        request.setEntityDescription(generateEntityDescriptionJournalArticle());
        request.setProjects(getProjects());
        request.setFileSet(getFileSet(UUID.randomUUID()));
        request.setContext(SOME_CONTEXT);

        Publication publication = PublicationMapper
            .toNewPublication(request, SOME_OWNER, SOME_URI, SOME_URI, getOrganization());

        assertNotNull(publication);
        assertEquals(publication.getCreatedDate(), publication.getModifiedDate());

    }

    @Test
    public void canMapUpdatePublicationRequestToExistingPublication() throws Exception {

        UpdatePublicationRequest request = new UpdatePublicationRequest();
        request.setIdentifier(SortableIdentifier.next());
        request.setEntityDescription(generateEntityDescriptionJournalArticle());
        request.setProjects(getProjects());
        request.setFileSet(getFileSet(UUID.randomUUID()));
        request.setSubjects(getSubjects());
        request.setContext(SOME_CONTEXT);

        Publication publication = PublicationMapper.toExistingPublication(request,
                PublicationGenerator.generateJournalArticlePublication());

        assertNotNull(publication);
        assertNotEquals(publication.getCreatedDate(), publication.getModifiedDate());
        assertEquals(getSubjects(), publication.getSubjects());
    }

    private List<URI> getSubjects() {
        return List.of(URI.create("http://uri.to.subject"));
    }

    @Test
    public void canMapPublicationAndContextToPublicationResponse() throws Exception {
        Publication publication = PublicationGenerator.generateJournalArticlePublication();

        PublicationResponse response = PublicationMapper
            .convertValue(publication, SOME_CONTEXT, PublicationResponse.class);

        assertNotNull(response);
        assertThat(response.getId(), notNullValue());
        assertThatIdIsPresent(response);
        assertThatTypIsPresent(response);
    }

    private void assertThatTypIsPresent(PublicationResponse response) throws JsonProcessingException {
        String string = dataModelObjectMapper.writeValueAsString(response);
        ObjectNode json = (ObjectNode) dataModelObjectMapper.readTree(string);
        assertThat(json.has("type"), is(equalTo(true)));
    }

    private void assertThatIdIsPresent(PublicationResponse response) throws JsonProcessingException {
        String string = dataModelObjectMapper.writeValueAsString(response);
        ObjectNode json = (ObjectNode) dataModelObjectMapper.readTree(string);
        assertThat(json.has("id"), is(equalTo(true)));
    }

    @Test
    public void canMapPublicationToPublicationResponse() throws Exception {
        Publication publication = PublicationGenerator.generateJournalArticlePublication();

        PublicationResponse response = PublicationMapper
            .convertValue(publication, PublicationResponse.class);

        assertNotNull(response);
        assertThat(response.getId(), notNullValue());
    }

    @Test
    public void convertValueReturnsCreatePublicationRequestWhenInputIsValidPublication() throws Exception {
        Publication publication = PublicationGenerator.generateJournalArticlePublication();

        CreatePublicationRequest request = PublicationMapper
            .convertValue(publication, CreatePublicationRequest.class);

        assertNotNull(request);
        assertNotNull(request.getContext());
        assertEquals(publication.getFileSet(), request.getFileSet());
        assertEquals(publication.getProjects(), request.getProjects());
        assertEquals(publication.getEntityDescription(), request.getEntityDescription());

    }

    @Test
    public void convertValueReturnsUpdatePublicationRequestWhenInputIsValidPublication() throws Exception {
        Publication publication = PublicationGenerator.generateJournalArticlePublication();

        UpdatePublicationRequest request = PublicationMapper
            .convertValue(publication, UpdatePublicationRequest.class);

        assertNotNull(request);
        assertNotNull(request.getContext());
        assertEquals(publication.getFileSet(), request.getFileSet());
        assertEquals(publication.getProjects(), request.getProjects());
        assertEquals(publication.getEntityDescription(), request.getEntityDescription());
    }
}

