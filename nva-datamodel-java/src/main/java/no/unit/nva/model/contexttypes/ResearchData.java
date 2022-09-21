package no.unit.nva.model.contexttypes;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import nva.commons.core.JacocoGenerated;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class ResearchData implements PublicationContext {

    private static final int STATIC_VALUE_FOR_CLASS_WITH_NO_FIELDS = 1;

    @JacocoGenerated
    @Override
    public int hashCode() {
        return STATIC_VALUE_FOR_CLASS_WITH_NO_FIELDS;
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object obj) {
        return obj instanceof ResearchData;
    }
}
