package no.unit.nva.model.contexttypes;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static no.unit.nva.model.contexttypes.utils.MigrateChannelIdUtil.isNewStyleIdentifier;
import static no.unit.nva.model.contexttypes.utils.MigrateChannelIdUtil.migrateToNewIdIfFound;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.net.URI;
import java.util.Objects;
import no.unit.nva.model.contexttypes.utils.ChannelType;
import nva.commons.core.JacocoGenerated;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Publisher implements PublishingHouse {

    private final URI id;

    @JsonCreator
    public Publisher(@JsonProperty("id") URI id) {
        this.id = isNull(id) || isNewStyleIdentifier(id) ? id : migrateToNewIdIfFound(id,
                                                                                      ChannelType.PUBLISHER);
    }

    public URI getId() {
        return id;
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Publisher)) {
            return false;
        }
        Publisher publisher = (Publisher) o;
        return Objects.equals(getId(), publisher.getId());
    }

    @Override
    public boolean isValid() {
        return nonNull(id);
    }
}
