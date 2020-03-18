package no.unit.nva.model.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URI;

import static no.unit.nva.model.util.OrgNumberMapper.NO_MAPPING_ERROR_MESSAGE;

public class OrgNumberMapperTest {

    public static final String VALID_CRISTIN_ID = "https://api.cristin.no/v2/institutions/20202";
    public static final String VALID_ORG_NUMBER = "919477822";
    public static final String INVALID_ORG_NUMBER = "123";

    @Test
    public void orgNumberExistingInMapReturnsCristinId() {
        URI cristinId = OrgNumberMapper.toCristinId(VALID_ORG_NUMBER);
        Assertions.assertEquals(VALID_CRISTIN_ID, cristinId.toString());
    }

    @Test
    public void prgNumberMissingInMapThrowsException() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            OrgNumberMapper.toCristinId(INVALID_ORG_NUMBER);
        }, NO_MAPPING_ERROR_MESSAGE +  INVALID_ORG_NUMBER);
    }

}