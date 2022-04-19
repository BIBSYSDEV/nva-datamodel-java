package no.unit.nva.model.instancetypes.journal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.fasterxml.jackson.core.JsonProcessingException;
import no.unit.nva.commons.json.JsonUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class JournalArticleContentTypeTest {

    @ParameterizedTest
    @EnumSource(JournalArticleContentType.class)
    void shouldReturnEnumerationWhenInputIsCurrentName(JournalArticleContentType journalArticleContentType)
        throws JsonProcessingException {
        var currentValue = "\"" + journalArticleContentType.getValue() + "\"";
        var output = JsonUtils.dtoObjectMapper.readValue(currentValue, JournalArticleContentType.class);
        assertEquals(output, journalArticleContentType);
    }

    @ParameterizedTest
    @EnumSource(JournalArticleContentType.class)
    void shouldReturnEnumerationWhenInputIsDeprecatedName(JournalArticleContentType journalArticleContentType)
        throws JsonProcessingException {
        var deprecatedValue = "\"" + journalArticleContentType.getDeprecatedValue() + "\"";
        var output = JsonUtils.dtoObjectMapper.readValue(deprecatedValue, JournalArticleContentType.class);
        assertEquals(output, journalArticleContentType);
    }
}