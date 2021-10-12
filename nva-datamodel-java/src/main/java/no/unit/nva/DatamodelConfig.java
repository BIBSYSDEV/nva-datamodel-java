package no.unit.nva;

import static java.util.Objects.nonNull;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import nva.commons.core.JsonUtils;

public class DatamodelConfig {

    public static final ObjectMapper dataModelObjectMapper = JsonUtils.dtoObjectMapper;


    public static <T> Set<T> nonEmptyOrDefault(Set<T> set) {
        return nonNull(set) ? set : Collections.emptySet();
    }

    public static <T> List<T> nonEmptyOrDefault(List<T> list) {
        return nonNull(list) ? list : Collections.emptyList();
    }

    public static <K, V> Map<K, V> nonEmptyOrDefault(Map<K, V> map) {
        return nonNull(map) ? map : Collections.emptyMap();
    }
}
