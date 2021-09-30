package provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class InstanceTypeProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of("ArtisticDesignClothingDesign"),
                Arguments.of("ArtisticDesignExhibition"),
                Arguments.of("ArtisticDesignGraphicDesign"),
                Arguments.of("ArtisticDesignIllustration"),
                Arguments.of("ArtisticDesignInteractionDesign"),
                Arguments.of("ArtisticDesignInteriorDesign"),
                Arguments.of("ArtisticDesignLightDesign"),
                Arguments.of("ArtisticDesignOther"),
                Arguments.of("ArtisticDesignProductDesign"),
                Arguments.of("ArtisticDesignServiceDesign"),
                Arguments.of("ArtisticDesignWebDesign"),
                Arguments.of("BookAbstracts"),
                Arguments.of("BookAnthology"),
                Arguments.of("BookMonograph"),
                Arguments.of("ChapterArticle"),
                Arguments.of("ConferenceLecture"),
                Arguments.of("ConferencePoster"),
                Arguments.of("Lecture"),
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
