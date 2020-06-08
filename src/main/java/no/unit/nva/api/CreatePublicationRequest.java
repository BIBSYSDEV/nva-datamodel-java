package no.unit.nva.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.Objects;
import no.unit.nva.WithContext;
import no.unit.nva.WithFile;
import no.unit.nva.WithMetadata;
import no.unit.nva.model.EntityDescription;
import no.unit.nva.model.FileSet;
import no.unit.nva.model.ResearchProject;
import no.unit.nva.WithFlags;
import nva.commons.utils.JacocoGenerated;

public class CreatePublicationRequest implements WithMetadata, WithFile, WithContext, WithFlags {

    private EntityDescription entityDescription;
    private ResearchProject project;
    private FileSet fileSet;
    @JsonProperty("@context")
    private JsonNode context;
    private Boolean doiRequested;

    @Override
    public EntityDescription getEntityDescription() {
        return  entityDescription;
    }

    @Override
    public void setEntityDescription(EntityDescription entityDescription) {
        this.entityDescription = entityDescription;
    }

    @Override
    public ResearchProject getProject() {
        return project;
    }

    @Override
    public void setProject(ResearchProject project) {
        this.project = project;
    }

    @Override
    public FileSet getFileSet() {
        return fileSet;
    }

    @Override
    public void setFileSet(FileSet fileSet) {
        this.fileSet = fileSet;
    }

    @Override
    public JsonNode getContext() {
        return context;
    }

    @Override
    public void setContext(JsonNode context) {
        this.context = context;
    }

    @Override
    public Boolean getDoiRequested() {
        return doiRequested;
    }

    @Override
    @JsonIgnore
    public void setDoiRequest(Boolean doiRequested) {
        this.doiRequested = doiRequested;
    }

    @Override
    @JacocoGenerated
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreatePublicationRequest that = (CreatePublicationRequest) o;
        return Objects.equals(entityDescription, that.entityDescription)
            && Objects.equals(project, that.project)
            && Objects.equals(fileSet, that.fileSet)
            && Objects.equals(context, that.context)
            && Objects.equals(doiRequested, that.doiRequested);
    }

    @Override
    @JacocoGenerated
    public int hashCode() {
        return Objects.hash(entityDescription, project, fileSet, context);
    }
}