package no.unit.nva.model.contexttypes.utils;

public enum ChannelType {

    JOURNAL("journal", "journal_migration_example.csv"),
    PUBLISHER("publisher", "publisher_migration_example.csv");

    public final String pathElement;
    public final String migrationFileName;

    ChannelType(String pathElement, String migrationFileName) {
        this.pathElement = pathElement;
        this.migrationFileName = migrationFileName;
    }
}