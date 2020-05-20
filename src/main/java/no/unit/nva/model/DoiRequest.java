package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.time.Instant;
import java.util.Objects;
import nva.commons.utils.JacocoGenerated;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class DoiRequest {

    private DoiRequestStatus status;
    private Instant date;

    public DoiRequest() {

    }

    private DoiRequest(DoiRequest.Builder builder) {
        setDate(builder.date);
        setStatus(builder.status);
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public DoiRequestStatus getStatus() {
        return status;
    }

    public void setStatus(DoiRequestStatus status) {
        this.status = status;
    }

    @JacocoGenerated
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DoiRequest)) {
            return false;
        }
        DoiRequest doiRequest = (DoiRequest) o;
        return Objects.equals(getDate(), doiRequest.getDate())
                && Objects.equals(getStatus(), doiRequest.getStatus());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getDate(), getStatus());
    }

    public static final class Builder {
        private Instant date;
        private DoiRequestStatus status;

        public Builder() {
        }

        public DoiRequest.Builder withDate(Instant date) {
            this.date = date;
            return this;
        }

        public DoiRequest.Builder withStatus(DoiRequestStatus status) {
            this.status = status;
            return this;
        }

        public DoiRequest build() {
            return new DoiRequest(this);
        }
    }
}
