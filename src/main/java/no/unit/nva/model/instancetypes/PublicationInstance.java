package no.unit.nva.model.instancetypes;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.unit.nva.model.exceptions.InvalidPageTypeException;
import no.unit.nva.model.pages.Pages;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({@JsonSubTypes.Type(name = "JournalArticle", value = JournalArticle.class)})
public interface PublicationInstance {

    Pages getPages();

    void setPages(Pages pages) throws InvalidPageTypeException;

    void setPeerReviewed(boolean peerReviewed);

    boolean isPeerReviewed();
}
