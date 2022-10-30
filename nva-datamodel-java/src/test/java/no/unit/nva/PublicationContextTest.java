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
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static nva.commons.core.attempt.Try.attempt;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;

class PublicationContextTest {

    public static final ObjectMapper MAPPER = JsonUtils.dtoObjectMapper;
    public static final String ENDS_WITH_S = ".*s$";

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

    /**
     * A test that will fail and cause issues due to it matching the plural s in a vain attempt
     * to stop developers from adding list types without declaring it in as a @set the context object.
     * @param type The PublicationInstance class
     */
    @ParameterizedTest
    @MethodSource("publicationInstanceProvider")
    void shouldSerializeAsRdfWithNoArrayPlurals(Class<?> type) {
        var exceptions = Set.of("pages", "series",  "movements", "partOfSeries", 
                "userAgreesToTermsAndConditions", "status", "approvalStatus");
        var publication = generatePublicationWithContext(type);
        var properties = extractAllRdfProperties(publication);
        properties.removeAll(exceptions);
        var testableList = properties.stream()
                .filter(s -> s.matches(ENDS_WITH_S)).collect(Collectors.toList());
        assertThat(testableList, is(emptyList()));
    }

    // TODO: actually filter the expected classes
    @Test
    void shouldWriteEveryNvaClassAsFragmentUri() {
        var objectResources = generateObjectClassListForEveryNvaPublicationType();
        var nvaObjects = objectResources.stream()
                .filter(PublicationContextTest::isAnNvaObjectResource)
                .filter(PublicationContextTest::isNonFragmentUri)
                .collect(Collectors.toList());
        assertThat(nvaObjects, is(emptyList()));
    }

    // TODO: test that every property and class is described in the ontology

    private static HashSet<String> generateObjectClassListForEveryNvaPublicationType() {
        var objectResources = new HashSet<String>();
        var statementsIterator = generateModelContainingEveryPublicationType().listStatements();
        statementsIterator.forEachRemaining(statement -> extractNonBlankObjectNodes(statement)
                .ifPresent(objectResources::add));

        return objectResources;
    }

    private static Optional<String> extractNonBlankObjectNodes(Statement statement) {
        var object = statement.getObject();
        return isFullyQualifiedUri(object)
                ? Optional.of(object.asResource().getURI())
                : Optional.empty();
    }

    private static boolean isFullyQualifiedUri(RDFNode object) {
        return object.isResource() && !object.asResource().isAnon();
    }

    private static Model generateModelContainingEveryPublicationType() {
        var model = ModelFactory.createDefaultModel();
        publicationInstanceProvider().collect(Collectors.toList())
                .forEach(type -> RDFDataMgr.read(model, generatePublicationWithContext(type), Lang.JSONLD11));
        return model;
    }

    private static boolean isNonFragmentUri(String item) {
        return !item.contains("#");
    }

    private static boolean isAnNvaObjectResource(String item) {
        return item.startsWith("https://nva.sikt.no/");
    }

    private static HashSet<String> extractAllRdfProperties(InputStream inputStream) {
        var statementsIterator = getPublicationStatements(inputStream);
        var properties = new HashSet<String>();
        statementsIterator.forEachRemaining(statement -> properties.add(statement.getPredicate().getLocalName()));
        return properties;
    }

    private static StmtIterator getPublicationStatements(InputStream inputStream) {
        var model = ModelFactory.createDefaultModel();
        RDFDataMgr.read(model, inputStream, Lang.JSONLD11);
        return model.listStatements();
    }

    private static ByteArrayInputStream generatePublicationWithContext(Class<?> type) {
        var publication = PublicationGenerator.randomPublication(type);
        return new ByteArrayInputStream((addContextObjectToPublication(publication)));
    }

    private static byte[] addContextObjectToPublication(Publication publication) {
        var jsonNode = (ObjectNode) attempt(() -> MAPPER.valueToTree(publication)).orElseThrow();
        jsonNode.set("@context", attempt(() -> MAPPER.readTree(publication.getJsonLdContext())).orElseThrow());
        return jsonNode.toString().getBytes();
    }
}
