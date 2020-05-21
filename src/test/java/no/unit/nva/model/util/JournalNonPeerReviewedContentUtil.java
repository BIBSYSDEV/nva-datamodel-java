package no.unit.nva.model.util;

import nva.commons.utils.IoUtils;

import java.nio.file.Path;

public class JournalNonPeerReviewedContentUtil {
    public static final String NON_PEER_REVIEWED_TEMPLATE = "journal_content_non_peer_reviewed.json";

    public static String generateJsonString(String type,
                                            String volume,
                                            String issue,
                                            String articleNumber,
                                            String begin,
                                            String end,
                                            boolean peerReviewed) {
        String template = IoUtils.stringFromResources(Path.of(NON_PEER_REVIEWED_TEMPLATE));
        return String.format(template, type, volume, issue, articleNumber, begin, end, peerReviewed);
    }
}
