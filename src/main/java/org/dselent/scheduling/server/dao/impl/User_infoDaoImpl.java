package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.User_infoDao;
import org.dselent.scheduling.server.extractor.User_InfoExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.miscellaneous.QueryStringBuilder;
import org.dselent.scheduling.server.model.User_Info;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;


/*
 * @Repository annotation
 * https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/stereotype/Repository.html
 * 
 * Useful link
 * https://howtodoinjava.com/spring/spring-core/how-to-use-spring-component-repository-service-and-controller-annotations/
 */
@Repository
public class User_infoDaoImpl extends BaseDaoImpl<User_Info> implements User_infoDao
{
	@Override
	public int insert(User_Info userInfoModel, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) throws SQLException
	{
		
		validateColumnNames(insertColumnNameList);
		validateColumnNames(keyHolderColumnNameList);

		String queryTemplate = QueryStringBuilder.generateInsertString(User_Info.TABLE_NAME, insertColumnNameList);
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    
	    List<Map<String, Object>> keyList = new ArrayList<>();
	    KeyHolder keyHolder = new GeneratedKeyHolder(keyList);
	    
	    for(String insertColumnName : insertColumnNameList)
	    {
	    	addParameterMapValue(parameters, insertColumnName, userInfoModel);
	    }
	    // new way, but unfortunately unnecessary class creation is slow and wasteful (i.e. wrong)
	    // insertColumnNames.forEach(insertColumnName -> addParameterMap(parameters, insertColumnName, userInfoModel));
	    
	    int rowsAffected = namedParameterJdbcTemplate.update(queryTemplate, parameters, keyHolder);
	    
	    Map<String, Object> keyMap = keyHolder.getKeys();
	    
	    for(String keyHolderColumnName : keyHolderColumnNameList)
	    {
	    	addObjectValue(keyMap, keyHolderColumnName, userInfoModel);
	    }
	    	    
	    return rowsAffected;
		
	}
	
	
	@Override
	public List<User_Info> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException
	{
		User_InfoExtractor extractor = new User_InfoExtractor();
		String queryTemplate = QueryStringBuilder.generateSelectString(User_Info.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    List<User_Info> usersList = jdbcTemplate.query(queryTemplate, extractor, parameters);
	    
	    return usersList;
	}

	@Override
	public User_Info findById(int id) throws SQLException
	{
		String columnName = QueryStringBuilder.convertColumnName(User_Info.getColumnName(User_Info.Columns.ID), false);
		List<String> selectColumnNames = User_Info.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<User_Info> usersList = select(selectColumnNames, queryTermList, orderByList);
	
	    User_Info userInfo = null;
	    
	    if(!usersList.isEmpty())
	    {
	    	userInfo = usersList.get(0);
	    }
	    
	    return userInfo;
	}
	
	@Override
	public int update(String columnName, Object newValue, List<QueryTerm> queryTermList)
	{
		String queryTemplate = QueryStringBuilder.generateUpdateString(User_Info.TABLE_NAME, columnName, queryTermList);

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
		String queryTemplate = QueryStringBuilder.generateDeleteString(User_Info.TABLE_NAME, queryTermList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);
	    
		return rowsAffected;
	}

	private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, User_Info userInfoModel)
	{
		String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);
    	
    	// Wish this could generalize
    	// The getter must be distinguished unless the models are designed as simply a map of columns-values
    	// Would prefer not being that generic since it may end up leading to all code being collections of strings
		
    	if(insertColumnName.equals(User_Info.getColumnName(User_Info.Columns.ID)))
    	{
    		parameters.addValue(parameterName, userInfoModel.getId());
    	}
		else if(insertColumnName.equals(User_Info.getColumnName(User_Info.Columns.USER_ROLE)))
		{
			parameters.addValue(parameterName, userInfoModel.getUserRole());
		}
    	else if(insertColumnName.equals(User_Info.getColumnName(User_Info.Columns.USERNAME)))
    	{
    		parameters.addValue(parameterName, userInfoModel.getUserName());
    	}
    	else if(insertColumnName.equals(User_Info.getColumnName(User_Info.Columns.FIRST_NAME)))
    	{
    		parameters.addValue(parameterName, userInfoModel.getFirstName());
    	}
    	else if(insertColumnName.equals(User_Info.getColumnName(User_Info.Columns.LAST_NAME)))
    	{
    		parameters.addValue(parameterName, userInfoModel.getLastName());
    	}
    	else if(insertColumnName.equals(User_Info.getColumnName(User_Info.Columns.EMAIL)))
    	{
    		parameters.addValue(parameterName, userInfoModel.getEmail());
    	}
		else if(insertColumnName.equals(User_Info.getColumnName(User_Info.Columns.DELETED)))
		{
			parameters.addValue(parameterName, userInfoModel.getDeleted());
		}
    	else if(insertColumnName.equals(User_Info.getColumnName(User_Info.Columns.ENCRYPTED_PASSWORD)))
    	{
    		parameters.addValue(parameterName, userInfoModel.getEncryptedPassword());
    	}
    	else if(insertColumnName.equals(User_Info.getColumnName(User_Info.Columns.SALT)))
    	{
    		parameters.addValue(parameterName, userInfoModel.getSalt());
    	}
    	else if(insertColumnName.equals(User_Info.getColumnName(User_Info.Columns.ACCOUNT_STATE)))
    	{
    		parameters.addValue(parameterName, userInfoModel.getAccountState());
    	}
    	else if(insertColumnName.equals(User_Info.getColumnName(User_Info.Columns.CREATED_AT)))
    	{
    		parameters.addValue(parameterName, userInfoModel.getCreatedAt());
    	}
    	else if(insertColumnName.equals(User_Info.getColumnName(User_Info.Columns.UPDATED_AT)))
    	{
    		parameters.addValue(parameterName, userInfoModel.getUpdatedAt());
    	}
		else if(insertColumnName.equals(User_Info.getColumnName(User_Info.Columns.LOGIN_TIME)))
		{
			parameters.addValue(parameterName, userInfoModel.getLoginTime());
		}
    	else
    	{
    		// should never end up here
    		// lists should have already been validated
    		throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
    	}
	}	

	private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, User_Info userInfoModel)
	{
    	if(keyHolderColumnName.equals(User_Info.getColumnName(User_Info.Columns.ID)))
    	{
    		userInfoModel.setId((Integer) keyMap.get(keyHolderColumnName));
    	}
		else if(keyHolderColumnName.equals(User_Info.getColumnName(User_Info.Columns.USER_ROLE)))
		{
			userInfoModel.setUserRole((Integer) keyMap.get(keyHolderColumnName));
		}
    	else if(keyHolderColumnName.equals(User_Info.getColumnName(User_Info.Columns.USERNAME)))
    	{
    		userInfoModel.setUserName((String) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(User_Info.getColumnName(User_Info.Columns.FIRST_NAME)))
    	{
    		userInfoModel.setFirstName((String) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(User_Info.getColumnName(User_Info.Columns.LAST_NAME)))
    	{
    		userInfoModel.setLastName((String) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(User_Info.getColumnName(User_Info.Columns.EMAIL)))
    	{
    		userInfoModel.setEmail((String) keyMap.get(keyHolderColumnName));
    	}
		else if(keyHolderColumnName.equals(User_Info.getColumnName(User_Info.Columns.DELETED)))
		{
			userInfoModel.setDeleted((Boolean) keyMap.get(keyHolderColumnName));
		}
    	else if(keyHolderColumnName.equals(User_Info.getColumnName(User_Info.Columns.ENCRYPTED_PASSWORD)))
    	{
    		userInfoModel.setEncryptedPassword((String) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(User_Info.getColumnName(User_Info.Columns.SALT)))
    	{
    		userInfoModel.setSalt((String) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(User_Info.getColumnName(User_Info.Columns.ACCOUNT_STATE)))
    	{
    		userInfoModel.setAccountState((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(User_Info.getColumnName(User_Info.Columns.CREATED_AT)))
    	{
    		userInfoModel.setCreatedAt((Timestamp) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(User_Info.getColumnName(User_Info.Columns.UPDATED_AT)))
    	{
    		userInfoModel.setUpdatedAt((Timestamp) keyMap.get(keyHolderColumnName));
    	}
		else if(keyHolderColumnName.equals(User_Info.getColumnName(User_Info.Columns.LOGIN_TIME)))
		{
			userInfoModel.setLoginTime((Timestamp) keyMap.get(keyHolderColumnName));
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
		List<String> actualColumnNames = User_Info.getColumnNameList();
		boolean valid = actualColumnNames.containsAll(columnNameList);
		
		if(!valid)
		{
			List<String> invalidColumnNames = new ArrayList<>(columnNameList);
			invalidColumnNames.removeAll(actualColumnNames);
			
			throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
		}
	}
}
