package org.dselent.scheduling.server.service;

import java.sql.SQLException;
import java.util.List;

import org.dselent.scheduling.server.dto.CreateUserDto;
import org.dselent.scheduling.server.dto.GetAllUserDto;
import org.dselent.scheduling.server.dto.GetOneUserDto;
import org.dselent.scheduling.server.model.UserInfo;
import org.springframework.stereotype.Service;

/**
 * Service layer to specify all business logic. Calls the dao layer when data retrieval is needed.
 * Interfaces specify the behavior and the implementation provide the specific implementations.
 * 
 * @author dselent
 *
 */
@Service
public interface UserService
{
	/**
	 * Registers a user into the system
	 * Performs an insert into the users table and users_roles_links table as a transaction
	 * 
	 * @param createUserDto DTO container information for the insertions
	 * @return A list of rows affected for each insert operation
	 * @throws SQLException
	 */
	public List<Integer> createUser(CreateUserDto createUserDto) throws SQLException;
	public List<Object> getOneUser(Integer requestedId) throws SQLException;
	public List<UserInfo> getAllUser(GetAllUserDto getAllUserDto) throws SQLException;
    public UserInfo loginUser(String userName, String password);
}
