package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import nva.commons.utils.JacocoGenerated;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class DoiRequest {

    private DoiRequestStatus status;
    private Instant date;
    private List<DoiRequestMessage> messages;

    @JacocoGenerated
    public DoiRequest() {
    }

    private DoiRequest(Builder builder) {
        setStatus(builder.status);
        setDate(builder.date);
        setMessages(builder.messages);
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

    public List<DoiRequestMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<DoiRequestMessage> messages) {
        this.messages = messages;
    }


    public DoiRequest.Builder copy() {
        return new DoiRequest.Builder().withStatus(getStatus()).withDate(getDate()).withMessages(getMessages());
    }


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
                && Objects.equals(getDate(), that.getDate())
                && Objects.equals(getMessages(), that.getMessages());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStatus(), getDate(), getMessages());
    }

    @SuppressWarnings("MissingJavadocMethod")
    public static final class Builder {
        private DoiRequestStatus status;
        private Instant date;
        private List<DoiRequestMessage> messages;

        public Builder() {
        }

        public Builder(DoiRequest copy) {
            this.status = copy.getStatus();
            this.date = copy.getDate();
            this.messages = copy.getMessages();
        }

        public Builder withStatus(DoiRequestStatus status) {
            this.status = status;
            return this;
        }

        public Builder withDate(Instant date) {
            this.date = date;
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
            if (isNull(this.messages)) {
                this.messages = new ArrayList<>();
            }
        }

        public DoiRequest build() {
            return new DoiRequest(this);
        }
    }
}
