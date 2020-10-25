package no.unit.nva.model.instancetypes.musicalcontent;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.instancetypes.NonPeerReviewedPaper;
import no.unit.nva.model.instancetypes.musicalcontent.exception.InvalidIsmnException;
import no.unit.nva.model.pages.Range;

import static java.util.Objects.nonNull;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class MusicNotation extends NonPeerReviewedPaper {

    private final Ismn ismn;

    /**
     * Creates an instance of Music Notation with all properties set.
     *
     * @param pages A range of pages.
     * @param ismn  The International Standard Music Number identifier string following ISO TC 46/SC 9.
     * @throws InvalidIsmnException If the ISMN is invalid.
     */
    @SuppressWarnings("PMD.NullAssignment")
    @JsonCreator
    public MusicNotation(@JsonProperty("pages") Range pages, @JsonProperty("ismn") String ismn) throws
            InvalidIsmnException {
        super(pages);
        this.ismn = nonNull(ismn) ? new Ismn(ismn) : null;
    }

    private MusicNotation(Builder builder) throws InvalidIsmnException {
        this(builder.pages, builder.ismn);
    }

    public String getIsmn() {
        return nonNull(ismn) ? ismn.raw() : null;
    }

    public String getFormattedIsmn() {
        return nonNull(ismn) ? ismn.formatted() : null;
    }

    public static final class Builder {

        private String ismn;
        private Range pages;

        public Builder() {
        }

        public Builder withIsmn(String ismn) {
            this.ismn = ismn;
            return this;
        }

        public Builder withPages(Range pages) {
            this.pages = pages;
            return this;
        }

        public MusicNotation build() throws InvalidIsmnException {
            return new MusicNotation(this);
        }
    }
}
