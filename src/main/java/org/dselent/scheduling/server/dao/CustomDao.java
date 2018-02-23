package org.dselent.scheduling.server.dao;

import java.util.List;

import org.dselent.scheduling.server.model.*;
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
    List<UserInfo> getAllUsersWithDepartment(String department);
    List<CourseInfo> selectCourseNameGivenUsernameAndTerm(String username, int term);
    List<CalendarInfo> selectStartAndEndTimesGivenUsernameAndTerm(String username, int term);
    //List<Notification> getNotificationsFromUser(String username);
    List<SectionInfo> getAllSectionsGivenUserAndDept(String username, String department);
    List<SectionInfo> getAllWishListSectionsGivenUser(String username);
    List<SectionInfo> getAllWishListSectionsGivenUserAndTerm(String username, int term);
    List<SectionInfo> getAllWishListSectionsGivenUserAndCourseName(String username, String courseName);
    List<SectionInfo> getAllSectionsGivenDepartment(String department);
    //List<SectionInfo> getAllSectionsGivenTerm(int term);
    //List<UserInfo> getUsersWithDept(String department);
    public List<List<Model>> selectAllTables();
}
