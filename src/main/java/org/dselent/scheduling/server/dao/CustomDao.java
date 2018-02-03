package org.dselent.scheduling.server.dao;

import java.util.List;

import org.dselent.scheduling.server.model.CalendarInfo;
import org.dselent.scheduling.server.model.CourseInfo;
import org.dselent.scheduling.server.model.SectionInfo;
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
	public List<CourseInfo> selectCourseNameGivenUsernameAndTerm (String username, int term);
	public List<CalendarInfo> selectStartAndEndTimesGivenUsernameAndTerm (String username, int term);
	public List<SectionInfo> getAllCoursesGivenUserAndDept (String username, String department);
	public List<SectionInfo> getAllWishListSectionsGivenUserAndTerm (String username, int term);


}
