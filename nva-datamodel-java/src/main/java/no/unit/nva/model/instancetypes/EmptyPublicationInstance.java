package no.unit.nva.model.instancetypes;

import no.unit.nva.model.pages.NullPages;

public class EmptyPublicationInstance implements PublicationInstance<NullPages> {

    private EmptyPublicationInstance(){
    }
    public static EmptyPublicationInstance create(){
        return new EmptyPublicationInstance();
    }


    @Override
    public NullPages getPages() {
        return new NullPages();
    }

    @Override
    public void setPages(NullPages pages) {

    }

    @Override
    public boolean isPeerReviewed() {
        return false;
    }
}
