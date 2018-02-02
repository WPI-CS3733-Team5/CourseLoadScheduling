package org.dselent.scheduling.server.dao.impl;

import org.dselent.scheduling.server.dao.InstructorInfoDao;
import org.dselent.scheduling.server.extractor.InstructorInfoExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.miscellaneous.QueryStringBuilder;
import org.dselent.scheduling.server.model.InstructorInfo;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InstructorInfoDaoImpl extends BaseDaoImpl<InstructorInfo> implements InstructorInfoDao {
    
    @Override
    public int insert(InstructorInfo InstructorInfoModel, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) throws SQLException
    {

        validateColumnNames(insertColumnNameList);
        validateColumnNames(keyHolderColumnNameList);

        String queryTemplate = QueryStringBuilder.generateInsertString(InstructorInfo.TABLE_NAME, insertColumnNameList);
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
    public List<InstructorInfo> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException
    {
        InstructorInfoExtractor extractor = new InstructorInfoExtractor();
        String queryTemplate = QueryStringBuilder.generateSelectString(InstructorInfo.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

        List<Object> objectList = new ArrayList<Object>();

        for(QueryTerm queryTerm : queryTermList)
        {
            objectList.add(queryTerm.getValue());
        }

        Object[] parameters = objectList.toArray();

        List<InstructorInfo> instructorInfoList = jdbcTemplate.query(queryTemplate, extractor, parameters);

        return instructorInfoList;
    }

    @Override
    public InstructorInfo findById(int id) throws SQLException
    {
        String columnName = QueryStringBuilder.convertColumnName(InstructorInfo.getColumnName(InstructorInfo.Columns.ID), false);
        List<String> selectColumnNames = InstructorInfo.getColumnNameList();

        List<QueryTerm> queryTermList = new ArrayList<>();
        QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
        queryTermList.add(idTerm);

        List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
        Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
        orderByList.add(order);

        List<InstructorInfo> usersList = select(selectColumnNames, queryTermList, orderByList);

        InstructorInfo userInfo = null;

        if(!usersList.isEmpty())
        {
            userInfo = usersList.get(0);
        }

        return userInfo;
    }

    @Override
    public int update(String columnName, Object newValue, List<QueryTerm> queryTermList)
    {
        String queryTemplate = QueryStringBuilder.generateUpdateString(InstructorInfo.TABLE_NAME, columnName, queryTermList);

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
        String queryTemplate = QueryStringBuilder.generateDeleteString(InstructorInfo.TABLE_NAME, queryTermList);

        List<Object> objectList = new ArrayList<Object>();

        for(QueryTerm queryTerm : queryTermList)
        {
            objectList.add(queryTerm.getValue());
        }

        Object[] parameters = objectList.toArray();

        int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);

        return rowsAffected;
    }

    private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, InstructorInfo InstructorInfoModel)
    {
        String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);

        // Wish this could generalize
        // The getter must be distinguished unless the models are designed as simply a map of columns-values
        // Would prefer not being that generic since it may end up leading to all code being collections of strings

        if(insertColumnName.equals(InstructorInfo.getColumnName(InstructorInfo.Columns.ID)))
        {
            parameters.addValue(parameterName, InstructorInfoModel.getId());
        }
        else if(insertColumnName.equals(InstructorInfo.getColumnName(InstructorInfo.Columns.RANK)))
        {
            parameters.addValue(parameterName, InstructorInfoModel.getRank());
        }
        else if(insertColumnName.equals(InstructorInfo.getColumnName(InstructorInfo.Columns.COURSE_LOAD)))
        {
            parameters.addValue(parameterName, InstructorInfoModel.getCourseLoad());
        }
        else if(insertColumnName.equals(InstructorInfo.getColumnName(InstructorInfo.Columns.PHONE_NUMBER)))
        {
            parameters.addValue(parameterName, InstructorInfoModel.getPhoneNumber());
        }
        else if(insertColumnName.equals(InstructorInfo.getColumnName(InstructorInfo.Columns.OFFICE)))
        {
            parameters.addValue(parameterName, InstructorInfoModel.getOffice());
        }
        else if(insertColumnName.equals(InstructorInfo.getColumnName(InstructorInfo.Columns.USER_INFO_ID)))
        {
            parameters.addValue(parameterName, InstructorInfoModel.getUserInfoId());
        }
        else if(insertColumnName.equals(InstructorInfo.getColumnName(InstructorInfo.Columns.DEPARTMENT)))
        {
            parameters.addValue(parameterName, InstructorInfoModel.getDepartment());
        }
        else
        {
            // should never end up here
            // lists should have already been validated
            throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
        }
    }

    private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, InstructorInfo InstructorInfoModel)
    {
        if(keyHolderColumnName.equals(InstructorInfo.getColumnName(InstructorInfo.Columns.ID)))
        {
            InstructorInfoModel.setId((Integer) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(InstructorInfo.getColumnName(InstructorInfo.Columns.RANK)))
        {
            InstructorInfoModel.setRank((String) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(InstructorInfo.getColumnName(InstructorInfo.Columns.COURSE_LOAD)))
        {
            InstructorInfoModel.setCourseLoad((Integer) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(InstructorInfo.getColumnName(InstructorInfo.Columns.PHONE_NUMBER)))
        {
            InstructorInfoModel.setCourseLoad((Integer) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(InstructorInfo.getColumnName(InstructorInfo.Columns.OFFICE)))
        {
            InstructorInfoModel.setOffice((String) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(InstructorInfo.getColumnName(InstructorInfo.Columns.USER_INFO_ID)))
        {
            InstructorInfoModel.setUserInfoId((Integer) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(InstructorInfo.getColumnName(InstructorInfo.Columns.DEPARTMENT)))
        {
            InstructorInfoModel.setDepartment((String) keyMap.get(keyHolderColumnName));
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
        List<String> actualColumnNames = InstructorInfo.getColumnNameList();
        boolean valid = actualColumnNames.containsAll(columnNameList);

        if(!valid)
        {
            List<String> invalidColumnNames = new ArrayList<>(columnNameList);
            invalidColumnNames.removeAll(actualColumnNames);

            throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
        }
    }
}
