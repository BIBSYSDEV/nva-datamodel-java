package no.unit.nva.api;

import static no.unit.nva.DatamodelConfig.dataModelObjectMapper;
import static nva.commons.core.ioutils.IoUtils.inputStreamFromResources;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;

public class PublicationContext {

    public static final String CONTEXT_PATH = "publicationContext.json";
    public static final String CONTEXT_ERROR_MESSAGE = "Error processing context: ";

    public static JsonNode getContext() {
        try {
            return dataModelObjectMapper.readTree(inputStreamFromResources(CONTEXT_PATH));
        } catch (IOException e) {
            throw new IllegalStateException(CONTEXT_ERROR_MESSAGE + CONTEXT_PATH, e);
        }
    }
}
