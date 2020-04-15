package no.unit.nva.model.pages;

import java.util.Objects;

public class Composite implements Pages {
    private Range introduction;
    private String pages;
    private boolean illustrated;

    private Composite(Builder builder) {
        setIntroduction(builder.introduction);
        setPages(builder.pages);
        setIllustrated(builder.illustrated);
    }

    public Range getIntroduction() {
        return introduction;
    }

    public void setIntroduction(Range introduction) {
        this.introduction = introduction;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public boolean isIllustrated() {
        return illustrated;
    }

    public void setIllustrated(boolean illustrated) {
        this.illustrated = illustrated;
    }

    public static final class Builder {
        private Range introduction;
        private String pages;
        private boolean illustrated;

        public Builder() {
        }

        public Builder withIntroduction(Range introduction) {
            this.introduction = introduction;
            return this;
        }

        public Builder withPages(String pages) {
            this.pages = pages;
            return this;
        }

        public Builder withIllustrated(boolean illustrated) {
            this.illustrated = illustrated;
            return this;
        }

        public Composite build() {
            return new Composite(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Composite)) {
            return false;
        }
        Composite composite = (Composite) o;
        return isIllustrated() == composite.isIllustrated()
                && Objects.equals(getIntroduction(), composite.getIntroduction())
                && Objects.equals(getPages(), composite.getPages());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIntroduction(), getPages(), isIllustrated());
    }
}
