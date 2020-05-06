package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({@JsonSubTypes.Type(name = "Journal", value = Journal.class)})
public interface PublicationContext {

    String getTitle();

    void setTitle(String title);

    Level getLevel();

    void setLevel(Level level);

    boolean isOpenAccess();

    void setOpenAccess(boolean openAccess);

    boolean isPeerReviewed();

    void setPeerReviewed(boolean peerReviewed);
}
