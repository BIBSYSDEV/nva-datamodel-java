package no.unit.nva.model;

import static no.unit.nva.DatamodelConfig.dataModelObjectMapper;
import static no.unit.nva.hamcrest.DoesNotHaveEmptyValues.doesNotHaveEmptyValuesIgnoringFields;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.sameInstance;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.bibsysdev.ResourcesBuildConfig;
import com.github.jsonldjava.core.JsonLdOptions;
import com.github.jsonldjava.core.JsonLdProcessor;
import com.github.jsonldjava.utils.JsonUtils;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;
import no.unit.nva.model.testing.PublicationGenerator;
import no.unit.nva.model.util.ContextUtil;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.junit.jupiter.api.Test;

class PublicationTest {

    public static final String PUBLICATION_CONTEXT_JSON = "src/main/resources/publicationContext.json";
    public static final String PUBLICATION_FRAME_JSON = "src/main/resources/publicationFrame.json";
    public static final Javers JAVERS = JaversBuilder.javers().build();
    public static final String DOI_REQUEST_FIELD = "doiRequest";

    
    @Test
    void getModelVersionReturnsModelVersionDefinedByGradle() {
        Publication samplePublication = PublicationGenerator.randomPublication();
        assertThat(samplePublication.getModelVersion(), is(equalTo(ResourcesBuildConfig.RESOURCES_MODEL_VERSION)));
    }

    @Test
    void equalsReturnsTrueWhenTwoPublicationInstancesHaveEquivalentFields() {
        Publication samplePublication = PublicationGenerator.randomPublication();
        Publication copy = samplePublication.copy().build();

        assertThat(copy, doesNotHaveEmptyValuesIgnoringFields(Set.of(DOI_REQUEST_FIELD)));

        Diff diff = JAVERS.compare(samplePublication, copy);
        assertThat(copy, is(not(sameInstance(samplePublication))));
        assertThat(diff.prettyPrint(),copy, is(equalTo(samplePublication)));
    }

    @Test
    void objectMapperReturnsSerializationWithAllFieldsSerialized()
        throws JsonProcessingException {
        Publication samplePublication = PublicationGenerator.randomPublication();
        String jsonString = dataModelObjectMapper.writeValueAsString(samplePublication);
        Publication copy = dataModelObjectMapper.readValue(jsonString, Publication.class);
        assertThat(copy, is(equalTo(samplePublication)));
    }

    protected JsonNode toPublicationWithContext(Publication publication) throws IOException {
        JsonNode document = dataModelObjectMapper.readTree(dataModelObjectMapper.writeValueAsString(publication));
        JsonNode context = dataModelObjectMapper.readTree(new FileInputStream(PUBLICATION_CONTEXT_JSON));
        ContextUtil.injectContext(document, context);
        return document;
    }

    protected Object produceFramedPublication(JsonNode publicationWithContext) throws IOException {
        Object input = JsonUtils.fromString(dataModelObjectMapper.writeValueAsString(publicationWithContext));
        Object frame = JsonUtils.fromInputStream(new FileInputStream(PUBLICATION_FRAME_JSON));
        JsonLdOptions options = new JsonLdOptions();
        options.setOmitGraph(true);
        options.setPruneBlankNodeIdentifiers(true);
        return JsonLdProcessor.frame(input, frame, options);
    }
}
