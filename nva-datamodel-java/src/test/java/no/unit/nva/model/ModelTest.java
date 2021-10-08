package no.unit.nva.model;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static no.unit.nva.DatamodelConfig.dataModelObjectMapper;
import static nva.commons.core.attempt.Try.attempt;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import no.unit.nva.JsonHandlingTest;
import no.unit.nva.model.pages.MonographPages;
import no.unit.nva.model.pages.Range;

public class ModelTest implements JsonHandlingTest {

    public static final String TYPE = "type";
    public static final String SERIES = "series";
    public static final String SERIES_NUMBER = "seriesNumber";
    public static final String PUBLISHER = "publisher";
    public static final String ISBN_LIST = "isbnList";
    public static final String PART_OF = "partOf";


    protected static MonographPages generateMonographPages(String introductionBegin,
                                                           String introductionEnd,
                                                           String pages,
                                                           boolean illustrated) {
        return new MonographPages.Builder()
            .withPages(pages)
            .withIllustrated(illustrated)
            .withIntroduction(generateRange(introductionBegin, introductionEnd))
            .build();
    }

    protected static MonographPages generateMonographPages() {
        return generateMonographPages("i", "ix", "321", true);
    }

    protected static Range generateRange(String begin, String end) {
        return new Range.Builder()
            .withBegin(begin)
            .withEnd(end)
            .build();
    }

    protected static Contributor generateContributor() {
        return new Contributor.Builder()
            .withSequence(0)
            .withRole(Role.CREATOR)
            .withAffiliations(Collections.singletonList(generateOrganization()))
            .withIdentity(generateIdentity())
            .withCorrespondingAuthor(true)
            .build();
    }

    protected static Identity generateIdentity() {
        return new Identity.Builder()
            .withId(URI.create("http://example.org/person/123"))
            .withArpId("arp123")
            .withOrcId("orc123")
            .withName("Navnesen, Navn")
            .withNameType(NameType.PERSONAL)
            .build();
    }

    protected static License generateLicense() {
        return new License.Builder()
            .withIdentifier("NTNU-CC-BY-4.0")
            .withLink(URI.create("http://example.org/license/123"))
            .withLabels(Collections.singletonMap("no", "CC-BY 4.0"))
            .build();
    }

    protected static Organization generateOrganization() {
        return new Organization.Builder()
            .withId(URI.create("http://example.org/org/123"))
            .withLabels(Collections.singletonMap("no", "Eksempelforlaget"))
            .build();
    }

    protected static String generatePublicationJson(String type,
                                                    String seriesTitle,
                                                    String seriesNumber,
                                                    String publisher,
                                                    List<String> isbnList,
                                                    String onlineIssn,
                                                    String printIssn,
                                                    URI partOf) {

        ObjectNode jsonNode = dataModelObjectMapper.createObjectNode();
        jsonNode.put(TYPE, type);

        jsonNode.set(SERIES, generateUnconfirmedSeriesJson(seriesTitle, printIssn, onlineIssn));
        jsonNode.put(SERIES_NUMBER, seriesNumber);
        jsonNode.set(PUBLISHER, generateUnconfirmedPublishersJson(publisher));
        jsonNode.set(ISBN_LIST, arrayNode(isbnList));
        if (nonNull(partOf)) {
            jsonNode.put(PART_OF, partOf.toString());
        }

        return attempt(() -> dataModelObjectMapper.writeValueAsString(jsonNode))
            .map(content -> dataModelObjectMapper.readValue(content, Map.class))
            .map(dataModelObjectMapper::writeValueAsString)
            .orElseThrow();
    }

    private static JsonNode generateUnconfirmedSeriesJson(String seriesTitle, String printIssn, String onlineIssn) {
        ObjectNode jsonNode = dataModelObjectMapper.createObjectNode();
        jsonNode.put("type", "UnconfirmedSeries");
        jsonNode.put("title", seriesTitle);
        jsonNode.put("issn", printIssn);
        jsonNode.put("onlineIssn", onlineIssn);
        return jsonNode;
    }

    private static JsonNode generateUnconfirmedPublishersJson(String publisher) {
        ObjectNode jsonNode = dataModelObjectMapper.createObjectNode();
        jsonNode.put("type", "UnconfirmedPublisher");
        jsonNode.put("name", publisher);
        return jsonNode;
    }

    private static JsonNode arrayNode(List<String> isbnList) {
        if (isNull(isbnList)) {
            return dataModelObjectMapper.nullNode();
        }
        ArrayNode arrayNode = dataModelObjectMapper.createArrayNode();
        isbnList.forEach(arrayNode::add);
        return arrayNode;
    }
}
