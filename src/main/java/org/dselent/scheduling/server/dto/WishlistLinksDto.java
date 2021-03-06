package org.dselent.scheduling.server.dto;

import javax.annotation.Generated;

/**
 * DTO = Data Transfer Object
 * Used to package/wrap several variables into a single object
 * Uses the Builder pattern for object instantiation
 * 
 * @author dselent
 *
 */
public class WishlistLinksDto
{
	private final Integer instructorInfoId;
	private final Integer sectionInfoId;


	// I added to the auto-generated code
	@Generated("SparkTools")
	private WishlistLinksDto(Builder builder)
	{
		// can add defaults if null for other places where the builder pattern is used
		
		this.instructorInfoId = builder.instructorInfoId;
		this.sectionInfoId = builder.sectionInfoId;
		
		// making claim that none of these can be null
		// add other state checks here as necessary
		
		if(this.instructorInfoId == null)
		{
			throw new IllegalStateException("Instructor Info Id cannot be null");
		}
		else if(this.sectionInfoId == null)
		{
			throw new IllegalStateException("firstName cannot be null");
		}
	throw new IllegalStateException("password cannot be null");
		}
	
	
	
	
	/**
	 * Creates builder to build {@link WishlistLinksDto}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder()
	{
		return new Builder();
	}
	
	public Integer getInstructorInfoId()
	{
		return instructorInfoId;
	}

	public Integer getSectionInfoId()
	{
		return sectionInfoId;
	}

	/**
	 * Builder to build {@link WishlistLinksDto}.
	 */
	@Generated("SparkTools")
	public static final class Builder
	{
		private Integer instructorInfoId;
		private Integer sectionInfoId;

		private Builder()
		{
		}

		public Builder withInstructorInfoId(Integer instructorInfoId)
		{
			this.instructorInfoId = instructorInfoId;
			return this;
		}

		public Builder withSectionInfoId(Integer sectionInfoId)
		{
			this.sectionInfoId = sectionInfoId;
			return this;
		}

		public WishlistLinksDto build()
		{
			return new WishlistLinksDto(this);
		}
	}
}
