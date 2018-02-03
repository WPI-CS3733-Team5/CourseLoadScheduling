package org.dselent.scheduling.server.dao.impl;

import java.util.List;

import org.dselent.scheduling.server.dao.CustomDao;
import org.dselent.scheduling.server.extractor.CalendarInfoExtractor;
import org.dselent.scheduling.server.extractor.CourseInfoExtractor;
import org.dselent.scheduling.server.extractor.SectionInfoExtractor;
import org.dselent.scheduling.server.extractor.UserInfoExtractor;
import org.dselent.scheduling.server.miscellaneous.QueryPathConstants;
import org.dselent.scheduling.server.model.CalendarInfo;
import org.dselent.scheduling.server.model.CourseInfo;
import org.dselent.scheduling.server.model.SectionInfo;
import org.dselent.scheduling.server.model.UserInfo;
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
		CourseInfoExtractor extractor = new CourseInfoExtractor();
		String queryTemplate = new String(QueryPathConstants.COURSES_GIVEN_USERNAME_AND_TERM);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("username", username);
		parameters.addValue("term", term);
		List<CourseInfo> courseInfoList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
		return courseInfoList;
	}

	@Override
	public List<CalendarInfo> selectStartAndEndTimesGivenUsernameAndTerm(String username, int term)
	{
		CalendarInfoExtractor extractor = new CalendarInfoExtractor();
		String queryTemplate = new String(QueryPathConstants.TIMES_GIVEN_USERNAME_AND_TERM);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("username", username);
		parameters.addValue("term", term);
		List<CalendarInfo> calendarInfoList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
		return calendarInfoList;
	}

	@Override
	public List<SectionInfo> getAllCoursesGivenUserAndDept(String username, String department)
	{
		SectionInfoExtractor extractor = new SectionInfoExtractor();
		String queryTemplate = new String(QueryPathConstants.COURSES_GIVEN_USERNAME_AND_DEPARTMENT);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("username", username);
		parameters.addValue("department", department);
		List<SectionInfo> SectionInfo = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
		return SectionInfo;
	}

	@Override
	public List<SectionInfo>  getAllWishListSectionsGivenUserAndTerm(String username, int term){
		SectionInfoExtractor extractor = new SectionInfoExtractor();
		String queryTemplate = new String(QueryPathConstants.SECTIONS_WITH_WISHLIST_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("username", username);
		parameters.addValue("term", term);
		List<SectionInfo> sectionsFromTermAndId = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
		return sectionsFromTermAndId;
	}

}
