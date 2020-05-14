package no.unit.nva.interfaces;

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
import java.io.IOException;
import java.net.URI;
import java.util.UUID;
import no.unit.nva.model.Publication;
import no.unit.nva.model.exceptions.InvalidIssnException;
import no.unit.nva.model.exceptions.MalformedContributorException;
import org.junit.jupiter.api.Test;

public class PublicationMapperTest {

    public static final URI SOME_URI = URI.create("http://example.org");
    public static final String SOME_OWNER = "owner";
    public static final ObjectNode SOME_CONTEXT = objectMapper.createObjectNode();

    @Test
    public void canMapCreatePublicationRequestToNewPublication()
        throws InvalidIssnException, MalformedContributorException {

        CreatePublicationRequest request = new CreatePublicationRequest();
        request.setIdentifier(UUID.randomUUID());
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
    public void canMapUpdatePublicationRequestToExistingPublication()
        throws InvalidIssnException, MalformedContributorException {

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
    public void canMapPublicationAndContextToPublicationResponse()
        throws InvalidIssnException, MalformedContributorException {
        Publication publication = getPublication();

        PublicationResponse response = PublicationMapper
            .toPublicationResponse(publication, SOME_CONTEXT);

        assertNotNull(response);
    }

    @Test
    public void canMapPublicationToPublicationResponse()
        throws InvalidIssnException, MalformedContributorException, IOException {
        Publication publication = getPublication();

        PublicationResponse response = PublicationMapper
            .toPublicationResponse(publication);

        assertNotNull(response);
    }

}
