package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.exceptions.InvalidIssnException;
import no.unit.nva.model.validator.IssnValidator;

import java.util.Objects;

import static java.util.Objects.nonNull;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Journal implements PublicationContext {
    private String title;
    private Level level;
    private boolean openAccess;
    private boolean peerReviewed;
    private String printIssn;
    private String onlineIssn;

    public Journal() {}

    private Journal(Journal.Builder builder) throws InvalidIssnException {
        super();
        setTitle(builder.title);
        setLevel(builder.level);
        setOpenAccess(builder.openAccess);
        setPeerReviewed(builder.peerReviewed);
        if (nonNull(builder.printIssn)) {
            setPrintIssn(builder.printIssn);
        }
        if (nonNull(builder.onlineIssn)) {
            setOnlineIssn(builder.onlineIssn);
        }
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
    public void setPrintIssn(String printIssn) throws InvalidIssnException {
        boolean isValid = IssnValidator.validate(printIssn);
        if (isValid) {
            this.printIssn = printIssn;
        } else {
            throw new InvalidIssnException(printIssn);
        }
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
    public void setOnlineIssn(String onlineIssn) throws InvalidIssnException {
        boolean isValid = IssnValidator.validate(onlineIssn);
        if (isValid) {
            this.onlineIssn = onlineIssn;
        } else {
            throw new InvalidIssnException(onlineIssn);
        }
    }

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

    public static final class Builder {
        private String title;
        private Level level;
        private boolean openAccess;
        private boolean peerReviewed;
        private String printIssn;
        private String onlineIssn;

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

        public Journal build() throws InvalidIssnException {
            return new Journal(this);
        }
    }

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
                && Objects.equals(getOnlineIssn(), journal.getOnlineIssn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPrintIssn(), getOnlineIssn());
    }
}
