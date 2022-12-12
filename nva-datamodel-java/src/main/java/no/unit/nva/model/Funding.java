package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.net.URI;
import java.util.Map;
import java.util.Objects;
import nva.commons.core.JacocoGenerated;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class Funding {

    private URI source;
    private String identifier;
    private String mainTitle;
    private Map<String, String> alternativeTitles;
    private MonetaryAmount amount;

    public Funding() {
    }

    public URI getSource() {
        return source;
    }

    public void setSource(URI source) {
        this.source = source;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getMainTitle() {
        return mainTitle;
    }

    public void setMainTitle(String mainTitle) {
        this.mainTitle = mainTitle;
    }

    public Map<String, String> getAlternativeTitles() {
        return alternativeTitles;
    }

    public void setAlternativeTitles(Map<String, String> alternativeTitles) {
        this.alternativeTitles = alternativeTitles;
    }

    public MonetaryAmount getAmount() {
        return amount;
    }

    public void setAmount(MonetaryAmount amount) {
        this.amount = amount;
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
               && identifier.equals(funding.identifier)
               && mainTitle.equals(funding.mainTitle)
               && Objects.equals(alternativeTitles, funding.alternativeTitles)
               && Objects.equals(amount, funding.amount);
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(source, identifier, mainTitle, alternativeTitles, amount);
    }
}
