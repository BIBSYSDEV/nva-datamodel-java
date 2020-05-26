package no.unit.nva.model.util;

public class ReportContentUtil {

    /**
     * Creates a JSON string that matches subclasses of ReportContent.
     *
     * @param type          The type of subclass of Report.
     * @param begin         The begin page number.
     * @param end           The end page number.
     * @param peerReviewed  The peer-review boolean. Can be set to true for testing purposes.
     * @return A formated JSON string.
     */
    public static String generateJsonString(String type,
                                            String begin,
                                            String end,
                                            boolean peerReviewed) {
        String template = "{\n"
                + "  \"type\": \"%s\",\n"
                + "  \"pages\": {\n"
                + "    \"type\": \"Range\",\n"
                + "    \"begin\": \"%s\",\n"
                + "    \"end\": \"%s\"\n"
                + "  },\n"
                + "  \"peerReviewed\": %s\n"
                + "}";
        return String.format(template, type, begin, end, peerReviewed);
    }
}
