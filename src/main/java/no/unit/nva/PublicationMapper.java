package no.unit.nva;

import static nva.commons.core.JsonUtils.objectMapper;
import static nva.commons.core.ioutils.IoUtils.inputStreamFromResources;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.net.URI;
import java.time.Instant;
import no.unit.nva.model.Organization;
import no.unit.nva.model.Publication;
import no.unit.nva.model.PublicationStatus;

public final class PublicationMapper {

    public static final String CONTEXT_PATH = "publicationContext.json";
    public static final String CONTEXT_ERROR_MESSAGE = "Error processing context: ";

    private PublicationMapper() {
    }

    /**
     * Maps a publication request to a publication and copies internal properties from existing publication.
     *
     * @param request   publication request
     * @param existing  existing publication to copy internal properties from
     * @param <REQUEST> request type
     * @return complete updated publication
     */
    public static <REQUEST extends PublicationBase> Publication toExistingPublication(
        REQUEST request, WithInternal existing) {
        Publication publication = toPublication(request);
        mapInternal(existing, publication);
        Instant now = Instant.now();
        publication.setModifiedDate(now);

        return publication;
    }

    /**
     * Maps a publication request to a new publication and sets internal properties for the first time.
     *
     * @param request   publication request
     * @param owner     owner
     * @param handle    handle
     * @param link      link
     * @param publisher publisher
     * @param <REQUEST> request type
     * @return complete new publication
     */
    public static <REQUEST extends PublicationBase> Publication toNewPublication(
        REQUEST request, String owner, URI handle, URI link, Organization publisher) {
        Publication publication = toPublication(request);
        Instant now = Instant.now();
        publication.setCreatedDate(now);
        publication.setModifiedDate(now);
        publication.setOwner(owner);
        publication.setHandle(handle);
        publication.setLink(link);
        publication.setPublisher(publisher);
        publication.setStatus(PublicationStatus.DRAFT);

        return publication;
    }

    /**
     * Maps a publication and context to specified type.
     *
     * @param publication publication
     * @param context     jsonld context
     * @return publication response
     */
    public static <R extends WithContext> R convertValue(
        Publication publication, JsonNode context, Class<R> responseType) {
        R response = objectMapper.convertValue(publication, responseType);
        response.setContext(context);
        return response;
    }

    /**
     * Maps a publication to specified type with default context.
     *
     * @param publication publication
     * @return publication response
     */
    public static <R extends WithContext> R convertValue(
        Publication publication, Class<R> responseType) {
        return convertValue(publication, getContext(), responseType);
    }

    private static JsonNode getContext() {
        try {
            return objectMapper.readTree(inputStreamFromResources(CONTEXT_PATH));
        } catch (IOException e) {
            throw new IllegalStateException(CONTEXT_ERROR_MESSAGE + CONTEXT_PATH, e);
        }
    }

    private static <REQUEST extends PublicationBase> Publication toPublication(REQUEST request) {
        Publication publication = new Publication();
        if (request instanceof WithIdentifier) {
            mapIdentifier((WithIdentifier) request, publication);
        }
        if (request instanceof WithMetadata) {
            mapMetadata((WithMetadata) request, publication);
        }
        if (request instanceof WithFile) {
            mapFile((WithFile) request, publication);
        }
        if (request instanceof WithInternal) {
            mapInternal((WithInternal) request, publication);
        }
        return publication;
    }

    private static void mapIdentifier(WithIdentifier request, Publication publication) {
        publication.setIdentifier(request.getIdentifier());
    }

    private static void mapMetadata(WithMetadata request, Publication publication) {
        publication.setEntityDescription(request.getEntityDescription());
        publication.setProjects(request.getProjects());
    }

    private static void mapFile(WithFile request, Publication publication) {
        publication.setFileSet(request.getFileSet());
    }

    private static void mapInternal(WithInternal request, Publication publication) {
        publication.setCreatedDate(request.getCreatedDate());
        publication.setHandle(request.getHandle());
        publication.setIndexedDate(request.getIndexedDate());
        publication.setLink(request.getLink());
        publication.setModifiedDate(request.getModifiedDate());
        publication.setOwner(request.getOwner());
        publication.setPublishedDate(request.getPublishedDate());
        publication.setPublisher(request.getPublisher());
        publication.setStatus(request.getStatus());
        publication.setDoi(request.getDoi());
        publication.setDoiRequest(request.getDoiRequest());
    }
}
