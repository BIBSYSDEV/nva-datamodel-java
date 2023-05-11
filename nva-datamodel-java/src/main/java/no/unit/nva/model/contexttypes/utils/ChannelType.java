package no.unit.nva.model.contexttypes.utils;

public enum ChannelType {

    JOURNAL("journal", "2023-05-11-NVA-PID-Tidsskrift.csv"),
    PUBLISHER("publisher", "2023-05-11-NVA-PID-Utgiver.csv"),
    SERIES("series", "2023-05-11-NVA-PID-Serie.csv");

    public final String pathElement;
    public final String migrationFileName;

    ChannelType(String pathElement, String migrationFileName) {
        this.pathElement = pathElement;
        this.migrationFileName = migrationFileName;
    }
}