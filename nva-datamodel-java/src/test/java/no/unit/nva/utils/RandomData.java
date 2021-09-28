package no.unit.nva.utils;

import com.github.javafaker.Faker;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomData {

    public static final String EXAMPLE_HOST = "https://www.example.com/";
    public static final String PATH_DELIMITER = "/";
    public static final Random RANDOM = new Random();
    public static final int FIRST_MULTIPLYING_FACTOR_FOR_ISBN_CALC = 1;
    public static final int SECOND_MULTIPLYING_FACTOR_FOR_ISBN_CALC = 3;
    private static final Faker FAKER = Faker.instance();

    public static String randomString() {
        return randomWord() + randomWord();
    }

    public static boolean randomBoolean() {
        return FAKER.bool().bool();
    }

    public static URI randomUri() {
        return URI.create(EXAMPLE_HOST + randomWord() + PATH_DELIMITER + randomWord());
    }

    public static <T> T randomElement(T[] values) {
        return values[RANDOM.nextInt(values.length)];
    }

    public static int randomInt(int bound) {
        return RANDOM.nextInt(bound);
    }

    public static String randomIsbn() {
        List<Integer> isbnDigits = new ArrayList<>();
        List<Integer> firstThreeDigits = List.of(9, 7, 8);
        List<Integer> rest9Digits = randomDigits();
        isbnDigits.addAll(firstThreeDigits);
        isbnDigits.addAll(rest9Digits);

        int lastDigit = calculateCheckDigit(isbnDigits);
        if (isbnCannotBeValid(lastDigit)) {
            return randomIsbn();
        }
        isbnDigits.add(lastDigit);
        return isbnDigits.stream().map(Object::toString).collect(Collectors.joining());
    }

    private static boolean isbnCannotBeValid(int lastDigit) {
        return lastDigit == 10;
    }

    private static String randomWord() {
        return FAKER.lorem().word();
    }

    private static int calculateCheckDigit(List<Integer> isbnDigits) {
        return 10 - calculateIsbnSum(isbnDigits) % 10;
    }

    private static List<Integer> randomDigits() {
        return IntStream.range(0, 9).boxed()
            .map(ignored -> randomIsbnDigit())
            .collect(Collectors.toList());
    }

    private static int calculateIsbnSum(List<Integer> firstDigits) {
        int sum = 0;
        for (int index = 0; index < firstDigits.size(); index++) {
            if (index % 2 == 0) {
                sum += FIRST_MULTIPLYING_FACTOR_FOR_ISBN_CALC * firstDigits.get(index);
            } else {
                sum += SECOND_MULTIPLYING_FACTOR_FOR_ISBN_CALC * firstDigits.get(index);
            }
        }
        return sum;
    }

    private static int randomIsbnDigit() {
        return RandomData.randomInt(10);
    }
}
