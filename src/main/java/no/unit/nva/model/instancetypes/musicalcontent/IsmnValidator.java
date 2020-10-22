package no.unit.nva.model.instancetypes.musicalcontent;

import no.unit.nva.model.instancetypes.musicalcontent.exception.InvalidIsmnException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static no.unit.nva.model.instancetypes.musicalcontent.MusicNotation.ISMN_13_PREFIX;

public class IsmnValidator {

    public static final int ISMN_10_NUMERIC_LENGTH = 9;
    public static final int EVEN_MULTIPLIER = 1;
    public static final int ODD_MULTIPLIER = 3;

    public static final String CHECKBIT_ERROR_MESSAGE_TEMPLATE = "The checkbit %s should be %s in %s";
    public static final int ISMN_10_PREFIX_ASCII = 77;
    public static final int ISMN_10_PREFIX_AS_INT = 22;
    public static final Set<Character> validDelimiters = Set.of(' ', '-');
    public static final List<Integer> PREFIX_INTS = List.of(9, 7, 9, 0);
    public static final int ISMN_13_PREFIX_SIZE = 4;
    public static final String INVALID_ISMN_TEMPLATE = "The ISMN %s is invalid";
    public static final int CHECK_BIT_OFFSET = 1;

    protected void validate(String candidate) throws InvalidIsmnException {
        List<Integer> prefix = new ArrayList<>();
        List<Integer> body = new ArrayList<>();
        int checkBit = -1;

        for (int counter = 0; counter < candidate.length(); counter++) {
            char current = candidate.charAt(counter);

            if (validDelimiters.contains(current)) {
                continue;
            }

            if (isIsmn10Prefix(current, counter)) {
                prefix.add(Character.getNumericValue(current));
            } else if (isIsmn13Prefix(prefix, current)) {
                prefix.add(Character.getNumericValue(current));
            } else if (isCheckBit(candidate, counter)) {
                checkBit = Character.getNumericValue(current);
            } else if (Character.isDigit(current)) {
                body.add(Character.getNumericValue(current));
            } else {
                throw new InvalidIsmnException(String.format(INVALID_ISMN_TEMPLATE, candidate));
            }
        }

        validatePrefix(candidate, prefix, body);
        validateCheckBit(candidate, body, checkBit);
    }

    private void validatePrefix(String candidate, List<Integer> prefix, List<Integer> body) throws
            InvalidIsmnException {
        if (isStructurallyInvalid(prefix, body)) {
            throw new InvalidIsmnException(String.format(INVALID_ISMN_TEMPLATE, candidate));
        }
    }

    private boolean isStructurallyInvalid(List<Integer> prefix, List<Integer> body) {
        return isInvalidPrefix(prefix) || body.size() + CHECK_BIT_OFFSET != ISMN_10_NUMERIC_LENGTH;
    }

    private boolean isCheckBit(String candidate, int counter) {
        return counter == candidate.length() - 1;
    }

    private boolean isIsmn13Prefix(List<Integer> prefix, char current) {
        return !prefix.contains(ISMN_10_PREFIX_AS_INT)
                && prefix.size() < ISMN_13_PREFIX_SIZE && Character.isDigit(current);
    }

    private boolean isIsmn10Prefix(char current, int counter) {
        return counter == 0 && (int) current == ISMN_10_PREFIX_ASCII;
    }

    private void validateCheckBit(String candidate, List<Integer> body, int checkBit) throws InvalidIsmnException {
        List<Integer> testData = Stream.concat(PREFIX_INTS.stream(), body.stream()).collect(Collectors.toList());

        int calculated = IntStream.range(0, testData.size())
                .map(i -> isEven(i) ? EVEN_MULTIPLIER * testData.get(i) : ODD_MULTIPLIER * testData.get(i))
                .reduce(Integer::sum)
                .orElseThrow();

        int calculatedCheckBit = calculateModuloTenDifference(calculated);
        if (calculatedCheckBit != checkBit) {
            throw new InvalidIsmnException(String.format(CHECKBIT_ERROR_MESSAGE_TEMPLATE,
                    checkBit, calculatedCheckBit, candidate));
        }
    }

    private boolean isInvalidPrefix(List<Integer> prefix) {
        return !(prefix.size() == 1 && prefix.get(0) == ISMN_10_PREFIX_AS_INT)
                && !(prefix.size() == 4 && toPrefixString(prefix).equals(ISMN_13_PREFIX));
    }

    private String toPrefixString(List<Integer> prefix) {
        return prefix.stream().map(String::valueOf).collect(Collectors.joining());
    }

    private int calculateModuloTenDifference(int sum) {
        var modulo = sum % 10;
        return modulo != 0 ? 10 - modulo : 0;
    }

    private boolean isEven(int counter) {
        return counter % 2 == 0;
    }
}
