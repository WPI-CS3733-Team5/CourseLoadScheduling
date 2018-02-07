package org.dselent.scheduling.server.dao.impl;

import org.dselent.scheduling.server.dao.CalendarInfoDao;
import org.dselent.scheduling.server.extractor.CalendarInfoExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.CalendarInfo;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryStringBuilder;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CalendarInfoDaoImpl extends BaseDaoImpl<CalendarInfo> implements CalendarInfoDao {

    @Override
    public int insert(CalendarInfo calendarInfoModel, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) {
        validateColumnNames(insertColumnNameList);
        validateColumnNames(keyHolderColumnNameList);

        String queryTemplate = QueryStringBuilder.generateInsertString(CalendarInfo.TABLE_NAME, insertColumnNameList);
        MapSqlParameterSource parameters = new MapSqlParameterSource();

        List<Map<String, Object>> keyList = new ArrayList<>();
        KeyHolder keyHolder = new GeneratedKeyHolder(keyList);

        for(String insertColumnName : insertColumnNameList) {
            addParameterMapValue(parameters, insertColumnName, calendarInfoModel);
        }

        int rowsAffected = namedParameterJdbcTemplate.update(queryTemplate, parameters, keyHolder);

        Map<String, Object> keyMap = keyHolder.getKeys();

        for(String keyHolderColumnName : keyHolderColumnNameList)
        {
            addObjectValue(keyMap, keyHolderColumnName, calendarInfoModel);
        }

        return rowsAffected;
    }

    @Override
    public List<CalendarInfo> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) {
        CalendarInfoExtractor extractor = new CalendarInfoExtractor();
        String queryTemplate = QueryStringBuilder.generateSelectString(CalendarInfo.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

        List<Object> objectList = new ArrayList<Object>();

        for(QueryTerm queryTerm : queryTermList) {
            objectList.add(queryTerm.getValue());
        }

        Object[] parameters = objectList.toArray();

        List<CalendarInfo> calendarInfoList = jdbcTemplate.query(queryTemplate, extractor, parameters);

        return calendarInfoList;
    }

    @Override
    public int update(String columnName, Object newValue, List<QueryTerm> queryTermList) {
        String queryTemplate = QueryStringBuilder.generateUpdateString(CalendarInfo.TABLE_NAME, columnName, queryTermList);

        List<Object> objectList = new ArrayList<Object>();
        objectList.add(newValue);

        for(QueryTerm queryTerm : queryTermList) {
            objectList.add(queryTerm.getValue());
        }

        Object[] parameters = objectList.toArray();

        int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);

        return rowsAffected;
    }

    @Override
    public CalendarInfo findById(int id) throws SQLException{
        String columnName = QueryStringBuilder.convertColumnName(CalendarInfo.getColumnName(CalendarInfo.Columns.ID), false);
        List<String> selectColumnNames = CalendarInfo.getColumnNameList();

        List<QueryTerm> queryTermList = new ArrayList<>();
        QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
        queryTermList.add(idTerm);

        List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
        Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
        orderByList.add(order);

        List<CalendarInfo> calendarInfoList = select(selectColumnNames, queryTermList, orderByList);

        CalendarInfo calendarInfo = null;

        if(!calendarInfoList.isEmpty()) {
            calendarInfo = calendarInfoList.get(0);

        }

        return calendarInfo;
    }


    @Override
    public int delete(List<QueryTerm> queryTermList) {
        String queryTemplate = QueryStringBuilder.generateDeleteString(CalendarInfo.TABLE_NAME, queryTermList);

        List<Object> objectList = new ArrayList<Object>();

        for(QueryTerm queryTerm : queryTermList) {
            objectList.add(queryTerm.getValue());
        }

        Object[] parameters = objectList.toArray();

        int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);

        return rowsAffected;

    }

    private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, CalendarInfo calendarInfoModel){
        String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);

        if(insertColumnName.equals(CalendarInfo.getColumnName(CalendarInfo.Columns.ID))) {
            parameters.addValue(parameterName, calendarInfoModel.getId());
        }

        else if(insertColumnName.equals(CalendarInfo.getColumnName(CalendarInfo.Columns.YEAR))) {
            parameters.addValue(parameterName, calendarInfoModel.getYear());
        }

        else if(insertColumnName.equals(CalendarInfo.getColumnName(CalendarInfo.Columns.SEMESTER))) {
            parameters.addValue(parameterName, calendarInfoModel.getSemester());
        }

        else if(insertColumnName.equals(CalendarInfo.getColumnName(CalendarInfo.Columns.CREDIT_AMOUNT))) {
            parameters.addValue(parameterName, calendarInfoModel.getCreditAmount());
        }

        else if(insertColumnName.equals(CalendarInfo.getColumnName(CalendarInfo.Columns.DAYS))){
            parameters.addValue(parameterName, calendarInfoModel.getDays());
        }

        else if(insertColumnName.equals(CalendarInfo.getColumnName(CalendarInfo.Columns.START_TIME))) {
            parameters.addValue(parameterName, calendarInfoModel.getStartTime());
        }

        else if(insertColumnName.equals(CalendarInfo.getColumnName(CalendarInfo.Columns.END_TIME))) {
            parameters.addValue(parameterName, calendarInfoModel.getEndTime());
        }

        else {
            throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
        }

    }

    private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, CalendarInfo calendarInfoModel){

        if(keyHolderColumnName.equals(CalendarInfo.getColumnName(CalendarInfo.Columns.ID))) {
            calendarInfoModel.setId((Integer) keyMap.get(keyHolderColumnName));
        }

        else if(keyHolderColumnName.equals(CalendarInfo.getColumnName(CalendarInfo.Columns.YEAR))) {
            calendarInfoModel.setYear((Integer) keyMap.get(keyHolderColumnName));
        }

        else if(keyHolderColumnName.equals(CalendarInfo.getColumnName(CalendarInfo.Columns.SEMESTER))) {
            calendarInfoModel.setSemester((Integer) keyMap.get(keyHolderColumnName));
        }

        else if(keyHolderColumnName.equals(CalendarInfo.getColumnName(CalendarInfo.Columns.CREDIT_AMOUNT))) {
            calendarInfoModel.setCreditAmount((Integer) keyMap.get(keyHolderColumnName));
        }

        else if(keyHolderColumnName.equals(CalendarInfo.getColumnName(CalendarInfo.Columns.DAYS))) {
            calendarInfoModel.setDays((String) keyMap.get(keyHolderColumnName));
        }

        else if(keyHolderColumnName.equals(CalendarInfo.getColumnName(CalendarInfo.Columns.START_TIME))) {
            calendarInfoModel.setStartTime((Integer) keyMap.get(keyHolderColumnName));
        }

        else if(keyHolderColumnName.equals(CalendarInfo.getColumnName(CalendarInfo.Columns.END_TIME))) {
            calendarInfoModel.setEndTime((Integer) keyMap.get(keyHolderColumnName));
        }

        else {
            throw new IllegalArgumentException("Invalid column name provided: " + keyHolderColumnName);
        }
    }

    @Override
    public void validateColumnNames(List<String> columnNameList) {
        List<String> validNames = CalendarInfo.getColumnNameList();
        boolean valid = validNames.containsAll(columnNameList);

        if (!valid){
            List<String> invalidNames = new ArrayList<>(columnNameList);
            invalidNames.removeAll(validNames);

            throw new IllegalArgumentException("Invalid column names provided: " + invalidNames);
        }
    }
}
