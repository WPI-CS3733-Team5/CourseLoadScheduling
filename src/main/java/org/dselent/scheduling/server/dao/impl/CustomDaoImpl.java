package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.dselent.scheduling.server.dao.CustomDao;
import org.dselent.scheduling.server.extractor.*;
import org.dselent.scheduling.server.miscellaneous.QueryPathConstants;
import org.dselent.scheduling.server.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class CustomDaoImpl implements CustomDao
{
	@Autowired
	protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	// can make custom models and extractors as needed or reuse existing ones if applicable

	@Override
	public List<UserInfo> getAllUsersWithDepartment(String department)
	{
		UserInfoExtractor extractor = new UserInfoExtractor();
		String queryTemplate = new String(QueryPathConstants.USERS_WITH_DEPT_QUERY);
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    parameters.addValue("department", department);
	    List<UserInfo> usersWithRoleList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
	    return usersWithRoleList;
	}

	@Override
	public List<CourseInfo> selectCourseNameGivenUsernameAndTerm(String username, int term)
	{
		/*
		CourseInfoExtractor extractor = new CourseInfoExtractor();
		String queryTemplate = new String(QueryPathConstants.COURSES_GIVEN_USERNAME_AND_TERM);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("username", username);
		parameters.addValue("term", term);
		List<CourseInfo> courseInfoList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
		return courseInfoList;
		*/
		return null;
	}

	@Override
	public List<CalendarInfo> selectStartAndEndTimesGivenUsernameAndTerm(String username, int term)
	{
		/*
		CalendarInfoExtractor extractor = new CalendarInfoExtractor();
		String queryTemplate = new String(QueryPathConstants.TIMES_GIVEN_USERNAME_AND_TERM);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("username", username);
		parameters.addValue("term", term);
		List<CalendarInfo> calendarInfoList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
		return calendarInfoList;
		*/
		return null;
	}

	@Override
	public List<SectionInfo> getAllSectionsGivenUserAndDept(String username, String department)
	{
		/*
		SectionInfoExtractor extractor = new SectionInfoExtractor();
		String queryTemplate = new String(QueryPathConstants.COURSES_GIVEN_USERNAME_AND_DEPARTMENT);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("username", username);
		parameters.addValue("department", department);
		List<SectionInfo> SectionInfo = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
		return SectionInfo;
		*/
		return null;
	}

	@Override
	public List<SectionInfo>  getAllWishListSectionsGivenUserAndTerm(String username, int term){
		/*
		SectionInfoExtractor extractor = new SectionInfoExtractor();
		String queryTemplate = new String(QueryPathConstants.SECTIONS_WITH_WISHLIST_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("username", username);
		parameters.addValue("term", term);
		List<SectionInfo> sectionsFromTermAndId = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
		return sectionsFromTermAndId;
		*/
		return null;
	}

	@Override
	public List<SectionInfo> getAllWishListSectionsGivenUserAndCourseName(String username, String courseName){
		/*
		SectionInfoExtractor extractor = new SectionInfoExtractor();
		String queryTemplate = new String(QueryPathConstants.WISHLIST_GIVEN_USER_AND_COURSENAME_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("username", username);
		parameters.addValue("course_name", courseName);
		List<SectionInfo> wishListSectionList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
		return wishListSectionList;
		*/
		return null;
	}

	@Override
	public List<SectionInfo> getAllWishListSectionsGivenUser(String username)
	{
		/*
		SectionInfoExtractor extractor = new SectionInfoExtractor();
		String queryTemplate = new String(QueryPathConstants.WISHLIST_FROM_USERNAME_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("roleId", username);
		List<SectionInfo> usersWithRoleList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
		return usersWithRoleList;
		*/
		return null;
	}

	@Override
	public List<SectionInfo> getAllSectionsGivenDepartment(String department)
	{
		/*
			SectionInfoExtractor extractor = new SectionInfoExtractor();
			String queryTemplate = new String(QueryPathConstants.SECTIONS_WITH_DEPARTMENT_QUERY);
		    MapSqlParameterSource parameters = new MapSqlParameterSource();
		    parameters.addValue("department", department);
		    List<SectionInfo> sectionsWithDepartmentList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
		    return sectionsWithDepartmentList;
		    */
		return null;
	}

	/**
	 *  The order of models to be returned is the following:
	 *  - UserInfo
     *  - InstructorInfo
	 *  - CourseInfo
	 *  - LabInfo
	 *  - SectionInfo
	 *  - CalendarInfo
	 *  - ScheduleLinks
     *
     *  ## WARNING ##
     *
     *  Lists must be cast in the right sequence in order to avoid
     *  problems.
	 *
	 * @return
	 * @throws SQLException
     *
     * @author Leo Gonsalves
	 */
	@Override
	public List<Object> selectAllTables()
    {
		UserInfoExtractor userInfoExtractor = new UserInfoExtractor();
		InstructorInfoExtractor instructorInfoExtractor = new InstructorInfoExtractor();
		CourseInfoExtractor courseInfoExtractor = new CourseInfoExtractor();
		LabInfoExtractor labInfoExtractor = new LabInfoExtractor();
		SectionInfoExtractor sectionInfoExtractor = new SectionInfoExtractor();
		CalendarInfoExtractor calendarInfoExtractor = new CalendarInfoExtractor();
		ScheduleLinksExtractor scheduleLinksExtractor = new ScheduleLinksExtractor();

		/* Extracting All Users */
        String userInfoQueryTemplate = new String(QueryPathConstants.ALL_USERS_QUERY);
        MapSqlParameterSource userParameters = new MapSqlParameterSource();
        List<UserInfo> allUsers = namedParameterJdbcTemplate.query(userInfoQueryTemplate, userParameters, userInfoExtractor);

        /* Extracting All Instructors */
        String instructorInfoQueryTemplate = new String(QueryPathConstants.ALL_INSTRUCTORS_QUERY);
        MapSqlParameterSource instructorParameters = new MapSqlParameterSource();
        List<InstructorInfo> allInstructors = namedParameterJdbcTemplate.query(instructorInfoQueryTemplate, instructorParameters, instructorInfoExtractor);

        /* Extracting All Courses */
        String courseInfoQueryTemplate = new String(QueryPathConstants.ALL_COURSES_QUERY);
        MapSqlParameterSource courseParameters = new MapSqlParameterSource();
        List<CourseInfo> allCourses = namedParameterJdbcTemplate.query(courseInfoQueryTemplate, courseParameters, courseInfoExtractor);

        /* Extracting All Labs */
        String labInfoQueryTemplate = new String(QueryPathConstants.ALL_LABS_QUERY);
        MapSqlParameterSource labParameters = new MapSqlParameterSource();
        List<LabInfo> allLabs = namedParameterJdbcTemplate.query(labInfoQueryTemplate, labParameters, labInfoExtractor);

        /* Extracting All Sections */
        String sectionInfoQueryTemplate = new String(QueryPathConstants.ALL_SECTIONS_QUERY);
        MapSqlParameterSource sectionParameters = new MapSqlParameterSource();
        List<SectionInfo> allSections = namedParameterJdbcTemplate.query(sectionInfoQueryTemplate, sectionParameters, sectionInfoExtractor);

        /* Extracting All Calendars */
        String calendarInfoQueryTemplate = new String(QueryPathConstants.ALL_CALENDARS_QUERY);
        MapSqlParameterSource calendarParamenter = new MapSqlParameterSource();
        List<CalendarInfo> allCalendars = namedParameterJdbcTemplate.query(calendarInfoQueryTemplate, calendarParamenter, calendarInfoExtractor);

        /* Extracting All Schedule */
        String scheduleInfoQueryTemplate = new String(QueryPathConstants.ALL_SCHEDULES_QUERY);
        MapSqlParameterSource scheduleParameters = new MapSqlParameterSource();
        List<ScheduleLinks> allSchedules = namedParameterJdbcTemplate.query(scheduleInfoQueryTemplate, scheduleParameters, scheduleLinksExtractor);

        List<Object> l = new LinkedList<>();
        l.add(allUsers);
        l.add(allInstructors);
        l.add(allCourses);
        l.add(allLabs);
        l.add(allSections);
        l.add(allCalendars);
        l.add(allSchedules);

        return l;
	}

}
