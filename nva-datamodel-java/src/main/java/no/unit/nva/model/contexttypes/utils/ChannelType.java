package no.unit.nva.model.contexttypes.utils;

public enum ChannelType {

    JOURNAL("journal", "journal_series_id_mapping_301023.csv"),
    PUBLISHER("publisher", "publisher_id_mapping_301023.csv"),
    SERIES("series", "journal_series_id_mapping_301023.csv");

    public final String pathElement;
    public final String migrationFileName;

    ChannelType(String pathElement, String migrationFileName) {
        this.pathElement = pathElement;
        this.migrationFileName = migrationFileName;
    }
}