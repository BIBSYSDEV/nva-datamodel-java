package no.unit.nva.model.util;

public class JournalNonPeerReviewedContentUtil {

    public static String generateJsonString(String type,
                                            String volume,
                                            String issue,
                                            String articleNumber,
                                            String begin,
                                            String end,
                                            boolean peerReviewed) {
        String template = "{\n" +
                "  \"type\": \"%s\",\n" +
                "  \"volume\": \"%s\",\n" +
                "  \"issue\": \"%s\",\n" +
                "  \"articleNumber\": \"%s\",\n" +
                "  \"pages\": {\n" +
                "    \"type\": \"Range\",\n" +
                "    \"begin\": \"%s\",\n" +
                "    \"end\": \"%s\"\n" +
                "  },\n" +
                "  \"peerReviewed\": %s\n" +
                "}";
        return String.format(template, type, volume, issue, articleNumber, begin, end, peerReviewed);
    }
}
