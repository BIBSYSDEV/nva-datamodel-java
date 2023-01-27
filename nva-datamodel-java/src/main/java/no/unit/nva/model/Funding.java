package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.net.URI;
import java.time.Instant;
import java.util.Map;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(name = "ConfirmedFunding", value = ConfirmedFunding.class),
    @JsonSubTypes.Type(name = "UnconfirmedFunding", value = UnconfirmedFunding.class)
})
public interface Funding {
    URI getSource();

    void setSource(URI source);

    String getIdentifier();

    void setIdentifier(String identifier);

    Map<String, String> getLabels();

    void setLabels(Map<String, String> labels);

    MonetaryAmount getFundingAmount();

    void setFundingAmount(MonetaryAmount fundingAmount);

    Instant getActiveFrom();

    void setActiveFrom(Instant activeFrom);

    Instant getActiveTo();

    void setActiveTo(Instant activeTo);
}
