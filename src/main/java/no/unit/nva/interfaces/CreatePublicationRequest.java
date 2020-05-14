package no.unit.nva.interfaces;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.UUID;
import no.unit.nva.model.EntityDescription;
import no.unit.nva.model.FileSet;
import no.unit.nva.model.ResearchProject;

public class CreatePublicationRequest implements WithIdentifier, WithMetadata, WithFile, WithContext {

    private UUID identifier;
    private EntityDescription entityDescription;
    private ResearchProject project;
    private FileSet fileSet;
    @JsonProperty("@context")
    private JsonNode context;

    @Override
    public UUID getIdentifier() {
        return identifier;
    }

    @Override
    public void setIdentifier(UUID identifier) {
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
}