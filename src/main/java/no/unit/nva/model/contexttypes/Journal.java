package no.unit.nva.model.contexttypes;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.net.URI;
import java.net.URL;
import no.unit.nva.model.Level;
import no.unit.nva.model.contexttypes.utils.IssnUtil;
import no.unit.nva.model.exceptions.InvalidIssnException;
import nva.commons.core.JacocoGenerated;

import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Journal implements BasicContext, SerialPublication, LinkedContext {
    private String title;
    private Level level;
    private boolean openAccess;
    private boolean peerReviewed;
    private String printIssn;
    private String onlineIssn;
    private URL url;
    private URI linkedContext;

    public Journal() {
    }

    private Journal(Journal.Builder builder) throws InvalidIssnException {
        super();
        setTitle(builder.title);
        setLevel(builder.level);
        setOpenAccess(builder.openAccess);
        setPeerReviewed(builder.peerReviewed);
        setPrintIssn(builder.printIssn);
        setOnlineIssn(builder.onlineIssn);
        setUrl(builder.url);
        setLinkedContextURI(builder.linkedContext);
    }


    public String getPrintIssn() {
        return printIssn;
    }

    /**
     * Sets the print ISSN for a Journal object.
     *
     * @param printIssn a valid ISSN
     * @throws InvalidIssnException Thrown if the ISSN is invalid
     */
    @JsonSetter
    @Override
    public void setPrintIssn(String printIssn) throws InvalidIssnException {
        this.printIssn = IssnUtil.checkIssn(printIssn);
    }

    public String getOnlineIssn() {
        return onlineIssn;
    }

    /**
     * Sets the online ISSN for a Journal object.
     *
     * @param onlineIssn a valid ISSN
     * @throws InvalidIssnException Thrown if the ISSN is invalid
     */
    @JsonSetter
    @Override
    public void setOnlineIssn(String onlineIssn) throws InvalidIssnException {
        this.onlineIssn = IssnUtil.checkIssn(onlineIssn);
    }

    public String getTitle() {
        return title;
    }

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

    @Override
    public URL getUrl() {
        return url;
    }

    @Override
    public void setUrl(URL url) {
        this.url = url;
    }

    @Override
    public URI getLinkedContext() {
        return linkedContext;
    }

    @Override
    public void setLinkedContext(URI linkedContext) {
        this.linkedContext = linkedContext;
    }

    private void setLinkedContextURI(URI linkedContext) {
        this.linkedContext = linkedContext;
    }

    public static final class Builder {
        private String title;
        private Level level;
        private boolean openAccess;
        private boolean peerReviewed;
        private String printIssn;
        private String onlineIssn;
        private URL url;
        private URI linkedContext;

        public Builder() {
        }

        public Journal.Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Journal.Builder withLevel(Level level) {
            this.level = level;
            return this;
        }

        public Journal.Builder withOpenAccess(boolean openAccess) {
            this.openAccess = openAccess;
            return this;
        }

        public Journal.Builder withPeerReviewed(boolean peerReviewed) {
            this.peerReviewed = peerReviewed;
            return this;
        }

        public Journal.Builder withPrintIssn(String printIssn) {
            this.printIssn = printIssn;
            return this;
        }

        public Journal.Builder withOnlineIssn(String onlineIssn) {
            this.onlineIssn = onlineIssn;
            return this;
        }

        public Journal.Builder withUrl(URL url) {
            this.url = url;
            return this;
        }

        public Journal.Builder withLinkedContext(String linkedContext) {
            this.linkedContext = URI.create(linkedContext);
            return this;
        }


        public Journal build() throws InvalidIssnException {
            return new Journal(this);
        }
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Journal)) {
            return false;
        }
        Journal journal = (Journal) o;
        return Objects.equals(getTitle(), journal.getTitle())
                && Objects.equals(getPrintIssn(), journal.getPrintIssn())
                && Objects.equals(isPeerReviewed(), journal.isPeerReviewed())
                && Objects.equals(isOpenAccess(), journal.isOpenAccess())
                && Objects.equals(getLevel(), journal.getLevel())
                && Objects.equals(getOnlineIssn(), journal.getOnlineIssn())
                && Objects.equals(getUrl(), journal.getUrl())
                && Objects.equals(getLinkedContext(), journal.getLinkedContext());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getPrintIssn(), getOnlineIssn());
    }
}
