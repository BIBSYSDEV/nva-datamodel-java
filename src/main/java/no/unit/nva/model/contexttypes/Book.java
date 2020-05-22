package no.unit.nva.model.contexttypes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.Level;
import nva.commons.utils.JacocoGenerated;

import java.util.List;
import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Book implements PublicationContext {

    private String title;
    private Level level;
    private boolean openAccess;
    private boolean peerReviewed;
    @JsonProperty("isbn")
    private List<String> isbns;

    /**
     * Constructor for Book objects.
     * @param title The title of the book.
     * @param level The NPI level of the publisher.
     * @param openAccess The open access status of the document.
     * @param peerReviewed The peer-review status of the document.
     * @param isbns The list of ISBNs of the document.
     */
    public Book(@JsonProperty("title") String title,
                @JsonProperty("level") Level level,
                @JsonProperty("openAccess") boolean openAccess,
                @JsonProperty("peerReviewed") boolean peerReviewed,
                @JsonProperty("isbn") List<String> isbns) {
        this.title = title;
        this.level = level;
        this.openAccess = openAccess;
        this.peerReviewed = peerReviewed;
        this.isbns = isbns;
    }

    @JsonCreator

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Level getLevel() {
        return level;
    }

    @Override
    public void setLevel(Level level) {
        this.level = level;
    }

    @Override
    public boolean isOpenAccess() {
        return openAccess;
    }

    @Override
    public void setOpenAccess(boolean openAccess) {
        this.openAccess = openAccess;
    }

    @Override
    public boolean isPeerReviewed() {
        return peerReviewed;
    }

    @Override
    public void setPeerReviewed(boolean peerReviewed) {
        this.peerReviewed = peerReviewed;
    }

    public List<String> getIsbns() {
        return isbns;
    }

    public void setIsbns(List<String> isbns) {
        this.isbns = isbns;
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Book)) {
            return false;
        }
        Book book = (Book) o;
        return isOpenAccess() == book.isOpenAccess()
                && isPeerReviewed() == book.isPeerReviewed()
                && Objects.equals(getTitle(), book.getTitle())
                && getLevel() == book.getLevel()
                && Objects.equals(getIsbns(), book.getIsbns());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getLevel(), isOpenAccess(), isPeerReviewed(), getIsbns());
    }
}
