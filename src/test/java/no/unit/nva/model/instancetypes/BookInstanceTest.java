package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.databind.ObjectMapper;
import no.unit.nva.model.pages.MonographPages;
import no.unit.nva.model.pages.Pages;
import no.unit.nva.model.pages.Range;
import nva.commons.utils.JsonUtils;

public class BookInstanceTest {
    protected final ObjectMapper objectMapper = JsonUtils.objectMapper;

    protected String generateBookInstanceJson(String type,
                                              String introductionBegin,
                                              String introductionEnd,
                                              String pages,
                                              boolean illustrated,
                                              boolean peerReviewed,
                                              boolean openAccess) {
        return "{\n"
                + "  \"type\" : \"" + type + "\",\n"
                + "  \"pages\" : {\n"
                + "    \"type\" : \"MonographPages\",\n"
                + "    \"introduction\" : {\n"
                + "      \"type\" : \"Range\",\n"
                + "      \"begin\" : \"" + introductionBegin + "\",\n"
                + "      \"end\" : \"" + introductionEnd + "\"\n"
                + "    },\n"
                + "    \"pages\" : \"" + pages + "\",\n"
                + "    \"illustrated\" : " + illustrated + "\n"
                + "  },\n"
                + "  \"peerReviewed\" : " + peerReviewed + ",\n"
                + "  \"openAccess\" : " + openAccess + "\n"
                + "}";
    }

    protected Pages generateMonographPages(String pages,
                                           boolean illustrated,
                                           String introductionBegin,
                                           String introductionEnd) {
        return new MonographPages.Builder()
                .withPages(pages)
                .withIllustrated(illustrated)
                .withIntroduction(generateRange(introductionBegin, introductionEnd))
                .build();
    }

    private Range generateRange(String begin, String end) {
        return new Range.Builder()
                .withBegin(begin)
                .withEnd(end)
                .build();
    }
}
