package no.unit.nva.sampleproject;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Stream;
import no.unit.nva.model.Publication;
import no.unit.nva.model.testing.PublicationGenerator;
import no.unit.nva.model.testing.PublicationInstanceBuilder;
import nva.commons.core.JsonUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    void generatorReturnsPublicationWithNonEmptyFields() throws IOException {
        Publication publication = PublicationGenerator.randomPublication();
        String json = JsonUtils.dtoObjectMapper.writeValueAsString(publication);
        writeJson(json, publication.getIdentifier().toString());
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
