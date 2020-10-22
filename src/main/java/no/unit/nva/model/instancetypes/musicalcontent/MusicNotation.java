package no.unit.nva.model.instancetypes.musicalcontent;

import static no.unit.nva.model.instancetypes.musicalcontent.IsmnValidator.validate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import no.unit.nva.model.instancetypes.NonPeerReviewedPaper;
import no.unit.nva.model.instancetypes.musicalcontent.exception.InvalidIsmnException;
import no.unit.nva.model.pages.Range;
import nva.commons.utils.JacocoGenerated;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class MusicNotation extends NonPeerReviewedPaper {

    public static final String ISMN_HYPHEN = "-";
    public static final int CHECKBIT_ISMN_13 = 12;
    public static final String ISMN_10_PREFIX = "M";
    public static final String ISMN_13_PREFIX = "9790";
    public static final int PREFIX_BEGIN = 0;
    public static final int PREFIX_END = 4;
    public static final int INFIX_BEGIN = 3;
    public static final int REGISTRANT_END = 5;
    public static final int THREE_CHARACTER_REGISTRANT = 7;
    public static final int FOUR_CHARACTER_REGISTRANT = 8;
    public static final int FIVE_CHARACTER_REGISTRANT = 9;
    public static final int SIX_CHARACTER_REGISTRANT = 10;
    public static final int SEVEN_CHARACTER_REGISTRANT = 11;
    public static final Map<String, Integer> REGISTRANTS = Map.of(
        "0", THREE_CHARACTER_REGISTRANT,
        "1", FOUR_CHARACTER_REGISTRANT,
        "2", FOUR_CHARACTER_REGISTRANT,
        "3", FOUR_CHARACTER_REGISTRANT,
        "4", FIVE_CHARACTER_REGISTRANT,
        "5", FIVE_CHARACTER_REGISTRANT,
        "6", FIVE_CHARACTER_REGISTRANT,
        "7", SIX_CHARACTER_REGISTRANT,
        "8", SIX_CHARACTER_REGISTRANT,
        "9", SEVEN_CHARACTER_REGISTRANT);

    private final String ismn;

    /**
     * Creates an instance of Music Notation with all properties set.
     *
     * @param pages A range of pages.
     * @param ismn  The International Standard Music Number identifier string following ISO TC 46/SC 9.
     * @throws InvalidIsmnException If the ISMN is invalid.
     */
    @JsonCreator
    public MusicNotation(@JsonProperty("pages") Range pages, @JsonProperty("ismn") String ismn) throws
                                                                                                InvalidIsmnException {
        super(pages);
        this.ismn = extractBareIsmn(ismn);
    }

    private MusicNotation(Builder builder) throws InvalidIsmnException {
        this(builder.pages, builder.ismn);
    }

    /**
     * This returns a hyphenated version of the ISMN for presentation and search purposes.
     *
     * @return Hyphenated representation of the stored string.
     */
    public String getFormattedIsmn() {
        return formatIsmnStringWithHyphens();
    }

    @JacocoGenerated
    public String getIsmn() {
        return ismn;
    }

    private String formatIsmnStringWithHyphens() {
        String registrant = extractIsmnRegistrant();
        return String.join(ISMN_HYPHEN,
            formatIsmnPrefix(),
            registrant,
            extractIsmnItem(registrant),
            extractIsmnCheckbit()
        );
    }

    private String extractIsmnCheckbit() {
        return ismn.substring(CHECKBIT_ISMN_13);
    }

    private String extractIsmnItem(String registrant) {
        return ismn.substring(PREFIX_END + registrant.length(), CHECKBIT_ISMN_13);
    }

    private String formatIsmnPrefix() {
        var musicland = ismn.substring(PREFIX_BEGIN, INFIX_BEGIN);
        var infix = ismn.substring(INFIX_BEGIN, PREFIX_END);
        return String.join(ISMN_HYPHEN, musicland, infix);
    }

    private String extractIsmnRegistrant() {
        var registrantBegin = ismn.substring(PREFIX_END, REGISTRANT_END);
        return ismn.substring(PREFIX_END, REGISTRANTS.get(registrantBegin));
    }

    private String extractBareIsmn(String candidate) throws InvalidIsmnException {
        List<Integer> bareCandidate = extractCandidateDigits(candidate);
        validate(candidate);
        return digitListToString(bareCandidate);
    }

    private String digitListToString(List<Integer> bareCandidate) {
        return bareCandidate.stream()
            .map(String::valueOf)
            .collect(Collectors.joining());
    }

    private List<Integer> extractCandidateDigits(String candidate) {
        return candidate.startsWith(ISMN_10_PREFIX)
            ? convertToDigitList(candidate.replace(ISMN_10_PREFIX, ISMN_13_PREFIX))
            : convertToDigitList(candidate);
    }

    private List<Integer> convertToDigitList(String candidate) {
        return candidate.chars()
            .mapToObj(Character::getNumericValue)
            .filter(c -> c >= 0 && c <= 9)
            .collect(Collectors.toList());
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
