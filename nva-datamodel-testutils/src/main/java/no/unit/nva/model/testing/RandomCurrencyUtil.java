package no.unit.nva.model.testing;

import java.util.Currency;
import java.util.stream.Collectors;
import no.unit.nva.testutils.RandomDataGenerator;

public final class RandomCurrencyUtil {
    private RandomCurrencyUtil() {
        // no-op
    }

    public static String randomCurrency() {
        var currencies =
            Currency.getAvailableCurrencies().stream().map(Currency::getCurrencyCode).collect(Collectors.toList());

        return RandomDataGenerator.randomElement(currencies);
    }

}
