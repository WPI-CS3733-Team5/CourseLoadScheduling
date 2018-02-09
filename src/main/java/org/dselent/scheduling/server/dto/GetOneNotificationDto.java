package org.dselent.scheduling.server.dto;

import java.util.Objects;

/**
 * Created by dhmchorney on 2/9/2018.
 */
public class GetOneNotificationDto {

    private final Integer requestedNotificationId;

    private GetOneNotificationDto(Builder builder){
        this.requestedNotificationId = builder.requestedNotificationId;

        if(requestedNotificationId == null){
            throw new IllegalStateException("fields cannot be null");
        }
    }

    public Integer getToUserInfoId(){return requestedNotificationId;}

    public static Builder builder(){return new Builder();}

    public static final class Builder{
        private Integer requestedNotificationId;

        private Builder(){}

        public static Builder aGetOneNotificationDto(){return new Builder();}

        public Builder withRequestedNotificationId(Integer requestedNotificationId){
            this.requestedNotificationId = requestedNotificationId;
            return this;
        }

        public GetOneNotificationDto build(){
            GetOneNotificationDto getOneNotificationDto = new GetOneNotificationDto(this);
            return getOneNotificationDto;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetOneNotificationDto that = (GetOneNotificationDto) o;
        return Objects.equals(requestedNotificationId, that.requestedNotificationId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(requestedNotificationId);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GetOneNotificationDto{");
        sb.append("requestedNotificationId=").append(requestedNotificationId);
        sb.append('}');
        return sb.toString();
    }
}
