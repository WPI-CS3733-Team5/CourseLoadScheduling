package org.dselent.scheduling.server.dto;

import javax.annotation.Generated;
import java.util.Objects;

/**
 * DTO = Data Transfer Object
 * Used to package/wrap several variables into a single object
 * Uses the Builder pattern for object instantiation
 * 
 * @author dselent
 *
 */
public class CreateCourseDto
{
	private final String courseName;
	private final String requiredFrequencyPerTerm;
	private final String requiredFrequencyPerSemester;
	private final String requiredFrequencyPerYear;
	private final Float creditAmount;
	private final Boolean deleted;
	private final String department;
	private final Integer courseNumber;


	// I added to the auto-generated code
	@Generated("SparkTools")
	private CreateUserDto(Builder builder)
	{
		// can add defaults if null for other places where the builder pattern is used

		this.courseName = builder.courseName;
		this.requiredFrequencyPerTerm = builder.requiredFrequencyPerTerm;
		this.requiredFrequencyPerSemester = builder.requiredFrequencyPerSemester;
		this.requiredFrequencyPerYear = builder.requiredFrequencyPerYear;
		this.creditAmount = builder.creditAmount;
		this.deleted = builder.deleted;
		this.department = builder.department;
		this.courseNumber = builder.courseNumber;
		
		// making claim that none of these can be null
		// add other state checks here as necessary
		
		if(this.courseName == null)
		{
			throw new IllegalStateException("courseName cannot be null");
		}
		else if(this.creditAmount == null)
		{
			throw new IllegalStateException("credit amount cannot be null");
		}
		else if(this.deleted == null)
		{
			throw new IllegalStateException("deleted cannot be null");
		}
		else if(this.department == null)
		{
			throw new IllegalStateException("department cannot be null");
		}
		else if(this.courseNumber == null)
		{
			throw new IllegalStateException("courseNumber cannot be null");
		}
	}




	public String getCourseName() {
		return courseName;
	}




	public String getRequiredFrequencyPerTerm() {
		return requiredFrequencyPerTerm;
	}




	public String getRequiredFrequencyPerSemester() {
		return requiredFrequencyPerSemester;
	}




	public String getRequiredFrequencyPerYear() {
		return requiredFrequencyPerYear;
	}




	public String getCreditAmount() {
		return creditAmount;
	}




	public Boolean getDeleted() {
		return deleted;
	}




	public String getDepartment() {
		return department;
	}




	public Integer getCourseNumber() {
		return courseNumber;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((courseName == null) ? 0 : courseName.hashCode());
		result = prime * result + ((courseNumber == null) ? 0 : courseNumber.hashCode());
		result = prime * result + ((creditAmount == null) ? 0 : creditAmount.hashCode());
		result = prime * result + ((deleted == null) ? 0 : deleted.hashCode());
		result = prime * result + ((department == null) ? 0 : department.hashCode());
		result = prime * result
				+ ((requiredFrequencyPerSemester == null) ? 0 : requiredFrequencyPerSemester.hashCode());
		result = prime * result + ((requiredFrequencyPerTerm == null) ? 0 : requiredFrequencyPerTerm.hashCode());
		result = prime * result + ((requiredFrequencyPerYear == null) ? 0 : requiredFrequencyPerYear.hashCode());
		return result;
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreateCourseDto other = (CreateCourseDto) obj;
		if (courseName == null) {
			if (other.courseName != null)
				return false;
		} else if (!courseName.equals(other.courseName))
			return false;
		if (courseNumber == null) {
			if (other.courseNumber != null)
				return false;
		} else if (!courseNumber.equals(other.courseNumber))
			return false;
		if (creditAmount == null) {
			if (other.creditAmount != null)
				return false;
		} else if (!creditAmount.equals(other.creditAmount))
			return false;
		if (deleted == null) {
			if (other.deleted != null)
				return false;
		} else if (!deleted.equals(other.deleted))
			return false;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (requiredFrequencyPerSemester == null) {
			if (other.requiredFrequencyPerSemester != null)
				return false;
		} else if (!requiredFrequencyPerSemester.equals(other.requiredFrequencyPerSemester))
			return false;
		if (requiredFrequencyPerTerm == null) {
			if (other.requiredFrequencyPerTerm != null)
				return false;
		} else if (!requiredFrequencyPerTerm.equals(other.requiredFrequencyPerTerm))
			return false;
		if (requiredFrequencyPerYear == null) {
			if (other.requiredFrequencyPerYear != null)
				return false;
		} else if (!requiredFrequencyPerYear.equals(other.requiredFrequencyPerYear))
			return false;
		return true;
	}




	@Override
	public String toString() {
		return "CreateCourseDto [courseName=" + courseName + ", requiredFrequencyPerTerm=" + requiredFrequencyPerTerm
				+ ", requiredFrequencyPerSemester=" + requiredFrequencyPerSemester + ", requiredFrequencyPerYear="
				+ requiredFrequencyPerYear + ", creditAmount=" + creditAmount + ", deleted=" + deleted + ", department="
				+ department + ", courseNumber=" + courseNumber + "]";
	}




	/**
	 * Creates builder to build {@link CreateUserDto}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder()
	{
		return new Builder();
	}

	/**
	 * Builder to build {@link CreateUserDto}.
	 */
	@Generated("SparkTools")
	public static final class Builder
	{
		public Integer courseNumber;
		public String department;
		public Float creditAmount;
		public Boolean deleted;
		public String requiredFrequencyPerYear;
		public String requiredFrequencyPerSemester;
		public String requiredFrequencyPerTerm;
		private  String courseName;

		private Builder()
		{
		}

		public Builder withCourseName(String courseName)
		{
			this.courseName = courseName;
			return this;
		}

		public Builder withRequiredFrequencyPerTerm(String requiredFrequencyPerTerm)
		{
			this.requiredFrequencyPerTerm = requiredFrequencyPerTerm;
			return this;
		}

		public Builder withRequiredFrequencyPerSemester(String requiredFrequencyPerSemester)
		{
			this.requiredFrequencyPerSemester = requiredFrequencyPerSemester;
			return this;
		}

	
		public Builder withDeleted(Boolean deleted)
		{
			this.deleted = deleted;
			return this;
		}

		public Builder withCreditAmount(Float creditAmount)
		{
			this.creditAmount = creditAmount;
			return this;
		}
	
		/*
		public Builder withDeleted(Boolean deleted)
		{
			this.deleted = deleted;
			return this;
		}
*/

		public Builder withCourseNumber(Integer courseNumber)
		{
			this.courseNumber = courseNumber;
			return this;
		}

		public Builder withDepartment(String department)
		{
			if(department != null){
				this.department = department;
			} else {
				this.department = "NA";
			}
			return this;
		}

		public CreateUserDto build()
		{
			return new CreateUserDto(this);
		}
	}
}
