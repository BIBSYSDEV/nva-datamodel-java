package no.unit.nva.model.contexttypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import no.unit.nva.model.contexttypes.utils.IssnUtil;
import no.unit.nva.model.exceptions.InvalidIsbnException;
import no.unit.nva.model.exceptions.InvalidIssnException;
import no.unit.nva.model.exceptions.InvalidUnconfirmedSeriesException;
import nva.commons.core.JacocoGenerated;

import java.util.List;
import java.util.Objects;

public class Report extends Book implements SerialPublication, BasicContext {

    private final String printIssn;
    private final String onlineIssn;

    public Report(@JsonProperty("series") BookSeries series,
                  @JsonProperty("seriesTitle") String seriesTitle,
                  @JsonProperty("seriesNumber") String seriesNumber,
                  @JsonProperty("publisher") String publisher,
                  @JsonProperty("isbnList") List<String> isbnList,
                  @JsonProperty("printIssn") String printIssn,
                  @JsonProperty("onlineIssn") String onlineIssn)
            throws InvalidIsbnException, InvalidIssnException, InvalidUnconfirmedSeriesException {
        super(series, seriesTitle, seriesNumber, publisher, isbnList);
        this.printIssn = IssnUtil.checkIssn(printIssn);
        this.onlineIssn = IssnUtil.checkIssn(onlineIssn);
    }

    private Report(Builder builder) throws InvalidIssnException, InvalidIsbnException,
            InvalidUnconfirmedSeriesException {
        this(builder.series, null, builder.seriesNumber, builder.publisher, builder.isbnList, builder.printIssn,
                builder.onlineIssn);
    }

    @Override
    public String getPrintIssn() {
        return printIssn;
    }

    @Override
    public String getOnlineIssn() {
        return onlineIssn;
    }

    public static final class Builder {
        private BookSeries series;
        private String seriesNumber;
        private String publisher;
        private List<String> isbnList;
        private String printIssn;
        private String onlineIssn;

        public Builder() {
        }

        public Builder withSeries(BookSeries series) {
            this.series = series;
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

        public Report build() throws InvalidIssnException, InvalidIsbnException, InvalidUnconfirmedSeriesException {
            return new Report(this);
        }
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
        Report report = (Report) o;
        return Objects.equals(getPrintIssn(), report.getPrintIssn())
                && Objects.equals(getOnlineIssn(), report.getOnlineIssn());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getPrintIssn(), getOnlineIssn());
    }
}
