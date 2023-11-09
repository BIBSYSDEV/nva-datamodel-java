package no.unit.nva.model.associatedartifacts;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * The RightsRetentionStrategy interface represents the strategy for rights retention.
 * It makes use of Jackson annotations to handle JSON serialization and deserialization into
 * the correct implementing types: CustomerRightsRetentionStrategy, OverriddenRightsRetentionStrategy,
 * and NullRightsRetentionStrategy.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(name = CustomerRightsRetentionStrategy.TYPE_NAME, value = CustomerRightsRetentionStrategy.class),
    @JsonSubTypes.Type(
        name = OverriddenRightsRetentionStrategy.TYPE_NAME,
        value = OverriddenRightsRetentionStrategy.class
    ),
    @JsonSubTypes.Type(name = NullRightsRetentionStrategy.TYPE_NAME, value = NullRightsRetentionStrategy.class),
})
public interface RightsRetentionStrategy {
    // Define any common methods here, if needed
}
