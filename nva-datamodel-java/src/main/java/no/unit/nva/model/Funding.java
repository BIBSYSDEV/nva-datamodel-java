package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.net.URI;
import java.time.Instant;
import java.util.Map;
import java.util.Objects;
import nva.commons.core.JacocoGenerated;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Funding {

    private URI source;
    private URI id;
    private String identifier;
    private String name;
    private Map<String, String> alternativeName;
    private MonetaryAmount fundingAmount;
    private Instant activeFrom;
    private Instant activeTo;

    public Funding() {
    }

    public URI getSource() {
        return source;
    }

    public void setSource(URI source) {
        this.source = source;
    }

    public URI getId() {
        return id;
    }

    public void setId(URI id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getAlternativeName() {
        return alternativeName;
    }

    public void setAlternativeName(Map<String, String> alternativeName) {
        this.alternativeName = alternativeName;
    }

    public MonetaryAmount getFundingAmount() {
        return fundingAmount;
    }

    public void setFundingAmount(MonetaryAmount fundingAmount) {
        this.fundingAmount = fundingAmount;
    }

    public Instant getActiveFrom() {
        return activeFrom;
    }

    public void setActiveFrom(Instant activeFrom) {
        this.activeFrom = activeFrom;
    }

    public Instant getActiveTo() {
        return activeTo;
    }

    public void setActiveTo(Instant activeTo) {
        this.activeTo = activeTo;
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Funding funding = (Funding) o;
        return source.equals(funding.source)
               && Objects.equals(id, funding.id)
               && Objects.equals(identifier, funding.identifier)
               && name.equals(funding.name)
               && alternativeName.equals(funding.alternativeName)
               && Objects.equals(fundingAmount, funding.fundingAmount)
               && Objects.equals(activeFrom, funding.activeFrom)
               && Objects.equals(activeTo, funding.activeTo);
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(source, id, identifier, name, alternativeName, fundingAmount, activeFrom, activeTo);
    }
}
