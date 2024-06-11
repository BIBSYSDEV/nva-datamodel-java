package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.time.Instant;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public record ImportDetail(Instant importDate, ImportSource source) { }
