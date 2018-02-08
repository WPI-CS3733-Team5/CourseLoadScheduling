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
public class GetOneUserDto
{
	private final Integer id;
	private final Integer user_role;
	private final String username;
	private final String first_name;
	private final String last_name;
	private final String email;
	private final Boolean deleted;
	private final String encrypted_password;
	private final Integer account_state;
	private final String rank;
	private final Integer course_load;
	private final String phone_number;
	private final String office;
	private final String department;


	// I added to the auto-generated code
	@Generated("SparkTools")
	private GetOneUserDto(Builder builder)
	{
		// can add defaults if null for other places where the builder pattern is used

		this.id = builder.id;
		this.user_role = builder.userRole;
		this.username = builder.userName;
		this.first_name = builder.firstName;
		this.last_name = builder.lastName;
		this.email = builder.email;
		this.deleted = builder.deleted;
		this.encrypted_password = builder.password;
		this.account_state = builder.accountState;
		this.rank = builder.rank;
		this.course_load = builder.couseLoad;
		this.phone_number = builder.phoneNumber;
		this.office = builder.office;
		this.department = builder.department;
		
		// making claim that none of these can be null
		// add other state checks here as necessary
		
		if(this.username == null)
		{
			throw new IllegalStateException("userName cannot be null");
		}
		else if(this.first_name == null)
		{
			throw new IllegalStateException("firstName cannot be null");
		}
		else if(this.last_name == null)
		{
			throw new IllegalStateException("lastName cannot be null");
		}
		else if(this.email == null)
		{
			throw new IllegalStateException("email cannot be null");
		}
		else if(this.encrypted_password == null)
		{
			throw new IllegalStateException("password cannot be null");
		}
		else if(this.user_role == null)
		{
			throw new IllegalStateException("user_role cannot be null");
		}
		else if(this.deleted == null)
		{
			throw new IllegalStateException("deleted cannot be null");
		}
		else if(this.account_state == null)
		{
			throw new IllegalStateException("account_state cannot be null");
		}
		else if(this.id == null)
		{
			throw new IllegalStateException("id cannot be null");
		}
	}

	public Integer getId() { return id; }

	public Integer getUser_role() {
		return user_role;
	}

	public String getUsername() {
		return username;
	}

	public String getFirst_name() {
		return first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public String getEmail() {
		return email;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public String getEncrypted_password() {
		return encrypted_password;
	}

	public Integer getAccount_state() {
		return account_state;
	}

	public String getRank() {
		return rank;
	}

	public Integer getCourse_load() {
		return course_load;
	}

	public String getPhone_number() {
		return phone_number;
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
		GetOneUserDto that = (GetOneUserDto) o;
		return Objects.equals(getId(), that.getId()) &&
				Objects.equals(getUser_role(), that.getUser_role()) &&
				Objects.equals(getUsername(), that.getUsername()) &&
				Objects.equals(getFirst_name(), that.getFirst_name()) &&
				Objects.equals(getLast_name(), that.getLast_name()) &&
				Objects.equals(getEmail(), that.getEmail()) &&
				Objects.equals(getDeleted(), that.getDeleted()) &&
				Objects.equals(getEncrypted_password(), that.getEncrypted_password()) &&
				Objects.equals(getAccount_state(), that.getAccount_state()) &&
				Objects.equals(getRank(), that.getRank()) &&
				Objects.equals(getCourse_load(), that.getCourse_load()) &&
				Objects.equals(getPhone_number(), that.getPhone_number()) &&
				Objects.equals(getOffice(), that.getOffice()) &&
				Objects.equals(getDepartment(), that.getDepartment());
	}

	@Override
	public int hashCode() {

		return Objects.hash(
				getId(),
				getUser_role(),
				getUsername(),
				getFirst_name(),
				getLast_name(),
				getEmail(),
				getDeleted(),
				getEncrypted_password(),
				getAccount_state(),
				getRank(),
				getCourse_load(),
				getPhone_number(),
				getOffice(),
				getDepartment());
	}

	@Override
	public String toString() {
		return "CreateUserDto{" +
				"id ='" + id + '\'' +
				", user_role='" + user_role + '\'' +
				", username='" + username + '\'' +
				", first_name='" + first_name + '\'' +
				", last_name='" + last_name + '\'' +
				", email='" + email + '\'' +
				", deleted=" + deleted +
				", encrypted_password='" + encrypted_password + '\'' +
				", account_state=" + account_state +
				", rank='" + rank + '\'' +
				", course_load=" + course_load +
				", phone_number=" + phone_number +
				", office='" + office + '\'' +
				", department='" + department + '\'' +
				'}';
	}



	/**
	 * Creates builder to build {@link GetOneUserDto}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder()
	{
		return new Builder();
	}

	/**
	 * Builder to build {@link GetOneUserDto}.
	 */
	@Generated("SparkTools")
	public static final class Builder
	{
		private  Integer id;
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

		public Builder withId(Integer id)
		{
			this.id = id;
			return this;
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
			}
			return this;
		}

		public Builder withCourseLoad(Integer courseLoad)
		{
			if(courseLoad != null){
				this.couseLoad = courseLoad;
			}
			return this;
		}

		public Builder withPhoneNumber(String phoneNumber)
		{
			if(phoneNumber != null){
				this.phoneNumber = phoneNumber;
			}
			return this;
		}
		public Builder withOffice(String office)
		{
			if(office != null){
				this.office = office;
			}
			return this;
		}
		public Builder withDepartment(String department)
		{
			if(department != null){
				this.department = department;
			}
			return this;
		}

		public GetOneUserDto build()
		{
			return new GetOneUserDto(this);
		}
	}
}
