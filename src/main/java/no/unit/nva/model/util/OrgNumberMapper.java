package no.unit.nva.model.util;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class OrgNumberMapper {

    public static final Map<String,URI> orgNumberCristinIdMap;
    public static final String NO_MAPPING_ERROR_MESSAGE = "No mapping for orgNumber: ";

    public static final String UNIT_ORG_NUMBER = "NO919477822";
    public static final URI UNIT_CRISTIN_ID = URI.create("https://api.cristin.no/v2/institutions/20202");

    public static final String NORSK_REGNESENTRAL_ORG_NUMBER = "NO914086434";
    public static final URI NORSK_REGNESENTRAL_CRISTIN_ID = URI.create("https://api.cristin.no/v2/units/7467.0.0.0");

    public static final String FJELLHAUG_ORG_NUMBER = "NO973259415";
    public static final URI FJELLHAUG_CRISTIN_ID = URI.create("https://api.cristin.no/v2/units/258.0.0.0");

    public static final String UIO_ORG_NUMBER = "NO971035854";
    public static final URI UIO_CRISTIN_ID = URI.create("https://api.cristin.no/v2/units/185.90.0.0");

    public static final String NINA_ORG_NUMBER = "NO950037687";
    public static final URI NINA_CRISTIN_ID = URI.create("https://api.cristin.no/v2/units/7511.0.0.0");

    public static final String NORCE_ORG_NUMBER = "NO987375639";
    public static final URI NORCE_CRISTIN_ID = URI.create("https://api.cristin.no/v2/units/2057.0.0.0");

    public static final String SINTEF_ORG_NUMBER = "NO919303808";
    public static final URI SINTEF_CRISTIN_ID = URI.create("https://api.cristin.no/v2/units/7401.0.0.0");

    public static final String NTNU_ORG_NUMBER = "NO974767880";
    public static final URI NTNU_CRISTIN_ID = URI.create("https://api.cristin.no/v2/units/194.0.0.0");

    public static final String BI_ORG_NUMBER = "NO971228865";
    public static final URI BI_CRISTIN_ID = URI.create("https://api.cristin.no/v2/units/158.0.0.0");


    static {
        orgNumberCristinIdMap = new HashMap<>();
        orgNumberCristinIdMap.put(UNIT_ORG_NUMBER, UNIT_CRISTIN_ID);
        orgNumberCristinIdMap.put(NORSK_REGNESENTRAL_ORG_NUMBER, NORSK_REGNESENTRAL_CRISTIN_ID);
        orgNumberCristinIdMap.put(FJELLHAUG_ORG_NUMBER, FJELLHAUG_CRISTIN_ID);
        orgNumberCristinIdMap.put(UIO_ORG_NUMBER, UIO_CRISTIN_ID);
        orgNumberCristinIdMap.put(NINA_ORG_NUMBER, NINA_CRISTIN_ID);
        orgNumberCristinIdMap.put(NORCE_ORG_NUMBER, NORCE_CRISTIN_ID);
        orgNumberCristinIdMap.put(SINTEF_ORG_NUMBER, SINTEF_CRISTIN_ID);
        orgNumberCristinIdMap.put(NTNU_ORG_NUMBER, NTNU_CRISTIN_ID);
        orgNumberCristinIdMap.put(BI_ORG_NUMBER, BI_CRISTIN_ID);
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
