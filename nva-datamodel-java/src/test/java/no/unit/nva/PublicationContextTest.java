package no.unit.nva;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import no.unit.nva.commons.json.JsonUtils;
import no.unit.nva.model.Publication;
import no.unit.nva.model.testing.PublicationGenerator;
import no.unit.nva.model.testing.PublicationInstanceBuilder;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static nva.commons.core.attempt.Try.attempt;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;

class PublicationContextTest {

    public static final ObjectMapper MAPPER = JsonUtils.dtoObjectMapper;

    public static Stream<Class<?>> publicationInstanceProvider() {
        return PublicationInstanceBuilder.listPublicationInstanceTypes().stream();
    }

    @Test
    void shouldReturnContextObjectWhenRequested() {
        assertThat(new Publication().getJsonLdContext(), is(not(nullValue())));
    }

    @Test
    void shouldNotSerializeTheJsonLdContextObjectFromTheClass() {
        var serialized = attempt(() -> MAPPER.writeValueAsString(new Publication())).orElseThrow();
        assertThat(serialized, not(containsString("jsonLdContext")));
    }

    // TODO: actually filter the expected classes from ontology
    @Test
    void shouldWriteEveryNvaClassAsFragmentUri() {
        var objectResources = generateListOfDistinctObjectClassesForEveryNvaPublicationType();
        var nvaObjects = objectResources.stream()
                .filter(PublicationContextTest::isAnNvaObjectResource)
                .filter(PublicationContextTest::isNonFragmentUri)
                .collect(Collectors.toList());
        assertThat(nvaObjects, is(emptyList()));
    }

    @Test
    void shouldDefineEveryArrayPropertyAsContainerSet() {
        var allNvaCollectionProperties = generateSetOfPropertiesThatHaveCollectionTypeForEveryNvaType();
        var contextContainerSets = getAllContextContainerSetTerms();
        assertThat(contextContainerSets, is(equalTo(allNvaCollectionProperties)));
    }

    // TODO: test that every property and class is described in the ontology

    private Set<String> getAllContextContainerSetTerms() {
        var elements = getElementsFromJsonContextObject();
        return extractPropertyKeysThatSpecifyContainerSets(elements);
    }

    private static HashSet<String> extractPropertyKeysThatSpecifyContainerSets(
            Iterator<Map.Entry<String, JsonNode>> elements) {
        var properties = new HashSet<String>();
        while (elements.hasNext()) {
            var current = elements.next();
            if (isContainerSetSpecification(current)) {
                properties.add(current.getKey());
            }
        }
        return properties;
    }

    private static Iterator<Map.Entry<String, JsonNode>> getElementsFromJsonContextObject() {
        var contextFromPublication = new Publication().getJsonLdContext();
        var jsonLdContext = stringToTree(contextFromPublication);
        return jsonLdContext.fields();
    }

    private static boolean isContainerSetSpecification(Map.Entry<String, JsonNode> current) {
        return current.getValue().isObject()
                && current.getValue().has("@container")
                && current.getValue().get("@container").asText().equals("@set");
    }

    private Set<String> generateSetOfPropertiesThatHaveCollectionTypeForEveryNvaType() {
        return publicationInstanceProvider()
                .map(PublicationGenerator::randomPublication)
                .map(PublicationContextTest::convertToJsonNode)
                .map(PublicationContextTest::extractArrayFieldNames)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    private static Set<String> extractArrayFieldNames(JsonNode jsonNode) {
        var fields = jsonNode.fields();
        var fieldNames = new HashSet<String>();
        extractPPropertiesThaHaveArrayValue(fields, fieldNames);
        return fieldNames;
    }

    private static void extractPPropertiesThaHaveArrayValue(
            Iterator<Map.Entry<String, JsonNode>> fields, HashSet<String> fieldNames) {
        while (fields.hasNext()) {
            var current = fields.next();
            if (current.getValue().isArray()) {
                fieldNames.add(current.getKey());
                iterateArray(current.getValue().elements(), fieldNames);
            }
            if (current.getValue().isObject()) {
                extractPPropertiesThaHaveArrayValue(current.getValue().fields(), fieldNames);
            }
        }
    }

    private static void iterateArray(Iterator<JsonNode> elements, HashSet<String> fieldNames) {
        while (elements.hasNext()) {
            extractPPropertiesThaHaveArrayValue(elements.next().fields(), fieldNames);
        }
    }

    private static Set<String> generateListOfDistinctObjectClassesForEveryNvaPublicationType() {
        return generateStatementsForEveryPublicationType().stream()
                .map(Statement::getObject)
                .filter(PublicationContextTest::isFullyQualifiedUri)
                .map(RDFNode::asResource)
                .map(Resource::getURI)
                .collect(Collectors.toSet());
    }

    private static boolean isFullyQualifiedUri(RDFNode object) {
        return object.isResource() && !object.asResource().isAnon();
    }

    private static Set<Statement> generateStatementsForEveryPublicationType() {
        var publications = publicationInstanceProvider()
                .map(PublicationContextTest::generatePublicationWithContext)
                .collect(Collectors.toList());
        return getDistinctStatementsOfResources(publications);
    }

    private static Set<Statement> getDistinctStatementsOfResources(List<InputStream> inputStreams) {
        var model = ModelFactory.createDefaultModel();
        inputStreams.forEach(inputStream -> RDFDataMgr.read(model, inputStream, Lang.JSONLD11));
        return model.listStatements().toSet();
    }

    private static boolean isNonFragmentUri(String item) {
        return !item.contains("#");
    }

    private static boolean isAnNvaObjectResource(String item) {
        return item.startsWith("https://nva.sikt.no/");
    }

    private static InputStream generatePublicationWithContext(Class<?> type) {
        var publication = PublicationGenerator.randomPublication(type);
        return new ByteArrayInputStream((addContextObjectToPublication(publication)));
    }

    private static byte[] addContextObjectToPublication(Publication publication) {
        var jsonNode = (ObjectNode) convertToJsonNode(publication);
        jsonNode.set("@context", stringToTree(publication.getJsonLdContext()));
        return jsonNode.toString().getBytes();
    }

    private static JsonNode stringToTree(String string) {
        return attempt(() -> MAPPER.readTree(string)).orElseThrow();
    }

    private static JsonNode convertToJsonNode(Publication instance) {
        return (JsonNode) attempt(() -> MAPPER.valueToTree(instance)).orElseThrow();
    }
}
