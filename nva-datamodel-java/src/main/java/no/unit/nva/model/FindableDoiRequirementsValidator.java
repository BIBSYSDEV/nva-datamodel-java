package no.unit.nva.model;

import static java.util.Objects.nonNull;
import static no.unit.nva.model.PublicationStatus.PUBLISHED;
import static no.unit.nva.model.PublicationStatus.PUBLISHED_METADATA;
import java.util.Optional;
import java.util.Set;
import no.unit.nva.model.instancetypes.PublicationInstance;
import nva.commons.core.JacocoGenerated;
import nva.commons.core.StringUtils;

final class FindableDoiRequirementsValidator {

    private static final Set<PublicationStatus> VALID_PUBLICATION_STATUS_FOR_FINDABLE_DOI =
        Set.of(PUBLISHED, PUBLISHED_METADATA);

    @JacocoGenerated
    private FindableDoiRequirementsValidator() {

    }

    static boolean meetsFindableDoiRequirements(Publication publication) {
        return hasCorrectPublishedStatus(publication) && mandatoryFieldsAreNotNull(publication);
    }

    private static boolean hasCorrectPublishedStatus(Publication publication) {
        return VALID_PUBLICATION_STATUS_FOR_FINDABLE_DOI.contains(publication.getStatus());
    }

    private static boolean mandatoryFieldsAreNotNull(Publication publication) {
        return nonNull(publication.getIdentifier())
               && nonNull(publication.getPublisher())
               && nonNull(publication.getPublisher().getId())
               && nonNull(publication.getModifiedDate())
               && hasAMainTitle(publication)
               && hasAnInstanceType(publication)
               && hasPublicationYear(publication);
    }


    private static boolean hasPublicationYear(Publication publication) {
        return Optional.ofNullable(publication.getEntityDescription())
                   .map(EntityDescription::getDate)
                   .map(PublicationDate::getYear)
                   .isPresent();
    }

    private static boolean hasAnInstanceType(Publication publication) {
        return Optional.ofNullable(publication.getEntityDescription())
                   .map(EntityDescription::getReference)
                   .map(Reference::getPublicationInstance)
                   .map(PublicationInstance::getInstanceType)
                   .isPresent();
    }

    private static boolean hasAMainTitle(Publication publication) {
        return Optional.ofNullable(publication.getEntityDescription())
                   .map(EntityDescription::getMainTitle)
                   .map(StringUtils::isNotEmpty)
                   .orElse(false);
    }
}
