package org.dselent.scheduling.server.dto;

import java.util.Objects;

/**
 * Created by dhmchorney on 2/9/2018.
 */
public class GetOneNotificationDto {

    private final Integer id;

    private GetOneNotificationDto(Builder builder){
        this.id = builder.id;

        if(id == null){
            throw new IllegalStateException("fields cannot be null");
        }
    }

    public Integer getId(){return id;}

    public static Builder builder(){return new Builder();}

    public static final class Builder{
        private Integer id;

        private Builder(){}

        public static Builder aGetOneNotificationDto(){return new Builder();}

        public Builder withid(Integer id){
            this.id = id;
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
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GetOneNotificationDto{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
