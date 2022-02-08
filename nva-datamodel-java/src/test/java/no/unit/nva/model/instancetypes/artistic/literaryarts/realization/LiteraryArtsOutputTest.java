package no.unit.nva.model.instancetypes.artistic.literaryarts.realization;

import static no.unit.nva.testutils.RandomDataGenerator.randomIsbn13;
import static no.unit.nva.testutils.RandomDataGenerator.randomLocalDateTime;
import static no.unit.nva.testutils.RandomDataGenerator.randomString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import no.unit.nva.commons.json.JsonUtils;
import no.unit.nva.model.contexttypes.UnconfirmedPublisher;
import no.unit.nva.model.exceptions.InvalidIsbnException;
import no.unit.nva.model.time.Instant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class LiteraryArtsOutputTest {

    public static Stream<AudioVisualType> audioVisualTypeProvider() {
        return Arrays.stream(AudioVisualType.values());
    }

    @Test
    void shouldRoundTripJsonBackAndForth() throws InvalidIsbnException, JsonProcessingException {
        PrintedMatter printedMatter = new PrintedMatter(randomUnconfirmedPublisher(),
                                                        randomNvaInstant(),
                                                        List.of(randomIsbn13()),
                                                        randomString(),
                                                        PrintedMatter.TYPE_VALUE);

        var json = JsonUtils.dtoObjectMapper.writeValueAsString(printedMatter);
        var deserialized = JsonUtils.dtoObjectMapper.readValue(json, PrintedMatter.class);
        var deserialized2 = JsonUtils.dtoObjectMapper.readValue(json, LiteraryArtsOutput.class);
        assertThat(deserialized, is(not(nullValue())));
        assertThat(deserialized2, is(not(nullValue())));
    }

//    @ParameterizedTest
//    @MethodSource("audioVisualTypeProvider")
    @Test
    void shouldRoundTripJsonBackAndForth2() throws InvalidIsbnException,
                                                                                  JsonProcessingException {
        AudioVisual audioVisual = new AudioVisual(AudioVisualType.PODCAST,
                                                  randomUnconfirmedPublisher(),
                                                  randomNvaInstant(),
                                                  List.of(randomIsbn13()),
                                                  randomString());

        var json = JsonUtils.dtoObjectMapper.writeValueAsString(audioVisual);
        var deserialized = JsonUtils.dtoObjectMapper.readValue(json, AudioVisual.class);
        var deserialized2 = JsonUtils.dtoObjectMapper.readValue(json, LiteraryArtsOutput.class);
        assertThat(deserialized, is(not(nullValue())));
        assertThat(deserialized2, is(not(nullValue())));
        assertThat(deserialized, is(equalTo(deserialized2)));
        assertThat(deserialized.getType(), is(equalTo(AudioVisualType.PODCAST.getType())));
    }

    private static UnconfirmedPublisher randomUnconfirmedPublisher() {
        return new UnconfirmedPublisher(randomString());
    }

    private static Instant randomNvaInstant() {
        return new Instant(randomLocalDateTime());
    }
}