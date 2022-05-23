package no.unit.nva.model.instancetypes.chapter;

import static java.lang.String.format;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static no.unit.nva.model.instancetypes.chapter.ChapterArticleContentType.DELIMITER;
import static no.unit.nva.model.instancetypes.chapter.ChapterArticleContentType.ERROR_MESSAGE_TEMPLATE;
import static no.unit.nva.testutils.RandomDataGenerator.randomString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.exc.ValueInstantiationException;
import no.unit.nva.commons.json.JsonUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class ChapterArticleContentTypeTest {

    @ParameterizedTest
    @EnumSource(ChapterArticleContentType.class)
    void shouldReturnChapterArticleContentTypeWhenInputIsCurrentValue(
        ChapterArticleContentType chapterArticleContentType)
        throws JsonProcessingException {
        var currentValue = "\"" + chapterArticleContentType.getValue() + "\"";
        var expectedChapterArticleContentType = JsonUtils.dtoObjectMapper.readValue(currentValue,
                                                                                    ChapterArticleContentType.class);
        assertEquals(expectedChapterArticleContentType, chapterArticleContentType);
    }

    @ParameterizedTest
    @EnumSource(ChapterArticleContentType.class)
    void shouldReturnChapterArticleContentTypeWhenInputIsDeprecatedValue(
        ChapterArticleContentType chapterArticleContentType)
        throws JsonProcessingException {
        var deprecated = "\"" + chapterArticleContentType.getDeprecatedValue() + "\"";
        var expectedChapterArticleContentType = JsonUtils.dtoObjectMapper.readValue(deprecated,
                                                                                    ChapterArticleContentType.class);
        assertEquals(expectedChapterArticleContentType, chapterArticleContentType);
    }

    @Test
    void shouldThrowErrorWithProperErrorMessageWhenInvalidInputValueSupplied() {
        var invalidInput = randomString();
        var inputJsonValue = "\"" + invalidInput + "\"";
        Executable handleRequest = () -> JsonUtils.dtoObjectMapper.readValue(inputJsonValue,
                                                                             ChapterArticleContentType.class);

        var response = assertThrows(ValueInstantiationException.class, handleRequest);
        var actualErrorMessage = createErrorMessage(invalidInput);
        assertThat(response.getMessage(), containsString(actualErrorMessage));
    }

    private static String createErrorMessage(String value) {
        return format(ERROR_MESSAGE_TEMPLATE, value, stream(ChapterArticleContentType.values())
            .map(ChapterArticleContentType::toString).collect(joining(DELIMITER)));
    }
}