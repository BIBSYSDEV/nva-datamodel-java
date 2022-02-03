package no.unit.nva.model.instancetypes.artistic.realization;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.instancetypes.artistic.ArchitectureOutput;
import no.unit.nva.model.time.Instant;
import no.unit.nva.model.time.Time;
import nva.commons.core.JacocoGenerated;

import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class MentionInPublication implements ArchitectureOutput {

    public static final String TITLE = "title";
    public static final String ISSUE = "issue";
    public static final String TIME = "time";
    public static final String OTHER_INFORMATION = "otherInformation";

    @JsonProperty(TITLE)
    private final String title;
    @JsonProperty(ISSUE)
    private final String issue;
    @JsonProperty(TIME)
    private final Time time;
    @JsonProperty(OTHER_INFORMATION)
    private final String otherInformation;
    @JsonProperty(SEQUENCE)
    private final int sequence;

    public MentionInPublication(@JsonProperty(TITLE) String title,
                                @JsonProperty(ISSUE) String issue,
                                @JsonProperty(TIME) Instant time,
                                @JsonProperty(OTHER_INFORMATION) String otherInformation,
                                @JsonProperty(SEQUENCE) int sequence) {
        this.title = title;
        this.issue = issue;
        this.time = time;
        this.otherInformation = otherInformation;
        this.sequence = sequence;
    }

    public String getTitle() {
        return title;
    }

    public String getIssue() {
        return issue;
    }

    public Time getTime() {
        return time;
    }

    public String getOtherInformation() {
        return otherInformation;
    }

    @Override
    public int getSequence() {
        return sequence;
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MentionInPublication)) {
            return false;
        }
        MentionInPublication that = (MentionInPublication) o;
        return getSequence() == that.getSequence()
                && Objects.equals(getTitle(), that.getTitle())
                && Objects.equals(getIssue(), that.getIssue())
                && Objects.equals(getTime(), that.getTime())
                && Objects.equals(getOtherInformation(), that.getOtherInformation());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getIssue(), getTime(), getOtherInformation(), getSequence());
    }
}
