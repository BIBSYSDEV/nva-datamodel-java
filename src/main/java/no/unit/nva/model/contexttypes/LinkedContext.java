package no.unit.nva.model.contexttypes;

import java.net.URI;

/**
 * LinkedContexts are contexts that are expressed as a URI, allowing an existing (external) Publication
 * to be used as a PublicationContext. For example, a BookAnthology containing a Chapter or a Book as part of a series.
 *
 *
 * <pre>
 * {
 *   "type" : "Book",
 *   "seriesTitle" : "A series title",
 *   "seriesNumber" : "123",
 *   "publisher" : "Full publisher details",
 *   "level" : "LEVEL_2",
 *   "openAccess" : false,
 *   "peerReviewed" : false,
 *   "isbnList" : [ "9780201309515", "9788131700075" ],
 *   "linkedContext" : "http://example.com/context"
 * }
 * </pre>
 */
public interface LinkedContext extends PublicationContext {

    URI getLinkedContext();

    void setLinkedContext(String linkedContext);
}
