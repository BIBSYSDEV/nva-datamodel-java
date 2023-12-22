package no.unit.nva.model.associatedartifacts;

import static no.unit.nva.model.contexttypes.ExhibitionContent.STATIC_VALUE_FOR_HASH_CODE;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.annotation.JsonTypeName;
import nva.commons.core.JacocoGenerated;

@JsonTypeInfo(use = Id.NAME, property = "type")
@JsonTypeName(CustomerRightsRetentionStrategy.TYPE_NAME)
public final class CustomerRightsRetentionStrategy implements RightsRetentionStrategy {

    public static final String TYPE_NAME = "CustomerRightsRetentionStrategy";

    @JsonCreator
    private CustomerRightsRetentionStrategy() {
    }

    public static CustomerRightsRetentionStrategy create() {
        return new CustomerRightsRetentionStrategy();
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
        return o instanceof CustomerRightsRetentionStrategy;
    }
}
