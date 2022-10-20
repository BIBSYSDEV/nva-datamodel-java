package no.unit.nva;

import no.unit.nva.model.associatedartifacts.AssociatedArtifact;

import java.util.List;

public interface WithAssociatedArtifact extends PublicationBase {

    List<AssociatedArtifact> getAssociatedArtifacts();

    void setAssociatedArtifacts(List<AssociatedArtifact> associatedArtifact);

}
