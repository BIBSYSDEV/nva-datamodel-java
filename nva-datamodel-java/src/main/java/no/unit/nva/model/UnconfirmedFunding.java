package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.net.URI;
import java.time.Instant;
import java.util.Map;
import java.util.Objects;
import nva.commons.core.JacocoGenerated;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class UnconfirmedFunding implements Funding {
    private URI source;
    private String identifier;
    private Map<String, String> labels;
    private MonetaryAmount fundingAmount;
    private Instant activeFrom;
    private Instant activeTo;

    public UnconfirmedFunding() {
    }

    @Override
    public URI getSource() {
        return source;
    }

    @Override
    public void setSource(URI source) {
        this.source = source;
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
        UnconfirmedFunding unconfirmedFunding = (UnconfirmedFunding) o;
        return source.equals(unconfirmedFunding.source)
               && Objects.equals(identifier, unconfirmedFunding.identifier)
               && labels.equals(unconfirmedFunding.labels)
               && Objects.equals(fundingAmount, unconfirmedFunding.fundingAmount)
               && Objects.equals(activeFrom, unconfirmedFunding.activeFrom)
               && Objects.equals(activeTo, unconfirmedFunding.activeTo);
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(source, identifier, labels, fundingAmount, activeFrom, activeTo);
    }

}
