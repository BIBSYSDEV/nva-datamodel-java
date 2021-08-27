package no.unit.nva.model.contexttypes;

import no.unit.nva.model.contexttypes.utils.IssnUtil;
import no.unit.nva.model.exceptions.InvalidIsbnException;
import no.unit.nva.model.exceptions.InvalidIssnException;
import nva.commons.core.JacocoGenerated;

import java.net.URI;
import java.util.List;
import java.util.Objects;

public class Report extends Book implements SerialPublication, BasicContext {

    private String printIssn;
    private String onlineIssn;

    public Report() {
        super();
    }

    private Report(Builder builder) throws InvalidIssnException, InvalidIsbnException {
        super();
        setSeriesTitle(builder.seriesTitle);
        setSeriesNumber(builder.seriesNumber);
        setPublisher(builder.publisher);
        setIsbnList(builder.isbnList);
        setPrintIssn(builder.printIssn);
        setOnlineIssn(builder.onlineIssn);
        setLinkedContext(builder.linkedContext);
    }

    public String getPrintIssn() {
        return printIssn;
    }

    @Override
    public void setPrintIssn(String printIssn) throws InvalidIssnException {
        this.printIssn = IssnUtil.checkIssn(printIssn);
    }

    public String getOnlineIssn() {
        return onlineIssn;
    }

    @Override
    public void setOnlineIssn(String onlineIssn) throws InvalidIssnException {
        this.onlineIssn = IssnUtil.checkIssn(onlineIssn);
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Report)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Report report = (Report) o;
        return Objects.equals(getPrintIssn(), report.getPrintIssn())
                && Objects.equals(getOnlineIssn(), report.getOnlineIssn());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPrintIssn(), getOnlineIssn());
    }

    public static final class Builder {
        private String seriesTitle;
        private String seriesNumber;
        private String publisher;
        private List<String> isbnList;
        private String printIssn;
        private String onlineIssn;
        private URI linkedContext;


        public Builder() {
        }

        public Builder withSeriesTitle(String seriesTitle) {
            this.seriesTitle = seriesTitle;
            return this;
        }

        public Builder withSeriesNumber(String seriesNumber) {
            this.seriesNumber = seriesNumber;
            return this;
        }

        public Builder withPublisher(String publisher) {
            this.publisher = publisher;
            return this;
        }

        public Builder withIsbnList(List<String> isbnList) {
            this.isbnList = isbnList;
            return this;
        }

        public Builder withPrintIssn(String printIssn) {
            this.printIssn = printIssn;
            return this;
        }

        public Builder withOnlineIssn(String onlineIssn) {
            this.onlineIssn = onlineIssn;
            return this;
        }

        public Builder withLinkedContext(URI linkedContext) {
            this.linkedContext = linkedContext;
            return this;
        }

        public Report build() throws InvalidIssnException, InvalidIsbnException {
            return new Report(this);
        }
    }
}
