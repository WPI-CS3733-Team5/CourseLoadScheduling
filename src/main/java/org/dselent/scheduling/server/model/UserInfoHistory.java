package org.dselent.scheduling.server.model;

import java.sql.JDBCType;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserInfoHistory extends Model
{
	// table name
	public static final String TABLE_NAME = "user_info_history";
		
	// column names
	public static enum Columns
	{
		ID,
		USER_INFO_ID,
		USER_ROLE,
		USERNAME,
		FIRST_NAME,
		LAST_NAME,
		EMAIL,
		ENCRYPTED_PASSWORD,
		SALT,
		ACCOUNT_STATE,
		CREATED_AT,
		UPDATED_AT,
		LOGIN_TIME
	}

	// enum list
	private static final List<Columns> COLUMN_LIST = new ArrayList<>();
	
	// type mapping
	private static final Map<Columns, JDBCType> COLUMN_TYPE_MAP = new HashMap<>();
	
	static
	{
		for(Columns key : Columns.values())
		{
			COLUMN_LIST.add(key);
		}
		
		COLUMN_TYPE_MAP.put(Columns.ID, JDBCType.INTEGER);
		COLUMN_TYPE_MAP.put(Columns.USER_INFO_ID, JDBCType.INTEGER);
		COLUMN_TYPE_MAP.put(Columns.USER_ROLE, JDBCType.INTEGER);
		COLUMN_TYPE_MAP.put(Columns.USERNAME, JDBCType.VARCHAR);
		COLUMN_TYPE_MAP.put(Columns.FIRST_NAME, JDBCType.VARCHAR);
		COLUMN_TYPE_MAP.put(Columns.LAST_NAME, JDBCType.VARCHAR);
		COLUMN_TYPE_MAP.put(Columns.EMAIL, JDBCType.VARCHAR);
		COLUMN_TYPE_MAP.put(Columns.ENCRYPTED_PASSWORD, JDBCType.VARCHAR);
		COLUMN_TYPE_MAP.put(Columns.SALT, JDBCType.VARCHAR);
		COLUMN_TYPE_MAP.put(Columns.ACCOUNT_STATE, JDBCType.INTEGER);
		COLUMN_TYPE_MAP.put(Columns.CREATED_AT, JDBCType.TIMESTAMP_WITH_TIMEZONE);
		COLUMN_TYPE_MAP.put(Columns.UPDATED_AT, JDBCType.TIMESTAMP_WITH_TIMEZONE);
		COLUMN_TYPE_MAP.put(Columns.LOGIN_TIME, JDBCType.TIMESTAMP_WITH_TIMEZONE);
	};
	
	// attributes
	
    @JsonProperty("userInfoHistory_id")
	private Integer id;
    
    @JsonProperty("userInfoHistory_userInfoId")
	private Integer userInfoId;
    
    @JsonProperty("userInfoHistory_userRole")
	private Integer userRole;
    
    @JsonProperty("userInfoHistory_userName")
	private String userName;
    
    @JsonProperty("userInfoHistory_firstName")
	private String firstName;
    
    @JsonProperty("userInfoHistory_lastName")
	private String lastName;
    
    @JsonProperty("userInfoHistory_email")
	private String email;
    
    @JsonProperty("userInfoHistory_encryptedPassword")
	private String encryptedPassword;
    
    @JsonProperty("userInfoHistory_salt")
	private String salt;
    
    @JsonProperty("userInfoHistory_accountState")
	private Integer accountState;
    
    @JsonProperty("userInfoHistory_createdAt")
	private Instant createdAt;
    
    @JsonProperty("userInfoHistory_updatedAt")
	private Instant updatedAt;
    
    @JsonProperty("userInfoHistory_loginTime")
	private Instant loginTime;

	// methods
	
	public static JDBCType getColumnType(Columns column)
	{
		return COLUMN_TYPE_MAP.get(column);
	}
	
	public static String getColumnName(Columns column)
	{
		return column.toString().toLowerCase();
	}
	
	public static List<String> getColumnNameList()
	{
		List<String> columnNameList = new ArrayList<>();
		
		for(Columns column : COLUMN_LIST)
		{
			columnNameList.add(getColumnName(column));
		}
		
		return columnNameList;
	}
	
	//
	
	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}
	
	public Integer getUserInfoId()
	{
		return userInfoId;
	}

	public void setUserInfoId(Integer userInfoId)
	{
		this.userInfoId = userInfoId;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}


	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getEncryptedPassword()
	{
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword)
	{
		this.encryptedPassword = encryptedPassword;
	}

	public String getSalt()
	{
		return salt;
	}

	public void setSalt(String salt)
	{
		this.salt = salt;
	}

	public Integer getAccountState()
	{
		return accountState;
	}

	public void setAccountState(Integer accountState)
	{
		this.accountState = accountState;
	}

	public Instant getCreatedAt()
	{
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt)
	{
		this.createdAt = createdAt;
	}
	
	public void setCreatedAt(Timestamp createdAt)
	{
		if(createdAt != null)
		{
			this.createdAt = createdAt.toInstant();
		}
	}

	public Integer getUserRole() {
		return userRole;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}

	public Instant getLoginTime() {
		return loginTime;
	}

	public void setUserRole(Integer userRole) {
		this.userRole = userRole;
	}

	public void setLoginTime(Timestamp loginTime)
	{
		if(loginTime != null)
		{
			this.loginTime = loginTime.toInstant();
		}
	}
	public void setUpdatedAt(Timestamp updatedAt)
	{
		if(updatedAt != null)
		{
			this.updatedAt = updatedAt.toInstant();
		}
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + ((updatedAt == null) ? 0 : updatedAt.hashCode());
		result = prime * result + ((loginTime == null) ? 0 : loginTime.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((encryptedPassword == null) ? 0 : encryptedPassword.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((salt == null) ? 0 : salt.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + ((accountState == null) ? 0 : accountState.hashCode());
		result = prime * result + ((userInfoId == null) ? 0 : userInfoId.hashCode());
		result = prime * result + ((userRole == null) ? 0 : userRole.hashCode());

		return result;
	}



	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (!(obj instanceof UserInfoHistory))
		{
			return false;
		}
		UserInfoHistory other = (UserInfoHistory) obj;
		if (createdAt == null)
		{
			if (other.createdAt != null)
			{
				return false;
			}
		}
		else if (!createdAt.equals(other.createdAt))
		{
			return false;
		}
		if (updatedAt == null)
		{
			if (other.updatedAt != null)
			{
				return false;
			}
		}
		else if (!updatedAt.equals(other.updatedAt))
		{
			return false;
		}
		if (loginTime == null)
		{
			if (other.loginTime != null)
			{
				return false;
			}
		}
		else if (!loginTime.equals(other.loginTime))
		{
			return false;
		}
		if (email == null)
		{
			if (other.email != null)
			{
				return false;
			}
		}
		else if (!email.equals(other.email))
		{
			return false;
		}
		if (userRole == null)
		{
			if (other.userRole != null)
			{
				return false;
			}
		}
		else if (!userRole.equals(other.userRole))
		{
			return false;
		}
		if (encryptedPassword == null)
		{
			if (other.encryptedPassword != null)
			{
				return false;
			}
		}
		else if (!encryptedPassword.equals(other.encryptedPassword))
		{
			return false;
		}
		if (firstName == null)
		{
			if (other.firstName != null)
			{
				return false;
			}
		}
		else if (!firstName.equals(other.firstName))
		{
			return false;
		}
		if (id == null)
		{
			if (other.id != null)
			{
				return false;
			}
		}
		else if (!id.equals(other.id))
		{
			return false;
		}
		if (lastName == null)
		{
			if (other.lastName != null)
			{
				return false;
			}
		}
		else if (!lastName.equals(other.lastName))
		{
			return false;
		}
		if (salt == null)
		{
			if (other.salt != null)
			{
				return false;
			}
		}
		else if (!salt.equals(other.salt))
		{
			return false;
		}
		if (userName == null)
		{
			if (other.userName != null)
			{
				return false;
			}
		}
		else if (!userName.equals(other.userName))
		{
			return false;
		}
		if (accountState == null)
		{
			if (other.accountState != null)
			{
				return false;
			}
		}
		else if (!accountState.equals(other.accountState))
		{
			return false;
		}
		if (userInfoId == null)
		{
			if (other.userInfoId != null)
			{
				return false;
			}
		}
		else if (!userInfoId.equals(other.userInfoId))
		{
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "UserInfoHistory{" +
				"id=" + id +
				", userInfoId=" + userInfoId +
				", userRole=" + userRole +
				", userName='" + userName + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				", encryptedPassword='" + encryptedPassword + '\'' +
				", salt='" + salt + '\'' +
				", accountState=" + accountState +
				", createdAt=" + createdAt +
				", updatedAt=" + updatedAt +
				", loginTime=" + loginTime +
				'}';
	}
}
