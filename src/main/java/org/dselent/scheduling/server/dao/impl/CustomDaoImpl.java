package org.dselent.scheduling.server.dao.impl;

import java.util.List;

import org.dselent.scheduling.server.dao.CustomDao;
import org.dselent.scheduling.server.extractor.UserInfoExtractor;
import org.dselent.scheduling.server.extractor.CourseInfoExtractor;
import org.dselent.scheduling.server.miscellaneous.QueryPathConstants;
import org.dselent.scheduling.server.model.UserInfo;
import org.dselent.scheduling.server.model.CourseInfo;

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
	public List<CourseInfo> selectCourseNameGivenUsernameAndTerm(String username, int term)
	{
		CourseInfoExtractor extractor = new CourseInfoExtractor();
		String queryTemplate = new String(QueryPathConstants.COURSES_WITH_USERNAME_AND_TERM);
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    parameters.addValue("username", username);
	    parameters.addValue("term", term);
	    List<CourseInfo> courseInfoList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
	    return courseInfoList;
	}
	
}
