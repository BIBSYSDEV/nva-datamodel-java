package no.unit.nva.model.contexttypes.utils;

import no.unit.nva.model.exceptions.InvalidIssnException;
import no.unit.nva.model.validator.IssnValidator;

import static java.util.Objects.isNull;

public class IssnUtil {
    /**
     * Returns a valid ISSN or null.
     *
     * @param issn a valid ISSN
     * @throws InvalidIssnException Thrown if the ISSN is invalid
     */
    @SuppressWarnings("PMD.NullAssignment")
    public static String checkIssn(String issn) throws InvalidIssnException {
        if (isNull(issn) || issn.isEmpty()) {
            return null;
        }
        boolean isValid = IssnValidator.validate(issn);
        if (isValid) {
            return issn;
        } else {
            throw new InvalidIssnException(issn);
        }
    }
}
