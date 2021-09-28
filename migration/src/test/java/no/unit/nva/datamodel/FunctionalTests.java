package no.unit.nva.datamodel;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import org.junit.jupiter.api.Test;

public class FunctionalTests {

    @Test
    public void testsRun() {
        assertThat(1, is(equalTo(1)));
    }
}
