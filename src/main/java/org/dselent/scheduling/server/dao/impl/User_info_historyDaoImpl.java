package org.dselent.scheduling.server.dao.impl;

import org.dselent.scheduling.server.dao.User_Info_HistoryDao;
import org.dselent.scheduling.server.dao.User_infoDao;
import org.dselent.scheduling.server.extractor.UsersExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.miscellaneous.QueryStringBuilder;
import org.dselent.scheduling.server.model.User_Info;
import org.dselent.scheduling.server.model.User_Info_History;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.sql.Timestamp;
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
public class User_Info_HistoryDaoImpl extends BaseDaoImpl<User_Info_History> implements User_Info_HistoryDao
{
	@Override
	public int insert(User_Info_History userInfoHistoryModel, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) throws SQLException
	{
		
		validateColumnNames(insertColumnNameList);
		validateColumnNames(keyHolderColumnNameList);

		String queryTemplate = QueryStringBuilder.generateInsertString(User_Info.TABLE_NAME, insertColumnNameList);
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    
	    List<Map<String, Object>> keyList = new ArrayList<>();
	    KeyHolder keyHolder = new GeneratedKeyHolder(keyList);
	    
	    for(String insertColumnName : insertColumnNameList)
	    {
	    	addParameterMapValue(parameters, insertColumnName, userInfoHistoryModel);
	    }
	    // new way, but unfortunately unnecessary class creation is slow and wasteful (i.e. wrong)
	    // insertColumnNames.forEach(insertColumnName -> addParameterMap(parameters, insertColumnName, userInfoHistoryModel));
	    
	    int rowsAffected = namedParameterJdbcTemplate.update(queryTemplate, parameters, keyHolder);
	    
	    Map<String, Object> keyMap = keyHolder.getKeys();
	    
	    for(String keyHolderColumnName : keyHolderColumnNameList)
	    {
	    	addObjectValue(keyMap, keyHolderColumnName, userInfoHistoryModel);
	    }
	    	    
	    return rowsAffected;
		
	}
	
	
	@Override
	public List<User_Info_History> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException
	{
		UsersExtractor extractor = new UsersExtractor();
		String queryTemplate = QueryStringBuilder.generateSelectString(User_Info_History.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    List<User_Info_History> user_info_historyList = jdbcTemplate.query(queryTemplate, extractor, parameters);
	    
	    return user_info_historyList;
	}

	@Override
	public User_Info_History findById(int id) throws SQLException
	{
		String columnName = QueryStringBuilder.convertColumnName(User_Info_History.getColumnName(User_Info_History.Columns.ID), false);
		List<String> selectColumnNames = User_Info_History.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<User_Info_History> user_info_historyList = select(selectColumnNames, queryTermList, orderByList);
	
	    User_Info_History user_info_history = null;
	    
	    if(!user_info_historyList.isEmpty())
	    {
	    	user_info_history = user_info_historyList.get(0);
	    }
	    
	    return user_info_history;
	}
	
	@Override
	public int update(String columnName, Object newValue, List<QueryTerm> queryTermList)
	{
		String queryTemplate = QueryStringBuilder.generateUpdateString(User_Info_History.TABLE_NAME, columnName, queryTermList);

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
		String queryTemplate = QueryStringBuilder.generateDeleteString(User_Info_History.TABLE_NAME, queryTermList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);
	    
		return rowsAffected;
	}

	private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, User_Info_History userInfoHistoryModel)
	{
		String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);
    	
    	// Wish this could generalize
    	// The getter must be distinguished unless the models are designed as simply a map of columns-values
    	// Would prefer not being that generic since it may end up leading to all code being collections of strings
		
    	if(insertColumnName.equals(User_Info_History.getColumnName(User_Info_History.Columns.ID)))
    	{
    		parameters.addValue(parameterName, userInfoHistoryModel.getId());
    	}
		else if(insertColumnName.equals(User_Info_History.getColumnName(User_Info_History.Columns.USER_INFO_ID)))
		{
			parameters.addValue(parameterName, userInfoHistoryModel.getUserRole());
		}
		else if(insertColumnName.equals(User_Info_History.getColumnName(User_Info_History.Columns.USER_ROLE)))
		{
			parameters.addValue(parameterName, userInfoHistoryModel.getUserRole());
		}
    	else if(insertColumnName.equals(User_Info_History.getColumnName(User_Info_History.Columns.USERNAME)))
    	{
    		parameters.addValue(parameterName, userInfoHistoryModel.getUserName());
    	}
    	else if(insertColumnName.equals(User_Info_History.getColumnName(User_Info_History.Columns.FIRST_NAME)))
    	{
    		parameters.addValue(parameterName, userInfoHistoryModel.getFirstName());
    	}
    	else if(insertColumnName.equals(User_Info_History.getColumnName(User_Info_History.Columns.LAST_NAME)))
    	{
    		parameters.addValue(parameterName, userInfoHistoryModel.getLastName());
    	}
    	else if(insertColumnName.equals(User_Info_History.getColumnName(User_Info_History.Columns.EMAIL)))
    	{
    		parameters.addValue(parameterName, userInfoHistoryModel.getEmail());
    	}
    	else if(insertColumnName.equals(User_Info_History.getColumnName(User_Info_History.Columns.ENCRYPTED_PASSWORD)))
    	{
    		parameters.addValue(parameterName, userInfoHistoryModel.getEncryptedPassword());
    	}
    	else if(insertColumnName.equals(User_Info_History.getColumnName(User_Info_History.Columns.SALT)))
    	{
    		parameters.addValue(parameterName, userInfoHistoryModel.getSalt());
    	}
    	else if(insertColumnName.equals(User_Info_History.getColumnName(User_Info_History.Columns.ACCOUNT_STATE)))
    	{
    		parameters.addValue(parameterName, userInfoHistoryModel.getAccountState());
    	}
    	else if(insertColumnName.equals(User_Info_History.getColumnName(User_Info_History.Columns.CREATED_AT)))
    	{
    		parameters.addValue(parameterName, userInfoHistoryModel.getCreatedAt());
    	}
    	else if(insertColumnName.equals(User_Info_History.getColumnName(User_Info_History.Columns.UPDATED_AT)))
    	{
    		parameters.addValue(parameterName, userInfoHistoryModel.getUpdatedAt());
    	}
		else if(insertColumnName.equals(User_Info_History.getColumnName(User_Info_History.Columns.LOGIN_TIME)))
		{
			parameters.addValue(parameterName, userInfoHistoryModel.getLoginTime());
		}
    	else
    	{
    		// should never end up here
    		// lists should have already been validated
    		throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
    	}
	}	

	private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, User_Info_History userInfoHistoryModel)
	{
    	if(keyHolderColumnName.equals(User_Info_History.getColumnName(User_Info_History.Columns.ID)))
    	{
			userInfoHistoryModel.setId((Integer) keyMap.get(keyHolderColumnName));
    	}
		else if(keyHolderColumnName.equals(User_Info_History.getColumnName(User_Info_History.Columns.USER_ROLE)))
		{
			userInfoHistoryModel.setUserRole((Integer) keyMap.get(keyHolderColumnName));
		}
    	else if(keyHolderColumnName.equals(User_Info_History.getColumnName(User_Info_History.Columns.USERNAME)))
    	{
			userInfoHistoryModel.setUserName((String) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(User_Info_History.getColumnName(User_Info_History.Columns.FIRST_NAME)))
    	{
			userInfoHistoryModel.setFirstName((String) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(User_Info_History.getColumnName(User_Info_History.Columns.LAST_NAME)))
    	{
			userInfoHistoryModel.setLastName((String) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(User_Info_History.getColumnName(User_Info_History.Columns.EMAIL)))
    	{
			userInfoHistoryModel.setEmail((String) keyMap.get(keyHolderColumnName));
		}
    	else if(keyHolderColumnName.equals(User_Info_History.getColumnName(User_Info_History.Columns.ENCRYPTED_PASSWORD)))
    	{
			userInfoHistoryModel.setEncryptedPassword((String) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(User_Info_History.getColumnName(User_Info_History.Columns.SALT)))
    	{
			userInfoHistoryModel.setSalt((String) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(User_Info_History.getColumnName(User_Info_History.Columns.ACCOUNT_STATE)))
    	{
			userInfoHistoryModel.setAccountState((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(User_Info_History.getColumnName(User_Info_History.Columns.CREATED_AT)))
    	{
			userInfoHistoryModel.setCreatedAt((Timestamp) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(User_Info_History.getColumnName(User_Info_History.Columns.UPDATED_AT)))
    	{
			userInfoHistoryModel.setUpdatedAt((Timestamp) keyMap.get(keyHolderColumnName));
    	}
		else if(keyHolderColumnName.equals(User_Info_History.getColumnName(User_Info_History.Columns.LOGIN_TIME)))
		{
			userInfoHistoryModel.setLoginTime((Timestamp) keyMap.get(keyHolderColumnName));
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
		List<String> actualColumnNames = User_Info_History.getColumnNameList();
		boolean valid = actualColumnNames.containsAll(columnNameList);
		
		if(!valid)
		{
			List<String> invalidColumnNames = new ArrayList<>(columnNameList);
			invalidColumnNames.removeAll(actualColumnNames);
			
			throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
		}
	}
}
