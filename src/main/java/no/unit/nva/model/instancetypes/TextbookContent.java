package no.unit.nva.model.instancetypes;

public interface TextbookContent {

    @Deprecated
    default Boolean isTextbookContent() {
        return null;
    }

    @Deprecated
    void setTextbookContent(boolean textbookContent);
}
