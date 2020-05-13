package no.unit.nva.model.validator;

import static java.util.Objects.isNull;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;


public class IssnValidator {

    public static final int ISSN_STANDARD_LENGTH = 9;
    public static final int ISSN_HYPHEN_POSITION = 4;
    public static final char HYPHEN = '-';
    public static final int MATCH_TEN = 10;
    public static final List<Character> CHARACTER_LIST =
            List.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');

    /**
     * Validator for ISSNs, checks the structure and modulo 11 checkbit.
     * The validator does not check for empty or null values.
     *
     * @param issn An ISSN for validation
     * @return Boolean for the result of the validation
     */
    public static boolean validate(String issn) {
        if (isNull(issn)) {
            return false;
        }
        
        String value = issn.toUpperCase(Locale.ENGLISH);

        if (value.length() != ISSN_STANDARD_LENGTH) {
            return false;
        }

        List<Character> characters = new ArrayList<>(asCharacterList(value));

        if (hasIllegalHyphenation(characters)) {
            return false;
        }

        characters.remove(ISSN_HYPHEN_POSITION);

        int lastElement = characters.size() - 1;
        char checkbit = characters.get(lastElement);
        characters.remove(lastElement);

        List<Integer> integers = characters.stream()
                .filter(CHARACTER_LIST::contains)
                .map(IssnValidator::asInteger)
                .collect(Collectors.toList());

        return integers.size() == characters.size() && validateCheckbit(integers, checkbit);
    }

    private static int asInteger(Character character) {
        return Integer.parseInt(String.valueOf(character));
    }

    private static boolean hasIllegalHyphenation(List<Character> characters) {
        return characters.get(ISSN_HYPHEN_POSITION) != HYPHEN;
    }

    private static boolean validateCheckbit(List<Integer> integers, char character) {
        return generateCheckbit(integers) == character;
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

    private static List<Character> asCharacterList(String string) {
        return new AbstractList<>() {
            @Override
            public Character get(int i) {
                return string.charAt(i);
            }

            @Override
            public int size() {
                return string.length();
            }
        };
    }
}
