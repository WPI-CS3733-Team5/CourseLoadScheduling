package org.dselent.scheduling.server.miscellaneous;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * CreateUser all custom SQL query files here
 * 
 * @author dselent
 *
 */
public class QueryPathConstants
{
	private static String BASE_QUERY_PATH = "sql" + File.separator + "Active" + File.separator;
	private static String SQL_EXTENSION = ".sql";

	private static String USERS_WITH_ROLE_PATH = BASE_QUERY_PATH + "CustomUsersWithRole" + SQL_EXTENSION;
	private static String USERS_WITH_DEPT_PATH = BASE_QUERY_PATH + "SelectUserGivenDepartment" + SQL_EXTENSION;
	private static String COURSES_GIVEN_USERNAME_AND_TERM_PATH = BASE_QUERY_PATH + "selectCourseNameGivenUsernameAndTerm" + SQL_EXTENSION;
	private static String TIMES_GIVEN_USERNAME_AND_TERM_PATH = BASE_QUERY_PATH + "selectStartAndEndTimesGivenUsernameAndTerm" + SQL_EXTENSION;
	private static String COURSES_GIVEN_USERNAME_AND_DEPARTMENT_PATH = BASE_QUERY_PATH + "selectCoursesGivenUserAndDepartment" + SQL_EXTENSION;
	private static String SECTIONS_WITH_WISHLIST_QUERY_PATH = BASE_QUERY_PATH + "selectSectionInfoGivenUsernameAndTerm" + SQL_EXTENSION;
	private static String WISHLIST_GIVEN_USER_AND_COURSENAME_QUERY_PATH = BASE_QUERY_PATH + "selectWishlistSectionsGivenUsernameAndCourseNAme" + SQL_EXTENSION;
	private static String WISHLIST_FROM_USER_PATH = BASE_QUERY_PATH + "SelectWishlistGivenUser" + SQL_EXTENSION;
	private static String SECTIONS_WITH_DEPARTMENT_PATH = BASE_QUERY_PATH + "SectionsWithDepartment" + SQL_EXTENSION;
	private static String ALL_USERS_PATH = BASE_QUERY_PATH + "SelectAllUsers" + SQL_EXTENSION;
	private static String ALL_COURSES_PATH = BASE_QUERY_PATH + "SelectAllCourses" + SQL_EXTENSION;
	private static String ALL_INSTRUCTORS_PATH = BASE_QUERY_PATH + "SelectAllInstructors" + SQL_EXTENSION;
	private static String ALL_LABS_PATH = BASE_QUERY_PATH + "SelectAllLabs" + SQL_EXTENSION;
	private static String ALL_SCHEDULES_PATH = BASE_QUERY_PATH + "SelectAllScheduleLinks" + SQL_EXTENSION;
	private static String ALL_SECTIONS_PATH = BASE_QUERY_PATH + "SelectAllSections" + SQL_EXTENSION;
	private static String ALL_CALENDARS_PATH = BASE_QUERY_PATH + "SelectAllCalendar" + SQL_EXTENSION;

	/////////////////////////////////////////////////////////////////////////////////////////////////
	

	public static String USERS_WITH_ROLE_QUERY = readFile(USERS_WITH_ROLE_PATH);
	public static String USERS_WITH_DEPT_QUERY = readFile(USERS_WITH_DEPT_PATH);
	public static String COURSES_GIVEN_USERNAME_AND_TERM = readFile(COURSES_GIVEN_USERNAME_AND_TERM_PATH);
	public static String TIMES_GIVEN_USERNAME_AND_TERM = readFile(TIMES_GIVEN_USERNAME_AND_TERM_PATH);
	public static String COURSES_GIVEN_USERNAME_AND_DEPARTMENT = readFile(COURSES_GIVEN_USERNAME_AND_DEPARTMENT_PATH);
	public static String SECTIONS_WITH_WISHLIST_QUERY = readFile(SECTIONS_WITH_WISHLIST_QUERY_PATH);
	public static String WISHLIST_GIVEN_USER_AND_COURSENAME_QUERY = readFile(WISHLIST_GIVEN_USER_AND_COURSENAME_QUERY_PATH);
	public static String WISHLIST_FROM_USERNAME_QUERY = readFile(WISHLIST_FROM_USER_PATH);
	public static String SECTIONS_WITH_DEPARTMENT_QUERY = readFile(SECTIONS_WITH_DEPARTMENT_PATH);
	private QueryPathConstants()
	{
		
	}
	
	public static String readFile(String path)
	{
		byte[] encodedbytes = null;
		
		try
		{
			Resource resource = new ClassPathResource(path);
			encodedbytes = Files.readAllBytes(Paths.get(resource.getURI()));
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
		
		return new String(encodedbytes);
	}

}
