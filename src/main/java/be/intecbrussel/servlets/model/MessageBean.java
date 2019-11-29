package be.intecbrussel.servlets.model;

import java.time.LocalDateTime;

public class MessageBean {
    private LocalDateTime localDateTime;
    private String name;
    private String message;

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public MessageBean setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
        return this;
    }

    public String getName() {
        return name;
    }

    public MessageBean setName(String name) {
        this.name = name;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public MessageBean setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MessageBean{");
        sb.append("localDateTime=").append(localDateTime);
        sb.append(", name='").append(name).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
