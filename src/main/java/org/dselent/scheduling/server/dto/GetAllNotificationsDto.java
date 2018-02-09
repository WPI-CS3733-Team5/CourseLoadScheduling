package org.dselent.scheduling.server.dto;

import java.util.Objects;

/**
 * Created by dhmchorney on 2/9/2018.
 */
public class GetAllNotificationsDto {

    private final Integer toUserInfoId;

    private GetAllNotificationsDto(Builder builder){
        this.toUserInfoId = builder.toUserInfoId;

        if(toUserInfoId == null){
            throw new IllegalStateException("fields cannot be null");
        }
    }

    public Integer getToUserInfoId(){return toUserInfoId;}

    public static Builder builder(){return new Builder();}

    public static final class Builder{
        private Integer toUserInfoId;

        private Builder(){}

        public static Builder aGetAllNotificationsDto(){return new Builder();}

        public Builder withToUserInfoId(Integer toUserInfoId){
            this.toUserInfoId = toUserInfoId;
            return this;
        }

        public GetAllNotificationsDto build(){
            GetAllNotificationsDto getAllNotificationsDto = new GetAllNotificationsDto(this);
            return getAllNotificationsDto;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetAllNotificationsDto that = (GetAllNotificationsDto) o;
        return Objects.equals(toUserInfoId, that.toUserInfoId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(toUserInfoId);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GetAllNotificationsDto{");
        sb.append("toUserInfoId=").append(toUserInfoId);
        sb.append('}');
        return sb.toString();
    }
}
