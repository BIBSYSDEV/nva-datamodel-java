package no.unit.nva;

import static java.util.Objects.isNull;

import java.net.URI;
import java.time.Instant;
import java.util.Optional;
import no.unit.nva.model.Organization;

public interface WithIndex extends WithInternal {

    String DYNAMODB_KEY_DELIMITER = "#";

    /**
     * Getter to create value used in DynamoDB indexing during serialization.
     *
     * @return publisherId
     */
    default String getPublisherId() {
        Organization publisher = Optional.ofNullable(getPublisher())
            .orElseThrow(() -> new IllegalStateException("Object publisher can not be null"));
        return Optional.ofNullable(publisher.getId())
            .map(URI::toString)
            .orElseThrow(() -> new IllegalStateException("Property publisher.id can not be null"));
    }

    /**
     * Getter to create value used in DynamoDB indexing during serialization.
     *
     * @return publisherOwnerDate
     */
    default String getPublisherOwnerDate() {
        String owner = Optional.ofNullable(getOwner())
            .orElseThrow(() -> new IllegalStateException("Property owner can not be null"));
        String date = Optional.ofNullable(getModifiedDate())
            .map(Instant::toString)
            .orElseThrow(() -> new IllegalStateException("Property modifiedDate can not be null"));
        return String.join(DYNAMODB_KEY_DELIMITER, getPublisherId(), owner, date);
    }

    /**
     * Getter to create value used in DynamoDB indexing during serialization.
     *
     * @return doiRequestStatusDate
     */
    default String getDoiRequestStatusDate() {
        if (isNull(getDoiRequest())) {
            return null; // not indexed
        }
        String statusString = Optional.ofNullable(getDoiRequest().getStatus()).map(Enum::toString)
            .orElseThrow(() -> new IllegalStateException("Property doiRequest.status can not be null"));
        String dateString = Optional.ofNullable(getDoiRequest().getCreatedDate()).map(Instant::toString)
            .orElseThrow(() -> new IllegalStateException("Property doiRequest.date can not be null"));

        return String.join(DYNAMODB_KEY_DELIMITER, statusString, dateString);
    }
}
