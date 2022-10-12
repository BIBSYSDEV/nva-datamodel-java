package no.unit.nva.model.associatedartifacts;

import com.fasterxml.jackson.annotation.JsonProperty;
import no.unit.nva.file.model.File;
import no.unit.nva.file.model.FileType;
import no.unit.nva.file.model.License;

import java.time.Instant;
import java.util.UUID;

public class AssociatedFile extends File implements AssociatedArtifact {

    /**
     * Constructor for no.unit.nva.file.model.File objects. A file object is valid if it has a license or is explicitly
     * marked as an administrative agreement.
     *
     * @param type                    The type of file
     * @param identifier              A UUID that identifies the file in storage
     * @param name                    The original name of the file
     * @param mimeType                The mimetype of the file
     * @param size                    The size of the file
     * @param license                 The license for the file, may be null if and only if the file is an administrative
     *                                agreement
     * @param administrativeAgreement True if the file is an administrative agreement
     * @param publisherAuthority      True if the file owner has publisher authority
     * @param embargoDate             The date after which the file may be published
     */

    public AssociatedFile(@JsonProperty("type") FileType type,
                          @JsonProperty("identifier") UUID identifier,
                          @JsonProperty("name") String name,
                          @JsonProperty("mimeType") String mimeType,
                          @JsonProperty("size") Long size,
                          @JsonProperty("license") License license,
                          @JsonProperty("administrativeAgreement") boolean administrativeAgreement,
                          @JsonProperty("publisherAuthority") boolean publisherAuthority,
                          @JsonProperty("embargoDate") Instant embargoDate) {
        super(type, identifier, name, mimeType, size, license,
                administrativeAgreement, publisherAuthority, embargoDate);
    }
}

