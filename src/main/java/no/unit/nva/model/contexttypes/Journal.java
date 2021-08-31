package no.unit.nva.model.contexttypes;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.contexttypes.utils.IssnUtil;
import no.unit.nva.model.exceptions.InvalidIssnException;
import nva.commons.core.JacocoGenerated;

import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Journal implements BasicContext, SerialPublication {
    private String title;
    private String printIssn;
    private String onlineIssn;

    public Journal() {
    }

    private Journal(Journal.Builder builder) throws InvalidIssnException {
        super();
        setTitle(builder.title);
        setPrintIssn(builder.printIssn);
        setOnlineIssn(builder.onlineIssn);
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

    public static final class Builder {
        private String title;
        private String printIssn;
        private String onlineIssn;

        public Builder() {
        }

        public Journal.Builder withTitle(String title) {
            this.title = title;
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
                && Objects.equals(getOnlineIssn(), journal.getOnlineIssn());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getPrintIssn(), getOnlineIssn());
    }
}
