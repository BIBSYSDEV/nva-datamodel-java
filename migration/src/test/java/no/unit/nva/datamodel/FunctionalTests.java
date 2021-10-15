package no.unit.nva.datamodel;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import nva.commons.core.Environment;
import nva.commons.core.JsonUtils;
import nva.commons.core.ioutils.IoUtils;
import org.apache.commons.io.FileUtils;
import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.BuildTask;
import org.gradle.testkit.runner.GradleRunner;
import org.gradle.testkit.runner.TaskOutcome;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

@Tag("migrationTest")
public class FunctionalTests {

    public static final String CURRENT_DATAMODEL_VERSION = new Environment().readEnv("DATAMODEL_VERSION");
    public static final String SERIALIZATIONS = "serializations";
    public static final String SAMPLE_PROJECT_FOLDER_NAME = "sample-generation";
    public static final String DEPENDENCIES_FILE = "libs.versions.toml";
    public static final String MODEL_VERSION_FIELD_IN_SERIALIZED_RESOURCES = "modelVersion";
    @TempDir
    File tempFolder;

    @BeforeEach
    public void init() throws IOException {
        crateSampleProjectFolder();
        deleteExistingSerializations();
        buildNvaDatamodel();
        waitUntilLibraryHasBeenRegisteredInMavenLocal();
        buildSampleProject();
    }

    @Tag("migrationTest")
    @Test
    public void gradleRunnerRunsSampleProject() throws FileNotFoundException, JsonProcessingException {
        List<String> jsons = listSerializedPublications();
        assertThat(jsons, is(not(empty())));
        for (String json : jsons) {
            ObjectNode objectNode = (ObjectNode) JsonUtils.dtoObjectMapper.readTree(json);
            assertThat(objectNode.get(MODEL_VERSION_FIELD_IN_SERIALIZED_RESOURCES).textValue(),
                       is(equalTo(CURRENT_DATAMODEL_VERSION)));
        }
    }

    private void buildSampleProject() {
        BuildResult buildResult = GradleRunner.create()
            .withProjectDir(tempFolder)
            .withArguments("build")
            .build();
        buildResult.getTasks().forEach(task -> assertThat(task.getOutcome(), is(not(equalTo(TaskOutcome.FAILED)))));
    }

    private void waitUntilLibraryHasBeenRegisteredInMavenLocal() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void buildNvaDatamodel() {
        BuildResult gradleRunner = GradleRunner.create()
            .withProjectDir(rootFolder())
            .withArguments("publishToMavenLocal")
            .build();
        gradleRunner.getTasks()
            .stream()
            .map(BuildTask::getOutcome)
            .forEach(outcome -> assertThat(outcome, is(not(equalTo(TaskOutcome.FAILED)))));
    }

    private void crateSampleProjectFolder() throws IOException {
        File sampleProjectFolder = new File(rootFolder(), SAMPLE_PROJECT_FOLDER_NAME);
        assertThat(sampleProjectFolder.exists(), is(true));
        FileUtils.copyDirectory(sampleProjectFolder, tempFolder);
        injectDatamodelVersion();
    }

    private File rootFolder() {
        return new File("").getAbsoluteFile().getParentFile();
    }

    private void deleteExistingSerializations() throws IOException {
        File serializationsFolder = new File(tempFolder, SERIALIZATIONS);
        FileUtils.deleteDirectory(serializationsFolder);
        serializationsFolder.mkdirs();
    }

    private void injectDatamodelVersion() throws IOException {
        String libsVersionFile = IoUtils.stringFromResources(Path.of(DEPENDENCIES_FILE));
        libsVersionFile = libsVersionFile.replaceAll("<DATAMODEL_VERSION_PLACEHOLDER>",
                                                     CURRENT_DATAMODEL_VERSION);
        File projectGradleFolder = new File(tempFolder, "gradle").getAbsoluteFile();
        File newLibsFile = new File(projectGradleFolder, DEPENDENCIES_FILE);
        BufferedWriter writer = new BufferedWriter(new FileWriter(newLibsFile));
        writer.write(libsVersionFile);
        writer.flush();
        writer.close();
    }

    private List<String> listSerializedPublications() throws FileNotFoundException {
        File serializationsFolder = new File(tempFolder, SERIALIZATIONS);
        File[] serializedFiles = serializationsFolder.listFiles();
        List<String> jsons = new ArrayList<>();
        for (File file : serializedFiles) {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String json = reader.lines().collect(Collectors.joining(System.lineSeparator()));
            jsons.add(json);
        }
        return jsons;
    }
}
