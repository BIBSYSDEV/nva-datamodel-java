package no.unit.nva.model.instancetypes.exhibition.manifestations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import no.unit.nva.model.time.Instant;
import no.unit.nva.model.time.Time;
import nva.commons.core.JacocoGenerated;

import java.util.Objects;

public class ExhibitionMentionInPublication implements ExhibitionProductionManifestation {

    public static final String TITLE_FIELD = "title";
    public static final String ISSUE_FIELD = "issue";
    public static final String DATE_FIELD = "date";
    public static final String OTHER_INFORMATION_FIELD = "otherInformation";

    @JsonProperty(TITLE_FIELD)
    private final String title;
    @JsonProperty(ISSUE_FIELD)
    private final String issue;
    @JsonProperty(DATE_FIELD)
    private final Time date;
    @JsonProperty(OTHER_INFORMATION_FIELD)
    private final String otherInformation;

    @JsonCreator
    public ExhibitionMentionInPublication(@JsonProperty(TITLE_FIELD) String title,
                                @JsonProperty(ISSUE_FIELD) String issue,
                                @JsonProperty(DATE_FIELD) Instant date,
                                @JsonProperty(OTHER_INFORMATION_FIELD) String otherInformation) {
        this.title = title;
        this.issue = issue;
        this.date = date;
        this.otherInformation = otherInformation;
    }

    public String getTitle() {
        return title;
    }

    public String getIssue() {
        return issue;
    }

    public Time getDate() {
        return date;
    }

    public String getOtherInformation() {
        return otherInformation;
    }

    @Override
    @JacocoGenerated
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ExhibitionMentionInPublication)) {
            return false;
        }
        ExhibitionMentionInPublication that = (ExhibitionMentionInPublication) o;
        return Objects.equals(getTitle(), that.getTitle())
                && Objects.equals(getIssue(), that.getIssue())
                && Objects.equals(getDate(), that.getDate())
                && Objects.equals(getOtherInformation(), that.getOtherInformation());
    }

    @Override
    @JacocoGenerated
    public int hashCode() {
        return Objects.hash(getTitle(), getIssue(), getDate(), getOtherInformation());
    }
}
