package no.unit.nva.model.associatedartifacts;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.annotation.JsonTypeName;
import nva.commons.core.JacocoGenerated;

@JsonTypeInfo(use = Id.NAME, property = "type")
@JsonTypeName(OverriddenRightsRetentionStrategy.TYPE_NAME)
public final class OverriddenRightsRetentionStrategy implements RightsRetentionStrategy {

    public static final String TYPE_NAME = "OverriddenRightsRetentionStrategy";
    private static final int STATIC_VALUE_FOR_HASH_CODE = 103_245;

    @JsonCreator
    private OverriddenRightsRetentionStrategy() {
    }

    public static OverriddenRightsRetentionStrategy create() {
        return new OverriddenRightsRetentionStrategy();
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
        return o instanceof OverriddenRightsRetentionStrategy;
    }
}
