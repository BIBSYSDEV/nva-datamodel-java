package no.unit.nva.model.util;

public class JournalNonPeerReviewedContentUtil {

    /**
     * Creates a JSON string that matches subclasses of JournalNonPeerReviewedContent.
     *
     * @param type          The type of subclass of JournalNonPeerReviewedContent.
     * @param volume        Volume of the content.
     * @param issue         Issue of the content.
     * @param articleNumber Article number of the content.
     * @param begin         The begin page number.
     * @param end           The end page number.
     * @param peerReviewed  The peer-review boolean. Can be set to true for testing purposes.
     * @return A formated JSON string.
     */
    public static String generateJsonString(String type,
                                            String volume,
                                            String issue,
                                            String articleNumber,
                                            String begin,
                                            String end,
                                            boolean peerReviewed) {
        String template = "{\n"
                + "  \"type\": \"%s\",\n"
                + "  \"volume\": \"%s\",\n"
                + "  \"issue\": \"%s\",\n"
                + "  \"articleNumber\": \"%s\",\n"
                + "  \"pages\": {\n"
                + "    \"type\": \"Range\",\n"
                + "    \"begin\": \"%s\",\n"
                + "    \"end\": \"%s\"\n"
                + "  },\n"
                + "  \"peerReviewed\": %s\n"
                + "}";
        return String.format(template, type, volume, issue, articleNumber, begin, end, peerReviewed);
    }
}
