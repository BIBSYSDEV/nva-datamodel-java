package no.unit.nva.model.contexttypes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class NullPublisher implements PublishingHouse {
    private static final int STATIC_VALUE_FOR_HASH_CODE = 56;

    @JsonCreator
    public NullPublisher() {
    }

    @Override
    public int hashCode() {
        return STATIC_VALUE_FOR_HASH_CODE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        return o instanceof NullPublisher;
    }

    @Override
    public boolean isValid() {
        return true;
    }
}
