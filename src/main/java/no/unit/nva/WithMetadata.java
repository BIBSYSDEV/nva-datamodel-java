package no.unit.nva;

import no.unit.nva.model.EntityDescription;
import no.unit.nva.model.ResearchProject;

import java.util.List;

public interface WithMetadata extends PublicationBase {

    EntityDescription getEntityDescription();

    void setEntityDescription(EntityDescription entityDescription);

    List<ResearchProject> getProjects();

    void setProjects(List<ResearchProject> projects);
}
