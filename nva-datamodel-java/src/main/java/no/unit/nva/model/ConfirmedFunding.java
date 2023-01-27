package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.net.URI;
import java.time.Instant;
import java.util.Map;
import java.util.Objects;
import nva.commons.core.JacocoGenerated;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class ConfirmedFunding implements Funding {

    private URI source;
    private URI id;
    private String identifier;
    private Map<String, String> labels;
    private MonetaryAmount fundingAmount;
    private Instant activeFrom;
    private Instant activeTo;

    public ConfirmedFunding() {
    }

    @Override
    public URI getSource() {
        return source;
    }

    @Override
    public void setSource(URI source) {
        this.source = source;
    }

    public URI getId() {
        return id;
    }

    public void setId(URI id) {
        this.id = id;
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public Map<String, String> getLabels() {
        return labels;
    }

    @Override
    public void setLabels(Map<String, String> labels) {
        this.labels = labels;
    }

    @Override
    public MonetaryAmount getFundingAmount() {
        return fundingAmount;
    }

    @Override
    public void setFundingAmount(MonetaryAmount fundingAmount) {
        this.fundingAmount = fundingAmount;
    }

    @Override
    public Instant getActiveFrom() {
        return activeFrom;
    }

    @Override
    public void setActiveFrom(Instant activeFrom) {
        this.activeFrom = activeFrom;
    }

    @Override
    public Instant getActiveTo() {
        return activeTo;
    }

    @Override
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
        ConfirmedFunding funding = (ConfirmedFunding) o;
        return source.equals(funding.source)
               && Objects.equals(id, funding.id)
               && Objects.equals(identifier, funding.identifier)
               && labels.equals(funding.labels)
               && Objects.equals(fundingAmount, funding.fundingAmount)
               && Objects.equals(activeFrom, funding.activeFrom)
               && Objects.equals(activeTo, funding.activeTo);
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(source, id, identifier, labels, fundingAmount, activeFrom, activeTo);
    }
}
