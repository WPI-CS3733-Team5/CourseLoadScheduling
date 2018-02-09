package org.dselent.scheduling.server.dto;

import java.util.Objects;

/**
 *  Data transfer object for creating and getting Notifications.
 *
 *  Written by: Leo Gonsalves
 */
public class NotificationDto {

    private final String message;
    private final Integer fromUserInfoId;
    private final Integer toUserInfoId;

    /* Constructor */
    private NotificationDto(Builder builder)
    {
        this.message = builder.message;
        this.fromUserInfoId = builder.fromUserInfoId;
        this.toUserInfoId = builder.toUserInfoId;

        if (message == null
                || fromUserInfoId == null
                || toUserInfoId == null){
            throw new IllegalStateException("fields cannot be null");
        }
    }

    /* Getters */
    public String getMessage()
    {
        return message;
    }

    public Integer getFromUserInfoId()
    {
        return fromUserInfoId;
    }

    public Integer getToUserInfoId()
    {
        return toUserInfoId;
    }

    /* Builder */
    public static Builder builder()
    {
        return new Builder();
    }

    public static final class Builder {
        private String message;
        private Integer fromUserInfoId;
        private Integer toUserInfoId;

        private Builder() {
        }

        public static Builder aNotificationDto() {
            return new Builder();
        }

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder withFromUserInfoId(Integer fromUserInfoId) {
            this.fromUserInfoId = fromUserInfoId;
            return this;
        }

        public Builder withToUserInfoId(Integer toUserInfoId) {
            this.toUserInfoId = toUserInfoId;
            return this;
        }

        public NotificationDto build() {
            NotificationDto notificationDto = new NotificationDto(this);
            return notificationDto;
        }
    }

    /* Auto-generated methods */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationDto that = (NotificationDto) o;
        return Objects.equals(message, that.message) &&
                Objects.equals(fromUserInfoId, that.fromUserInfoId) &&
                Objects.equals(toUserInfoId, that.toUserInfoId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(message, fromUserInfoId, toUserInfoId);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NotificationDto{");
        sb.append("message='").append(message).append('\'');
        sb.append(", fromUserInfoId=").append(fromUserInfoId);
        sb.append(", toUserInfoId=").append(toUserInfoId);
        sb.append('}');
        return sb.toString();
    }
}
