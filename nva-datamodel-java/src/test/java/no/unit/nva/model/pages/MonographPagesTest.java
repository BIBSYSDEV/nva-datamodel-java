package no.unit.nva.model.pages;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class MonographPagesTest {

    @DisplayName("MonographPages exists")
    @Test
    void monographPagesExists() {
        new MonographPages();
    }

    @DisplayName("MonoographPages accepts null for introduction")
    @ParameterizedTest
    @NullSource
    void monographPagesDoesNotThrowExceptionMonographPagesWhenIntroductionIsNull(Range introduction) {
        assertDoesNotThrow(
            () -> {
                new MonographPages.Builder()
                        .withIllustrated(true)
                        .withIntroduction(introduction)
                        .withPages("333")
                        .build();
            }
        );
    }
}