package no.unit.nva.model.associatedartifacts.file;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.Instant;
import java.util.UUID;

@SuppressWarnings("PMD.ExcessiveParameterList")
@JsonTypeInfo(use = Id.NAME, property = "type")
@JsonTypeName(PublishedFile.TYPE)
public class PublishedFile extends File {
    
    public static final String TYPE = "PublishedFile";
    public static final String PUBLISHED_BY = "publishedBy";
    public static final String PUBLISHED_DATE = "publishedDate";

    /**
     * Constructor for no.unit.nva.file.model.File objects. A file object is valid if it has a license or is explicitly
     * marked as an administrative agreement.
     *
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
    @JsonProperty(PUBLISHED_BY)
    private final String publishedBy;
    @JsonProperty(PUBLISHED_DATE)
    private final Instant publishedDate;

    @JsonCreator
    public PublishedFile(
        @JsonProperty(IDENTIFIER_FIELD) UUID identifier,
        @JsonProperty(NAME_FIELD) String name,
        @JsonProperty(MIME_TYPE_FIELD) String mimeType,
        @JsonProperty(SIZE_FIELD) Long size,
        @JsonProperty(LICENSE_FIELD) License license,
        @JsonProperty(ADMINISTRATIVE_AGREEMENT_FIELD) boolean administrativeAgreement,
        @JsonProperty(PUBLISHER_AUTHORITY_FIELD) boolean publisherAuthority,
        @JsonProperty(EMBARGO_DATE_FIELD) Instant embargoDate,
        @JsonProperty(PUBLISHED_BY) String publishedBy,
        @JsonProperty(PUBLISHED_DATE) Instant publishedDate) {
        super(identifier, name, mimeType, size, license, administrativeAgreement, publisherAuthority, embargoDate);
        this.publishedBy = publishedBy;
        this.publishedDate = publishedDate;
        if (administrativeAgreement) {
            throw new IllegalStateException("An administrative agreement is not publishable");
        }
    }
    
    @Override
    public boolean isVisibleForNonOwner() {
        return !isAdministrativeAgreement() && fileDoesNotHaveActiveEmbargo();
    }
    
    
    @Override
    public PublishedFile toPublishedFile() {
        return this;
    }

    public Instant getPublishedDate() {
        return publishedDate;
    }

    public String getPublishedBy() {
        return publishedBy;
    }
}
