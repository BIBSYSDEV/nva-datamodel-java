package no.unit.nva.model.associatedartifacts;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.Objects;
import nva.commons.core.JacocoGenerated;

/**
 * The RightsRetentionStrategy interface represents the strategy for rights retention. It makes use of Jackson
 * annotations to handle JSON serialization and deserialization into the correct implementing types:
 * CustomerRightsRetentionStrategy, OverriddenRightsRetentionStrategy, and NullRightsRetentionStrategy.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
    @Type(name = CustomerRightsRetentionStrategy.TYPE_NAME, value = CustomerRightsRetentionStrategy.class),
    @Type(name = OverriddenRightsRetentionStrategy.TYPE_NAME, value =
                                                                               OverriddenRightsRetentionStrategy.class),
    @Type(name = NullRightsRetentionStrategy.TYPE_NAME, value = NullRightsRetentionStrategy.class),})
public class RightsRetentionStrategy {

    public static final int STATIC_VALUE_FOR_HASH_CODE = 221_391;
    public static final String LEGAL_NOTE_FIELD = "legalNote";
    public static final String FOLLOWS_POLICY_FIELD = "followsPolicy";
    private final String legalNote;
    private final Boolean followsPolicy;

    @JsonCreator
    public RightsRetentionStrategy(@JsonProperty(LEGAL_NOTE_FIELD) String legalNote,
                                   @JsonProperty(FOLLOWS_POLICY_FIELD) Boolean followsPolicy) {
        this.legalNote = legalNote;
        this.followsPolicy = followsPolicy;
    }

    public Boolean getFollowsPolicy() {
        return followsPolicy;
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getLegalNote(), getFollowsPolicy());
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RightsRetentionStrategy that = (RightsRetentionStrategy) o;
        return Objects.equals(getLegalNote(), that.getLegalNote()) && Objects.equals(getFollowsPolicy(),
                                                                                     that.getFollowsPolicy());
    }

    public String getLegalNote() {
        return legalNote;
    }
}
