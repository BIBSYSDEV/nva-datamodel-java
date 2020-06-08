package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.unit.nva.model.exceptions.InvalidPageRangeException;
import no.unit.nva.model.pages.MonographPages;
import no.unit.nva.model.pages.Range;
import nva.commons.utils.JsonUtils;

import java.util.LinkedHashMap;

import static java.util.Objects.nonNull;

public class InstanceTest {

    protected final ObjectMapper objectMapper = JsonUtils.objectMapper;

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

    protected String generateArticleJsonString(String type,
                                        String volume,
                                        String issue,
                                        String articleNumber,
                                        String begin,
                                        String end,
                                        boolean peerReviewed) throws JsonProcessingException {
        return generateJsonString(type, volume, issue, articleNumber, begin, end, null, false, peerReviewed);
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
            case "Report":
            case "ReportPolicy":
            case "ReportResearch":
            case "ReportWorkingPaper":
                instance.put("pages", getMonographPagesMap(introductionBegin, introductionEnd, pages, illustrated));
                instance.put("peerReviewed", false);
                break;
            default:
                instance.put("pages", getMonographPagesMap(introductionBegin, introductionEnd, pages, illustrated));
                instance.put("peerReviewed", peerReviewed);
        }


        return objectMapper.writeValueAsString(instance);
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

    protected MonographPages generateMonographPages(String introductionBegin,
                                                    String introductionEnd,
                                                    String pages,
                                                    boolean illustrated) throws InvalidPageRangeException {
        return new MonographPages.Builder()
                .withPages(pages)
                .withIllustrated(illustrated)
                .withIntroduction(generateRange(introductionBegin, introductionEnd))
                .build();
    }

    protected Range generateRange(String begin, String end) throws InvalidPageRangeException {
        return new Range.Builder()
                .withBegin(begin)
                .withEnd(end)
                .build();
    }
}
