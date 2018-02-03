package org.dselent.scheduling.server.dao;

import java.util.List;

import org.dselent.scheduling.server.model.CourseInfo;
import org.dselent.scheduling.server.model.Notification;
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
    List<UserInfo> getAllUsersWithDepartment(String department);
    List<Notification> getNotificationsFromUser(String username);
    List<SectionInfo> getAllSectionsGivenUserAndDept(String username, String department);
    List<SectionInfo> getAllWishListSectionsGivenUser(String username);
    List<SectionInfo> getAllWishListSectionsGivenUserAndTerm(String username, int term);
    List<SectionInfo> getAllWishListSectionsGivenUserAndCourseNum(String username, int courseNum);
    List<SectionInfo> getAllSectionsGivenDept(String department);
    List<SectionInfo> getAllSectionsGivenTerm(int term);
    List<UserInfo> getUsersWithDept(String department);
}
