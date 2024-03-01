package no.unit.nva.model.associatedartifacts.file;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import java.net.URI;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import no.unit.nva.commons.json.JsonSerializable;
import no.unit.nva.model.associatedartifacts.AssociatedArtifact;
import no.unit.nva.model.associatedartifacts.CustomerRightsRetentionStrategy;
import no.unit.nva.model.associatedartifacts.NullRightsRetentionStrategy;
import no.unit.nva.model.associatedartifacts.RightsRetentionStrategy;
import no.unit.nva.model.associatedartifacts.RightsRetentionStrategyConfiguration;
import nva.commons.core.JacocoGenerated;

/**
 * An object that represents the description of a file.
 */
@SuppressWarnings("PMD.ExcessiveParameterList")
// TODO: Remove File annotation once all data has been migrated
@JsonTypeInfo(use = Id.NAME, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(name = PublishedFile.TYPE, value = PublishedFile.class),
    @JsonSubTypes.Type(names = {UnpublishedFile.TYPE, "File"}, value = UnpublishedFile.class),
    @JsonSubTypes.Type(name = AdministrativeAgreement.TYPE, value = AdministrativeAgreement.class)
})
public abstract class File implements JsonSerializable, AssociatedArtifact {

    public static final String IDENTIFIER_FIELD = "identifier";
    public static final String NAME_FIELD = "name";
    public static final String MIME_TYPE_FIELD = "mimeType";
    public static final String SIZE_FIELD = "size";
    public static final String LICENSE_FIELD = "license";
    public static final String ADMINISTRATIVE_AGREEMENT_FIELD = "administrativeAgreement";
    public static final String PUBLISHER_AUTHORITY_FIELD = "publisherAuthority";
    public static final String EMBARGO_DATE_FIELD = "embargoDate";
    public static final String RIGTHTS_RETENTION_STRATEGY = "rightsRetentionStrategy";
    public static final Map<String, URI> LICENSE_MAP = Map.of(
        "CC BY", URI.create("https://creativecommons.org/licenses/by/4.0"),
        "CC BY-NC", URI.create("https://creativecommons.org/licenses/by-nc/4.0"),
        "CC BY-NC-ND", URI.create("https://creativecommons.org/licenses/by-nc-nd/4.0"),
        "CC BY-NC-SA", URI.create("https://creativecommons.org/licenses/by-nc-sa/4.0"),
        "CC BY-ND", URI.create("https://creativecommons.org/licenses/by-nd/4.0"),
        "CC BY-SA", URI.create("https://creativecommons.org/licenses/by-sa/4.0"),
        "CC0", URI.create("https://creativecommons.org/publicdomain/zero/1.0"),
        "RightsReserved", URI.create("http://rightsstatements.org/vocab/InC/1.0/"));

    public static final String MISSING_LICENSE =
        "The file is not annotated as an administrative agreement and should have a license";
    public static final String CCBY_LICENSE =
        "Files with the CustomerRightsRetentionStrategy must have the CC BY license if publisherAuthority is false.";
    public static final String ERROR_MESSAGE = "The specified license cannot be converted into a valid URI license: ";
    public static final String LEGAL_NOTE_FIELD = "legalNote";
    private static final Supplier<Pattern> LICENSE_VALIDATION_PATTERN =
        () -> Pattern.compile("^(http|https)://.*$");

    @JsonProperty(IDENTIFIER_FIELD)
    private final UUID identifier;
    @JsonProperty(NAME_FIELD)
    private final String name;
    @JsonProperty(MIME_TYPE_FIELD)
    private final String mimeType;
    @JsonProperty(SIZE_FIELD)
    private final Long size;
    @JsonProperty(LICENSE_FIELD)
    private final URI license;
    @JsonProperty(ADMINISTRATIVE_AGREEMENT_FIELD)
    private final boolean administrativeAgreement;
    @JsonProperty(PUBLISHER_AUTHORITY_FIELD)
    private final boolean publisherAuthority;
    @JsonProperty(EMBARGO_DATE_FIELD)
    private final Instant embargoDate;
    @JsonProperty(RIGTHTS_RETENTION_STRATEGY)
    private final RightsRetentionStrategy rightsRetentionStrategy;
    @JsonProperty(LEGAL_NOTE_FIELD)
    private final String legalNote;

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
     * @param rightsRetentionStrategy The rights retention strategy for the file
     */

    protected File(
        @JsonProperty(IDENTIFIER_FIELD) UUID identifier,
        @JsonProperty(NAME_FIELD) String name,
        @JsonProperty(MIME_TYPE_FIELD) String mimeType,
        @JsonProperty(SIZE_FIELD) Long size,
        @JsonProperty(LICENSE_FIELD) Object license,
        @JsonProperty(ADMINISTRATIVE_AGREEMENT_FIELD) boolean administrativeAgreement,
        @JsonProperty(PUBLISHER_AUTHORITY_FIELD) boolean publisherAuthority,
        @JsonProperty(EMBARGO_DATE_FIELD) Instant embargoDate,
        @JsonProperty(RIGTHTS_RETENTION_STRATEGY) RightsRetentionStrategy rightsRetentionStrategy,
        @JsonProperty(LEGAL_NOTE_FIELD) String legalNote) {

        this.identifier = identifier;
        this.name = name;
        this.mimeType = mimeType;
        this.size = size;
        this.license = validateUriLicense(parseLicense(license));
        this.administrativeAgreement = administrativeAgreement;
        this.publisherAuthority = publisherAuthority;
        this.embargoDate = embargoDate;
        this.rightsRetentionStrategy = assignDefaultStrategyIfNull(rightsRetentionStrategy);
        this.legalNote = legalNote;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Validate the file.
     */
    public void validate() {
        if (!administrativeAgreement && isNull(license)) {
            throw new MissingLicenseException(MISSING_LICENSE);
        }
        if (!publisherAuthority
            && rightsRetentionStrategy instanceof CustomerRightsRetentionStrategy
            && !license.equals(LICENSE_MAP.get("CC BY"))) {
            throw new CCByLicenseException(CCBY_LICENSE);
        }
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public String getMimeType() {
        return mimeType;
    }

    public Long getSize() {
        return size;
    }

    public String getLegalNote() {
        return legalNote;
    }

    public URI getLicense() {
        return license;
    }

    public boolean isAdministrativeAgreement() {
        return administrativeAgreement;
    }

    public boolean isPublisherAuthority() {
        return publisherAuthority;
    }

    public Optional<Instant> getEmbargoDate() {
        return Optional.ofNullable(embargoDate);
    }

    public RightsRetentionStrategy getRightsRetentionStrategy() {
        return rightsRetentionStrategy;
    }

    public boolean fileDoesNotHaveActiveEmbargo() {
        return getEmbargoDate().map(date -> Instant.now().isAfter(date)).orElse(true);
    }

    public UnpublishedFile toUnpublishedFile() {
        return new UnpublishedFile(getIdentifier(), getName(), getMimeType(), getSize(), getLicense(),
                                   isAdministrativeAgreement(), isPublisherAuthority(), getEmbargoDate().orElse(null),
                                   getRightsRetentionStrategy(), getLegalNote());
    }

    public PublishedFile toPublishedFile() {
        return new PublishedFile(getIdentifier(), getName(), getMimeType(), getSize(), getLicense(),
                                 isAdministrativeAgreement(), isPublisherAuthority(),
                                 getEmbargoDate().orElse(null),
                                 getRightsRetentionStrategy(), getLegalNote(), Instant.now());
    }

    public final AdministrativeAgreement toAdministrativeAgreement() {
        if (isAdministrativeAgreement()) {
            return new AdministrativeAgreement(getIdentifier(), getName(), getMimeType(), getSize(), getLicense(),
                                               isAdministrativeAgreement(), isPublisherAuthority(),
                                               getEmbargoDate().orElse(null), getRightsRetentionStrategy());
        }
        throw new IllegalStateException("Can not make unpublishable a non-administrative agreement");
    }

    public final AdministrativeAgreement toUnpublishableFile() {
        return new AdministrativeAgreement(getIdentifier(), getName(), getMimeType(), getSize(),
                                           getLicense(), isAdministrativeAgreement(),
                                           isPublisherAuthority(), getEmbargoDate().orElse(null),
                                           getRightsRetentionStrategy());
    }

    public abstract boolean isVisibleForNonOwner();

    @Override
    @JacocoGenerated
    public int hashCode() {
        return Objects.hash(getIdentifier(), getName(), getMimeType(), getSize(), getLicense(),
                            isAdministrativeAgreement(),
                            isPublisherAuthority(), getEmbargoDate(), getRightsRetentionStrategy());
    }

    @Override
    @JacocoGenerated
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof File file)) {
            return false;
        }
        return isAdministrativeAgreement() == file.isAdministrativeAgreement()
               && isPublisherAuthority() == file.isPublisherAuthority()
               && Objects.equals(getIdentifier(), file.getIdentifier())
               && Objects.equals(getName(), file.getName())
               && Objects.equals(getMimeType(), file.getMimeType())
               && Objects.equals(getSize(), file.getSize())
               && Objects.equals(getLicense(), file.getLicense())
               && Objects.equals(getEmbargoDate(), file.getEmbargoDate())
               && Objects.equals(getRightsRetentionStrategy(), file.getRightsRetentionStrategy());
    }

    @Override
    public String toString() {
        return toJsonString();
    }

    /**
     * Assigns a default RightsRetentionStrategy if the provided strategy is null. The default strategy is an instance
     * of NullRightsRetentionStrategy.
     *
     * @param strategy The RightsRetentionStrategy to be checked.
     * @return The provided strategy if it's not null, or a new NullRightsRetentionStrategy otherwise.
     */
    private RightsRetentionStrategy assignDefaultStrategyIfNull(RightsRetentionStrategy strategy) {
        return nonNull(strategy) ? strategy
                   : NullRightsRetentionStrategy.create(RightsRetentionStrategyConfiguration.UNKNOWN);
    }

    private URI parseLicense(Object license) {
        if (isNull(license)) {
            return null;
        }
        if (license instanceof LinkedHashMap) {
            var licenseName = getLicenseValue((LinkedHashMap<?, ?>) license);
            return LICENSE_MAP.get(licenseName);
        } else {
            return URI.create(license.toString());
        }
    }

    private String getLicenseValue(Map<?, ?> license) {
        return (String) license.get("identifier");
    }

    /**
     * Validates and formats a provided URI license.
     *
     * @param license the URI license to be validated
     * @return the formatted license if the provided license is valid and not a "RightsReserved" license, or the
     *     original license if it's valid and a "RightsReserved" license
     * @throws IllegalArgumentException if the license is null or not valid
     */
    private URI validateUriLicense(URI license) {
        if (isNull(license) || !isValidUriLicense(license) || license.equals(LICENSE_MAP.get("RightsReserved"))) {
            return license;
        }
        return formatValidUriLicense(license);
    }

    private boolean isValidUriLicense(URI license) {
        var matcher = LICENSE_VALIDATION_PATTERN.get().matcher(license.toString());
        return matcher.matches();
    }

    private URI formatValidUriLicense(URI license) {
        String formatedLicenseURL = license.toString().replaceFirst(license.getScheme(), "https")
                                        .replaceAll("/$", "")
                                        .toLowerCase(Locale.ROOT);
        return URI.create(formatedLicenseURL);
    }

    public static final class Builder {

        private UUID identifier;
        private String name;
        private String mimeType;
        private Long size;
        private URI license;
        private boolean administrativeAgreement;
        private boolean publisherAuthority;
        private Instant embargoDate;
        private RightsRetentionStrategy rightsRetentionStrategy;
        private String legalNote;

        private Builder() {
        }

        public Builder withIdentifier(UUID identifier) {
            this.identifier = identifier;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withMimeType(String mimeType) {
            this.mimeType = mimeType;
            return this;
        }

        public Builder withSize(Long size) {
            this.size = size;
            return this;
        }

        public Builder withLicense(URI license) {
            this.license = license;
            return this;
        }

        public Builder withAdministrativeAgreement(boolean administrativeAgreement) {
            this.administrativeAgreement = administrativeAgreement;
            return this;
        }

        public Builder withPublisherAuthority(boolean publisherAuthority) {
            this.publisherAuthority = publisherAuthority;
            return this;
        }

        public Builder withEmbargoDate(Instant embargoDate) {
            this.embargoDate = embargoDate;
            return this;
        }

        public Builder withRightsRetentionStrategy(RightsRetentionStrategy rightsRetentionStrategy) {
            this.rightsRetentionStrategy = rightsRetentionStrategy;
            return this;
        }

        public Builder withLegalNote(String legalNote) {
            this.legalNote = legalNote;
            return this;
        }

        public File buildPublishedFile() {
            return new PublishedFile(identifier, name, mimeType, size, license, administrativeAgreement,
                                     publisherAuthority, embargoDate, rightsRetentionStrategy,
                                     legalNote, Instant.now());
        }

        public File buildUnpublishedFile() {
            return new UnpublishedFile(identifier, name, mimeType, size, license, administrativeAgreement,
                                       publisherAuthority,
                                       embargoDate, rightsRetentionStrategy, legalNote);
        }

        public File buildUnpublishableFile() {
            return new AdministrativeAgreement(identifier, name, mimeType, size, license, administrativeAgreement,
                                               publisherAuthority,
                                               embargoDate, rightsRetentionStrategy);
        }
    }
}