package no.unit.nva;

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
        Organization publisher = Optional.ofNullable(getPublisher()).orElseThrow(
            () -> new IllegalStateException("Object publisher can not be null"));
        return Optional.ofNullable(publisher.getId()).orElseThrow(
            () -> new IllegalStateException("Property publisher.id can not be null")).toString();
    }

    /**
     * Getter to create value used in DynamoDB indexing during serialization.
     *
     * @return publisherOwnerDate
     */
    default String getPublisherOwnerDate() {
        return String.join(DYNAMODB_KEY_DELIMITER,
            getPublisherId(),
            Optional.ofNullable(getOwner()).orElseThrow(
                () -> new IllegalStateException("Property owner can not be null")),
            Optional.ofNullable(getModifiedDate()).orElseThrow(
                () -> new IllegalStateException("Property modifiedDate can not be null")).toString()
        );
    }

}
