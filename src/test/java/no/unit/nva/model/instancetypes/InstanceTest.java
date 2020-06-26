package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.unit.nva.model.ModelTest;
import no.unit.nva.model.exceptions.InvalidPageRangeException;
import nva.commons.utils.JsonUtils;

import java.util.LinkedHashMap;

import static java.util.Objects.nonNull;

public class InstanceTest extends ModelTest {

    public static final String CHAPTER_ARTICLE = "ChapterArticle";
    protected final ObjectMapper objectMapper = JsonUtils.objectMapper;

    protected String generateMonographJsonString(String type, MonographTestData testData) throws
            JsonProcessingException {
        String begin = testData.getPages().getIntroduction().getBegin();
        String end = testData.getPages().getIntroduction().getEnd();
        String pages = testData.getPages().getPages();
        boolean illustrated = testData.getPages().isIllustrated();
        return generateJsonString(type, null, null, null, begin, end,
                pages, illustrated, testData.isPeerReviewed());
    }

    protected String generateMonographJsonString(String type,
                                                 String introductionBegin,
                                                 String introductionEnd,
                                                 String pages,
                                                 boolean illustrated) throws JsonProcessingException {
        return generateJsonString(type, null, null, null, introductionBegin,
                introductionEnd, pages, illustrated, false);

    }

    protected String generateMonographJsonString(String type,
                                                 String introductionBegin,
                                                 String introductionEnd,
                                                 String pages,
                                                 boolean illustrated,
                                                 boolean peerReviewed) throws JsonProcessingException {
        return generateJsonString(type, null, null, null, introductionBegin,
                introductionEnd, pages, illustrated, peerReviewed);

    }

    protected JsonNode generateMonographJson(String type,
                                             String introductionBegin,
                                             String introductionEnd,
                                             String pages,
                                             boolean illustrated,
                                             boolean peerReviewed) {
        LinkedHashMap<String, Object> instance = generateMapRepresentation(type, null, null, null, introductionBegin,
                introductionEnd, pages, illustrated, peerReviewed);
        return objectMapper.valueToTree(instance);
    }

    protected String generateArticleJsonString(String type, JournalTestData testData) throws JsonProcessingException {
        return generateJsonString(type,
                testData.getVolume(),
                testData.getIssue(),
                testData.getArticleNumber(),
                testData.getPages().getBegin(),
                testData.getPages().getEnd(),
                null,
                false,
                testData.isPeerReviewed()
        );
    }

    protected String generateJsonString(String type,
                                        String volume,
                                        String issue,
                                        String articleNumber,
                                        String introductionBegin,
                                        String introductionEnd,
                                        String pages,
                                        boolean illustrated,
                                        boolean peerReviewed) throws JsonProcessingException {
        LinkedHashMap<String, Object> instance = generateMapRepresentation(type,
                volume,
                issue,
                articleNumber,
                introductionBegin,
                introductionEnd,
                pages,
                illustrated,
                peerReviewed);
        return objectMapper.writeValueAsString(instance);
    }

    protected String generateChapterArticleJsonString(String begin, String end, boolean peerReviewed) throws
            JsonProcessingException {
        return generateJsonString(CHAPTER_ARTICLE,
                null,
                null,
                null,
                begin,
                end,
                null,
                false,
                peerReviewed);
    }

    protected JsonNode generateChapterArticleJson(String begin, String end, boolean peerReviewed) {
        LinkedHashMap<String, Object> instance = generateMapRepresentation(CHAPTER_ARTICLE,
                null,
                null,
                null,
                begin,
                end,
                null,
                false,
                peerReviewed);
        return objectMapper.valueToTree(instance);
    }

    protected LinkedHashMap<String, Object> generateMapRepresentation(String type,
                                                                      String volume,
                                                                      String issue,
                                                                      String articleNumber,
                                                                      String introductionBegin,
                                                                      String introductionEnd,
                                                                      String pages,
                                                                      boolean illustrated,
                                                                      boolean peerReviewed) {
        LinkedHashMap<String, Object> instance = new LinkedHashMap<>();
        instance.put("type", type);

        switch (type) {
            case "JournalArticle":
            case "JournalLeader":
            case "JournalLetter":
            case "JournalReview":
            case "JournalShortCommunication":
                instance.put("volume", volume);
                instance.put("issue", issue);
                instance.put("articleNumber", articleNumber);
                instance.put("pages", getRangeMap(introductionBegin, introductionEnd));
                instance.put("peerReviewed", peerReviewed);
                break;
            case "ChapterArticle":
                instance.put("pages", getRangeMap(introductionBegin, introductionEnd));
                instance.put("peerReviewed", peerReviewed);
                break;
            default:
                instance.put("pages", getMonographPagesMap(introductionBegin, introductionEnd, pages, illustrated));
                instance.put("peerReviewed", peerReviewed);
        }
        return instance;
    }

    protected String generateArticleWithPeerReview(String type) throws JsonProcessingException,
            InvalidPageRangeException {
        JournalTestData testData = new JournalTestData(true);
        return generateArticleJsonString(type, testData);
    }

    private LinkedHashMap<String, Object> getMonographPagesMap(String begin,
                                                               String end,
                                                               String pages,
                                                               boolean illustrated) {
        LinkedHashMap<String, Object> introduction = getRangeMap(begin, end);
        LinkedHashMap<String, Object> monographPagesMap = new LinkedHashMap<>();
        monographPagesMap.put("type", "MonographPages");
        if (!introduction.isEmpty()) {
            monographPagesMap.put("introduction", introduction);
        }
        monographPagesMap.put("pages", pages);
        monographPagesMap.put("illustrated", illustrated);
        return monographPagesMap;
    }

    private LinkedHashMap<String, Object> getRangeMap(String begin, String end) {
        LinkedHashMap<String, Object> introduction = new LinkedHashMap<>();
        if (nonNull(begin) && nonNull(end)) {
            introduction.put("type", "Range");
            introduction.put("begin", begin);
            introduction.put("end", end);
        }
        return introduction;
    }
}
