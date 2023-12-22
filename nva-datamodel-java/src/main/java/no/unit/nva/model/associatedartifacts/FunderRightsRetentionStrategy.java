package no.unit.nva.model.associatedartifacts;

import com.fasterxml.jackson.annotation.JsonCreator;
import nva.commons.core.JacocoGenerated;

public final class FunderRightsRetentionStrategy implements RightsRetentionStrategy {

    public static final String TYPE_NAME = "FunderRightsRetentionStrategy";
    public static final int STATIC_VALUE_FOR_HASH_CODE = 103_245;

    @JsonCreator
    private FunderRightsRetentionStrategy() {
    }

    public static FunderRightsRetentionStrategy create() {
        return new FunderRightsRetentionStrategy();
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
        return o instanceof FunderRightsRetentionStrategy;
    }
}
