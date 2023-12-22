package no.unit.nva.model.associatedartifacts;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.annotation.JsonTypeName;
import nva.commons.core.JacocoGenerated;

@JsonTypeInfo(use = Id.NAME, property = "type")
@JsonTypeName(CustomerRightsRetentionStrategy.TYPE_NAME)
public final class CustomerRightsRetentionStrategy implements RightsRetentionStrategy {

    public static final String TYPE_NAME = "CustomerRightsRetentionStrategy";
    public static final int STATIC_VALUE_FOR_HASH_CODE = 103_245;

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
