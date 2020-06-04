package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.unit.nva.model.exceptions.InvalidPageRangeException;
import no.unit.nva.model.pages.MonographPages;
import no.unit.nva.model.pages.Pages;
import no.unit.nva.model.pages.Range;
import nva.commons.utils.JsonUtils;

import java.util.LinkedHashMap;

import static java.util.Objects.nonNull;

public class BookInstanceTest {

    protected static final String BEGINNING_PAGE = "1";
    protected static final String ENDING_PAGE = "22";

    protected final ObjectMapper objectMapper = JsonUtils.objectMapper;

    protected String generateBookInstanceJson(String type,
                                              String introductionBegin,
                                              String introductionEnd,
                                              String pages,
                                              boolean illustrated,
                                              boolean peerReviewed,
                                              boolean openAccess) throws JsonProcessingException {

        LinkedHashMap<String, String> introduction = new LinkedHashMap<>();
        if (nonNull(introductionBegin) && nonNull(introductionEnd)) {
            introduction.put("type", "Range");
            introduction.put("begin", introductionBegin);
            introduction.put("end", introductionEnd);
        }

        LinkedHashMap<String, Object> monographPages = new LinkedHashMap<>();
        monographPages.put("type", "MonographPages");
        if (!introduction.isEmpty()) {
            monographPages.put("introduction", introduction);
        }
        monographPages.put("pages", pages);
        monographPages.put("illustrated", illustrated);

        LinkedHashMap<String, Object> bookInstance = new LinkedHashMap<>();
        bookInstance.put("type", type);
        bookInstance.put("pages", monographPages);
        bookInstance.put("peerReviewed", peerReviewed);
        bookInstance.put("openAccess", openAccess);

        return objectMapper.writeValueAsString(bookInstance);
    }

    protected Pages generateMonographPages(String pages,
                                           boolean illustrated,
                                           String introductionBegin,
                                           String introductionEnd) throws InvalidPageRangeException {
        return new MonographPages.Builder()
                .withPages(pages)
                .withIllustrated(illustrated)
                .withIntroduction(generateRange(introductionBegin, introductionEnd))
                .build();
    }

    protected Range generateRange() throws InvalidPageRangeException {
        return generateRange(BEGINNING_PAGE, ENDING_PAGE);
    }

    protected Range generateRange(String begin, String end) throws InvalidPageRangeException {
        return new Range.Builder()
                .withBegin(begin)
                .withEnd(end)
                .build();
    }
}
