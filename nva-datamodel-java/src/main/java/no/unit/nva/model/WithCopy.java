package no.unit.nva.model;

import no.unit.nva.model.associatedartifacts.InvalidAssociatedArtifactsException;

public interface WithCopy<T> {

    /**
     * Returns a Builder filled in with a copy of the data of the original object.
     *
     * @return a builder instance with filled in data.
     * @throws InvalidAssociatedArtifactsException if the copy contains an invalid artifacts list.
     */
    T copy() throws InvalidAssociatedArtifactsException;
}
