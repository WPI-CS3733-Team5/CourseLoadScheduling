package org.dselent.scheduling.server.model;

import java.sql.JDBCType;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;


public class UserInfo extends Model
{
	// table name
	public static final String TABLE_NAME = "user_info";
		
	// column names
	public static enum Columns
	{
		ID,
		USER_ROLE,
		USERNAME,
		FIRST_NAME,
		LAST_NAME,
		EMAIL,
		DELETED,
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
		COLUMN_TYPE_MAP.put(Columns.USER_ROLE, JDBCType.INTEGER);
		COLUMN_TYPE_MAP.put(Columns.USERNAME, JDBCType.VARCHAR);
		COLUMN_TYPE_MAP.put(Columns.FIRST_NAME, JDBCType.VARCHAR);
		COLUMN_TYPE_MAP.put(Columns.LAST_NAME, JDBCType.VARCHAR);
		COLUMN_TYPE_MAP.put(Columns.EMAIL, JDBCType.INTEGER);
		COLUMN_TYPE_MAP.put(Columns.DELETED, JDBCType.INTEGER);
		COLUMN_TYPE_MAP.put(Columns.ENCRYPTED_PASSWORD, JDBCType.VARCHAR);
		COLUMN_TYPE_MAP.put(Columns.SALT, JDBCType.VARCHAR);
		COLUMN_TYPE_MAP.put(Columns.ACCOUNT_STATE, JDBCType.INTEGER);
		COLUMN_TYPE_MAP.put(Columns.CREATED_AT, JDBCType.TIMESTAMP_WITH_TIMEZONE);
		COLUMN_TYPE_MAP.put(Columns.UPDATED_AT, JDBCType.TIMESTAMP_WITH_TIMEZONE);
		COLUMN_TYPE_MAP.put(Columns.LOGIN_TIME, JDBCType.INTEGER);

	};
	
	// attributes
	
    @JsonProperty("userInfo_id")
	private Integer id;
    
    @JsonProperty("userInfo_userRole")
	private Integer userRole;
    
    @JsonProperty("userInfo_userName")
	private String userName;
    
    @JsonProperty("userInfo_firstName")
	private String firstName;
    
    @JsonProperty("userInfo_lastName")
	private String lastName;
    
    @JsonProperty("userInfo_email")
	private String email;
    
    @JsonProperty("userInfo_deleted")
	private Boolean deleted;
    
    @JsonProperty("userInfo_encryptedPassword")
	private String encryptedPassword;
    
    @JsonProperty("userInfo_salt")
	private String salt;
    
    @JsonProperty("userInfo_accountState")
	private Integer accountState;
    
    @JsonProperty("userInfo_createdAt")
	private Instant createdAt;
    
    @JsonProperty("userInfo_updatedAt")
	private Instant updatedAt;
    
    @JsonProperty("userInfo_loginTime")
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


	public Integer getId() {
		return id;
	}

	public Integer getUserRole() {
		return userRole;
	}

	public String getUserName() {
		return userName;
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

	public String getSalt() {
		return salt;
	}

	public Integer getAccountState() {
		return accountState;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}

	public Instant getLoginTime() {
		return loginTime;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setUserRole(Integer userRole) {
		this.userRole = userRole;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public void setAccountState(Integer accountState) {
		this.accountState = accountState;
	}

	public void setCreatedAt(Timestamp createdAt)
	{
		if(createdAt != null)
		{
			this.createdAt = createdAt.toInstant();
		}
	}
	public void setUpdatedAt(Timestamp updatedAt)
	{
		if(updatedAt != null)
		{
			this.updatedAt = updatedAt.toInstant();
		}
	}
	public void setLoginTime(Timestamp loginTime)
	{
		if(loginTime != null)
		{
			this.loginTime = loginTime.toInstant();
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserInfo user_info = (UserInfo) o;
		return Objects.equals(getId(), user_info.getId()) &&
				Objects.equals(getUserRole(), user_info.getUserRole()) &&
				Objects.equals(getUserName(), user_info.getUserName()) &&
				Objects.equals(getFirstName(), user_info.getFirstName()) &&
				Objects.equals(getLastName(), user_info.getLastName()) &&
				Objects.equals(getEmail(), user_info.getEmail()) &&
				Objects.equals(getDeleted(), user_info.getDeleted()) &&
				Objects.equals(getEncryptedPassword(), user_info.getEncryptedPassword()) &&
				Objects.equals(getSalt(), user_info.getSalt()) &&
				Objects.equals(getAccountState(), user_info.getAccountState()) &&
				Objects.equals(getCreatedAt(), user_info.getCreatedAt()) &&
				Objects.equals(getUpdatedAt(), user_info.getUpdatedAt()) &&
				Objects.equals(getLoginTime(), user_info.getLoginTime());
	}

	@Override
	public int hashCode() {

		return Objects.hash(getId(),
				getUserRole(),
				getUserName(),
				getFirstName(),
				getLastName(),
				getEmail(),
				getDeleted(),
				getEncryptedPassword(),
				getSalt(),
				getAccountState(),
				getCreatedAt(),
				getUpdatedAt(),
				getLoginTime());
	}

	@Override
	public String toString() {
		return "User_Info{" +
				"id=" + id +
				", userRole=" + userRole +
				", userName='" + userName + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				", deleted=" + deleted +
				", encryptedPassword='" + encryptedPassword + '\'' +
				", salt='" + salt + '\'' +
				", accountState=" + accountState +
				", createdAt=" + createdAt +
				", updatedAt=" + updatedAt +
				", loginTime=" + loginTime +
				'}';
	}
}
