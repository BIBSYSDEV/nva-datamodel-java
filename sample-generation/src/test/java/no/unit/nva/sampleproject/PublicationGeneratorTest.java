package no.unit.nva.sampleproject;

import static no.unit.nva.commons.json.JsonUtils.dtoObjectMapper;
import static nva.commons.core.attempt.Try.attempt;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Stream;
import no.unit.nva.model.Publication;
import no.unit.nva.model.testing.PublicationGenerator;
import no.unit.nva.model.testing.PublicationInstanceBuilder;
import no.unit.nva.testutils.ExceptionUtils;
import nva.commons.core.attempt.Failure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class PublicationGeneratorTest {

    public static final String SERIALIZATIONS_FOLDER_NAME = "serializations";
    private File serializationsFolder;

    @BeforeEach
    public void init() {
        serializationsFolder = new File(SERIALIZATIONS_FOLDER_NAME).getAbsoluteFile();
        serializationsFolder.mkdirs();
    }

    @ParameterizedTest
    @MethodSource("publicationsProvider")
    void generatorReturnsPublicationWithNonEmptyFields() throws Exception {
        Publication publication =
            attempt(()->PublicationGenerator.randomPublication()).orElseThrow(fail->logAndThrow(fail));
        String json = dtoObjectMapper.writeValueAsString(publication);
        writeJson(json, publication.getIdentifier().toString());
    }

    private Exception logAndThrow(Failure<Publication> fail) {
        String error = ExceptionUtils.stackTraceToString(fail.getException());
        System.out.println(error);
        return fail.getException();
    }

    static Stream<Publication> publicationsProvider() {
        return PublicationInstanceBuilder.listPublicationInstanceTypes()
            .stream()
            .map(PublicationGenerator::randomPublication);
    }

    private void writeJson(String json, String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(serializationsFolder, filename + ".json")));
        writer.write(json);
        writer.close();
    }
}
