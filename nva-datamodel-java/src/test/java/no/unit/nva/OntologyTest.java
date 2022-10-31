package no.unit.nva;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import no.unit.nva.commons.json.JsonUtils;
import no.unit.nva.model.Publication;
import no.unit.nva.model.testing.PublicationGenerator;
import no.unit.nva.model.testing.PublicationInstanceBuilder;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.SimpleSelector;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static nva.commons.core.attempt.Try.attempt;
import static nva.commons.core.ioutils.IoUtils.inputStreamFromResources;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsIterableContaining.hasItems;

class OntologyTest {

    public static final ObjectMapper MAPPER = JsonUtils.dtoObjectMapper;

    public static Stream<Class<?>> publicationInstanceProvider() {
        return PublicationInstanceBuilder.listPublicationInstanceTypes().stream();
    }

    @Test
    void shouldContainEveryVisibleClassOfModel() {
        List<String> ontologyClasses = new ArrayList<>(extractClassesFromOntology());
        var modelClasses = new ArrayList<>(getModelClasses()).toArray(String[]::new);
        assertThat(ontologyClasses, hasItems(modelClasses));
    }

    private Set<String> getModelClasses() {
        var inputStreams = generateAllNvaTypes();
        var selector = new SimpleSelector(null, RDF.type, (RDFNode) null);
        var v =  getModelFromJson(inputStreams);
        return v.listStatements(selector).toSet().stream()
                .map(Statement::getObject)
                .map(RDFNode::asResource)
                .map(Resource::getLocalName)
                .collect(Collectors.toSet());
    }

    private static List<InputStream> generateAllNvaTypes() {
        return publicationInstanceProvider().map(PublicationGenerator::randomPublication)
                .map(OntologyTest::serializeToJson)
                .map(OntologyTest::addContextObject)
                .map(item -> new ByteArrayInputStream(item.getBytes(StandardCharsets.UTF_8)))
                .collect(Collectors.toList());
    }

    private static String addContextObject(String string) {
        var jsonNode = (ObjectNode) attempt(() -> MAPPER.readTree(string)).orElseThrow();
        var context = attempt(() -> MAPPER.readTree(inputStreamFromResources("publicationContext.json"))).orElseThrow();
        jsonNode.set("@context", context);
        return attempt(() -> MAPPER.writeValueAsString(jsonNode)).orElseThrow();
    }

    private Model getModelFromJson(List<InputStream> inputStreams) {
        var model = ModelFactory.createDefaultModel();
        inputStreams.forEach(item -> RDFDataMgr.read(model, item, Lang.JSONLD11));
        return model;
    }

    private static String serializeToJson(Publication object) {
        return attempt(() -> MAPPER.writeValueAsString(object)).orElseThrow();
    }

    private Set<String> extractClassesFromOntology() {
        var model = createModelOfFile(ontology());
        var selector = new SimpleSelector(null, RDF.type, RDFS.Class);
        return model.listStatements(selector).toSet().stream()
                .map(Statement::getSubject)
                .map(Resource::getLocalName)
                .collect(Collectors.toSet());
    }

    private Model createModelOfFile(InputStream inputStream) {
        var model = ModelFactory.createDefaultModel();
        RDFDataMgr.read(model, inputStream, Lang.TURTLE);
        return model;
    }

    private InputStream ontology() {
        return inputStreamFromResources("publication-ontology.ttl");
    }
}
