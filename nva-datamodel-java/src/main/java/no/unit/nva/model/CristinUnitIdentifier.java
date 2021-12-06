package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Objects;

public class CristinUnitIdentifier {

    public static final String SPLIT_PATTERN_FOR_CRISTIN_ORG_IDENTIFIERS = "\\.";
    private final int firstLevel;
    private final int secondLevel;
    private final int thirdLevel;
    private final int fourthLevel;

    private CristinUnitIdentifier(int firstLevel, int secondLevel, int thirdLevel, int fourthLevel) {
        this.firstLevel = firstLevel;
        this.secondLevel = secondLevel;
        this.thirdLevel = thirdLevel;
        this.fourthLevel = fourthLevel;
    }

    @JsonCreator
    public static CristinUnitIdentifier fromString(String cristinUnitId) {
        String[] levels = cristinUnitId.split(SPLIT_PATTERN_FOR_CRISTIN_ORG_IDENTIFIERS);
        int firstLevel = Integer.parseInt(levels[0]);
        int secondLevel = Integer.parseInt(levels[1]);
        int thirdLevel = Integer.parseInt(levels[2]);
        int fourthLevel = Integer.parseInt(levels[3]);
        return new CristinUnitIdentifier(firstLevel, secondLevel, thirdLevel, fourthLevel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstLevel, secondLevel, thirdLevel, fourthLevel);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CristinUnitIdentifier)) {
            return false;
        }
        CristinUnitIdentifier that = (CristinUnitIdentifier) o;
        return firstLevel == that.firstLevel
               && secondLevel == that.secondLevel
               && thirdLevel == that.thirdLevel
               && fourthLevel == that.fourthLevel;
    }

    @JsonValue
    @Override
    public String toString() {
        return String.format("%d.%d.%d.%d", firstLevel, secondLevel, thirdLevel, fourthLevel);
    }
}
