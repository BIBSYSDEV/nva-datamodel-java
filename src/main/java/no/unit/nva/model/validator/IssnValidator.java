package no.unit.nva.model.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class IssnValidator {

    public static final int ISSN_STANDARD_LENGTH = 9;
    public static final int ISSN_HYPHEN_POSITION = 4;
    public static final char HYPHEN = '-';
    public static final int MATCH_TEN = 10;

    /**
     * Validator for ISSNs, checks the structure and modulo 11 checkbit.
     * The validator does not check for empty or null values.
     *
     * @param issn An ISSN for validation
     * @return Boolean for the result of the validation
     */
    public static boolean validate(String issn) {

        if (issn.length() != ISSN_STANDARD_LENGTH) {
            return false;
        }

        String value = issn.toUpperCase(Locale.ENGLISH);
        char[] characters = value.toCharArray();

        if (characters[ISSN_HYPHEN_POSITION] != HYPHEN) {
            return false;
        }

        List<Integer> integers = new ArrayList<>();

        for (int counter = 0; counter < characters.length - 1; counter++) {
            int number;
            if (counter != ISSN_HYPHEN_POSITION) {
                try {
                    number = Integer.parseInt(String.valueOf(characters[counter]));
                } catch (NumberFormatException e) {
                    return false;
                }
                integers.add(number);
            }
        }

        return generateCheckbit(integers) == characters[characters.length - 1];
    }

    private static char generateCheckbit(List<Integer> integerList) {
        int sum = 0;
        int positionFromRight = 8;
        for (int number : integerList) {
            sum = sum + (number * positionFromRight);
            positionFromRight = positionFromRight - 1;
        }

        int modulo = 11 - (sum % 11);
        if (modulo == MATCH_TEN) {
            return 'X';
        }

        return Character.forDigit(modulo, 10);
    }
}
