package no.unit.nva.model.instancetypes.media;

public enum ContributionType {
    INTERNET("Internet"),
    JOURNAL("Journal"),
    NEWSPAPER("Newspaper"),
    OTHER("Other"),
    RADIO("Radio"),
    TELEVISION("Television");

    private final String name;

    ContributionType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
