package no.unit.nva.model.instancetypes.artistic;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.instancetypes.PublicationInstance;
import no.unit.nva.model.pages.NullPages;
import nva.commons.core.JacocoGenerated;

import java.util.List;
import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Architecture implements PublicationInstance<NullPages> {
    public static final String SUBTYPE = "subtype";
    public static final String DESCRIPTION = "description";
    public static final String OUTPUT = "architectureOutput";

    @JsonProperty(SUBTYPE)
    private final ArchitectureSubtypeEnum subtype;
    @JsonProperty(DESCRIPTION)
    private final String description;
    @JsonProperty(OUTPUT)
    private final List<ArchitectureOutput> architectureOutput;

    public Architecture(@JsonProperty(SUBTYPE) ArchitectureSubtypeEnum subtype,
                        @JsonProperty(DESCRIPTION) String description,
                        @JsonProperty(OUTPUT) List<ArchitectureOutput> architectureOutput) {
        this.subtype = subtype;
        this.description = description;
        this.architectureOutput = architectureOutput;
    }

    @JsonGetter
    @Override
    public NullPages getPages() {
        return new NullPages();
    }

    @JsonSetter
    @Override
    public void setPages(NullPages pages) {

    }

    @JsonGetter
    @Override
    public boolean isPeerReviewed() {
        return false;
    }

    public ArchitectureSubtypeEnum getSubtype() {
        return subtype;
    }

    public String getDescription() {
        return description;
    }

    public List<ArchitectureOutput> getArchitectureOutput() {
        return architectureOutput;
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Architecture)) {
            return false;
        }
        Architecture that = (Architecture) o;
        return getSubtype() == that.getSubtype()
                && Objects.equals(getDescription(), that.getDescription())
                && Objects.equals(getArchitectureOutput(), that.getArchitectureOutput());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getSubtype(), getDescription(), getArchitectureOutput());
    }
}
