package no.unit.nva.datamodel;

import static no.unit.nva.datamodel.migration.MigrationConfig.objectMapper;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import no.unit.nva.model.Publication;
import nva.commons.core.Environment;
import nva.commons.core.JsonUtils;
import nva.commons.core.ioutils.IoUtils;
import org.apache.commons.io.FileUtils;
import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.BuildTask;
import org.gradle.testkit.runner.GradleRunner;
import org.gradle.testkit.runner.TaskOutcome;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

@Tag("migrationTest")
public class FunctionalTests {

    public static final String CURRENT_DATAMODEL_VERSION = new Environment().readEnv("DATAMODEL_VERSION");
    public static final String PREVIOUS_DATAMODEL_VERSION = "0.14.8";
    public static final String SERIALIZATIONS_SUBFOLDER = "serializations";
    public static final String SAMPLE_PROJECT_FOLDER_NAME = "sample-generation";
    public static final String DEPENDENCIES_FILE = "libs.versions.toml";
    public static final String MODEL_VERSION_FIELD_IN_SERIALIZED_RESOURCES = "modelVersion";
    public static final int SOME_TIME_FOR_MAVEN_LOCAL_TO_REGISTER_CHANGES = 2000;
    public static final String HARDCODED_DATAMODEL_VERSION_PLACEHOLDER_IN_RESOURCE_FILE =
        "<DATAMODEL_VERSION_PLACEHOLDER>";
    public static final String GRADLE_FOLDER_IN_PROJECS = "gradle";
    public static final String GRADLE_BUILD_COMMAND = "build";
    public static final String GRADLE_COMMAND_FOR_PUBLISHING_TO_MAVEN_LOCAL = "publishToMavenLocal";
    public static final String CURRENT_FOLDER = "";

    @TempDir
    static File temporaryDir;
    static File sampleProjectCurrentVersion;
    static File sampleProjectPreviousVersion;

    @BeforeAll
    public static void init() throws IOException {
        setupTemporaryFolders();
        buildWipNvaDatamodel();
        waitUntilLibraryHasBeenRegisteredInMavenLocal();
        buildSampleProject(CURRENT_DATAMODEL_VERSION, sampleProjectCurrentVersion);
        buildSampleProject(PREVIOUS_DATAMODEL_VERSION, sampleProjectPreviousVersion);
    }

    @Tag("migrationTest")
    @Test
    public void gradleRunnerRunsSampleProjectOnCurrentVersion() throws FileNotFoundException, JsonProcessingException {
        List<String> jsons = listSerializedPublications(sampleProjectCurrentVersion);
        assertThat(jsons, is(not(empty())));
        for (String json : jsons) {
            ObjectNode objectNode = (ObjectNode) JsonUtils.dtoObjectMapper.readTree(json);
            assertThat(objectNode.get(MODEL_VERSION_FIELD_IN_SERIALIZED_RESOURCES).textValue(),
                       is(equalTo(CURRENT_DATAMODEL_VERSION)));
        }
    }

    @Tag("migrationTest")
    @Test
    public void gradleRunnerRunsSampleProjectOnPreviousVersion() throws FileNotFoundException, JsonProcessingException {
        List<String> jsons = listSerializedPublications(sampleProjectPreviousVersion);
        assertThat(jsons, is(not(empty())));
        for (String json : jsons) {
            ObjectNode objectNode = (ObjectNode) JsonUtils.dtoObjectMapper.readTree(json);
            assertThat(objectNode.get(MODEL_VERSION_FIELD_IN_SERIALIZED_RESOURCES).textValue(),
                       is(equalTo(PREVIOUS_DATAMODEL_VERSION)));
        }
    }

    @Tag("migrationTest")
    @Test
    public void currentVersionSerializationsShouldBeDeserializedByCurrentVersion() throws FileNotFoundException {
        List<String> jsons = listSerializedPublications(sampleProjectCurrentVersion);
        for (String json : jsons) {
            assertDoesNotThrow(() -> objectMapper.readValue(json, Publication.class));
        }
    }

    @Tag("migrationTest")
    @Test
    public void previousVersionSerializationsShouldBeDeserializedByCurrentVersion() throws FileNotFoundException {
        List<String> jsons = listSerializedPublications(sampleProjectPreviousVersion);
        for (String json : jsons) {
            assertDoesNotThrow(() -> objectMapper.readValue(json, Publication.class));
        }
    }

    private static void setupTemporaryFolders() throws IOException {
        sampleProjectCurrentVersion = new File(temporaryDir, "current");
        sampleProjectPreviousVersion = new File(temporaryDir, "previous");
        createDirectory(sampleProjectCurrentVersion);
        createDirectory(sampleProjectPreviousVersion);
    }

    private static void createDirectory(File folder) throws IOException {
        Files.createDirectory(folder.getAbsoluteFile().toPath());
    }

    private static void buildSampleProject(String modelVersion, File testingFolder) throws IOException {
        createSampleProjectFolder(testingFolder, modelVersion);
        deleteExistingSerializations(testingFolder);
        buildSampleProject(testingFolder);
    }

    private static void buildSampleProject(File sampleProjectFile) {
        BuildResult buildResult = GradleRunner.create()
            .withProjectDir(sampleProjectFile)
            .withArguments(GRADLE_BUILD_COMMAND)
            .build();
        buildResult.getTasks().forEach(task -> assertThat(task.getOutcome(), is(not(equalTo(TaskOutcome.FAILED)))));
    }

    private static void waitUntilLibraryHasBeenRegisteredInMavenLocal() {
        try {
            Thread.sleep(SOME_TIME_FOR_MAVEN_LOCAL_TO_REGISTER_CHANGES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void buildWipNvaDatamodel() {
        BuildResult gradleRunner = GradleRunner.create()
            .withProjectDir(rootFolder())
            .withArguments(GRADLE_COMMAND_FOR_PUBLISHING_TO_MAVEN_LOCAL)
            .build();
        gradleRunner.getTasks()
            .stream()
            .map(BuildTask::getOutcome)
            .forEach(outcome -> assertThat(outcome, is(not(equalTo(TaskOutcome.FAILED)))));
    }

    private static void createSampleProjectFolder(File testingFolder, String version) throws IOException {
        File sampleProjectFolder = new File(rootFolder(), SAMPLE_PROJECT_FOLDER_NAME);
        assertThat(sampleProjectFolder.exists(), is(true));
        FileUtils.copyDirectory(sampleProjectFolder, testingFolder);
        injectDatamodelVersion(testingFolder, version);
    }

    private static File rootFolder() {
        return new File(CURRENT_FOLDER).getAbsoluteFile().getParentFile();
    }

    private static void deleteExistingSerializations(File sampleProjectFolder) throws IOException {
        File serializationsFolder = new File(sampleProjectFolder, SERIALIZATIONS_SUBFOLDER);
        FileUtils.deleteDirectory(serializationsFolder);
        createDirectory(serializationsFolder);
    }

    private static void injectDatamodelVersion(File testingFolder, String version) throws IOException {
        String libsVersionFile = IoUtils.stringFromResources(Path.of(DEPENDENCIES_FILE));
        libsVersionFile = libsVersionFile.replaceAll(HARDCODED_DATAMODEL_VERSION_PLACEHOLDER_IN_RESOURCE_FILE, version);
        File projectGradleFolder = new File(testingFolder, GRADLE_FOLDER_IN_PROJECS).getAbsoluteFile();
        File newLibsFile = new File(projectGradleFolder, DEPENDENCIES_FILE);
        BufferedWriter writer = new BufferedWriter(new FileWriter(newLibsFile));
        writer.write(libsVersionFile);
        writer.flush();
        writer.close();
    }

    private List<String> listSerializedPublications(File folder) throws FileNotFoundException {
        File serializationsFolder = new File(folder, SERIALIZATIONS_SUBFOLDER);
        File[] serializedFiles = serializationsFolder.listFiles();
        List<String> jsons = new ArrayList<>();
        assert serializedFiles != null;
        for (File file : serializedFiles) {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String json = reader.lines().collect(Collectors.joining(System.lineSeparator()));
            jsons.add(json);
        }
        return jsons;
    }
}
