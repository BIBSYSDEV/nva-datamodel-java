package no.unit.nva.model.file;

import static java.time.temporal.ChronoUnit.DAYS;
import static no.unit.nva.hamcrest.DoesNotHaveEmptyValues.doesNotHaveEmptyValues;
import static no.unit.nva.testutils.RandomDataGenerator.randomBoolean;
import static no.unit.nva.testutils.RandomDataGenerator.randomInstant;
import static no.unit.nva.testutils.RandomDataGenerator.randomInteger;
import static no.unit.nva.testutils.RandomDataGenerator.randomString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anEmptyMap;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.net.URI;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;
import no.unit.nva.commons.json.JsonUtils;
import no.unit.nva.model.associatedartifacts.file.File;
import no.unit.nva.model.associatedartifacts.file.LegacyFile;
import no.unit.nva.model.associatedartifacts.file.License;
import no.unit.nva.model.associatedartifacts.file.MissingLicenseException;
import no.unit.nva.model.associatedartifacts.file.PublishedFile;
import no.unit.nva.model.associatedartifacts.file.UnpublishableFile;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class FileModelTest {
    
    public static final URI CC_BY_URI = URI.create("https://creativecommons.org/licenses/by/4.0/");
    public static final String APPLICATION_PDF = "application/pdf";
    public static final String FIRST_FILE_TXT = "First_file.txt";
    public static final String CC_BY = "CC-BY";
    public static final String CC_BY_4_0 = "CC-BY 4.0";
    public static final String EN = "en";
    public static final long SIZE = 200L;
    public static final ObjectMapper dataModelObjectMapper = JsonUtils.dtoObjectMapper;
    public static final boolean NOT_ADMINISTRATIVE_AGREEMENT = false;
    private static final boolean ADMINISTRATIVE_AGREEMENT = true;
    
    public static Stream<File> fileProvider() {
        var legacyFile = randomLegacyFile();
        var publishedFile = randomPublishedFile();
        var unpublishedFile = randomUnpublishedFile();
        var unpublishableFile = randomUnpublishableFile();
        return Stream.of(legacyFile, publishedFile, unpublishedFile, unpublishableFile);
    }
    
    public static Stream<File> notAdministrativeAgreements() {
        return Stream.of(randomLegacyFile(), randomPublishedFile(), randomUnpublishedFile(),
            unpublishableNotAdministrativeAgreement());
    }
    
    public static License getCcByLicense() {
        return new License.Builder()
                   .withIdentifier(CC_BY)
                   .withLabels(Map.of(EN, CC_BY_4_0))
                   .withLink(CC_BY_URI)
                   .build();
    }
    
    @ParameterizedTest
    @MethodSource("fileProvider")
    void shouldRoundTripAllFileTypes(File file) throws JsonProcessingException {
        var json = JsonUtils.dtoObjectMapper.writeValueAsString(file);
        var deserialzed = JsonUtils.dtoObjectMapper.readValue(json, File.class);
        assertThat(deserialzed, doesNotHaveEmptyValues());
        assertThat(deserialzed, is(equalTo(file)));
    }
    
    @Test
    void shouldThrowMissingLicenseExceptionWhenFileIsNotAdministrativeAgreementAndLicenseIsMissing() {
        var file = getPublishedFile(FIRST_FILE_TXT, false, null);
        assertThrows(MissingLicenseException.class, file::validate);
    }
    
    @Test
    void shouldNotThrowMissingLicenseExceptionWhenFileIsAdministrativeAgreementAndLicenseIsMissing() {
        var file = getAdministrativeAgreement(FIRST_FILE_TXT, true, null);
        assertDoesNotThrow(file::validate);
    }
    
    @Test
    void shouldNotThrowMissingLicenseExceptionWhenFileIsAdministrativeAgreementAndLicenseIsPresent() {
        var file = getAdministrativeAgreement(FIRST_FILE_TXT, true, getCcByLicense());
        assertDoesNotThrow(file::validate);
    }
    
    @ParameterizedTest(name = "should not throw MissingLicenseException when not administrative agreement")
    @MethodSource("notAdministrativeAgreements")
    void shouldNotThrowMissingLicenseExceptionWhenFileIsNotAdministrativeAgreementAndLicenseIsPresent(File file) {
        assertDoesNotThrow(file::validate);
    }
    
    @Test
    void shouldReturnEmptySetWhenLicenseLabelsAreNull() {
        var license = new License.Builder()
                          .withIdentifier(CC_BY)
                          .withLink(CC_BY_URI)
                          .build();
        assertThat(license.getLabels(), is(anEmptyMap()));
    }
    
    @ParameterizedTest
    @MethodSource("fileProvider")
    void shouldReturnSerializedModel(File file) throws JsonProcessingException {
        var mapped = dataModelObjectMapper.writeValueAsString(file);
        var unmapped = dataModelObjectMapper.readValue(mapped, File.class);
        assertThat(file, equalTo(unmapped));
        assertThat(file, doesNotHaveEmptyValues());
    }
    
    @Test
    void shouldNotBeVisibleForNonOwnersWhenFileIsAdministrativeAgreement() {
        var file = randomAdministrativeAgreement();
        assertFalse(file.isVisibleForNonOwner());
    }
    
    @Test
    void shouldNotBeVisibleForNonOwnersWhenFileIsEmbargoed() {
        var embargoedFile = publishedFileWithActiveEmbargo();
        assertFalse(embargoedFile.isVisibleForNonOwner());
    }
    
    @Test
    void shouldNotAllowPublishableFilesToBeAdministrativeAgreements() {
        assertThrows(IllegalStateException.class, this::illegalPublishedFile);
        assertThrows(IllegalStateException.class, this::illegalUnPublishedFile);
        assertThrows(IllegalStateException.class, this::illegalLegacyFile);
    }
    
    @Test
    void legacyFileShouldBeMigratedToPublishedFile() throws JsonProcessingException {
        var file = randomLegacyFile();
        var jsonString = file.toJsonString();
        var json = (ObjectNode) JsonUtils.dtoObjectMapper.readTree(jsonString);
        json.put("type", LegacyFile.LEGACY_TYPE);
        
        var legacyJson = JsonUtils.dtoObjectMapper.writeValueAsString(json);
        var beforeMigration = JsonUtils.dtoObjectMapper.readValue(legacyJson, File.class);
        assertThat(beforeMigration, is(instanceOf(LegacyFile.class)));
        
        var migrated = beforeMigration.toJsonString();
        var migratedDeserialized = JsonUtils.dtoObjectMapper.readValue(migrated, File.class);
        assertThat(migratedDeserialized, is(instanceOf(PublishedFile.class)));
    }
    
    @Test
    void shouldNotBeVisibleForNonOwnerWhenUnpublished() throws JsonProcessingException {
        var file = randomUnpublishedFile();
        var mapped = dataModelObjectMapper.writeValueAsString(file);
        var unmapped = dataModelObjectMapper.readValue(mapped, File.class);
        
        assertThat(file.isVisibleForNonOwner(), equalTo(false));
        assertThat(unmapped.isVisibleForNonOwner(), equalTo(false));
    }
    
    private static File randomUnpublishableFile() {
        return new UnpublishableFile(UUID.randomUUID(), randomString(), randomString(),
            randomInteger().longValue(),
            getCcByLicense(), randomBoolean(), randomBoolean(), randomInstant());
    }
    
    private static File randomUnpublishedFile() {
        return buildNonAdministrativeAgreement().buildUnpublishedFile();
    }
    
    private static File randomPublishedFile() {
        return buildNonAdministrativeAgreement().buildPublishedFile();
    }
    
    private static File randomLegacyFile() {
        return buildNonAdministrativeAgreement().buildLegacyFile();
    }
    
    private static File.Builder buildNonAdministrativeAgreement() {
        return File.builder()
                   .withName(randomString())
                   .withAdministrativeAgreement(NOT_ADMINISTRATIVE_AGREEMENT)
                   .withMimeType(randomString())
                   .withSize(randomInteger().longValue())
                   .withEmbargoDate(randomInstant())
                   .withLicense(getCcByLicense())
                   .withIdentifier(UUID.randomUUID())
                   .withPublisherAuthority(randomBoolean());
    }
    
    private static File unpublishableNotAdministrativeAgreement() {
        return new UnpublishableFile(UUID.randomUUID(),
            randomString(),
            randomString(),
            randomInteger().longValue(),
            getCcByLicense(),
            NOT_ADMINISTRATIVE_AGREEMENT,
            randomBoolean(),
            randomInstant());
    }
    
    private static File.Builder admAgreementBuilder() {
        return File.builder()
                   .withAdministrativeAgreement(true)
                   .withName(randomString());
    }
    
    private File getAdministrativeAgreement(String fileName, boolean administrativeAgreement, License license) {
        return File.builder()
                   .withIdentifier(UUID.randomUUID())
                   .withLicense(license)
                   .withName(fileName)
                   .withAdministrativeAgreement(administrativeAgreement)
                   .buildUnpublishableFile();
    }
    
    private UnpublishableFile randomAdministrativeAgreement() {
        return new UnpublishableFile(UUID.randomUUID(), randomString(), randomString(),
            randomInteger().longValue(),
            getCcByLicense(), ADMINISTRATIVE_AGREEMENT, randomBoolean(), randomInstant());
    }
    
    private PublishedFile publishedFileWithActiveEmbargo() {
        return new PublishedFile(UUID.randomUUID(),
            randomString(),
            randomString(),
            randomInteger().longValue(),
            getCcByLicense(),
            NOT_ADMINISTRATIVE_AGREEMENT,
            randomBoolean(),
            Instant.now().plus(1, DAYS));
    }
    
    private File illegalPublishedFile() {
        return admAgreementBuilder().buildPublishedFile();
    }
    
    private File illegalUnPublishedFile() {
        return admAgreementBuilder().buildUnpublishedFile();
    }
    
    private File illegalLegacyFile() {
        return admAgreementBuilder().buildLegacyFile();
    }
    
    private File getPublishedFile(String fileName, boolean administrativeAgreement, License license) {
        return getPublishedFile(UUID.randomUUID(), fileName, administrativeAgreement, null, license);
    }
    
    private File getPublishedFile(UUID identifier,
                                  String fileName,
                                  boolean administrativeAgreement,
                                  Instant embargo,
                                  License license) {
        return File.builder()
                   .withAdministrativeAgreement(administrativeAgreement)
                   .withEmbargoDate(embargo)
                   .withIdentifier(identifier)
                   .withLicense(license)
                   .withMimeType(APPLICATION_PDF)
                   .withName(fileName)
                   .withPublisherAuthority(true)
                   .withSize(SIZE)
                   .buildPublishedFile();
    }
}