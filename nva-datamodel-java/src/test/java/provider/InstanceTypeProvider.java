package provider;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class InstanceTypeProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of("ArtisticDesign"),
                Arguments.of("ArtisticDesignOther"),
                Arguments.of("BookAbstracts"),
                Arguments.of("BookAnthology"),
                Arguments.of("BookMonograph"),
                Arguments.of("ChapterArticle"),
                Arguments.of("ConferenceLecture"),
                Arguments.of("ConferencePoster"),
                Arguments.of("Lecture"),
                Arguments.of("OtherPresentation"),
                Arguments.of("DegreeBachelor"),
                Arguments.of("DegreeMaster"),
                Arguments.of("DegreePhd"),
                Arguments.of("FeatureArticle"),
                Arguments.of("JournalArticle"),
                Arguments.of("JournalInterview"),
                Arguments.of("JournalCorrigendum"),
                Arguments.of("JournalLeader"),
                Arguments.of("JournalLetter"),
                Arguments.of("JournalReview"),
                Arguments.of("JournalShortCommunication"),
                Arguments.of("OtherStudentWork"),
                Arguments.of("ReportBasic"),
                Arguments.of("ReportPolicy"),
                Arguments.of("ReportResearch"),
                Arguments.of("ReportWorkingPaper")
        );
    }
}
