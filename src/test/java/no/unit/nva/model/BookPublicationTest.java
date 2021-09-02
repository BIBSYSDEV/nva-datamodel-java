package no.unit.nva.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import no.unit.nva.model.contexttypes.Book;
import no.unit.nva.model.contexttypes.BookSeries;
import no.unit.nva.model.contexttypes.UnconfirmedSeries;
import no.unit.nva.model.exceptions.InvalidIsbnException;
import no.unit.nva.model.exceptions.MalformedContributorException;
import no.unit.nva.model.util.PublicationGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

public class BookPublicationTest extends PublicationTest {

    @DisplayName("Book publications can be created")
    @Test
    void publicationReturnPublicationWhenInputIsValid() throws MalformedContributorException, IOException,
            InvalidIsbnException {
        Publication bookPublication = PublicationGenerator.generateBookMonographPublication();
        JsonNode document = toPublicationWithContext(bookPublication);

        Publication publicationFromJson = objectMapper.readValue(objectMapper.writeValueAsString(document),
                Publication.class);

        assertThat(bookPublication, is(equalTo(publicationFromJson)));
    }

    @Test
    void publicationReturnsSeriesWhenInputIsSeriesTitle() throws JsonProcessingException {
        String book = "{\n"
                + "  \"type\" : \"Publication\",\n"
                + "  \"identifier\" : \"c443030e-9d56-43d8-afd1-8c89105af555\",\n"
                + "  \"status\" : \"DRAFT\",\n"
                + "  \"owner\" : \"eier@example.org\",\n"
                + "  \"publisher\" : {\n"
                + "    \"type\" : \"Organization\",\n"
                + "    \"id\" : \"http://example.org/org/123\",\n"
                + "    \"labels\" : {\n"
                + "      \"no\" : \"Eksempelforlaget\"\n"
                + "    }\n"
                + "  },\n"
                + "  \"createdDate\" : \"2020-09-23T09:51:23.044996Z\",\n"
                + "  \"modifiedDate\" : \"2020-09-23T09:51:23.044996Z\",\n"
                + "  \"publishedDate\" : \"2020-09-23T09:51:23.044996Z\",\n"
                + "  \"indexedDate\" : \"2020-09-23T09:51:23.044996Z\",\n"
                + "  \"handle\" : \"http://example.org/handle/123\",\n"
                + "  \"doi\" : \"http://example.org/doi/1231/98765\",\n"
                + "  \"doiRequest\" : {\n"
                + "    \"type\" : \"DoiRequest\",\n"
                + "    \"status\" : \"REQUESTED\",\n"
                + "    \"modifiedDate\" : \"2020-09-23T09:51:23.044996Z\",\n"
                + "    \"createdDate\" : \"2020-09-23T09:51:23.044996Z\",\n"
                + "    \"messages\" : [ {\n"
                + "      \"type\" : \"DoiRequestMessage\",\n"
                + "      \"text\" : \"Some Text\",\n"
                + "      \"author\" : \"SomeAuthor\",\n"
                + "      \"timestamp\" : \"2020-09-23T09:51:23.044996Z\"\n"
                + "    } ]\n"
                + "  },\n"
                + "  \"link\" : \"http://example.org/link\",\n"
                + "  \"entityDescription\" : {\n"
                + "    \"type\" : \"EntityDescription\",\n"
                + "    \"mainTitle\" : \"Hovedtittelen\",\n"
                + "    \"alternativeTitles\" : {\n"
                + "      \"en\" : \"English title\"\n"
                + "    },\n"
                + "    \"language\" : \"http://example.org/norsk\",\n"
                + "    \"date\" : {\n"
                + "      \"type\" : \"PublicationDate\",\n"
                + "      \"year\" : \"2020\",\n"
                + "      \"month\" : \"4\",\n"
                + "      \"day\" : \"7\"\n"
                + "    },\n"
                + "    \"contributors\" : [ {\n"
                + "      \"type\" : \"Contributor\",\n"
                + "      \"identity\" : {\n"
                + "        \"type\" : \"Identity\",\n"
                + "        \"id\" : \"http://example.org/person/123\",\n"
                + "        \"name\" : \"Navnesen, Navn\",\n"
                + "        \"nameType\" : \"Personal\",\n"
                + "        \"orcId\" : \"orc123\",\n"
                + "        \"arpId\" : \"arp123\"\n"
                + "      },\n"
                + "      \"affiliations\" : [ {\n"
                + "        \"type\" : \"Organization\",\n"
                + "        \"id\" : \"http://example.org/org/123\",\n"
                + "        \"labels\" : {\n"
                + "          \"no\" : \"Eksempelforlaget\"\n"
                + "        }\n"
                + "      } ],\n"
                + "      \"role\" : \"Creator\",\n"
                + "      \"sequence\" : 0,\n"
                + "      \"correspondingAuthor\" : true,\n"
                + "      \"email\" : \"nn@example.org\"\n"
                + "    } ],\n"
                + "    \"npiSubjectHeading\" : \"010\",\n"
                + "    \"tags\" : [ \"dokumenter\", \"publikasjoner\" ],\n"
                + "    \"description\" : \"Beskriver innholdet i dokumentet på en annen måte enn abstrakt\",\n"
                + "    \"reference\" : {\n"
                + "      \"type\" : \"Reference\",\n"
                + "      \"publicationContext\" : {\n"
                + "        \"type\" : \"Book\",\n"
                + "        \"seriesTitle\" : \"Horse-oriented tooling in automated testing\",\n"
                + "        \"seriesNumber\" : \"215\",\n"
                + "        \"publisher\" : \"Slalom publishing\",\n"
                + "        \"isbList\" : []\n"
                + "      },\n"
                + "      \"doi\" : \"https://123123/213123.com\",\n"
                + "      \"publicationInstance\" : {\n"
                + "        \"type\" : \"JournalArticle\",\n"
                + "        \"volume\" : \"24\",\n"
                + "        \"issue\" : \"2\",\n"
                + "        \"articleNumber\" : \"1234456\",\n"
                + "        \"contentType\" : \"Professional article\",\n"
                + "        \"peerReviewed\" : true,\n"
                + "        \"originalResearch\" : false,\n"
                + "        \"pages\" : {\n"
                + "          \"type\" : \"Range\",\n"
                + "          \"begin\" : \"1\",\n"
                + "          \"end\" : \"15\"\n"
                + "        }\n"
                + "      }\n"
                + "    },\n"
                + "    \"metadataSource\" : \"https://example.org/doi?doi=123/123\",\n"
                + "    \"abstract\" : \"En lang streng som beskriver innholdet i dokumentet metadataene omtaler.\"\n"
                + "  },\n"
                + "  \"fileSet\" : {\n"
                + "    \"type\" : \"FileSet\",\n"
                + "    \"files\" : [ {\n"
                + "      \"type\" : \"File\",\n"
                + "      \"identifier\" : \"5032710d-a326-43d3-a8fb-57a451873c78\",\n"
                + "      \"name\" : \"new document(1)\",\n"
                + "      \"mimeType\" : \"application/pdf\",\n"
                + "      \"size\" : 2,\n"
                + "      \"license\" : {\n"
                + "        \"type\" : \"License\",\n"
                + "        \"identifier\" : \"NTNU-CC-BY-4.0\",\n"
                + "        \"labels\" : {\n"
                + "          \"no\" : \"CC-BY 4.0\"\n"
                + "        },\n"
                + "        \"link\" : \"http://example.org/license/123\"\n"
                + "      },\n"
                + "      \"administrativeAgreement\" : true,\n"
                + "      \"publisherAuthority\" : true,\n"
                + "      \"embargoDate\" : \"2020-09-23T09:51:23.044996Z\"\n"
                + "    } ]\n"
                + "  },\n"
                + "  \"projects\" : [ {\n"
                + "    \"type\" : \"ResearchProject\",\n"
                + "    \"id\" : \"http://link.to.cristin.example.org/123\",\n"
                + "    \"name\" : \"Det gode prosjektet\",\n"
                + "    \"grants\" : [ {\n"
                + "      \"type\" : \"Grant\",\n"
                + "      \"source\" : \"Norsk rødt felaget\",\n"
                + "      \"id\" : \"123123\"\n"
                + "    } ],\n"
                + "    \"approvals\" : [ {\n"
                + "      \"type\" : \"Approval\",\n"
                + "      \"date\" : \"2020-09-23T09:51:23.044996Z\",\n"
                + "      \"approvedBy\" : \"REK\",\n"
                + "      \"approvalStatus\" : \"APPLIED\",\n"
                + "      \"applicationCode\" : \"123123\"\n"
                + "    } ]\n"
                + "  } ],\n"
                + "  \"additionalIdentifiers\" : [ {\n"
                + "    \"type\" : \"AdditionalIdentifier\",\n"
                + "    \"source\" : \"fakesource\",\n"
                + "    \"value\" : \"1234\"\n"
                + "  } ],\n"
                + "  \"subjects\" : [ \"http://example.org/subject/123\" ],\n"
                + "  \"modelVersion\" : \"0.12.4\"\n"
                + "}";
        Publication publication = objectMapper.readValue(book, Publication.class);
        BookSeries actual = ((Book) publication.getEntityDescription()
                .getReference()
                .getPublicationContext())
                .getSeries();

        assertThat(actual, instanceOf(UnconfirmedSeries.class));
    }
}
