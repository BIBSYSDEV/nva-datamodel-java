package no.unit.nva.model.instancetypes.chapter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.instancetypes.PeerReviewedPaper;
import no.unit.nva.model.pages.Range;

import static java.util.Objects.isNull;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class ChapterArticle extends PeerReviewedPaper {

    public static final String PAGES_FIELD = "pages";
    public static final String PEER_REVIEWED_FIELD = "peerReviewed";

    public ChapterArticle(Range pages,
                          boolean peerReviewed,
                          boolean originalResearch) {
        super(pages, peerReviewed, originalResearch);
    }

    @JsonCreator
    public static ChapterArticle fromJson(@JsonProperty(PAGES_FIELD) Range pages,
                           @JsonProperty(PEER_REVIEWED_FIELD) boolean peerReviewed,
                           @JsonProperty("originalResearch") boolean originalResearch,
                           @JsonProperty("contentType") ChapterArticleContentType contentType) {
        if (ChapterArticleContentType.ACADEMIC_CHAPTER.equals(contentType)) {
            return new AcademicChapter(pages, peerReviewed);
        } else if (ChapterArticleContentType.ENCYCLOPEDIA_CHAPTER.equals(contentType)) {
            return new EncyclopediaChapter(pages, peerReviewed);
        } else if (ChapterArticleContentType.EXHIBITION_CATALOG_CHAPTER.equals(contentType)) {
            return new ExhibitionCatalogChapter(pages, peerReviewed);
        } else if (ChapterArticleContentType.INTRODUCTION.equals(contentType)) {
            return new Introduction(pages, peerReviewed);
        } else if (ChapterArticleContentType.NON_FICTION_CHAPTER.equals(contentType)) {
            return new NonFictionChapter(pages, peerReviewed);
        } else if (ChapterArticleContentType.POPULAR_SCIENCE_CHAPTER.equals(contentType)) {
            return new PopularScienceChapter(pages, peerReviewed);
        } else if (ChapterArticleContentType.TEXTBOOK_CHAPTER.equals(contentType)) {
            return new TextbookChapter(pages, peerReviewed);
        } else if (isNull(contentType)) {
            return new AcademicChapter(pages, peerReviewed);
        } else {
            throw new UnsupportedOperationException("The Chapter article subtype is unknown");
        }
    }

}
