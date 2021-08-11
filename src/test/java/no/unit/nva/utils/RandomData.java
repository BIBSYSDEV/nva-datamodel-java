package no.unit.nva.utils;

import com.github.javafaker.Faker;
import java.net.URI;
import java.util.Random;

public class RandomData {

    public static final String EXAMPLE_HOST = "https://www.example.com/";
    public static final String PATH_DELIMITER = "/";
    public static final Random RANDOM = new Random();
    private static final Faker FAKER = Faker.instance();

    public static String randomString() {
        return randomWord() + randomWord();
    }

    public static boolean randomBoolean() {
        return FAKER.bool().bool();
    }

    public static URI randomURI() {
        return URI.create(EXAMPLE_HOST + randomWord() + PATH_DELIMITER + randomWord());
    }

    public static <T> T randomElement(T[] values) {
        return values[RANDOM.nextInt(values.length)];
    }

    private static String randomWord() {
        return FAKER.lorem().word();
    }
}
