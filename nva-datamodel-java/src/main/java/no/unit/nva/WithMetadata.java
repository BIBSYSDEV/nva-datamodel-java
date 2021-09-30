package no.unit.nva;

import java.net.URI;
import java.util.List;
import no.unit.nva.model.EntityDescription;
import no.unit.nva.model.ResearchProject;

public interface WithMetadata extends PublicationBase {

    EntityDescription getEntityDescription();

    void setEntityDescription(EntityDescription entityDescription);

    List<ResearchProject> getProjects();

    void setProjects(List<ResearchProject> projects);

    List<URI> getSubjects();

    void setSubjects(List<URI> subjects);
}