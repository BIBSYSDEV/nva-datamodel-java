package no.unit.nva.api.externalmodel;

import com.fasterxml.jackson.databind.ObjectMapper;
import no.unit.nva.model.File;
import no.unit.nva.model.Publication;
import nva.commons.utils.JsonUtils;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import static no.unit.nva.model.util.PublicationGenerator.generatePublication;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PublicationDtoTest {

    public static final ObjectMapper objectMapper = JsonUtils.objectMapper;
    public static final String TIMESTAMP_REGEX = "[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}\\.[0-9]+";
    public static final String SOME_TIMESTAMP = "2020-09-23T09:51:23.044996Z";
    public static final String DOCUMENTATION_PATH_TEMPLATE = "documentation/external/%s.json";
    public static final UUID REPLACEMENT_IDENTIFIER_1 = UUID.fromString("c443030e-9d56-43d8-afd1-8c89105af555");
    public static final UUID REPLACEMENT_IDENTIFIER_2 = UUID.fromString("5032710d-a326-43d3-a8fb-57a451873c78");

    @ParameterizedTest(name = "Round-trip publication of type {0}")
    @ValueSource(strings = {
            "BookAnthology",
            "BookMonograph",
            "CartographicMap",
            "ChapterArticle",
            "DegreeBachelor",
            "DegreeMaster",
            "DegreePhd",
            "FeatureArticle",
            "JournalArticle",
            "JournalCorrigendum",
            "JournalLeader",
            "JournalLetter",
            "JournalReview",
            "JournalShortCommunication",
            "MusicNotation",
            "OtherStudentWork",
            "ReportPolicy",
            "ReportResearch",
            "ReportWorkingPaper"})
    void publicationDtoMapsPublication(String type) throws Exception {
        Publication expected = generatePublication(type);
        var publicationDto = new PublicationDto(expected);
        var mappedPublicationDto = objectMapper.writeValueAsString(publicationDto);
        var roundTrippedPublication = objectMapper.readValue(mappedPublicationDto, PublicationDto.class);
        var actual = roundTrippedPublication.toPublication();

        assertThatThereAreNoDifferencesAtomically(expected, actual);
        assertEquals(actual, expected);
        writePublicationDtoToFile(type, expected);
    }

    /**
     * This method checks the equality at an atomic level and the diff object contains the
     * differences between the first and actual.
     * @param first A publication
     * @param second Another publication
     */
    private void assertThatThereAreNoDifferencesAtomically(Publication first, Publication second) {
        Javers javers = JaversBuilder.javers().build();
        Diff diff = javers.compare(first, second);
        if (diff.hasChanges()) {
            throw new RuntimeException(diff.changesSummary());
        }
    }

    private void writePublicationDtoToFile(String instanceType, Publication publication) throws IOException {
        publication.setIdentifier(REPLACEMENT_IDENTIFIER_1);
        publication.getFileSet().getFiles().forEach(file -> publication.getFileSet()
                .setFiles(List.of(copyWithNewIdentifier(file))));
        String path = String.format(DOCUMENTATION_PATH_TEMPLATE, instanceType);
        var publicationJson = objectMapper.writeValueAsString(new PublicationDto(publication))
                .replaceAll(TIMESTAMP_REGEX, SOME_TIMESTAMP);
        Files.write(Paths.get(path), publicationJson.getBytes());
    }

    private File copyWithNewIdentifier(File file) {
        return new File(REPLACEMENT_IDENTIFIER_2,
                file.getName(),
                file.getMimeType(),
                file.getSize(),
                file.getLicense(),
                file.isAdministrativeAgreement(),
                file.isPublisherAuthority(),
                file.getEmbargoDate());
    }
}