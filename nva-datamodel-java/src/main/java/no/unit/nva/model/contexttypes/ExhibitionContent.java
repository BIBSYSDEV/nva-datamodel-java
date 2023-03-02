package no.unit.nva.model.contexttypes;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class ExhibitionContent implements BasicContext {

    public static final int STATIC_VALUE_FOR_HASH_CODE = 103_245;

    public ExhibitionContent() {
        // NO-OP
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
        return o instanceof BasicContext;
    }
}
