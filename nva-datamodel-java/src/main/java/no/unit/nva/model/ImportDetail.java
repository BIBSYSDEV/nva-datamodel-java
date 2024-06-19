package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.time.Instant;
import no.unit.nva.model.ImportSource.Source;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public record ImportDetail(Instant importDate, ImportSource source) {

    public static ImportDetail fromSource(Source source, Instant importDate) {
        return new ImportDetail(importDate, ImportSource.fromSource(source));
    }

    public static ImportDetail fromBrageArchive(String archive, Instant importDate) {
        return new ImportDetail(importDate, ImportSource.fromBrageArchive(archive));
    }
}
