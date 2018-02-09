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
public class GetAllUserDto
{
	private final Integer userRole;
	private final String username;
	private final String firstName;
	private final String lastName;
	private final String email;
	private final Boolean deleted;
	private final String encryptedPassword;
	private final Integer accountState;
	private final String rank;
	private final Integer courseLoad;
	private final String phoneNumber;
	private final String office;
	private final String department;


	// I added to the auto-generated code
	@Generated("SparkTools")
	private GetAllUserDto(Builder builder)
	{
		// can add defaults if null for other places where the builder pattern is used

		this.userRole = builder.userRole;
		this.username = builder.userName;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.email = builder.email;
		this.deleted = builder.deleted;
		this.encryptedPassword = builder.password;
		this.accountState = builder.accountState;
		this.rank = builder.rank;
		this.courseLoad = builder.couseLoad;
		this.phoneNumber = builder.phoneNumber;
		this.office = builder.office;
		this.department = builder.department;
		
		// making claim that none of these can be null
		// add other state checks here as necessary
		
		if(this.username == null)
		{
			throw new IllegalStateException("userName cannot be null");
		}
		else if(this.firstName == null)
		{
			throw new IllegalStateException("firstName cannot be null");
		}
		else if(this.lastName == null)
		{
			throw new IllegalStateException("lastName cannot be null");
		}
		else if(this.email == null)
		{
			throw new IllegalStateException("email cannot be null");
		}
		else if(this.encryptedPassword == null)
		{
			throw new IllegalStateException("password cannot be null");
		}
		else if(this.userRole == null)
		{
			throw new IllegalStateException("userRole cannot be null");
		}
		else if(this.deleted == null)
		{
			throw new IllegalStateException("deleted cannot be null");
		}
		else if(this.accountState == null)
		{
			throw new IllegalStateException("accountState cannot be null");
		}
	}

	public Integer getUserRole() {
		return userRole;
	}

	public String getUsername() {
		return username;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public Integer getAccountState() {
		return accountState;
	}

	public String getRank() {
		return rank;
	}

	public Integer getCourseLoad() {
		return courseLoad;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getOffice() {
		return office;
	}

	public String getDepartment() {
		return department;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		GetAllUserDto that = (GetAllUserDto) o;
		return Objects.equals(getUserRole(), that.getUserRole()) &&
				Objects.equals(getUsername(), that.getUsername()) &&
				Objects.equals(getFirstName(), that.getFirstName()) &&
				Objects.equals(getLastName(), that.getLastName()) &&
				Objects.equals(getEmail(), that.getEmail()) &&
				Objects.equals(getDeleted(), that.getDeleted()) &&
				Objects.equals(getEncryptedPassword(), that.getEncryptedPassword()) &&
				Objects.equals(getAccountState(), that.getAccountState()) &&
				Objects.equals(getRank(), that.getRank()) &&
				Objects.equals(getCourseLoad(), that.getCourseLoad()) &&
				Objects.equals(getPhoneNumber(), that.getPhoneNumber()) &&
				Objects.equals(getOffice(), that.getOffice()) &&
				Objects.equals(getDepartment(), that.getDepartment());
	}

	@Override
	public int hashCode() {

		return Objects.hash(getUserRole(), getUsername(), getFirstName(), getLastName(), getEmail(), getDeleted(), getEncryptedPassword(), getAccountState(), getRank(), getCourseLoad(), getPhoneNumber(), getOffice(), getDepartment());
	}

	@Override
	public String toString() {
		return "CreateUserDto{" +
				"userRole='" + userRole + '\'' +
				", username='" + username + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				", deleted=" + deleted +
				", encryptedPassword='" + encryptedPassword + '\'' +
				", accountState=" + accountState +
				", rank='" + rank + '\'' +
				", courseLoad=" + courseLoad +
				", phoneNumber=" + phoneNumber +
				", office='" + office + '\'' +
				", department='" + department + '\'' +
				'}';
	}



	/**
	 * Creates builder to build {@link GetAllUserDto}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder()
	{
		return new Builder();
	}

	/**
	 * Builder to build {@link GetAllUserDto}.
	 */
	@Generated("SparkTools")
	public static final class Builder
	{
		private  Integer userRole;
		private  String userName;
		private  String firstName;
		private  String lastName;
		private  String email;
		private  Boolean deleted;
		private  String password;
		private  Integer accountState;
		private  String rank;
		private  Integer couseLoad;
		private  String phoneNumber;
		private  String office;
		private  String department;

		private Builder()
		{
		}

		public Builder withUserRole(Integer userRole)
		{
			this.userRole = userRole;
			return this;
		}

		public Builder withUserName(String userName)
		{
			this.userName = userName;
			return this;
		}

		public Builder withFirstName(String firstName)
		{
			this.firstName = firstName;
			return this;
		}

		public Builder withLastName(String lastName)
		{
			this.lastName = lastName;
			return this;
		}

		public Builder withEmail(String email)
		{
			this.email = email;
			return this;
		}

		public Builder withDeleted(Boolean deleted)
		{
			this.deleted = deleted;
			return this;
		}

		public Builder withPassword(String password)
		{
			this.password = password;
			return this;
		}

		public Builder withAccountState(Integer accountState)
		{
			this.accountState = accountState;
			return this;
		}

		public Builder withRank(String rank)
		{
			if(rank != null){
				this.rank = rank;
			} else {
				this.rank = "NA";
			}
			return this;
		}

		public Builder withCourseLoad(Integer courseLoad)
		{
			if(courseLoad != null){
				this.couseLoad = courseLoad;
			} else {
				this.couseLoad = 0;
			}
			return this;
		}

		public Builder withPhoneNumber(String phoneNumber)
		{
			if(phoneNumber != null){
				this.phoneNumber = phoneNumber;
			} else {
				this.phoneNumber = "NA";
			}
			return this;
		}
		public Builder withOffice(String office)
		{
			if(office != null){
				this.office = office;
			} else {
				this.office = "NA";
			}
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

		public GetAllUserDto build()
		{
			return new GetAllUserDto(this);
		}
	}
}
