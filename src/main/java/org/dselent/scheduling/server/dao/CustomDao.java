package org.dselent.scheduling.server.dao;

import java.util.List;

import org.dselent.scheduling.server.model.UserInfo;
import org.springframework.stereotype.Repository;

/**
 * Interface for all daos for custom queries.
 * 
 * @author dselent
 *
 */
@Repository
public interface CustomDao
{
	// custom queries here
	public List<UserInfo> getAllUsersWithDepartment(String department);

}
