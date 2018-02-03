package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.LabInfoDao;
import org.dselent.scheduling.server.extractor.LabInfoExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.LabInfo;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryStringBuilder;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;


@Repository
public class LabInfoDaoImpl extends BaseDaoImpl<LabInfo> implements LabInfoDao
{
    @Override
    public int insert(LabInfo labInfoModel, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) throws SQLException
    {
        validateColumnNames(insertColumnNameList);
        validateColumnNames(keyHolderColumnNameList);

        String queryTemplate = QueryStringBuilder.generateInsertString(LabInfo.TABLE_NAME, insertColumnNameList);
        MapSqlParameterSource parameters = new MapSqlParameterSource();

        List<Map<String, Object>> keyList = new ArrayList<>();
        KeyHolder keyHolder = new GeneratedKeyHolder(keyList);

        for(String insertColumnName : insertColumnNameList)
        {
           addParameterMapValue(parameters, insertColumnName, labInfoModel);
        }

        int rowsAffected = namedParameterJdbcTemplate.update(queryTemplate, parameters, keyHolder);

        Map<String, Object> keyMap = keyHolder.getKeys();

        for(String keyHolderColumnName : keyHolderColumnNameList)
        {
           addObjectValue(keyMap, keyHolderColumnName, labInfoModel);
        }

        return rowsAffected;

    }


    @Override
    public List<LabInfo> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException
    {
        LabInfoExtractor extractor = new LabInfoExtractor();
        String queryTemplate = QueryStringBuilder.generateSelectString(LabInfo.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

        List<Object> objectList = new ArrayList<Object>();

        for(QueryTerm queryTerm : queryTermList)
        {
            objectList.add(queryTerm.getValue());
        }

        Object[] parameters = objectList.toArray();

        List<LabInfo> labInfoList = jdbcTemplate.query(queryTemplate, extractor, parameters);

        return labInfoList;

    }

    @Override
    public LabInfo findById(int id) throws SQLException
    {

        String columnName = QueryStringBuilder.convertColumnName(LabInfo.getColumnName(LabInfo.Columns.ID), false);
        List<String> selectColumnNames = LabInfo.getColumnNameList();

        List<QueryTerm> queryTermList = new ArrayList<>();
        QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
        queryTermList.add(idTerm);

        List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
        Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
        orderByList.add(order);

        List<LabInfo> labInfoList = select(selectColumnNames, queryTermList, orderByList);

        LabInfo labInfo = null;

        if(!labInfoList.isEmpty())
        {
            labInfo = labInfoList.get(0);

        }

        return labInfo;
    }

    @Override
    public int update(String columnName, Object newValue, List<QueryTerm> queryTermList)
    {
        String queryTemplate = QueryStringBuilder.generateUpdateString(LabInfo.TABLE_NAME, columnName, queryTermList);

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
        String queryTemplate = QueryStringBuilder.generateDeleteString(LabInfo.TABLE_NAME, queryTermList);

        List<Object> objectList = new ArrayList<Object>();

        for(QueryTerm queryTerm : queryTermList)
        {
            objectList.add(queryTerm.getValue());
        }

        Object[] parameters = objectList.toArray();

        int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);

        return rowsAffected;

    }

    private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, LabInfo labInfoModel)
    {
        String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);

        if(insertColumnName.equals(LabInfo.getColumnName(LabInfo.Columns.ID)))
        {
            parameters.addValue(parameterName, labInfoModel.getId());
        }

        else if(insertColumnName.equals(LabInfo.getColumnName(LabInfo.Columns.SECTION_INFO_ID)))
        {
            parameters.addValue(parameterName, labInfoModel.getSectionInfoId());
        }

        else if(insertColumnName.equals(LabInfo.getColumnName(LabInfo.Columns.INSTRUCTOR_INFO_ID)))
        {
            parameters.addValue(parameterName, labInfoModel.getInstructorInfoId());
        }

        else if(insertColumnName.equals(LabInfo.getColumnName(LabInfo.Columns.LOCATION)))
        {
            parameters.addValue(parameterName, labInfoModel.getLocation());
        }

        else if(insertColumnName.equals(LabInfo.getColumnName(LabInfo.Columns.CALENDAR_INFO_ID)))
        {
            parameters.addValue(parameterName, labInfoModel.getCalendarInfoId());
        }

        else
        {
            throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
        }

    }

    private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, LabInfo labInfoModel)
    {
        if(keyHolderColumnName.equals(LabInfo.getColumnName(LabInfo.Columns.ID)))
        {
            labInfoModel.setId((Integer) keyMap.get(keyHolderColumnName));
        }

        else if(keyHolderColumnName.equals(LabInfo.getColumnName(LabInfo.Columns.SECTION_INFO_ID)))
        {
            labInfoModel.setSectionInfoId((Integer) keyMap.get(keyHolderColumnName));
        }

        else if(keyHolderColumnName.equals(LabInfo.getColumnName(LabInfo.Columns.INSTRUCTOR_INFO_ID)))
        {
            labInfoModel.setInstructorInfoId((Integer) keyMap.get(keyHolderColumnName));
        }

        else if(keyHolderColumnName.equals(LabInfo.getColumnName(LabInfo.Columns.LOCATION)))
        {
            labInfoModel.setLocation((String) keyMap.get(keyHolderColumnName));
        }

        else if(keyHolderColumnName.equals(LabInfo.getColumnName(LabInfo.Columns.CALENDAR_INFO_ID)))
        {
            labInfoModel.setCalendarInfoId((Integer) keyMap.get(keyHolderColumnName));
        }

        else
        {
            throw new IllegalArgumentException("Invalid column name provided: " + keyHolderColumnName);
        }

    }

    @Override
    public void validateColumnNames(List<String> columnNameList)
    {
        List<String> actualColumnNames = LabInfo.getColumnNameList();
        boolean valid = actualColumnNames.containsAll(columnNameList);

        if(!valid)
        {
            List<String> invalidColumnNames = new ArrayList<>(columnNameList);
            invalidColumnNames.removeAll(actualColumnNames);

            throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
        }

    }
}
