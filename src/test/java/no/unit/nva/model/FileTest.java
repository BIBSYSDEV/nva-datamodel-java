package no.unit.nva.model;

import no.unit.nva.model.exceptions.MissingLicenseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class FileTest {

    public static final String FILE_NAME = "Some file name.txt";
    public static final String MIME_TYPE = "application/pdf";
    public static final long FILE_SIZE = 1L;

    @DisplayName("The constructor exists")
    @Test
    void fileConstructorExists() {
        new File(UUID.randomUUID(), FILE_NAME, MIME_TYPE, FILE_SIZE, getLicense(), false, true, null);
    }

    private License getLicense() {
        return new License.Builder()
                    .withIdentifier("NVA-TEST-LICENSE")
                    .withLabels(Collections.singletonMap("en", "NVA-test-license"))
                    .withLink(URI.create("https://example.org/nva-test-license"))
                    .build();
    }

    @DisplayName("A file that is an administrative agreement does not need a license")
    @Test
    void fileWithAdministrativeAgreementAndNoLicenseIsValid() {
        assertDoesNotThrow(() -> {
            new File(UUID.randomUUID(), FILE_NAME, MIME_TYPE, FILE_SIZE, null, true, true, null);
        });
    }

    @DisplayName("A file validation that is not an administrative agreement with no license throws "
        + "MissingLicenseException")
    @Test
    void fileValidationWithNoLicenseAndNotAdministrativeAgreementThrowsMissingLicenseException() {
        File file = new File(UUID.randomUUID(), FILE_NAME, MIME_TYPE, FILE_SIZE, null, false, true, null);
        MissingLicenseException exception = assertThrows(MissingLicenseException.class, () -> {
            file.validate();
        });

        assertEquals(File.MISSING_LICENSE, exception.getMessage());
    }
}
