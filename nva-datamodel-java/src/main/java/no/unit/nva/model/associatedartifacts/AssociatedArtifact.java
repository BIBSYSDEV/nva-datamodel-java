package no.unit.nva.model.associatedartifacts;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import no.unit.nva.file.model.FileType;
import no.unit.nva.file.model.License;

import java.time.Instant;
import java.util.UUID;

public interface AssociatedArtifact {

    @JsonCreator
    static AssociatedArtifact fromJson(@JsonProperty("type") FileType type,
                                       @JsonProperty("identifier") UUID identifier,
                                       @JsonProperty("name") String name,
                                       @JsonProperty("mimeType") String mimeType,
                                       @JsonProperty("size") Long size,
                                       @JsonProperty("license") License license,
                                       @JsonProperty("administrativeAgreement") boolean administrativeAgreement,
                                       @JsonProperty("publisherAuthority") boolean publisherAuthority,
                                       @JsonProperty("embargoDate") Instant embargoDate) {
        return new AssociatedFile(type, identifier, name, mimeType, size, license,
                administrativeAgreement, publisherAuthority, embargoDate);
    }
}
