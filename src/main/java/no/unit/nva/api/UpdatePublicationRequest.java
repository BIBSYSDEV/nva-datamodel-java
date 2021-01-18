package no.unit.nva.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;
import java.util.Objects;
import no.unit.nva.WithContext;
import no.unit.nva.WithFile;
import no.unit.nva.WithIdentifier;
import no.unit.nva.WithMetadata;
import no.unit.nva.identifiers.SortableIdentifier;
import no.unit.nva.model.EntityDescription;
import no.unit.nva.model.FileSet;
import no.unit.nva.model.ResearchProject;
import nva.commons.core.JacocoGenerated;

public class UpdatePublicationRequest implements WithIdentifier, WithMetadata, WithFile, WithContext {

    private SortableIdentifier identifier;
    private EntityDescription entityDescription;
    private ResearchProject project;
    private FileSet fileSet;
    @JsonProperty("@context")
    private JsonNode context;
    private List<ResearchProject> projects;

    @Override
    public SortableIdentifier getIdentifier() {
        return identifier;
    }

    @Override
    public void setIdentifier(SortableIdentifier identifier) {
        this.identifier = identifier;
    }

    @Override
    public EntityDescription getEntityDescription() {
        return  entityDescription;
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
    @JacocoGenerated
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UpdatePublicationRequest that = (UpdatePublicationRequest) o;
        return Objects.equals(identifier, that.identifier)
            && Objects.equals(entityDescription, that.entityDescription)
            && Objects.equals(project, that.project)
            && Objects.equals(fileSet, that.fileSet)
            && Objects.equals(context, that.context);
    }

    @Override
    @JacocoGenerated
    public int hashCode() {
        return Objects.hash(identifier, entityDescription, project, fileSet, context);
    }
}
