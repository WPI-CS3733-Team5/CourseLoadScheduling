package org.dselent.scheduling.server.dao.impl;

import org.dselent.scheduling.server.extractor.UsersExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.miscellaneous.QueryStringBuilder;
import org.dselent.scheduling.server.model.Instructor_Info;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Instructor_InfoDaoImpl extends BaseDaoImpl<Instructor_Info> implements Instroctor_InfoDao {
    
    @Override
    public int insert(Instructor_Info InstructorInfoModel, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) throws SQLException
    {

        validateColumnNames(insertColumnNameList);
        validateColumnNames(keyHolderColumnNameList);

        String queryTemplate = QueryStringBuilder.generateInsertString(Instructor_Info.TABLE_NAME, insertColumnNameList);
        MapSqlParameterSource parameters = new MapSqlParameterSource();

        List<Map<String, Object>> keyList = new ArrayList<>();
        KeyHolder keyHolder = new GeneratedKeyHolder(keyList);

        for(String insertColumnName : insertColumnNameList)
        {
            addParameterMapValue(parameters, insertColumnName, InstructorInfoModel);
        }
        // new way, but unfortunately unnecessary class creation is slow and wasteful (i.e. wrong)
        // insertColumnNames.forEach(insertColumnName -> addParameterMap(parameters, insertColumnName, InstructorInfoModel));

        int rowsAffected = namedParameterJdbcTemplate.update(queryTemplate, parameters, keyHolder);

        Map<String, Object> keyMap = keyHolder.getKeys();

        for(String keyHolderColumnName : keyHolderColumnNameList)
        {
            addObjectValue(keyMap, keyHolderColumnName, InstructorInfoModel);
        }

        return rowsAffected;

    }

    @Override
    public List<Instructor_Info> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException
    {
        UsersExtractor extractor = new UsersExtractor();
        String queryTemplate = QueryStringBuilder.generateSelectString(Instructor_Info.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

        List<Object> objectList = new ArrayList<Object>();

        for(QueryTerm queryTerm : queryTermList)
        {
            objectList.add(queryTerm.getValue());
        }

        Object[] parameters = objectList.toArray();

        List<Instructor_Info> usersList = jdbcTemplate.query(queryTemplate, extractor, parameters);

        return usersList;
    }

    @Override
    public Instructor_Info findById(int id) throws SQLException
    {
        String columnName = QueryStringBuilder.convertColumnName(Instructor_Info.getColumnName(Instructor_Info.Columns.ID), false);
        List<String> selectColumnNames = Instructor_Info.getColumnNameList();

        List<QueryTerm> queryTermList = new ArrayList<>();
        QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
        queryTermList.add(idTerm);

        List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
        Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
        orderByList.add(order);

        List<Instructor_Info> usersList = select(selectColumnNames, queryTermList, orderByList);

        Instructor_Info userInfo = null;

        if(!usersList.isEmpty())
        {
            userInfo = usersList.get(0);
        }

        return userInfo;
    }

    @Override
    public int update(String columnName, Object newValue, List<QueryTerm> queryTermList)
    {
        String queryTemplate = QueryStringBuilder.generateUpdateString(Instructor_Info.TABLE_NAME, columnName, queryTermList);

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
        String queryTemplate = QueryStringBuilder.generateDeleteString(Instructor_Info.TABLE_NAME, queryTermList);

        List<Object> objectList = new ArrayList<Object>();

        for(QueryTerm queryTerm : queryTermList)
        {
            objectList.add(queryTerm.getValue());
        }

        Object[] parameters = objectList.toArray();

        int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);

        return rowsAffected;
    }

    private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, Instructor_Info InstructorInfoModel)
    {
        String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);

        // Wish this could generalize
        // The getter must be distinguished unless the models are designed as simply a map of columns-values
        // Would prefer not being that generic since it may end up leading to all code being collections of strings

        if(insertColumnName.equals(Instructor_Info.getColumnName(Instructor_Info.Columns.ID)))
        {
            parameters.addValue(parameterName, InstructorInfoModel.getId());
        }
        else if(insertColumnName.equals(Instructor_Info.getColumnName(Instructor_Info.Columns.USER_ROLE)))
        {
            parameters.addValue(parameterName, InstructorInfoModel.getUserRole());
        }
        else if(insertColumnName.equals(Instructor_Info.getColumnName(Instructor_Info.Columns.USERNAME)))
        {
            parameters.addValue(parameterName, InstructorInfoModel.getUserName());
        }
        else if(insertColumnName.equals(Instructor_Info.getColumnName(Instructor_Info.Columns.FIRST_NAME)))
        {
            parameters.addValue(parameterName, InstructorInfoModel.getFirstName());
        }
        else if(insertColumnName.equals(Instructor_Info.getColumnName(Instructor_Info.Columns.LAST_NAME)))
        {
            parameters.addValue(parameterName, InstructorInfoModel.getLastName());
        }
        else if(insertColumnName.equals(Instructor_Info.getColumnName(Instructor_Info.Columns.EMAIL)))
        {
            parameters.addValue(parameterName, InstructorInfoModel.getEmail());
        }
        else if(insertColumnName.equals(Instructor_Info.getColumnName(Instructor_Info.Columns.DELETED)))
        {
            parameters.addValue(parameterName, InstructorInfoModel.getDeleted());
        }
        else if(insertColumnName.equals(Instructor_Info.getColumnName(Instructor_Info.Columns.ENCRYPTED_PASSWORD)))
        {
            parameters.addValue(parameterName, InstructorInfoModel.getEncryptedPassword());
        }
        else if(insertColumnName.equals(Instructor_Info.getColumnName(Instructor_Info.Columns.SALT)))
        {
            parameters.addValue(parameterName, InstructorInfoModel.getSalt());
        }
        else if(insertColumnName.equals(Instructor_Info.getColumnName(Instructor_Info.Columns.ACCOUNT_STATE)))
        {
            parameters.addValue(parameterName, InstructorInfoModel.getAccountState());
        }
        else if(insertColumnName.equals(Instructor_Info.getColumnName(Instructor_Info.Columns.CREATED_AT)))
        {
            parameters.addValue(parameterName, InstructorInfoModel.getCreatedAt());
        }
        else if(insertColumnName.equals(Instructor_Info.getColumnName(Instructor_Info.Columns.UPDATED_AT)))
        {
            parameters.addValue(parameterName, InstructorInfoModel.getUpdatedAt());
        }
        else if(insertColumnName.equals(Instructor_Info.getColumnName(Instructor_Info.Columns.LOGIN_TIME)))
        {
            parameters.addValue(parameterName, InstructorInfoModel.getLoginTime());
        }
        else
        {
            // should never end up here
            // lists should have already been validated
            throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
        }
    }

    private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, Instructor_Info InstructorInfoModel)
    {
        if(keyHolderColumnName.equals(Instructor_Info.getColumnName(Instructor_Info.Columns.ID)))
        {
            InstructorInfoModel.setId((Integer) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(Instructor_Info.getColumnName(Instructor_Info.Columns.USER_ROLE)))
        {
            InstructorInfoModel.setUserRole((Integer) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(Instructor_Info.getColumnName(Instructor_Info.Columns.USERNAME)))
        {
            InstructorInfoModel.setUserName((String) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(Instructor_Info.getColumnName(Instructor_Info.Columns.FIRST_NAME)))
        {
            InstructorInfoModel.setFirstName((String) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(Instructor_Info.getColumnName(Instructor_Info.Columns.LAST_NAME)))
        {
            InstructorInfoModel.setLastName((String) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(Instructor_Info.getColumnName(Instructor_Info.Columns.EMAIL)))
        {
            InstructorInfoModel.setEmail((String) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(Instructor_Info.getColumnName(Instructor_Info.Columns.DELETED)))
        {
            InstructorInfoModel.setDeleted((Boolean) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(Instructor_Info.getColumnName(Instructor_Info.Columns.ENCRYPTED_PASSWORD)))
        {
            InstructorInfoModel.setEncryptedPassword((String) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(Instructor_Info.getColumnName(Instructor_Info.Columns.SALT)))
        {
            InstructorInfoModel.setSalt((String) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(Instructor_Info.getColumnName(Instructor_Info.Columns.ACCOUNT_STATE)))
        {
            InstructorInfoModel.setAccountState((Integer) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(Instructor_Info.getColumnName(Instructor_Info.Columns.CREATED_AT)))
        {
            InstructorInfoModel.setCreatedAt((Timestamp) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(Instructor_Info.getColumnName(Instructor_Info.Columns.UPDATED_AT)))
        {
            InstructorInfoModel.setUpdatedAt((Timestamp) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(Instructor_Info.getColumnName(Instructor_Info.Columns.LOGIN_TIME)))
        {
            InstructorInfoModel.setLoginTime((Timestamp) keyMap.get(keyHolderColumnName));
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
        List<String> actualColumnNames = Instructor_Info.getColumnNameList();
        boolean valid = actualColumnNames.containsAll(columnNameList);

        if(!valid)
        {
            List<String> invalidColumnNames = new ArrayList<>(columnNameList);
            invalidColumnNames.removeAll(actualColumnNames);

            throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
        }
    }
}
