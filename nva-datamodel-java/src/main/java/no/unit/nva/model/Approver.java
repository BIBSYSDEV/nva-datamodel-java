package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.Objects;
import nva.commons.core.JacocoGenerated;

@JsonTypeInfo(use = Id.NAME, property = "type")
@JsonTypeName(Approver.TYPE)
public class Approver {

    public static final String TYPE = "User";
    public static final String USERNAME = "username";
    @JsonProperty(USERNAME)
    private final String username;

    @JsonCreator
    public Approver(@JsonProperty(USERNAME) String username) {
        this.username = username;
    }

    public static Approver fromPublication(Publication publication) {
        return new Approver(publication.getResourceOwner().getOwner());
    }

    @Override
    @JacocoGenerated
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    @JacocoGenerated
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Approver)) {
            return false;
        }
        Approver user = (Approver) o;
        return Objects.equals(username, user.username);
    }
}

