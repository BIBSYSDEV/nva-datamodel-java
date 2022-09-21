package no.unit.nva.model.instancetypes.researchdata;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.net.URI;
import java.util.Set;

public class ReferencedBy extends UriSet {
    @JsonCreator
    public ReferencedBy(Set<URI> uris) {
        super(uris);
    }
}
