package no.unit.nva.interfaces;

import com.fasterxml.jackson.databind.JsonNode;

public interface WithContext {

    JsonNode getContext();

    void setContext(JsonNode context);

}
