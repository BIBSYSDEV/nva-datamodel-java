package no.unit.nva.model.associatedartifacts;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.annotation.JsonTypeName;
import nva.commons.core.JacocoGenerated;

@JsonTypeInfo(use = Id.NAME, property = "type")
@JsonTypeName(NullRightsRetentionStrategy.TYPE_NAME)
public class NullRightsRetentionStrategy extends RightsRetentionStrategy {

    public static final String TYPE_NAME = "NullRightsRetentionStrategy";

    @JsonCreator
    public NullRightsRetentionStrategy(@JsonProperty(LEGAL_NOTE_FIELD) String legalNote,
                                       @JsonProperty(FOLLOWS_POLICY_FIELD) Boolean followsPolicy) {
        super(legalNote, followsPolicy);
    }

    public static NullRightsRetentionStrategy defaultRightsRetentionStrategy() {
        return new NullRightsRetentionStrategy(null, Boolean.TRUE);
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return STATIC_VALUE_FOR_HASH_CODE;
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        return o instanceof NullRightsRetentionStrategy;
    }
}
