package no.unit.nva.model.contexttypes.utils;

import no.unit.nva.model.contexttypes.BookSeries;
import no.unit.nva.model.contexttypes.UnconfirmedSeries;
import no.unit.nva.model.exceptions.InvalidUnconfirmedSeriesException;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class BookSeriesUtil {

    public static BookSeries extractSeriesInformation(BookSeries series, String unconfirmedSeriesTitle)
            throws InvalidUnconfirmedSeriesException {

        if (nonNull(series) && series.isConfirmed()) {
            return series;
        }

        validateUnconfirmedSeries(series, unconfirmedSeriesTitle);

        if (nonNull(unconfirmedSeriesTitle) && isNull(series)) {
            return new UnconfirmedSeries(unconfirmedSeriesTitle);
        } else {
            return series;
        }
    }

    private static void validateUnconfirmedSeries(BookSeries series, String unconfirmedSeriesTitle)
            throws InvalidUnconfirmedSeriesException {
        if (hasSeriesStringAndSeriesObject(series, unconfirmedSeriesTitle)
                && hasUnmatchedSeriesStringValues(series, unconfirmedSeriesTitle)) {
            throw new InvalidUnconfirmedSeriesException();
        }
    }

    private static boolean hasUnmatchedSeriesStringValues(BookSeries series, String unconfirmedSeriesTitle) {
        return !series.isConfirmed()
                && !((UnconfirmedSeries) series).getTitle().equals(unconfirmedSeriesTitle);
    }

    private static boolean hasSeriesStringAndSeriesObject(BookSeries series, String unconfirmedSeriesTitle) {
        return nonNull(series) && nonNull(unconfirmedSeriesTitle);
    }
}
