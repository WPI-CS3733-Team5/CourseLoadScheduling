package org.dselent.scheduling.server.dao.impl;

import org.dselent.scheduling.server.dao.Calendar_InfoDao;
import org.dselent.scheduling.server.extractor.CalendarInfoExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.miscellaneous.QueryStringBuilder;
import org.dselent.scheduling.server.model.Calendar_Info;
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

public class CalendarInfoDaoImpl extends BaseDaoImpl<Calendar_Info> implements Calendar_InfoDao {

    @Override
    public int insert(Calendar_Info calendarInfoModel, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) {
        validateColumnNames(insertColumnNameList);
        validateColumnNames(keyHolderColumnNameList);

        String queryTemplate = QueryStringBuilder.generateInsertString(Calendar_Info.TABLE_NAME, insertColumnNameList);
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
    public List<Calendar_Info> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) {
        CalendarInfoExtractor extractor = new CalendarInfoExtractor();
        String queryTemplate = QueryStringBuilder.generateSelectString(Calendar_Info.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

        List<Object> objectList = new ArrayList<Object>();

        for(QueryTerm queryTerm : queryTermList) {
            objectList.add(queryTerm.getValue());
        }

        Object[] parameters = objectList.toArray();

        List<Calendar_Info> calendarInfoList = jdbcTemplate.query(queryTemplate, extractor, parameters);

        return calendarInfoList;
    }

    @Override
    public int update(String columnName, Object newValue, List<QueryTerm> queryTermList) {
        String queryTemplate = QueryStringBuilder.generateUpdateString(Calendar_Info.TABLE_NAME, columnName, queryTermList);

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
    public Calendar_Info findById(int id) throws SQLException{
        String columnName = QueryStringBuilder.convertColumnName(Calendar_Info.getColumnName(Calendar_Info.Columns.ID), false);
        List<String> selectColumnNames = Calendar_Info.getColumnNameList();

        List<QueryTerm> queryTermList = new ArrayList<>();
        QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
        queryTermList.add(idTerm);

        List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
        Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
        orderByList.add(order);

        List<Calendar_Info> calendarInfoList = select(selectColumnNames, queryTermList, orderByList);

        Calendar_Info calendarInfo = null;

        if(!calendarInfoList.isEmpty()) {
            calendarInfo = calendarInfoList.get(0);

        }

        return calendarInfo;
    }


    @Override
    public int delete(List<QueryTerm> queryTermList) {
        String queryTemplate = QueryStringBuilder.generateDeleteString(Calendar_Info.TABLE_NAME, queryTermList);

        List<Object> objectList = new ArrayList<Object>();

        for(QueryTerm queryTerm : queryTermList) {
            objectList.add(queryTerm.getValue());
        }

        Object[] parameters = objectList.toArray();

        int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);

        return rowsAffected;

    }

    private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, Calendar_Info calendarInfoModel){
        String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);

        if(insertColumnName.equals(Calendar_Info.getColumnName(calendarInfoModel.Columns.ID))) {
            parameters.addValue(parameterName, calendarInfoModel.getId());
        }

        else if(insertColumnName.equals(Calendar_Info.getColumnName(calendarInfoModel.Columns.YEAR))) {
            parameters.addValue(parameterName, calendarInfoModel.getYear());
        }

        else if(insertColumnName.equals(Calendar_Info.getColumnName(calendarInfoModel.Columns.SEMESTER))) {
            parameters.addValue(parameterName, calendarInfoModel.getSemester());
        }

        else if(insertColumnName.equals(Calendar_Info.getColumnName(calendarInfoModel.Columns.CREDIT_AMOUNT))) {
            parameters.addValue(parameterName, calendarInfoModel.getCreditAmount());
        }

        else if(insertColumnName.equals(Calendar_Info.getColumnName(calendarInfoModel.Columns.DAYS))){
            parameters.addValue(parameterName, calendarInfoModel.getDays());
        }

        else if(insertColumnName.equals(Calendar_Info.getColumnName(calendarInfoModel.Columns.START_TIME))) {
            parameters.addValue(parameterName, calendarInfoModel.getStartTime());
        }

        else if(insertColumnName.equals(Calendar_Info.getColumnName(calendarInfoModel.Columns.END_TIME))) {
            parameters.addValue(parameterName, calendarInfoModel.getEndTime());
        }

        else {
            throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
        }

    }

    private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, Calendar_Info calendarInfoModel){

        if(keyHolderColumnName.equals(Calendar_Info.getColumnName(Calendar_Info.Columns.ID))) {
            calendarInfoModel.setId((Integer) keyMap.get(keyHolderColumnName));
        }

        else if(keyHolderColumnName.equals(Calendar_Info.getColumnName(Calendar_Info.Columns.YEAR))) {
            calendarInfoModel.setYear((Integer) keyMap.get(keyHolderColumnName));
        }

        else if(keyHolderColumnName.equals(Calendar_Info.getColumnName(Calendar_Info.Columns.SEMESTER))) {
            calendarInfoModel.setSemester((Integer) keyMap.get(keyHolderColumnName));
        }

        else if(keyHolderColumnName.equals(Calendar_Info.getColumnName(Calendar_Info.Columns.CREDIT_AMOUNT))) {
            calendarInfoModel.setCreditAmount((Integer) keyMap.get(keyHolderColumnName));
        }

        else if(keyHolderColumnName.equals(Calendar_Info.getColumnName(Calendar_Info.Columns.DAYS))) {
            calendarInfoModel.setDays((String) keyMap.get(keyHolderColumnName));
        }

        else if(keyHolderColumnName.equals(Calendar_Info.getColumnName(Calendar_Info.Columns.START_TIME))) {
            calendarInfoModel.setStartTime((Integer) keyMap.get(keyHolderColumnName));
        }

        else if(keyHolderColumnName.equals(Calendar_Info.getColumnName(Calendar_Info.Columns.END_TIME))) {
            calendarInfoModel.setEndTime((Integer) keyMap.get(keyHolderColumnName));
        }

        else {
            throw new IllegalArgumentException("Invalid column name provided: " + keyHolderColumnName);
        }
    }

    @Override
    public void validateColumnNames(List<String> columnNameList) {
        List<String> validNames = Calendar_Info.getColumnNameList();
        boolean valid = validNames.containsAll(columnNameList);

        if (!valid){
            List<String> invalidNames = new ArrayList<>(columnNameList);
            invalidNames.removeAll(validNames);

            throw new IllegalArgumentException("Invalid column names provided: " + invalidNames);
        }
    }
}
