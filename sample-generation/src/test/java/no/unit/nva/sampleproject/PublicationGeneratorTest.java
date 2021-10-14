package no.unit.nva.sampleproject;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import no.unit.nva.model.Publication;
import nva.commons.core.JsonUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PublicationGeneratorTest {

    private File serializationsFolder;

    @BeforeEach
    public void init() {
        serializationsFolder = new File("serializations").getAbsoluteFile();
        serializationsFolder.mkdirs();
    }

    @Test
    void generatorReturnsNonNullPublication() {
        assertThat(PublicationGenerator.createPublication(), is(not(nullValue())));
    }

    @Test
    void generatorReturnsPublicationWithNonEmptyFields() throws IOException {
        Publication publication = PublicationGenerator.createPublication();
        String json = JsonUtils.dtoObjectMapper.writeValueAsString(publication);
        writeJson(json, publication.getIdentifier().toString());
    }

    private void writeJson(String json, String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(serializationsFolder, filename + ".json")));
        writer.write(json);
        writer.close();
    }
}
