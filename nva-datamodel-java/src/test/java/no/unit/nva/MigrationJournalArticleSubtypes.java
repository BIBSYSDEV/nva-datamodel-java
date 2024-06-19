package no.unit.nva;

import no.unit.nva.commons.json.JsonUtils;
import no.unit.nva.model.Publication;
import no.unit.nva.model.instancetypes.journal.JournalArticleContentType;
import org.javers.core.metamodel.annotation.IgnoreDeclaredProperties;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static nva.commons.core.attempt.Try.attempt;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

@Deprecated
class MigrationJournalArticleSubtypes {

    @ParameterizedTest
    @EnumSource(JournalArticleContentType.class)
    @Disabled
    void shouldDeserializeOldSubtypeJournalArticleContentTypeAsType(JournalArticleContentType content) {
        var type = content.getValue();
        var json = generateFullJournalArticleWithContentType(type);
        var journalArticle = attempt(() -> JsonUtils.dtoObjectMapper.readValue(json, Publication.class))
                .orElseThrow();
        var actual = journalArticle.getEntityDescription()
                .getReference().getPublicationInstance().getClass().getSimpleName();
        assertThat(actual, is(equalTo(type)));
    }

    private static String generateFullJournalArticleWithContentType(String contentType) {
        return "{\n"
                + "  \"type\" : \"Publication\",\n"
                + "  \"identifier\" : \"c443030e-9d56-43d8-afd1-8c89105af555\",\n"
                + "  \"status\" : \"PUBLISHED_METADATA\",\n"
                + "  \"resourceOwner\" : {\n"
                + "    \"owner\" : \"4TmBjGCN1AmmBB4I3j\",\n"
                + "    \"ownerAffiliation\" : \"https://www.example.org/nobisqui\"\n"
                + "  },\n"
                + "  \"publisher\" : {\n"
                + "    \"type\" : \"Organization\",\n"
                + "    \"id\" : \"https://www.example.org/amolestiae\",\n"
                + "    \"labels\" : {\n"
                + "      \"it\" : \"M2qeYhojtzlFhOf\"\n"
                + "    }\n"
                + "  },\n"
                + "  \"createdDate\" : \"1972-09-01T04:35:34.770Z\",\n"
                + "  \"modifiedDate\" : \"2001-08-13T14:29:04.580Z\",\n"
                + "  \"publishedDate\" : \"2020-12-23T06:01:38.371Z\",\n"
                + "  \"indexedDate\" : \"1980-08-06T18:33:15.367Z\",\n"
                + "  \"handle\" : \"https://www.example.org/ipsaea\",\n"
                + "  \"doi\" : \"https://doi.org/10.1234/eaque\",\n"
                + "  \"link\" : \"https://www.example.org/utvoluptatem\",\n"
                + "  \"entityDescription\" : {\n"
                + "    \"type\" : \"EntityDescription\",\n"
                + "    \"mainTitle\" : \"qIZNaXDKymx\",\n"
                + "    \"alternativeTitles\" : {\n"
                + "      \"is\" : \"Z9zlbat2Klf\"\n"
                + "    },\n"
                + "    \"language\" : \"http://lexvo.org/id/iso639-3/und\",\n"
                + "    \"date\" : {\n"
                + "      \"type\" : \"PublicationDate\",\n"
                + "      \"year\" : \"WhMPvnAqZD8c\",\n"
                + "      \"month\" : \"XkeaMca9UppPjCVvRK4\",\n"
                + "      \"day\" : \"wkAWmWCQ3M1brzNZiI\"\n"
                + "    },\n"
                + "    \"contributors\" : [ {\n"
                + "      \"type\" : \"Contributor\",\n"
                + "      \"identity\" : {\n"
                + "        \"type\" : \"Identity\",\n"
                + "        \"id\" : \"https://www.example.com/X4wvTuNwAc8QqQeB\",\n"
                + "        \"name\" : \"cpH3rsLYy2O9qzfP\",\n"
                + "        \"nameType\" : \"Organizational\",\n"
                + "        \"orcId\" : \"jhOSXlkR3PpeG4N\"\n"
                + "      },\n"
                + "      \"affiliations\" : [ {\n"
                + "        \"type\" : \"Organization\",\n"
                + "        \"id\" : \"https://www.example.com/SHf08a2UT8yiL4\",\n"
                + "        \"labels\" : {\n"
                + "          \"hu\" : \"hzppegUya2siCVvvVJ\"\n"
                + "        }\n"
                + "      } ],\n"
                + "      \"role\" : \"Writer\",\n"
                + "      \"sequence\" : 4,\n"
                + "      \"correspondingAuthor\" : false\n"
                + "    }, {\n"
                + "      \"type\" : \"Contributor\",\n"
                + "      \"identity\" : {\n"
                + "        \"type\" : \"Identity\",\n"
                + "        \"id\" : \"https://www.example.com/7oS1BcncWcDwx2JP\",\n"
                + "        \"name\" : \"oVR3pdbUvaepV\",\n"
                + "        \"nameType\" : \"Organizational\",\n"
                + "        \"orcId\" : \"SKr9XyTuIi\"\n"
                + "      },\n"
                + "      \"affiliations\" : [ {\n"
                + "        \"type\" : \"Organization\",\n"
                + "        \"id\" : \"https://www.example.com/MeBieb0Tyihiy\",\n"
                + "        \"labels\" : {\n"
                + "          \"ca\" : \"Sto0PAY8C0\"\n"
                + "        }\n"
                + "      } ],\n"
                + "      \"role\" : \"Soloist\",\n"
                + "      \"sequence\" : 5,\n"
                + "      \"correspondingAuthor\" : false\n"
                + "    } ],\n"
                + "    \"npiSubjectHeading\" : \"GuBHWYxnyMn2gU\",\n"
                + "    \"tags\" : [ \"xpdNqJyRETrdW\" ],\n"
                + "    \"description\" : \"WIQwOUiT9Qu8HNQA\",\n"
                + "    \"reference\" : {\n"
                + "      \"type\" : \"Reference\",\n"
                + "      \"publicationContext\" : {\n"
                + "        \"type\" : \"Journal\",\n"
                + "        \"id\" : \"https://api.dev.nva.aws.unit.no/publication-channels/eujd55boZtis7Ql0\"\n"
                + "      },\n"
                + "      \"doi\" : \"https://www.example.com/aFCyqrhxxyTnOW\",\n"
                + "      \"publicationInstance\" : {\n"
                + "        \"type\" : \"JournalArticle\",\n"
                + "        \"volume\" : \"d8lgJKdVnFH51\",\n"
                + "        \"issue\" : \"1SOozXNuiS8uojXSO\",\n"
                + "        \"articleNumber\" : \"BtGFNkZzVs1CKwjJLz\",\n"
                + "        \"contentType\" : \"" + contentType + "\",\n"
                + "        \"peerReviewed\" : true,\n"
                + "        \"originalResearch\" : false,\n"
                + "        \"pages\" : {\n"
                + "          \"type\" : \"Range\",\n"
                + "          \"begin\" : \"OiCuLxrY5vHYhtB0\",\n"
                + "          \"end\" : \"cs3eFzfcJ9lzs\"\n"
                + "        }\n"
                + "      }\n"
                + "    },\n"
                + "    \"metadataSource\" : \"https://www.example.com/Q86zpd2W9M\",\n"
                + "    \"abstract\" : \"SzqBaTsSA44k\"\n"
                + "  },\n"
                + "  \"projects\" : [ {\n"
                + "    \"type\" : \"ResearchProject\",\n"
                + "    \"id\" : \"https://www.example.org/assumendaeum\",\n"
                + "    \"name\" : \"qr9KS9w6G67SrtUzGmv\",\n"
                + "    \"approvals\" : [ {\n"
                + "      \"type\" : \"Approval\",\n"
                + "      \"date\" : \"2020-08-30T12:38:40.124Z\",\n"
                + "      \"approvedBy\" : \"DIRHEALTH\",\n"
                + "      \"approvalStatus\" : \"NOTAPPLIED\",\n"
                + "      \"applicationCode\" : \"bK0XmCJlYNwE0oUNb\"\n"
                + "    } ]\n"
                + "  } ],\n"
                + "  \"fundings\" : [ {\n"
                + "    \"type\" : \"ConfirmedFunding\",\n"
                + "    \"importSource\" : \"https://www.example.org/atvoluptatibus\",\n"
                + "    \"id\" : \"https://www.example.org/cupiditatevoluptas\",\n"
                + "    \"identifier\" : \"5INZCCOTpFiP8YNFmCJ\",\n"
                + "    \"name\" : \"7kTCaw7kUQIgeI0SXDG\",\n"
                + "    \"alternativeName\" : {\n"
                + "      \"en\" : \"QNy99yR6zoqCFq\"\n"
                + "    },\n"
                + "    \"fundingAmount\" : {\n"
                + "      \"currency\" : \"EUR\",\n"
                + "      \"amount\" : 973238067\n"
                + "    },\n"
                + "    \"activeFrom\" : \"2011-06-06T01:00:52.156Z\",\n"
                + "    \"activeTo\" : \"2019-05-04T03:50:10.791Z\"\n"
                + "  } ],\n"
                + "  \"additionalIdentifiers\" : [ {\n"
                + "    \"type\" : \"AdditionalIdentifier\",\n"
                + "    \"importSource\" : \"fakesource\",\n"
                + "    \"value\" : \"1234\"\n"
                + "  } ],\n"
                + "  \"subjects\" : [ \"https://www.example.org/harumquasi\" ],\n"
                + "  \"associatedArtifacts\" : [ {\n"
                + "    \"type\" : \"PublishedFile\",\n"
                + "    \"identifier\" : \"d8c5785c-2100-4097-b461-a7269d78bf03\",\n"
                + "    \"name\" : \"63IPVTamuKud3NP\",\n"
                + "    \"mimeType\" : \"QBk86jTCqlPBH\",\n"
                + "    \"size\" : 463477975,\n"
                + "    \"license\" : {\n"
                + "      \"type\" : \"License\",\n"
                + "      \"identifier\" : \"nmTcPkIZJPrTYF7iGE\",\n"
                + "      \"labels\" : {\n"
                + "        \"pt\" : \"XrgMENjWTy\"\n"
                + "      },\n"
                + "      \"link\" : \"https://www.example.com/w8VjoRRwdEYOUK0\"\n"
                + "    },\n"
                + "    \"administrativeAgreement\" : false,\n"
                + "    \"publisherAuthority\" : true,\n"
                + "    \"visibleForNonOwner\" : true\n"
                + "  }, {\n"
                + "    \"type\" : \"AssociatedLink\",\n"
                + "    \"id\" : \"https://www.example.com/Y9CEp5BhRSsNG57Ve1G\",\n"
                + "    \"name\" : \"sz5x297ukf2\",\n"
                + "    \"description\" : \"mVzLj4RXFc5Zd6DBZ7o\"\n"
                + "  } ],\n"
                + "  \"modelVersion\" : \"0.19.28\"\n"
                + "}";
    }
}
