package no.unit.nva.model.contexttypes;

public final  class EmptyPublicationContext implements PublicationContext {

    private EmptyPublicationContext(){

    }

    public static EmptyPublicationContext create(){
        return new EmptyPublicationContext();
    }



}
