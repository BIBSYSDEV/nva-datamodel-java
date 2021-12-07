package no.unit.nva.datamodel;

import static no.unit.nva.datamodel.migration.MigrationConfig.objectMapper;
import static nva.commons.core.attempt.Try.attempt;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import no.unit.nva.identifiers.SortableIdentifier;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class FunctionalTests {

    public static final String CURRENT_DATAMODEL_VERSION = new Environment().readEnv("DATAMODEL_VERSION");
    public static final String PREVIOUS_DATAMODEL_VERSION = "0.14.13";
    public static final String SERIALIZATIONS_SUBFOLDER = "serializations";
    public static final String SAMPLE_PROJECT_FOLDER_NAME = "sample-generation";
    public static final String DEPENDENCIES_FILE = "libs.versions.toml";
    public static final String MODEL_VERSION_FIELD_IN_SERIALIZED_RESOURCES = "modelVersion";
    public static final int SOME_TIME_FOR_MAVEN_LOCAL_TO_REGISTER_CHANGES = 2000;
    public static final String HARDCODED_DATAMODEL_VERSION_PLACEHOLDER_IN_RESOURCE_FILE =
        "<DATAMODEL_VERSION_PLACEHOLDER>";
    public static final String GRADLE_FOLDER_IN_PROJECTS = "gradle";
    public static final String GRADLE_BUILD_COMMAND = "build";
    public static final String GRADLE_COMMAND_FOR_PUBLISHING_TO_MAVEN_LOCAL = "publishToMavenLocal";
    public static final String CURRENT_FOLDER = "";
    public static final String DATAMODEL_DEPENDENCY_REGEX_FOR_REPLACEMENT =
        "datamodel\\s*=\\s*\\{\\s*strictly\\s*=\\s*'[^']+'\\s*}";
    public static final Pattern DATAMODEL_DEPENDENCY_REGEX_FOR_DETECTION =
        Pattern.compile("[\\s\\S]*\\s*datamodel\\s*=\\s*\\{\\s*strictly\\s*=\\s*'[^']+'\\s*}[\\s\\S]*");

    public static final String DATAMODEL_DEPENDENCY_NOT_FOUND_ERROR = "Could not find the datamodel dependency in the"
                                                                      + " 'libs.versions.toml' file";

    @TempDir
    static File temporaryDir;
    static File sampleProjectCurrentVersion;
    static File sampleProjectPreviousVersion;


    @BeforeAll
    public static void init() throws IOException {
        setupTemporaryFolders();
        publishCurrentVersionToMavenLocal();
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
            assertDoesNotThrow(() -> deserializeWithCurrentVersion(json));
        }
    }

    @Tag("migrationTest")
    @Test
    public void currentVersionShouldProduceSameDeserializationWhenDeserializingOldAndNewSerializedVersions()
        throws FileNotFoundException {
        List<String> jsons = listSerializedPublications(sampleProjectPreviousVersion);
        var deserializedFromOldVersionSerialization = jsons.stream()
            .map(this::deserializeWithCurrentVersion)
            .collect(Collectors.toMap(Publication::getIdentifier, publication -> publication));
        var newVersionSerializations = deserializedFromOldVersionSerialization.values().stream()
            .map(this::serializeWithCurrentVersion)
            .collect(Collectors.toList());
        var deserializedFromNewVersionSerialization =  newVersionSerializations
            .stream()
            .map(this::deserializeWithCurrentVersion)
            .collect(Collectors.toMap(Publication::getIdentifier, publication->publication));

        var allIdentifiers =deserializedFromOldVersionSerialization.keySet();
        for(SortableIdentifier identifier:allIdentifiers){
            var deserializedFromOldVersion = deserializedFromOldVersionSerialization.get(identifier);
            var deserializedFromNewVersion = deserializedFromNewVersionSerialization.get(identifier);
            assertThat(deserializedFromNewVersion,is(equalTo(deserializedFromOldVersion)));
        }
    }

    private String serializeWithCurrentVersion(Publication value) {
        return attempt(()->objectMapper.writeValueAsString(value)).orElseThrow();
    }

    private Publication deserializeWithCurrentVersion(String json) {
        return attempt(()->objectMapper.readValue(json, Publication.class)).orElseThrow();
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
            .withDebug(true)
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

    private static void publishCurrentVersionToMavenLocal() {
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
        File projectGradleFolder = new File(testingFolder, GRADLE_FOLDER_IN_PROJECTS).getAbsoluteFile();
        File dependenciesFile = new File(projectGradleFolder,DEPENDENCIES_FILE).getAbsoluteFile();
        String dependenciesFileContents = IoUtils.stringFromFile(dependenciesFile.toPath());

        assertThat(DATAMODEL_DEPENDENCY_NOT_FOUND_ERROR,
                   DATAMODEL_DEPENDENCY_REGEX_FOR_DETECTION.matcher(dependenciesFileContents).matches(), is(true));
        dependenciesFileContents=dependenciesFileContents.replaceFirst(DATAMODEL_DEPENDENCY_REGEX_FOR_REPLACEMENT, datamodelDependency(version));
        System.out.println(dependenciesFileContents);

        dependenciesFile.delete();

        BufferedWriter writer = new BufferedWriter(new FileWriter(dependenciesFile));
        writer.write(dependenciesFileContents);
        writer.flush();
        writer.close();
    }

    private static String datamodelDependency(String version) {
        return String.format( "%sdatamodel = {strictly = '%s'}%s",
                              System.lineSeparator(),
                              version,
                              System.lineSeparator()
        );
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

