package no.unit.nva.model;

import java.util.Objects;

public class MonetaryAmount {
    private Currency currency;
    private long amount;

    public MonetaryAmount() {
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MonetaryAmount that = (MonetaryAmount) o;
        return amount == that.amount && currency == that.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, amount);
    }
}
