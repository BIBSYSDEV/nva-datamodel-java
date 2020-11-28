package no.unit.nva.api.externalmodel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import no.unit.nva.api.externalmodel.mapping.PublicationDtoToPublicationMapperUtil;
import no.unit.nva.model.DoiRequest;
import no.unit.nva.model.FileSet;
import no.unit.nva.model.Publication;
import no.unit.nva.model.PublicationStatus;
import no.unit.nva.model.ResearchProject;
import nva.commons.utils.JacocoGenerated;

import java.net.URI;
import java.time.Instant;
import java.util.UUID;

@SuppressWarnings("PMD.ExcessiveParameterList")
public class PublicationDto {

    private final String type;
    private final Owner owner;
    private final Instant createdDate;
    private final Instant modifiedDate;
    private final Metadata metadata;
    private final UUID identifier;
    private final PublicationStatus status;
    private final DoiRequest doiRequest;
    private final ResearchProject project;
    private final FileSet files;
    private final URI doi;
    private final URI handle;
    private final Instant indexedDate;
    private final URI link;
    private final Instant publishedDate;

    @JsonCreator
    protected PublicationDto(
            @JsonProperty("createdDate") Instant createdDate,
            @JsonProperty("doi") URI doi,
            @JsonProperty("doiRequest") DoiRequest doiRequest,
            @JsonProperty("files") FileSet files,
            @JsonProperty("handle") URI handle,
            @JsonProperty("identifier") UUID identifier,
            @JsonProperty("indexedDate") Instant indexedDate,
            @JsonProperty("link") URI link,
            @JsonProperty("metadata") Metadata metadata,
            @JsonProperty("modifiedDate") Instant modifiedDate,
            @JsonProperty("owner") Owner owner,
            @JsonProperty("project") ResearchProject project,
            @JsonProperty("publishedDate") Instant publishedDate,
            @JsonProperty("status") PublicationStatus status,
            @JsonProperty("type") String type) {
        this.createdDate = createdDate;
        this.doi = doi;
        this.doiRequest = doiRequest;
        this.files = files;
        this.handle = handle;
        this.identifier = identifier;
        this.indexedDate = indexedDate;
        this.link = link;
        this.metadata = metadata;
        this.modifiedDate = modifiedDate;
        this.owner = owner;
        this.project = project;
        this.publishedDate = publishedDate;
        this.status = status;
        this.type = type;
    }

    protected PublicationDto(Publication publication) {
        this(PublicationToPublicationDtoMapperUtil.toPublicationDto(publication));
    }

    protected PublicationDto(PublicationDto publicationDto) {
        this.type = publicationDto.getType();
        this.owner = publicationDto.getOwner();
        this.createdDate = publicationDto.getCreatedDate();
        this.modifiedDate = publicationDto.getModifiedDate();
        this.metadata = publicationDto.getMetadata();
        this.identifier = publicationDto.getIdentifier();
        this.status = publicationDto.getStatus();
        this.doiRequest = publicationDto.getDoiRequest();
        this.project = publicationDto.getProject();
        this.files = publicationDto.getFiles();
        this.doi = publicationDto.getDoi();
        this.handle = publicationDto.getHandle();
        this.indexedDate = publicationDto.getIndexedDate();
        this.link = publicationDto.getLink();
        this.publishedDate = publicationDto.getPublishedDate();
    }

    public Publication toPublication() throws Exception {
        return PublicationDtoToPublicationMapperUtil.toPublication(this);
    }

    public String getType() {
        return type;
    }

    public UUID getIdentifier() {
        return identifier;
    }

    @JacocoGenerated
    public Owner getOwner() {
        return owner;
    }

    public PublicationStatus getStatus() {
        return status;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public Instant getModifiedDate() {
        return modifiedDate;
    }

    public DoiRequest getDoiRequest() {
        return doiRequest;
    }

    public ResearchProject getProject() {
        return project;
    }

    public FileSet getFiles() {
        return files;
    }

    @JacocoGenerated
    public URI getDoi() {
        return doi;
    }

    public URI getHandle() {
        return handle;
    }

    public Instant getIndexedDate() {
        return indexedDate;
    }

    public URI getLink() {
        return link;
    }

    @JacocoGenerated
    public Instant getPublishedDate() {
        return publishedDate;
    }

    @JacocoGenerated
    public Metadata getMetadata() {
        return metadata;
    }
}
