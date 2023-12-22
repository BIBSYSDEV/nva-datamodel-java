package no.unit.nva.model.associatedartifacts;

import static no.unit.nva.model.contexttypes.ExhibitionContent.STATIC_VALUE_FOR_HASH_CODE;
import com.fasterxml.jackson.annotation.JsonCreator;
import nva.commons.core.JacocoGenerated;

public final class FunderRightsRetentionStrategy implements RightsRetentionStrategy {

    public static final String TYPE_NAME = "FunderRightsRetentionStrategy";

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
