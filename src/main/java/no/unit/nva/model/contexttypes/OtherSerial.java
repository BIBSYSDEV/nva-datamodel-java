package no.unit.nva.model.contexttypes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.Level;
import no.unit.nva.model.exceptions.InvalidIssnException;
import nva.commons.utils.JacocoGenerated;

import java.net.URL;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class OtherSerial extends Journal {

    @JacocoGenerated
    @JsonCreator
    public OtherSerial() {
        super();
    }

    private OtherSerial(Builder builder) throws InvalidIssnException {
        super();
        setTitle(builder.title);
        setLevel(builder.level);
        setOpenAccess(builder.openAccess);
        setPeerReviewed(builder.peerReviewed);
        setPrintIssn(builder.printIssn);
        setOnlineIssn(builder.onlineIssn);
        setUrl(builder.url);
    }

    public static final class Builder {
        private String title;
        private Level level;
        private boolean openAccess;
        private boolean peerReviewed;
        private String printIssn;
        private String onlineIssn;
        private URL url;

        public Builder() {
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withLevel(Level level) {
            this.level = level;
            return this;
        }

        public Builder withOpenAccess(boolean openAccess) {
            this.openAccess = openAccess;
            return this;
        }

        public Builder withPeerReviewed(boolean peerReviewed) {
            this.peerReviewed = peerReviewed;
            return this;
        }

        public Builder withPrintIssn(String printIssn) {
            this.printIssn = printIssn;
            return this;
        }

        public Builder withOnlineIssn(String onlineIssn) {
            this.onlineIssn = onlineIssn;
            return this;
        }

        public Builder withUrl(URL url) {
            this.url = url;
            return this;
        }

        public OtherSerial build() throws InvalidIssnException {
            return new OtherSerial(this);
        }
    }
}
