package no.unit.nva;

import no.unit.nva.model.EntityDescription;
import no.unit.nva.model.ResearchProject;

public interface WithMetadata extends PublicationBase {

    EntityDescription getEntityDescription();

    void setEntityDescription(EntityDescription entityDescription);

    @Deprecated
    ResearchProject getProject();

    @Deprecated
    void setProject(ResearchProject project);

}
