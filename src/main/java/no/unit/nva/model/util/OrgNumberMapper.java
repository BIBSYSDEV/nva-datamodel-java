package no.unit.nva.model.util;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class OrgNumberMapper {

    public static final Map<String,URI> orgNumberCristinIdMap;
    public static final String NO_MAPPING_ERROR_MESSAGE = "No mapping for orgNumber: ";

    static {
        orgNumberCristinIdMap = new HashMap<>();
        orgNumberCristinIdMap.put("919477822", URI.create("https://api.cristin.no/v2/institutions/20202"));
        orgNumberCristinIdMap.put("914086434", URI.create("https://api.cristin.no/v2/units/7467.0.0.0"));
        orgNumberCristinIdMap.put("952125001", URI.create("https://api.cristin.no/v2/units/258.0.0.0"));
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
