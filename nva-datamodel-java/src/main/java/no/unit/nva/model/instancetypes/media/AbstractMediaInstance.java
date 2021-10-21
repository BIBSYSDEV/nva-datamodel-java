package no.unit.nva.model.instancetypes.media;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import no.unit.nva.model.instancetypes.PublicationInstance;
import no.unit.nva.model.pages.NullPages;
import nva.commons.core.JacocoGenerated;

import java.util.Objects;

public abstract class AbstractMediaInstance implements PublicationInstance<NullPages> {

    private static final String PAGES = "pages";
    public static final String CONTRIBUTION_TYPE = "contributionType";

    @JsonProperty(CONTRIBUTION_TYPE)
    private final ContributionType contributionType;
    @JsonProperty(PAGES)
    private static final NullPages pages = new NullPages();

    protected AbstractMediaInstance(@JsonProperty(CONTRIBUTION_TYPE) ContributionType contributionType) {
        this.contributionType = contributionType;
    }

    @JsonGetter(CONTRIBUTION_TYPE)
    public ContributionType getContributionType() {
        return contributionType;
    }

    @JsonGetter(PAGES)
    @Override
    public NullPages getPages() {
        return pages;
    }

    @JsonSetter(PAGES)
    @Override
    public abstract void setPages(NullPages pages);

    @Override
    public boolean isPeerReviewed() {
        return false;
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractMediaInstance)) {
            return false;
        }
        AbstractMediaInstance that = (AbstractMediaInstance) o;
        return getContributionType() == that.getContributionType();
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getContributionType());
    }
}
