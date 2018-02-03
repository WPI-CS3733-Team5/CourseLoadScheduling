package org.dselent.scheduling.server.dao.impl;

import org.dselent.scheduling.server.dao.CourseInfoDao;
import org.dselent.scheduling.server.extractor.CourseInfoExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.miscellaneous.QueryStringBuilder;
import org.dselent.scheduling.server.model.CourseInfo;
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

public class CourseInfoDaoImpl extends BaseDaoImpl<CourseInfo> implements CourseInfoDao{
    @Override
    public int insert(CourseInfo courseInfoModel, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) throws SQLException {
        validateColumnNames(insertColumnNameList);
        validateColumnNames(keyHolderColumnNameList);

        String queryTemplate = QueryStringBuilder.generateInsertString(CourseInfo.TABLE_NAME, insertColumnNameList);
        MapSqlParameterSource parameters = new MapSqlParameterSource();

        List<Map<String, Object>> keyList = new ArrayList<>();
        KeyHolder keyHolder = new GeneratedKeyHolder(keyList);

        for(String insertColumnName : insertColumnNameList) {
            addParameterMapValue(parameters, insertColumnName, courseInfoModel);
        }

        int rowsAffected = namedParameterJdbcTemplate.update(queryTemplate, parameters, keyHolder);

        Map<String, Object> keyMap = keyHolder.getKeys();

        for(String keyHolderColumnName : keyHolderColumnNameList)
        {
            addObjectValue(keyMap, keyHolderColumnName, courseInfoModel);
        }

        return rowsAffected;
    }

    @Override
    public List<CourseInfo> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException {
        CourseInfoExtractor extractor = new CourseInfoExtractor();
        String queryTemplate = QueryStringBuilder.generateSelectString(CourseInfo.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

        List<Object> objectList = new ArrayList<Object>();

        for(QueryTerm queryTerm : queryTermList) {
            objectList.add(queryTerm.getValue());
        }

        Object[] parameters = objectList.toArray();

        List<CourseInfo> courseInfoList = jdbcTemplate.query(queryTemplate, extractor, parameters);

        return courseInfoList;
    }

    @Override
    public CourseInfo findById(int id) throws SQLException {
        String columnName = QueryStringBuilder.convertColumnName(CourseInfo.getColumnName(CourseInfo.Columns.ID), false);
        List<String> selectColumnNames = CourseInfo.getColumnNameList();

        List<QueryTerm> queryTermList = new ArrayList<>();
        QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
        queryTermList.add(idTerm);

        List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
        Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
        orderByList.add(order);

        List<CourseInfo> courseInfoList = select(selectColumnNames, queryTermList, orderByList);

        CourseInfo courseInfo = null;

        if(!courseInfoList.isEmpty()) {
            courseInfo = courseInfoList.get(0);

        }

        return courseInfo;
    }

    @Override
    public int update(String columnName, Object newValue, List<QueryTerm> queryTermList) throws SQLException {
        String queryTemplate = QueryStringBuilder.generateUpdateString(CourseInfo.TABLE_NAME, columnName, queryTermList);

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
    public int delete(List<QueryTerm> queryTermList) throws SQLException {
        String queryTemplate = QueryStringBuilder.generateDeleteString(CourseInfo.TABLE_NAME, queryTermList);

        List<Object> objectList = new ArrayList<Object>();

        for(QueryTerm queryTerm : queryTermList) {
            objectList.add(queryTerm.getValue());
        }

        Object[] parameters = objectList.toArray();

        int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);

        return rowsAffected;
    }

    @Override
    public void validateColumnNames(List<String> columnNameList) {
        List<String> validNames = CourseInfo.getColumnNameList();
        boolean valid = validNames.containsAll(columnNameList);

        if (!valid){
            List<String> invalidNames = new ArrayList<>(columnNameList);
            invalidNames.removeAll(validNames);

            throw new IllegalArgumentException("Invalid column names provided: " + invalidNames);
        }
    }

    private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, CourseInfo courseInfoModel){
        String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);

        if(insertColumnName.equals(CourseInfo.getColumnName(CourseInfo.Columns.ID))) {
            parameters.addValue(parameterName, courseInfoModel.getId());
        }

        else if(insertColumnName.equals(CourseInfo.getColumnName(CourseInfo.Columns.COURSE_NAME))) {
            parameters.addValue(parameterName, courseInfoModel.getCourseName());
        }

        else if(insertColumnName.equals(CourseInfo.getColumnName(CourseInfo.Columns.REQUIRED_FREQUENCY_PER_TERM))) {
            parameters.addValue(parameterName, courseInfoModel.getRequireFrequencyPerTerm());
        }

        else if(insertColumnName.equals(CourseInfo.getColumnName(CourseInfo.Columns.REQUIRED_FREQUENCY_PER_SEMESTER))) {
            parameters.addValue(parameterName, courseInfoModel.getRequiredFrequencyPerSemester());
        }

        else if(insertColumnName.equals(CourseInfo.getColumnName(CourseInfo.Columns.REQUIRED_FREQUENCY_PER_YEAR))) {
            parameters.addValue(parameterName, courseInfoModel.getRequiredFrequencyPerYear());
        }

        else if(insertColumnName.equals(CourseInfo.getColumnName(CourseInfo.Columns.CREDIT_AMOUNT))) {
            parameters.addValue(parameterName, courseInfoModel.getCreditAmount());
        }

        else if(insertColumnName.equals(CourseInfo.getColumnName(CourseInfo.Columns.DELETED))) {
            parameters.addValue(parameterName, courseInfoModel.getDeleted());
        }

        else if(insertColumnName.equals(CourseInfo.getColumnName(CourseInfo.Columns.DEPARTMENT))) {
            parameters.addValue(parameterName, courseInfoModel.getDepartment());
        }

        else if(insertColumnName.equals(CourseInfo.getColumnName(CourseInfo.Columns.COURSE_NUMBER))) {
            parameters.addValue(parameterName, courseInfoModel.getCourseNumber());
        }

        else {
            throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
        }
    }

    private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, CourseInfo courseInfoModel){
        if(keyHolderColumnName.equals(CourseInfo.getColumnName(CourseInfo.Columns.ID))) {
            courseInfoModel.setId((Integer) keyMap.get(keyHolderColumnName));
        }

        else if(keyHolderColumnName.equals(CourseInfo.getColumnName(CourseInfo.Columns.COURSE_NAME))) {
            courseInfoModel.setCourseName((String) keyMap.get(keyHolderColumnName));
        }

        else if(keyHolderColumnName.equals(CourseInfo.getColumnName(CourseInfo.Columns.REQUIRED_FREQUENCY_PER_TERM))) {
            courseInfoModel.setRequireFrequencyPerTerm((Integer) keyMap.get(keyHolderColumnName));
        }

        else if(keyHolderColumnName.equals(CourseInfo.getColumnName(CourseInfo.Columns.REQUIRED_FREQUENCY_PER_SEMESTER))) {
            courseInfoModel.setRequiredFrequencyPerSemester((Integer) keyMap.get(keyHolderColumnName));
        }

        else if(keyHolderColumnName.equals(CourseInfo.getColumnName(CourseInfo.Columns.REQUIRED_FREQUENCY_PER_YEAR))) {
            courseInfoModel.setRequiredFrequencyPerYear((Integer) keyMap.get(keyHolderColumnName));
        }

        else if(keyHolderColumnName.equals(CourseInfo.getColumnName(CourseInfo.Columns.CREDIT_AMOUNT))) {
            courseInfoModel.setCreditAmount((Integer) keyMap.get(keyHolderColumnName));
        }

        else if(keyHolderColumnName.equals(CourseInfo.getColumnName(CourseInfo.Columns.DELETED))) {
            courseInfoModel.setDeleted((Boolean) keyMap.get(keyHolderColumnName));
        }

        else if(keyHolderColumnName.equals(CourseInfo.getColumnName(CourseInfo.Columns.DEPARTMENT))) {
            courseInfoModel.setDepartment((Integer) keyMap.get(keyHolderColumnName));
        }

        else if(keyHolderColumnName.equals(CourseInfo.getColumnName(CourseInfo.Columns.COURSE_NUMBER))) {
            courseInfoModel.setCourseNumber((Integer) keyMap.get(keyHolderColumnName));
        }

        else {
            throw new IllegalArgumentException("Invalid column name provided: " + keyHolderColumnName);
        }
    }
}
