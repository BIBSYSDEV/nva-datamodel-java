package no.unit.nva.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.time.Instant;
import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class DoiRequestMessage {

    private String text;
    private String author;
    private Instant timestamp;

    public DoiRequestMessage() {

    }

    private DoiRequestMessage(Builder builder) {
        setText(builder.text);
        setAuthor(builder.author);
        setTimestamp(builder.timestamp);
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DoiRequestMessage that = (DoiRequestMessage) o;
        return Objects.equals(getText(), that.getText())
                && Objects.equals(getAuthor(), that.getAuthor())
                && Objects.equals(getTimestamp(), that.getTimestamp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getText(), getAuthor(), getTimestamp());
    }

    @SuppressWarnings("MissingJavadocMethod")
    public static final class Builder {
        private String text;
        private String author;
        private Instant timestamp;

        public Builder() {
        }

        public Builder(DoiRequestMessage copy) {
            this.text = copy.getText();
            this.author = copy.getAuthor();
            this.timestamp = copy.getTimestamp();
        }

        public Builder withText(String text) {
            this.text = text;
            return this;
        }

        public Builder withAuthor(String author) {
            this.author = author;
            return this;
        }

        public Builder withTimestamp(Instant timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public DoiRequestMessage build() {
            return new DoiRequestMessage(this);
        }
    }
}
