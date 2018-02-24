package org.dselent.scheduling.server.dao.impl;

import org.dselent.scheduling.server.dao.InstructorInfoHistoryDao;
import org.dselent.scheduling.server.extractor.InstructorInfoHistoryExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.InstructorInfoHistory;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryStringBuilder;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/*
 * @Repository annotation
 * https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/stereotype/Repository.html
 * 
 * Useful link
 * https://howtodoinjava.com/spring/spring-core/how-to-use-spring-component-repository-service-and-controller-annotations/
 */
@Repository
public class InstructorInfoHistoryDaoImpl extends BaseDaoImpl<InstructorInfoHistory> implements InstructorInfoHistoryDao
{
	@Override
	public int insert(InstructorInfoHistory instructorInfoHistoryModel, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) throws SQLException
	{
		
		validateColumnNames(insertColumnNameList);
		validateColumnNames(keyHolderColumnNameList);

		String queryTemplate = QueryStringBuilder.generateInsertString(InstructorInfoHistory.TABLE_NAME, insertColumnNameList);
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    
	    List<Map<String, Object>> keyList = new ArrayList<>();
	    KeyHolder keyHolder = new GeneratedKeyHolder(keyList);
	    
	    for(String insertColumnName : insertColumnNameList)
	    {
	    	addParameterMapValue(parameters, insertColumnName, instructorInfoHistoryModel);
	    }
	    // new way, but unfortunately unnecessary class creation is slow and wasteful (i.e. wrong)
	    // insertColumnNames.forEach(insertColumnName -> addParameterMap(parameters, insertColumnName, instructorInfoHistoryModel));
	    
	    int rowsAffected = namedParameterJdbcTemplate.update(queryTemplate, parameters, keyHolder);
	    
	    Map<String, Object> keyMap = keyHolder.getKeys();
	    
	    for(String keyHolderColumnName : keyHolderColumnNameList)
	    {
	    	addObjectValue(keyMap, keyHolderColumnName, instructorInfoHistoryModel);
	    }
	    	    
	    return rowsAffected;
		
	}
	
	
	@Override
	public List<InstructorInfoHistory> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException
	{
		InstructorInfoHistoryExtractor extractor = new InstructorInfoHistoryExtractor();
		String queryTemplate = QueryStringBuilder.generateSelectString(InstructorInfoHistory.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    List<InstructorInfoHistory> instructorInfoHistoryList = jdbcTemplate.query(queryTemplate, extractor, parameters);
	    
	    return instructorInfoHistoryList;
	}

	@Override
	public InstructorInfoHistory findById(int id) throws SQLException
	{
		String columnName = QueryStringBuilder.convertColumnName(InstructorInfoHistory.getColumnName(InstructorInfoHistory.Columns.ID), false);
		List<String> selectColumnNames = InstructorInfoHistory.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<InstructorInfoHistory> instructorInfoHistoryList = select(selectColumnNames, queryTermList, orderByList);

		InstructorInfoHistory instructorInfoHistory = null;
	    
	    if(!instructorInfoHistoryList.isEmpty())
	    {
			instructorInfoHistory = instructorInfoHistoryList.get(0);
	    }
	    
	    return instructorInfoHistory;
	}
	
	@Override
	public int update(String columnName, Object newValue, List<QueryTerm> queryTermList)
	{
		String queryTemplate = QueryStringBuilder.generateUpdateString(InstructorInfoHistory.TABLE_NAME, columnName, queryTermList);

		List<Object> objectList = new ArrayList<Object>();
		objectList.add(newValue);
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);
	    
		return rowsAffected;
	}
	
	@Override
	public int delete(List<QueryTerm> queryTermList)
	{
		String queryTemplate = QueryStringBuilder.generateDeleteString(InstructorInfoHistory.TABLE_NAME, queryTermList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);
	    
		return rowsAffected;
	}

	private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, InstructorInfoHistory instructorInfoHistoryModel)
	{
		String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);
    	
    	// Wish this could generalize
    	// The getter must be distinguished unless the models are designed as simply a map of columns-values
    	// Would prefer not being that generic since it may end up leading to all code being collections of strings
		
    	if(insertColumnName.equals(InstructorInfoHistory.getColumnName(InstructorInfoHistory.Columns.ID)))
    	{
    		parameters.addValue(parameterName, instructorInfoHistoryModel.getId());
    	}
		else if(insertColumnName.equals(InstructorInfoHistory.getColumnName(InstructorInfoHistory.Columns.INSTRUCTOR_INFO_ID)))
		{
			parameters.addValue(parameterName, instructorInfoHistoryModel.getInstructorInfoId());
		}
    	else if(insertColumnName.equals(InstructorInfoHistory.getColumnName(InstructorInfoHistory.Columns.RANK)))
    	{
    		parameters.addValue(parameterName, instructorInfoHistoryModel.getRank());
    	}
    	else if(insertColumnName.equals(InstructorInfoHistory.getColumnName(InstructorInfoHistory.Columns.COURSE_LOAD)))
    	{
    		parameters.addValue(parameterName, instructorInfoHistoryModel.getCourseLoad());
    	}/*
    	else if(insertColumnName.equals(InstructorInfoHistory.getColumnName(InstructorInfoHistory.Columns.PHONE_NUMBER)))
    	{
    		parameters.addValue(parameterName, instructorInfoHistoryModel.getPhoneNumber());
    	}*/
    	else if(insertColumnName.equals(InstructorInfoHistory.getColumnName(InstructorInfoHistory.Columns.OFFICE)))
    	{
    		parameters.addValue(parameterName, instructorInfoHistoryModel.getOffice());
    	}
		else if(insertColumnName.equals(InstructorInfoHistory.getColumnName(InstructorInfoHistory.Columns.USER_INFO_ID)))
		{
			parameters.addValue(parameterName, instructorInfoHistoryModel.getUserInfoId());
		}
    	else if(insertColumnName.equals(InstructorInfoHistory.getColumnName(InstructorInfoHistory.Columns.DEPARTMENT)))
    	{
    		parameters.addValue(parameterName, instructorInfoHistoryModel.getDepartment());
    	}
    	else
    	{
    		// should never end up here
    		// lists should have already been validated
    		throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
    	}
	}	

	private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, InstructorInfoHistory instructorInfoHistoryModel)
	{
    	if(keyHolderColumnName.equals(InstructorInfoHistory.getColumnName(InstructorInfoHistory.Columns.ID)))
    	{
    		instructorInfoHistoryModel.setId((Integer) keyMap.get(keyHolderColumnName));
    	}
		else if(keyHolderColumnName.equals(InstructorInfoHistory.getColumnName(InstructorInfoHistory.Columns.INSTRUCTOR_INFO_ID)))
		{
			instructorInfoHistoryModel.setInstructorInfoId((Integer) keyMap.get(keyHolderColumnName));
		}
    	else if(keyHolderColumnName.equals(InstructorInfoHistory.getColumnName(InstructorInfoHistory.Columns.RANK)))
    	{
    		instructorInfoHistoryModel.setRank((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(InstructorInfoHistory.getColumnName(InstructorInfoHistory.Columns.COURSE_LOAD)))
    	{
    		instructorInfoHistoryModel.setCourseLoad((Integer) keyMap.get(keyHolderColumnName));
    	}/*
    	else if(keyHolderColumnName.equals(InstructorInfoHistory.getColumnName(InstructorInfoHistory.Columns.PHONE_NUMBER)))
    	{
    		instructorInfoHistoryModel.setPhoneNumber((String) keyMap.get(keyHolderColumnName));
    	}*/
    	else if(keyHolderColumnName.equals(InstructorInfoHistory.getColumnName(InstructorInfoHistory.Columns.OFFICE)))
    	{
    		instructorInfoHistoryModel.setOffice((String) keyMap.get(keyHolderColumnName));
    	}
		else if(keyHolderColumnName.equals(InstructorInfoHistory.getColumnName(InstructorInfoHistory.Columns.USER_INFO_ID)))
		{
			instructorInfoHistoryModel.setUserInfoId((Integer) keyMap.get(keyHolderColumnName));
		}
    	else if(keyHolderColumnName.equals(InstructorInfoHistory.getColumnName(InstructorInfoHistory.Columns.DEPARTMENT)))
    	{
    		instructorInfoHistoryModel.setDepartment((String) keyMap.get(keyHolderColumnName));
    	}
    	else
    	{
    		// should never end up here
    		// lists should have already been validated
    		throw new IllegalArgumentException("Invalid column name provided: " + keyHolderColumnName);
    	}
	}
	
	@Override
	public void validateColumnNames(List<String> columnNameList)
	{
		List<String> actualColumnNames = InstructorInfoHistory.getColumnNameList();
		boolean valid = actualColumnNames.containsAll(columnNameList);
		
		if(!valid)
		{
			List<String> invalidColumnNames = new ArrayList<>(columnNameList);
			invalidColumnNames.removeAll(actualColumnNames);
			
			throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
		}
	}
}
