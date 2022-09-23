package no.unit.nva.model.contexttypes.media;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;
import nva.commons.core.SingletonCollector;

public enum MediaSubTypeEnum {
    
    JOURNAL("Journal"),
    RADIO("Radio"),
    TV("TV"),
    INTERNET("Internet"),
    OTHER("Other");
    
    private String value;
    
    MediaSubTypeEnum(String value) {
        this.value = value;
    }
    
    @JsonCreator
    public static MediaSubTypeEnum parse(String candidate) {
        return Arrays.stream(MediaSubTypeEnum.values())
                   .filter(subType -> subType.value.equalsIgnoreCase(candidate))
                   .collect(SingletonCollector.collect());
    }
    
    @JsonValue
    public String getValue() {
        return value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
}
