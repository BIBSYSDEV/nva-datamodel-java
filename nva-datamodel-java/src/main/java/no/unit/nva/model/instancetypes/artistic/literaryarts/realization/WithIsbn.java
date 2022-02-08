package no.unit.nva.model.instancetypes.artistic.literaryarts.realization;

import no.unit.nva.model.exceptions.InvalidIsbnException;
import org.apache.commons.validator.routines.ISBNValidator;

import java.util.List;
import java.util.stream.Collectors;

import static no.unit.nva.model.contexttypes.Book.SPACES_AND_HYPHENS_REGEX;

public interface WithIsbn {
    ISBNValidator ISBN_VALIDATOR = new ISBNValidator();
    String EMPTY_STRING = "";

    default List<String> validateIsbn(List<String> isbns) throws InvalidIsbnException {
        var validated = isbns.stream()
                .map(isbn -> isbn.replaceAll(SPACES_AND_HYPHENS_REGEX, EMPTY_STRING))
                .map(ISBN_VALIDATOR::validate)
                .collect(Collectors.toList());
        if (!isbns.isEmpty()) {
            return validated;
        }
        throw new InvalidIsbnException(isbns);
    }

    List<String> getIsbns();
}
