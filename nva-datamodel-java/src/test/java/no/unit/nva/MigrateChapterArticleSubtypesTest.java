package no.unit.nva;

import no.unit.nva.commons.json.JsonUtils;
import no.unit.nva.model.Publication;
import no.unit.nva.model.instancetypes.chapter.ChapterArticleContentType;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static nva.commons.core.attempt.Try.attempt;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

class MigrateChapterArticleSubtypesTest {
    @ParameterizedTest
    @EnumSource(ChapterArticleContentType.class)
    @Disabled
    void shouldDeserializeOldSubtypeBookMonographContentTypeAsType(ChapterArticleContentType content) {
        var type = content.getValue();
        var json = generateFullChapterArticleWithContentType(type);
        var chapterArticle = attempt(() -> JsonUtils.dtoObjectMapper.readValue(json, Publication.class))
                .orElseThrow();
        var actual = chapterArticle.getEntityDescription()
                .getReference().getPublicationInstance().getClass().getSimpleName();
        assertThat(actual, is(equalTo(type)));
    }

    private String generateFullChapterArticleWithContentType(String type) {
        return "{\n"
                + "  \"type\" : \"Publication\",\n"
                + "  \"identifier\" : \"c443030e-9d56-43d8-afd1-8c89105af555\",\n"
                + "  \"status\" : \"NEW\",\n"
                + "  \"resourceOwner\" : {\n"
                + "    \"owner\" : \"RuYrbdooUL32\",\n"
                + "    \"ownerAffiliation\" : \"https://www.example.org/repellateum\"\n"
                + "  },\n"
                + "  \"publisher\" : {\n"
                + "    \"type\" : \"Organization\",\n"
                + "    \"id\" : \"https://www.example.org/errorrem\",\n"
                + "    \"labels\" : {\n"
                + "      \"bg\" : \"ttgW11eA97cznnf4GC4\"\n"
                + "    }\n"
                + "  },\n"
                + "  \"createdDate\" : \"2020-07-28T09:09:48.320Z\",\n"
                + "  \"modifiedDate\" : \"2002-08-03T23:05:35.416Z\",\n"
                + "  \"publishedDate\" : \"1991-09-28T07:19:46.957Z\",\n"
                + "  \"indexedDate\" : \"1983-09-18T05:29:53.500Z\",\n"
                + "  \"handle\" : \"https://www.example.org/repudiandaeullam\",\n"
                + "  \"doi\" : \"https://doi.org/10.1234/dolore\",\n"
                + "  \"link\" : \"https://www.example.org/reprehenderitsaepe\",\n"
                + "  \"entityDescription\" : {\n"
                + "    \"type\" : \"EntityDescription\",\n"
                + "    \"mainTitle\" : \"BLYSKI1nfdbkv\",\n"
                + "    \"alternativeTitles\" : {\n"
                + "      \"el\" : \"zm6679gekbLZW\"\n"
                + "    },\n"
                + "    \"language\" : \"http://lexvo.org/id/iso639-3/und\",\n"
                + "    \"date\" : {\n"
                + "      \"type\" : \"PublicationDate\",\n"
                + "      \"year\" : \"XXAhi6akNONu\",\n"
                + "      \"month\" : \"HEQzNoatbd6NSMKHRg0\",\n"
                + "      \"day\" : \"2ESjpduz4r7bS\"\n"
                + "    },\n"
                + "    \"contributors\" : [ {\n"
                + "      \"type\" : \"Contributor\",\n"
                + "      \"identity\" : {\n"
                + "        \"type\" : \"Identity\",\n"
                + "        \"id\" : \"https://www.example.com/XhSa9gtoce12\",\n"
                + "        \"name\" : \"75efqCBNvlir7c\",\n"
                + "        \"nameType\" : \"Organizational\",\n"
                + "        \"orcId\" : \"t5ZYTi4TYLff\"\n"
                + "      },\n"
                + "      \"affiliations\" : [ {\n"
                + "        \"type\" : \"Organization\",\n"
                + "        \"id\" : \"https://www.example.com/6HhoBSEkueTu2Z\",\n"
                + "        \"labels\" : {\n"
                + "          \"cs\" : \"x4Pn570F0gnE\"\n"
                + "        }\n"
                + "      } ],\n"
                + "      \"role\" : \"Curator\",\n"
                + "      \"sequence\" : 0,\n"
                + "      \"correspondingAuthor\" : false\n"
                + "    }, {\n"
                + "      \"type\" : \"Contributor\",\n"
                + "      \"identity\" : {\n"
                + "        \"type\" : \"Identity\",\n"
                + "        \"id\" : \"https://www.example.com/XzqAR37csm9zWJ\",\n"
                + "        \"name\" : \"4R2jeB61pvak8IWqj8H\",\n"
                + "        \"nameType\" : \"Organizational\",\n"
                + "        \"orcId\" : \"Aog7SFb5e54M\"\n"
                + "      },\n"
                + "      \"affiliations\" : [ {\n"
                + "        \"type\" : \"Organization\",\n"
                + "        \"id\" : \"https://www.example.com/N6MywIzDqqmSv4k4\",\n"
                + "        \"labels\" : {\n"
                + "          \"en\" : \"OL3z1bGqeL0LzNza\"\n"
                + "        }\n"
                + "      } ],\n"
                + "      \"role\" : \"Artist\",\n"
                + "      \"sequence\" : 5,\n"
                + "      \"correspondingAuthor\" : false\n"
                + "    } ],\n"
                + "    \"npiSubjectHeading\" : \"Rk8MXIukbIzwqLP6\",\n"
                + "    \"tags\" : [ \"S4dKS7Cn5r6\" ],\n"
                + "    \"description\" : \"w2BzLiBJVxLFuROrnY\",\n"
                + "    \"reference\" : {\n"
                + "      \"type\" : \"Reference\",\n"
                + "      \"publicationContext\" : {\n"
                + "        \"type\" : \"Anthology\",\n"
                + "        \"partOf\" : \"https://www.example.com/8Fj6K3c2Eyx\"\n"
                + "      },\n"
                + "      \"doi\" : \"https://www.example.com/TVeL0ieitk1mLBbMH\",\n"
                + "      \"publicationInstance\" : {\n"
                + "        \"type\" : \"ChapterArticle\",\n"
                + "        \"contentType\" : \"" + type + "\",\n"
                + "        \"peerReviewed\" : true,\n"
                + "        \"originalResearch\" : true,\n"
                + "        \"pages\" : {\n"
                + "          \"type\" : \"Range\",\n"
                + "          \"begin\" : \"qDEY1GhYXtxTVxAdE\",\n"
                + "          \"end\" : \"r3L4UKe6bg10Sqoj\"\n"
                + "        }\n"
                + "      }\n"
                + "    },\n"
                + "    \"metadataSource\" : \"https://www.example.com/Ag6Qro62b8DvL9p4\",\n"
                + "    \"abstract\" : \"CBLk1w1sHe\"\n"
                + "  },\n"
                + "  \"projects\" : [ {\n"
                + "    \"type\" : \"ResearchProject\",\n"
                + "    \"id\" : \"https://www.example.org/aliassaepe\",\n"
                + "    \"name\" : \"LOumMwmN3JQmFxCY8Ha\",\n"
                + "    \"approvals\" : [ {\n"
                + "      \"type\" : \"Approval\",\n"
                + "      \"date\" : \"2023-01-08T09:20:28.086Z\",\n"
                + "      \"approvedBy\" : \"REK\",\n"
                + "      \"approvalStatus\" : \"DECLINED\",\n"
                + "      \"applicationCode\" : \"xwWwEn0awmly\"\n"
                + "    } ]\n"
                + "  } ],\n"
                + "  \"fundings\" : [ {\n"
                + "    \"type\" : \"UnconfirmedFunding\",\n"
                + "    \"importSource\" : \"https://www.example.org/voluptatemsed\",\n"
                + "    \"identifier\" : \"Ue3A2hsgEG\",\n"
                + "    \"labels\" : {\n"
                + "      \"fr\" : \"gUeWn5rgnIaZ902P0Gm\"\n"
                + "    },\n"
                + "    \"fundingAmount\" : {\n"
                + "      \"currency\" : \"USD\",\n"
                + "      \"amount\" : 161825051\n"
                + "    },\n"
                + "    \"activeFrom\" : \"2011-05-05T00:03:24.088Z\",\n"
                + "    \"activeTo\" : \"2019-03-27T14:38:32.956Z\"\n"
                + "  }, {\n"
                + "    \"type\" : \"ConfirmedFunding\",\n"
                + "    \"importSource\" : \"https://www.example.org/laboriosamneque\",\n"
                + "    \"id\" : \"https://www.example.org/nullaquia\",\n"
                + "    \"identifier\" : \"Dl1wT7xbmq4C2I4sQKx\",\n"
                + "    \"labels\" : {\n"
                + "      \"da\" : \"H1ERfelmzeBQxdSs\"\n"
                + "    },\n"
                + "    \"fundingAmount\" : {\n"
                + "      \"currency\" : \"GBP\",\n"
                + "      \"amount\" : 981864134\n"
                + "    },\n"
                + "    \"activeFrom\" : \"1973-04-26T21:20:47.124Z\",\n"
                + "    \"activeTo\" : \"2018-03-07T11:19:24.360Z\"\n"
                + "  } ],\n"
                + "  \"additionalIdentifiers\" : [ {\n"
                + "    \"type\" : \"AdditionalIdentifier\",\n"
                + "    \"importSource\" : \"fakesource\",\n"
                + "    \"value\" : \"1234\"\n"
                + "  } ],\n"
                + "  \"subjects\" : [ \"https://www.example.org/maximevel\" ],\n"
                + "  \"associatedArtifacts\" : [ {\n"
                + "    \"type\" : \"PublishedFile\",\n"
                + "    \"identifier\" : \"46725458-c60c-44a6-a952-41d25c9e5de3\",\n"
                + "    \"name\" : \"gEUP4JgMXh1Q\",\n"
                + "    \"mimeType\" : \"orcFMiw4ZnM\",\n"
                + "    \"size\" : 1581080284,\n"
                + "    \"license\" : {\n"
                + "      \"type\" : \"License\",\n"
                + "      \"identifier\" : \"7PYHmgVdvENJTYWcm\",\n"
                + "      \"labels\" : {\n"
                + "        \"fi\" : \"0SwxwFiSVRwQvz5ijCd\"\n"
                + "      },\n"
                + "      \"link\" : \"https://www.example.com/PSyujG3bq4ccWVvdhL\"\n"
                + "    },\n"
                + "    \"administrativeAgreement\" : false,\n"
                + "    \"publisherAuthority\" : true,\n"
                + "    \"visibleForNonOwner\" : true\n"
                + "  }, {\n"
                + "    \"type\" : \"AssociatedLink\",\n"
                + "    \"id\" : \"https://www.example.com/GclXAVyGmqRrJ\",\n"
                + "    \"name\" : \"XS2oxVRgVr\",\n"
                + "    \"description\" : \"xRPrvEVJRdwxZ\"\n"
                + "  } ],\n"
                + "  \"modelVersion\" : \"0.19.29\"\n"
                + "}";
    }
}
