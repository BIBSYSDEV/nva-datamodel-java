package no.unit.nva;

import com.fasterxml.jackson.databind.node.ObjectNode;
import no.unit.nva.api.CreatePublicationRequest;
import no.unit.nva.api.PublicationResponse;
import no.unit.nva.api.UpdatePublicationRequest;
import no.unit.nva.model.DoiRequestStatus;
import no.unit.nva.model.Publication;
import no.unit.nva.model.util.PublicationGenerator;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.UUID;

import static no.unit.nva.model.util.PublicationGenerator.generateEntityDescriptionJournalArticle;
import static no.unit.nva.model.util.PublicationGenerator.getFileSet;
import static no.unit.nva.model.util.PublicationGenerator.getOrganization;
import static no.unit.nva.model.util.PublicationGenerator.getProject;
import static nva.commons.utils.JsonUtils.objectMapper;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PublicationMapperTest {

    public static final URI SOME_URI = URI.create("http://example.org");
    public static final String SOME_OWNER = "owner";
    public static final ObjectNode SOME_CONTEXT = objectMapper.createObjectNode();

    @Test
    public void canMapCreatePublicationRequestToNewPublication() throws Exception {

        CreatePublicationRequest request = new CreatePublicationRequest();
        request.setEntityDescription(generateEntityDescriptionJournalArticle());
        request.setProject(getProject());
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
        request.setIdentifier(UUID.randomUUID());
        request.setEntityDescription(generateEntityDescriptionJournalArticle());
        request.setProject(getProject());
        request.setFileSet(getFileSet(UUID.randomUUID()));
        request.setContext(SOME_CONTEXT);

        Publication publication = PublicationMapper.toExistingPublication(request,
                PublicationGenerator.generateJournalArticlePublication());

        assertNotNull(publication);
        assertNotEquals(publication.getCreatedDate(), publication.getModifiedDate());
    }

    @Test
    public void canMapPublicationAndContextToPublicationResponse() throws Exception {
        Publication publication = PublicationGenerator.generateJournalArticlePublication();

        PublicationResponse response = PublicationMapper
            .convertValue(publication, SOME_CONTEXT, PublicationResponse.class);

        assertNotNull(response);
    }

    @Test
    public void canMapPublicationToPublicationResponse() throws Exception {
        Publication publication = PublicationGenerator.generateJournalArticlePublication();

        PublicationResponse response = PublicationMapper
            .convertValue(publication, PublicationResponse.class);

        assertNotNull(response);
    }

    @Test
    public void convertValueReturnsCreatePublicationRequestWhenInputIsValidPublication() throws Exception {
        Publication publication = PublicationGenerator.generateJournalArticlePublication();

        CreatePublicationRequest request = PublicationMapper
            .convertValue(publication, CreatePublicationRequest.class);

        assertNotNull(request);
        assertNotNull(request.getContext());
        assertEquals(publication.getFileSet(), request.getFileSet());
        assertEquals(publication.getProject(), request.getProject());
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
        assertEquals(publication.getProject(), request.getProject());
        assertEquals(publication.getEntityDescription(), request.getEntityDescription());
    }

    @Test
    public void createPublicationCreatesDoiRequestOnDoiRequested() {
        CreatePublicationRequest request = new CreatePublicationRequest();
        request.setDoiRequest(true);

        Publication publication = PublicationMapper
            .toNewPublication(request, SOME_OWNER, SOME_URI, SOME_URI, getOrganization());

        assertNotNull(publication.getDoiRequest());
        assertNotNull(publication.getDoiRequest().getDate());
        assertEquals(publication.getDoiRequest().getStatus(), DoiRequestStatus.REQUESTED);
    }

    @Test
    public void updatePublicationCreatesDoiRequestOnDoiRequested() {
        CreatePublicationRequest request = new CreatePublicationRequest();
        request.setDoiRequest(true);

        Publication publication = PublicationMapper
            .toExistingPublication(request, new Publication());

        assertNotNull(publication.getDoiRequest());
        assertNotNull(publication.getDoiRequest().getDate());
        assertEquals(publication.getDoiRequest().getStatus(), DoiRequestStatus.REQUESTED);
    }

    @Test
    public void createPublicationCreatesDoiRequestOnDoiNotRequested() {
        CreatePublicationRequest request = new CreatePublicationRequest();
        request.setDoiRequest(false);

        Publication publication = PublicationMapper
            .toNewPublication(request, SOME_OWNER, SOME_URI, SOME_URI, getOrganization());

        assertNull(publication.getDoiRequest());
    }

    @Test
    public void updatePublicationCreatesDoiRequestOnDoiNotRequested() {
        CreatePublicationRequest request = new CreatePublicationRequest();
        request.setDoiRequest(false);

        Publication publication = PublicationMapper
            .toExistingPublication(request, new Publication());

        assertNull(publication.getDoiRequest());
    }
}

