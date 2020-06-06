package no.unit.nva.model.instancetypes;

public class ReportTestBase {

    /**
     * Creates a JSON string that matches subclasses of ReportContent.
     *
     * @param type              The type of subclass of Report.
     * @param pages             Pages.
     * @param introductionBegin The introduction begin page number.
     * @param introductionEnd   The introduction end page number.
     * @param illustrated       Illustrated.
     * @param peerReviewed      The peer-review boolean. Can be set to true for testing purposes.
     * @return A formated JSON string.
     */
    public String generateJsonString(String type,
                                            String pages,
                                            String introductionBegin,
                                            String introductionEnd,
                                            boolean illustrated,
                                            boolean peerReviewed) {
        String template = "{\n"
                + "  \"type\": \"%s\",\n"
                + "  \"pages\": {\n"
                + "    \"type\": \"MonographPages\",\n"
                + "    \"pages\": \"%s\",\n"
                + "    \"introduction\": {\n"
                + "      \"type\": \"Range\",\n"
                + "      \"begin\": \"%s\",\n"
                + "      \"end\": \"%s\"\n"
                + "    },\n"
                + "    \"illustrated\": %s\n"
                + "  },\n"
                + "  \"peerReviewed\": %s\n"
                + "}";
        return String.format(template, type, pages, introductionBegin, introductionEnd, illustrated, peerReviewed);
    }
}
