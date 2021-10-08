package no.unit.nva.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import java.net.URI;
import java.util.List;
import java.util.Objects;
import no.unit.nva.WithContext;
import no.unit.nva.WithFile;
import no.unit.nva.WithMetadata;
import no.unit.nva.model.EntityDescription;
import no.unit.nva.model.FileSet;
import no.unit.nva.model.Reference;
import no.unit.nva.model.ResearchProject;
import nva.commons.core.JacocoGenerated;

public class CreatePublicationRequest implements WithMetadata, WithFile, WithContext {

    private EntityDescription entityDescription;
    private FileSet fileSet;
    @JsonProperty("@context")
    private JsonNode context;
    private List<ResearchProject> projects;
    private List<URI> subjects;

    @Override
    public EntityDescription getEntityDescription() {
        return entityDescription;
    }

    @Override
    public void setEntityDescription(EntityDescription entityDescription) {
        this.entityDescription = entityDescription;
    }

    @Override
    public List<ResearchProject> getProjects() {
        return projects;
    }

    @Override
    public void setProjects(List<ResearchProject> projects) {
        this.projects = projects;
    }

    @Override
    public List<URI> getSubjects() {
        return subjects;
    }

    @Override
    public void setSubjects(List<URI> subjects) {
        this.subjects = subjects;
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

    public void addReferenceDoi(URI doi) {
        if (getEntityDescription() == null) {
            setEntityDescription(new EntityDescription());
        }
        if (getEntityDescription().getReference() == null) {
            getEntityDescription().setReference(new Reference());
        }
        getEntityDescription().getReference().setDoi(doi);
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getEntityDescription(), getFileSet(), getContext(), getProjects(),
                            getSubjects());
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CreatePublicationRequest)) {
            return false;
        }
        CreatePublicationRequest that = (CreatePublicationRequest) o;
        return Objects.equals(getEntityDescription(), that.getEntityDescription())
               && Objects.equals(getFileSet(), that.getFileSet())
               && Objects.equals(getContext(), that.getContext())
               && Objects.equals(getProjects(), that.getProjects())
               && Objects.equals(getSubjects(), that.getSubjects());
    }
}