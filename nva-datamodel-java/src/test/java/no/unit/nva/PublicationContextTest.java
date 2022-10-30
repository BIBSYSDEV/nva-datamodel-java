package no.unit.nva;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import no.unit.nva.commons.json.JsonUtils;
import no.unit.nva.model.Publication;
import no.unit.nva.model.testing.PublicationGenerator;
import no.unit.nva.model.testing.PublicationInstanceBuilder;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static nva.commons.core.attempt.Try.attempt;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
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

    /**
     * A test that will fail and cause issues due to it matching the plural s in a vain attempt
     * to stop developers from adding list types without declaring it in as a @set the context object.
     * @param tClass The PublicationInstance class
     */
    @ParameterizedTest
    @MethodSource("publicationInstanceProvider")
    void shouldSerializeAsRdfWithNoArrayPlurals(Class<?> tClass) {
        var exceptions = Set.of("pages", "series",  "movements", "partOfSeries", "userAgreesToTermsAndConditions");
        var publication = generatePublicationWithContext(tClass);
        var properties = extractAllRdfProperties(publication);
        properties.removeAll(exceptions);
        var testableList = properties.stream().filter(s -> s.matches(".*s$")).collect(Collectors.toList());
        assertThat(testableList, is(Collections.emptyList()));
    }

    @Test
    void shouldReportEveryPropertyAndClass() {
        var properties = new HashSet<String>();
        var objectResources = new HashSet<String>();
        for(Class<?> tClass : publicationInstanceProvider().collect(Collectors.toList())) {
            var publication = generatePublicationWithContext(tClass);
            var model = ModelFactory.createDefaultModel();
            RDFDataMgr.read(model, publication, Lang.JSONLD11);
            var statementsIterator = model.listStatements();

            while (statementsIterator.hasNext()) {
                Statement next = statementsIterator.next();
                properties.add(next.getPredicate().getLocalName());
                var object = next.getObject();
                if (object.isResource()) {
                    objectResources.add(object.asResource().getURI());
                }
            }
        }
        System.out.println("objects:" + String.join(",", objectResources));
        System.out.println("properties: " + String.join(",", properties));

    }

    private static HashSet<String> extractAllRdfProperties(InputStream inputStream) {
        var model = ModelFactory.createDefaultModel();
        RDFDataMgr.read(model, inputStream, Lang.JSONLD11);
        var statementsIterator = model.listStatements();
        var properties = new HashSet<String>();
        while (statementsIterator.hasNext()) {
            properties.add(statementsIterator.next().getPredicate().getLocalName());
        }
        return properties;
    }

    private static ByteArrayInputStream generatePublicationWithContext(Class<?> tClass) {
        var publication = PublicationGenerator.randomPublication(tClass);
        System.out.println(attempt(() -> MAPPER.writeValueAsString(publication)).orElseThrow());
        var jsonNode = (ObjectNode) attempt(() -> MAPPER.valueToTree(publication)).orElseThrow();
        jsonNode.set("@context", attempt(() -> MAPPER.readTree(publication.getJsonLdContext())).orElseThrow());
        return new ByteArrayInputStream(jsonNode.toString().getBytes());
    }
}
