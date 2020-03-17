package no.unit.nva.model.util;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class OrgNumberMapper {

    public static final Map<String,URI> orgNumberCristinIdMap;
    public static final String NO_MAPPING_ERROR_MESSAGE = "No mapping for orgNumber: ";

    public static final String UNIT_ORG_NUMBER = "919477822";
    public static final URI UNIT_CRISTIN_ID = URI.create("https://api.cristin.no/v2/institutions/20202");
    public static final String NORSK_REGNESENTRAL_ORG_NUMBER = "914086434";
    public static final URI NORSK_REGNESENTRAL_CRISTIN_ID = URI.create("https://api.cristin.no/v2/units/7467.0.0.0");
    public static final String FJELLHAUG_INTERNASJONALE_SKOLE_ORG_NUMBER = "952125001";
    public static final URI FJELLHAUG_INTERNASJONALE_SKOLE_CRISTIN_ID =
            URI.create("https://api.cristin.no/v2/units/258.0.0.0");

    static {
        orgNumberCristinIdMap = new HashMap<>();
        orgNumberCristinIdMap.put(UNIT_ORG_NUMBER, UNIT_CRISTIN_ID);
        orgNumberCristinIdMap.put(NORSK_REGNESENTRAL_ORG_NUMBER, NORSK_REGNESENTRAL_CRISTIN_ID);
        orgNumberCristinIdMap.put(FJELLHAUG_INTERNASJONALE_SKOLE_ORG_NUMBER, FJELLHAUG_INTERNASJONALE_SKOLE_CRISTIN_ID);
    }

    /**
     * Map OrgNumber to CristinId.
     *
     * @param orgNumber orgNumber
     * @return  cristinId
     */
    public static URI toCristinId(String orgNumber) {
        if (orgNumberCristinIdMap.containsKey(orgNumber)) {
            return orgNumberCristinIdMap.get(orgNumber);
        }
        throw new IllegalStateException(NO_MAPPING_ERROR_MESSAGE + orgNumber);
    }

}
