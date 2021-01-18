package no.unit.nva;

import java.util.List;
import no.unit.nva.model.EntityDescription;
import no.unit.nva.model.ResearchProject;

public interface WithMetadata extends PublicationBase {

    EntityDescription getEntityDescription();

    void setEntityDescription(EntityDescription entityDescription);

    List<ResearchProject> getProjects();

    void setProjects(List<ResearchProject> projects);
}
