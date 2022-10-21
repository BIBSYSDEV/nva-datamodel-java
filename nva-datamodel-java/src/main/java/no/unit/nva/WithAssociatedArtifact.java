package no.unit.nva;

import no.unit.nva.model.associatedartifacts.AssociatedArtifactList;
import no.unit.nva.model.associatedartifacts.InvalidAssociatedArtifactsException;

public interface WithAssociatedArtifact extends PublicationBase {

    AssociatedArtifactList getAssociatedArtifacts() throws InvalidAssociatedArtifactsException;

    void setAssociatedArtifacts(AssociatedArtifactList associatedArtifact);

}
