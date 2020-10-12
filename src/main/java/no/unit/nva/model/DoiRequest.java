package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import nva.commons.utils.JacocoGenerated;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class DoiRequest {

    private DoiRequestStatus status;
    private Instant createdDate;
    private List<DoiRequestMessage> messages;

    @JacocoGenerated
    public DoiRequest() {
        this.messages = Collections.emptyList();
    }

    private DoiRequest(Builder builder) {
        setStatus(builder.status);
        setCreatedDate(builder.createdDate);
        setMessages(builder.messages);
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public DoiRequestStatus getStatus() {
        return status;
    }

    public void setStatus(DoiRequestStatus status) {
        this.status = status;
    }

    public List<DoiRequestMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<DoiRequestMessage> messages) {
        this.messages = messages;
    }

    /**
     * Create a copy of DoiRequest.
     *
     * @return  DoiRequest copy
     */
    public DoiRequest.Builder copy() {
        return new DoiRequest.Builder()
            .withStatus(getStatus())
            .withCreatedDate(getCreatedDate())
            .withMessages(getMessages());
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
        DoiRequest that = (DoiRequest) o;

        return getStatus() == that.getStatus()
                && Objects.equals(getCreatedDate(), that.getCreatedDate())
                && Objects.equals(getMessages(), that.getMessages());
    }

    @JacocoGenerated
    @Override
    public int hashCode() {
        return Objects.hash(getStatus(), getCreatedDate(), getMessages());
    }


    @SuppressWarnings("MissingJavadocMethod")
    public static final class Builder {
        private DoiRequestStatus status;
        private Instant createdDate;
        private List<DoiRequestMessage> messages;

        public Builder() {
            messages = Collections.emptyList();
        }

        public Builder(DoiRequest copy) {
            this.status = copy.getStatus();
            this.createdDate = copy.getCreatedDate();
            this.messages = copy.getMessages();
        }

        public Builder withStatus(DoiRequestStatus status) {
            this.status = status;
            return this;
        }

        public Builder withCreatedDate(Instant createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public Builder withMessages(List<DoiRequestMessage> messages) {
            this.messages = messages;
            return this;
        }

        public Builder addMessage(DoiRequestMessage message) {
            initializeMessagesIfEmpty();
            this.messages.add(message);
            return this;
        }

        private void initializeMessagesIfEmpty() {
            if (this.messages.isEmpty()) {
                this.messages = new ArrayList<>();
            }
        }

        public DoiRequest build() {
            return new DoiRequest(this);
        }
    }
}
