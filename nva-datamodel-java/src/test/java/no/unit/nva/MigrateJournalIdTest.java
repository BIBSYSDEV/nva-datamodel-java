package no.unit.nva;

import static no.unit.nva.testutils.RandomDataGenerator.randomInteger;
import static nva.commons.core.attempt.Try.attempt;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import java.net.URI;
import java.time.LocalDate;
import no.unit.nva.commons.json.JsonUtils;
import no.unit.nva.model.Publication;
import no.unit.nva.model.contexttypes.Journal;
import nva.commons.core.paths.UriWrapper;
import org.junit.jupiter.api.Test;

class MigrateJournalIdTest {

    private static final String API_HOST = "localhost";
    private static final String BASE_PATH = "publication-channels";
    private static final String JOURNAL_PATH_ELEMENT = "journal";
    private static final int YEAR_START = 1900;

    @Test
    void shouldReplaceOldJournalIdWithNewId() {
        var year = randomYear();
        var oldIdentifier = "28102";
        var newIdentifier = "D61B0D47-C78A-48DC-8537-3AD87DEF4D5B";
        var oldId = constructPublicationChannelId(year, oldIdentifier);
        var expectedNewId = constructPublicationChannelId(year, newIdentifier);
        var journal = new Journal(oldId);
        assertThat(journal.getId(), is(equalTo(expectedNewId)));
    }

    @Test
    void shouldReplaceOldJournalIdWithNewIdInAcademicArticle() {
        var year = randomYear();
        var oldIdentifier = "28102";
        var newIdentifier = "D61B0D47-C78A-48DC-8537-3AD87DEF4D5B";
        var oldId = constructPublicationChannelId(year, oldIdentifier);
        var expectedNewId = constructPublicationChannelId(year, newIdentifier);
        var json = generateFullAcademicArticleWithId(oldId);
        var actualAcademicArticle = attempt(() -> JsonUtils.dtoObjectMapper.readValue(json, Publication.class)).orElseThrow();
        var actualJournal = extractAndCastJournalFromAcademicArticle(actualAcademicArticle);
        assertThat(actualJournal.getId(), is(equalTo(expectedNewId)));
    }

    private static Journal extractAndCastJournalFromAcademicArticle(Publication academicArticle) {
        return (Journal) academicArticle.getEntityDescription().getReference().getPublicationContext();
    }

    private static URI constructPublicationChannelId(String year, String identifier) {
        return UriWrapper.fromHost(API_HOST)
                   .addChild(BASE_PATH)
                   .addChild(JOURNAL_PATH_ELEMENT)
                   .addChild(identifier)
                   .addChild(year)
                   .getUri();
    }

    private String randomYear() {
        var bound = (LocalDate.now().getYear() + 1) - YEAR_START;
        return Integer.toString(YEAR_START + randomInteger(bound));
    }

    private String generateFullAcademicArticleWithId(URI id) {
        return "{\n"
               + "  \"type\" : \"Publication\",\n"
               + "  \"identifier\" : \"c443030e-9d56-43d8-afd1-8c89105af555\",\n"
               + "  \"status\" : \"DRAFT\",\n"
               + "  \"resourceOwner\" : {\n"
               + "    \"owner\" : \"VJUawlrvsVax\",\n"
               + "    \"ownerAffiliation\" : \"https://www.example.org/sapienterepudiandae\"\n"
               + "  },\n"
               + "  \"publisher\" : {\n"
               + "    \"type\" : \"Organization\",\n"
               + "    \"id\" : \"https://www.example.org/quasinon\",\n"
               + "    \"labels\" : {\n"
               + "      \"it\" : \"fjj8MYITJU3H1lu8WiU\"\n"
               + "    }\n"
               + "  },\n"
               + "  \"createdDate\" : \"1993-11-03T02:27:13.615Z\",\n"
               + "  \"modifiedDate\" : \"2014-01-31T15:19:11.791Z\",\n"
               + "  \"publishedDate\" : \"2002-03-09T22:37:20.821Z\",\n"
               + "  \"indexedDate\" : \"2010-04-14T13:31:21.621Z\",\n"
               + "  \"handle\" : \"https://www.example.org/nonqui\",\n"
               + "  \"doi\" : \"https://doi.org/10.1234/nemo\",\n"
               + "  \"link\" : \"https://www.example.org/iureomnis\",\n"
               + "  \"entityDescription\" : {\n"
               + "    \"type\" : \"EntityDescription\",\n"
               + "    \"mainTitle\" : \"PU9KgU3PwOX\",\n"
               + "    \"alternativeTitles\" : {\n"
               + "      \"es\" : \"DB4lhCb2um8xOCL5x\"\n"
               + "    },\n"
               + "    \"language\" : \"http://lexvo.org/id/iso639-3/und\",\n"
               + "    \"publicationDate\" : {\n"
               + "      \"type\" : \"PublicationDate\",\n"
               + "      \"year\" : \"kaGtZ2SWW4IRf6DfAv\",\n"
               + "      \"month\" : \"Gzhb6APySrQ7bFQlv\",\n"
               + "      \"day\" : \"BbNoykdHxqHZ2nd0FGA\"\n"
               + "    },\n"
               + "    \"contributors\" : [ {\n"
               + "      \"type\" : \"Contributor\",\n"
               + "      \"identity\" : {\n"
               + "        \"type\" : \"Identity\",\n"
               + "        \"id\" : \"https://www.example.com/ugLUzuwDOi2\",\n"
               + "        \"name\" : \"OzCQJEYYtzTp\",\n"
               + "        \"nameType\" : \"Personal\",\n"
               + "        \"orcId\" : \"qcUNClQk5Fa4DcxzH\"\n"
               + "      },\n"
               + "      \"affiliations\" : [ {\n"
               + "        \"type\" : \"Organization\",\n"
               + "        \"id\" : \"https://www.example.com/eeS2AiUoWx1JBKJ3\",\n"
               + "        \"labels\" : {\n"
               + "          \"en\" : \"7PYJtBnjr4B64iHyS\"\n"
               + "        }\n"
               + "      } ],\n"
               + "      \"role\" : {\n"
               + "        \"type\" : \"Researcher\"\n"
               + "      },\n"
               + "      \"sequence\" : 1,\n"
               + "      \"correspondingAuthor\" : false\n"
               + "    }, {\n"
               + "      \"type\" : \"Contributor\",\n"
               + "      \"identity\" : {\n"
               + "        \"type\" : \"Identity\",\n"
               + "        \"id\" : \"https://www.example.com/BJmXFqBjrj5Sib\",\n"
               + "        \"name\" : \"hCiAtaYbtJ0l9lg4\",\n"
               + "        \"nameType\" : \"Personal\",\n"
               + "        \"orcId\" : \"5gOx6TNel62he3JCj2\"\n"
               + "      },\n"
               + "      \"affiliations\" : [ {\n"
               + "        \"type\" : \"Organization\",\n"
               + "        \"id\" : \"https://www.example.com/ZyYCOnE3xg\",\n"
               + "        \"labels\" : {\n"
               + "          \"cs\" : \"8nVPBYTTv8h9IhavUvt\"\n"
               + "        }\n"
               + "      } ],\n"
               + "      \"role\" : {\n"
               + "        \"type\" : \"Architect\"\n"
               + "      },\n"
               + "      \"sequence\" : 8,\n"
               + "      \"correspondingAuthor\" : false\n"
               + "    } ],\n"
               + "    \"alternativeAbstracts\" : {\n"
               + "      \"pt\" : \"sAx6HkVCUEZK4\"\n"
               + "    },\n"
               + "    \"npiSubjectHeading\" : \"8bTFv8rpGPYyUTCApgZ\",\n"
               + "    \"tags\" : [ \"SrlBVQW8nSfQqWVAUuk\" ],\n"
               + "    \"description\" : \"2w6uAk643SI5vS\",\n"
               + "    \"reference\" : {\n"
               + "      \"type\" : \"Reference\",\n"
               + "      \"publicationContext\" : {\n"
               + "        \"type\" : \"Journal\",\n"
               + "        \"id\" : \""
               + id.toString()
               + "\"\n"
               + "      },\n"
               + "      \"doi\" : \"https://www.example.com/TVZipj6JuYUA\",\n"
               + "      \"publicationInstance\" : {\n"
               + "        \"type\" : \"AcademicArticle\",\n"
               + "        \"pages\" : {\n"
               + "          \"type\" : \"Range\",\n"
               + "          \"begin\" : \"Tb2H8IsVxRwfjGOj1I\",\n"
               + "          \"end\" : \"E6Bk0QeyUft\"\n"
               + "        },\n"
               + "        \"volume\" : \"mznjoxFnxKbRa15Yo\",\n"
               + "        \"issue\" : \"WK99YSTRK9l\",\n"
               + "        \"articleNumber\" : \"3r7T343QJxBTXrQX\"\n"
               + "      }\n"
               + "    },\n"
               + "    \"metadataSource\" : \"https://www.example.com/xKRpKll1jwO\",\n"
               + "    \"abstract\" : \"pflLOskTN3EKTGFS\"\n"
               + "  },\n"
               + "  \"projects\" : [ {\n"
               + "    \"type\" : \"ResearchProject\",\n"
               + "    \"id\" : \"https://www.example.org/nonvelit\",\n"
               + "    \"name\" : \"7Kq99VKcK6mfVZ\",\n"
               + "    \"approvals\" : [ {\n"
               + "      \"type\" : \"Approval\",\n"
               + "      \"approvalDate\" : \"1991-06-06T01:17:35.171Z\",\n"
               + "      \"approvedBy\" : \"REK\",\n"
               + "      \"approvalStatus\" : \"APPLIED\",\n"
               + "      \"applicationCode\" : \"wLr7OCPGjUqIq\"\n"
               + "    } ]\n"
               + "  } ],\n"
               + "  \"fundings\" : [ {\n"
               + "    \"type\" : \"UnconfirmedFunding\",\n"
               + "    \"source\" : \"https://www.example.org/rerumconsequatur\",\n"
               + "    \"identifier\" : \"IdctVQJ63h\",\n"
               + "    \"labels\" : {\n"
               + "      \"sv\" : \"pKeCwBYLJxpt\"\n"
               + "    },\n"
               + "    \"fundingAmount\" : {\n"
               + "      \"currency\" : \"GBP\",\n"
               + "      \"amount\" : 1051064775\n"
               + "    },\n"
               + "    \"activeFrom\" : \"2020-07-25T01:48:10.544Z\",\n"
               + "    \"activeTo\" : \"2022-02-25T01:18:55.397Z\"\n"
               + "  }, {\n"
               + "    \"type\" : \"ConfirmedFunding\",\n"
               + "    \"source\" : \"https://www.example.org/magnamcorporis\",\n"
               + "    \"id\" : \"https://www.example.org/pariatureaque\",\n"
               + "    \"identifier\" : \"qUh32gtcKSIjbOC\",\n"
               + "    \"labels\" : {\n"
               + "      \"se\" : \"s0uKeGOzeB9XB\"\n"
               + "    },\n"
               + "    \"fundingAmount\" : {\n"
               + "      \"currency\" : \"EUR\",\n"
               + "      \"amount\" : 1601597189\n"
               + "    },\n"
               + "    \"activeFrom\" : \"2010-07-19T06:37:32.607Z\",\n"
               + "    \"activeTo\" : \"2021-11-06T09:51:34.675Z\"\n"
               + "  } ],\n"
               + "  \"additionalIdentifiers\" : [ {\n"
               + "    \"type\" : \"AdditionalIdentifier\",\n"
               + "    \"source\" : \"fakesource\",\n"
               + "    \"value\" : \"1234\"\n"
               + "  } ],\n"
               + "  \"subjects\" : [ \"https://www.example.org/quipraesentium\" ],\n"
               + "  \"associatedArtifacts\" : [ {\n"
               + "    \"type\" : \"PublishedFile\",\n"
               + "    \"identifier\" : \"fa04dbf1-b9cd-421c-b0ba-da28d44152f0\",\n"
               + "    \"name\" : \"NeSw2UW1V0ezkKZlA25\",\n"
               + "    \"mimeType\" : \"1fv099CttTPYSe1\",\n"
               + "    \"size\" : 811333062,\n"
               + "    \"license\" : {\n"
               + "      \"type\" : \"License\",\n"
               + "      \"identifier\" : \"UJm0w03h7upk9\",\n"
               + "      \"labels\" : {\n"
               + "        \"de\" : \"QShFzkQ79c0T1B6z\"\n"
               + "      },\n"
               + "      \"link\" : \"https://www.example.com/GsfjlbPscAMv6Ae\"\n"
               + "    },\n"
               + "    \"administrativeAgreement\" : false,\n"
               + "    \"publisherAuthority\" : true,\n"
               + "    \"publishedDate\" : \"2014-01-30T09:38:35.768Z\",\n"
               + "    \"visibleForNonOwner\" : true\n"
               + "  }, {\n"
               + "    \"type\" : \"AssociatedLink\",\n"
               + "    \"id\" : \"https://www.example.com/xec6fKydjDr\",\n"
               + "    \"name\" : \"5IE3Sm7A8AuVSC\",\n"
               + "    \"description\" : \"JovMd59Ga27rXoXV\"\n"
               + "  } ],\n"
               + "  \"rightsHolder\" : \"kyKpAZo53ySWow8yZ\",\n"
               + "  \"modelVersion\" : \"0.20.18\"\n"
               + "}";
    }
}
