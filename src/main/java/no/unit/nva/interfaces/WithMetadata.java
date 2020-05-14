package no.unit.nva.interfaces;

import no.unit.nva.model.EntityDescription;
import no.unit.nva.model.ResearchProject;

public interface WithMetadata {

    EntityDescription getEntityDescription();

    void setEntityDescription(EntityDescription entityDescription);

    ResearchProject getProject();

    void setProject(ResearchProject project);

}
