package org.dselent.scheduling.server.dto;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * Created by dhmchorney on 2/9/2018.
 */
public class GetOneNotificationDto {

    private final Integer id;

    private GetOneNotificationDto(Builder builder){
        this.id = builder.id;

        if(this.id == null){
            throw new IllegalStateException("fields cannot be null");
        }
        
    }
    
    
    
    public Integer getId() {
		return id;
	}



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetOneNotificationDto that = (GetOneNotificationDto) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId());
    }
    
    @Override
    public String toString() {
    	return "GetOneNotificationDto{" +
    			"id=" + id + '\'' + 
    			"}";
    }
    
    /**
	 * Creates builder to build {@link GetOneNotificationDto}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder()
	{
		return new Builder();
	}

	/**
	 * Builder to build {@link GetOneNotificationDto}.
	 */
	@Generated("SparkTools")
	public static final class Builder
	{
		private Integer id;

		private Builder()
		{
		}

		public Builder withId(Integer id)
		{
			this.id = id;
			return this;
		}
		
		public GetOneNotificationDto build()
		{
			return new GetOneNotificationDto(this);
		}
	}
    
}
