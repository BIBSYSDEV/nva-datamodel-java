package no.unit.nva;

import static no.unit.nva.model.util.PublicationGenerator.getEntityDescription;
import static no.unit.nva.model.util.PublicationGenerator.getFileSet;
import static no.unit.nva.model.util.PublicationGenerator.getOrganization;
import static no.unit.nva.model.util.PublicationGenerator.getProject;
import static no.unit.nva.model.util.PublicationGenerator.getPublication;
import static nva.commons.utils.JsonUtils.objectMapper;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fasterxml.jackson.databind.node.ObjectNode;
import java.net.URI;
import java.util.UUID;
import no.unit.nva.api.CreatePublicationRequest;
import no.unit.nva.api.PublicationResponse;
import no.unit.nva.api.UpdatePublicationRequest;
import no.unit.nva.model.Publication;
import org.junit.jupiter.api.Test;

public class PublicationMapperTest {

    public static final URI SOME_URI = URI.create("http://example.org");
    public static final String SOME_OWNER = "owner";
    public static final ObjectNode SOME_CONTEXT = objectMapper.createObjectNode();

    @Test
    public void canMapCreatePublicationRequestToNewPublication() throws Exception {

        CreatePublicationRequest request = new CreatePublicationRequest();
        request.setEntityDescription(getEntityDescription());
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
        request.setEntityDescription(getEntityDescription());
        request.setProject(getProject());
        request.setFileSet(getFileSet(UUID.randomUUID()));
        request.setContext(SOME_CONTEXT);

        Publication publication = PublicationMapper.toExistingPublication(request, getPublication());

        assertNotNull(publication);
        assertNotEquals(publication.getCreatedDate(), publication.getModifiedDate());
    }

    @Test
    public void canMapPublicationAndContextToPublicationResponse() throws Exception {
        Publication publication = getPublication();

        PublicationResponse response = PublicationMapper
            .toResponse(publication, SOME_CONTEXT, PublicationResponse.class);

        assertNotNull(response);
    }

    @Test
    public void canMapPublicationToPublicationResponse() throws Exception {
        Publication publication = getPublication();

        PublicationResponse response = PublicationMapper
            .toResponse(publication, PublicationResponse.class);

        assertNotNull(response);
    }

}
