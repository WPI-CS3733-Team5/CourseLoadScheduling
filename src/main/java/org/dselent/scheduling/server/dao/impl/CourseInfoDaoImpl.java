package org.dselent.scheduling.server.dao.impl;

import org.dselent.scheduling.server.dao.Course_InfoDao;
import org.dselent.scheduling.server.extractor.CourseInfoExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.miscellaneous.QueryStringBuilder;
import org.dselent.scheduling.server.model.Course_Info;
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

public class CourseInfoDaoImpl extends BaseDaoImpl<Course_Info> implements Course_InfoDao{
    @Override
    public int insert(Course_Info courseInfoModel, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) throws SQLException {
        validateColumnNames(insertColumnNameList);
        validateColumnNames(keyHolderColumnNameList);

        String queryTemplate = QueryStringBuilder.generateInsertString(Course_Info.TABLE_NAME, insertColumnNameList);
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
    public List<Course_Info> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException {
        CourseInfoExtractor extractor = new CourseInfoExtractor();
        String queryTemplate = QueryStringBuilder.generateSelectString(Course_Info.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

        List<Object> objectList = new ArrayList<Object>();

        for(QueryTerm queryTerm : queryTermList) {
            objectList.add(queryTerm.getValue());
        }

        Object[] parameters = objectList.toArray();

        List<Course_Info> courseInfoList = jdbcTemplate.query(queryTemplate, extractor, parameters);

        return courseInfoList;
    }

    @Override
    public Course_Info findById(int id) throws SQLException {
        String columnName = QueryStringBuilder.convertColumnName(Course_Info.getColumnName(Course_Info.Columns.ID), false);
        List<String> selectColumnNames = Course_Info.getColumnNameList();

        List<QueryTerm> queryTermList = new ArrayList<>();
        QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
        queryTermList.add(idTerm);

        List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
        Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
        orderByList.add(order);

        List<Course_Info> courseInfoList = select(selectColumnNames, queryTermList, orderByList);

        Course_Info courseInfo = null;

        if(!courseInfoList.isEmpty()) {
            courseInfo = courseInfoList.get(0);

        }

        return courseInfo;
    }

    @Override
    public int update(String columnName, Object newValue, List<QueryTerm> queryTermList) throws SQLException {
        String queryTemplate = QueryStringBuilder.generateUpdateString(Course_Info.TABLE_NAME, columnName, queryTermList);

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
        String queryTemplate = QueryStringBuilder.generateDeleteString(Course_Info.TABLE_NAME, queryTermList);

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
        List<String> validNames = Course_Info.getColumnNameList();
        boolean valid = validNames.containsAll(columnNameList);

        if (!valid){
            List<String> invalidNames = new ArrayList<>(columnNameList);
            invalidNames.removeAll(validNames);

            throw new IllegalArgumentException("Invalid column names provided: " + invalidNames);
        }
    }

    private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, Course_Info courseInfoModel){
        String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);

        if(insertColumnName.equals(Course_Info.getColumnName(courseInfoModel.Columns.ID))) {
            parameters.addValue(parameterName, courseInfoModel.getId());
        }

        else if(insertColumnName.equals(Course_Info.getColumnName(courseInfoModel.Columns.COURSE_NAME))) {
            parameters.addValue(parameterName, courseInfoModel.getCourseName());
        }

        else if(insertColumnName.equals(Course_Info.getColumnName(courseInfoModel.Columns.REQUIRED_FREQUENCY_PER_TERM))) {
            parameters.addValue(parameterName, courseInfoModel.getRequireFrequencyPerTerm());
        }

        else if(insertColumnName.equals(Course_Info.getColumnName(courseInfoModel.Columns.REQUIRED_FREQUENCY_PER_SEMESTER))) {
            parameters.addValue(parameterName, courseInfoModel.getRequiredFrequencyPerSemester());
        }

        else if(insertColumnName.equals(Course_Info.getColumnName(courseInfoModel.Columns.REQUIRED_FREQUENCY_PER_YEAR))) {
            parameters.addValue(parameterName, courseInfoModel.getRequiredFrequencyPerYear());
        }

        else if(insertColumnName.equals(Course_Info.getColumnName(courseInfoModel.Columns.CREDIT_AMOUNT))) {
            parameters.addValue(parameterName, courseInfoModel.getCreditAmount());
        }

        else if(insertColumnName.equals(Course_Info.getColumnName(courseInfoModel.Columns.DELETED))) {
            parameters.addValue(parameterName, courseInfoModel.getDeleted());
        }

        else if(insertColumnName.equals(Course_Info.getColumnName(courseInfoModel.Columns.DEPARTMENT))) {
            parameters.addValue(parameterName, courseInfoModel.getDepartment());
        }

        else if(insertColumnName.equals(Course_Info.getColumnName(courseInfoModel.Columns.COURSE_NUMBER))) {
            parameters.addValue(parameterName, courseInfoModel.getCourseNumber());
        }

        else {
            throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
        }
    }

    private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, Course_Info courseInfoModel){
        if(keyHolderColumnName.equals(Course_Info.getColumnName(Course_Info.Columns.ID))) {
            courseInfoModel.setId((Integer) keyMap.get(keyHolderColumnName));
        }

        else if(keyHolderColumnName.equals(Course_Info.getColumnName(Course_Info.Columns.COURSE_NAME))) {
            courseInfoModel.setCourseName((String) keyMap.get(keyHolderColumnName));
        }

        else if(keyHolderColumnName.equals(Course_Info.getColumnName(Course_Info.Columns.REQUIRED_FREQUENCY_PER_TERM))) {
            courseInfoModel.setRequireFrequencyPerTerm((Integer) keyMap.get(keyHolderColumnName));
        }

        else if(keyHolderColumnName.equals(Course_Info.getColumnName(Course_Info.Columns.REQUIRED_FREQUENCY_PER_SEMESTER))) {
            courseInfoModel.setRequiredFrequencyPerSemester((Integer) keyMap.get(keyHolderColumnName));
        }

        else if(keyHolderColumnName.equals(Course_Info.getColumnName(Course_Info.Columns.REQUIRED_FREQUENCY_PER_YEAR))) {
            courseInfoModel.setRequiredFrequencyPerYear((Integer) keyMap.get(keyHolderColumnName));
        }

        else if(keyHolderColumnName.equals(Course_Info.getColumnName(Course_Info.Columns.CREDIT_AMOUNT))) {
            courseInfoModel.setCreditAmount((Integer) keyMap.get(keyHolderColumnName));
        }

        else if(keyHolderColumnName.equals(Course_Info.getColumnName(Course_Info.Columns.DELETED))) {
            courseInfoModel.setDeleted((Boolean) keyMap.get(keyHolderColumnName));
        }

        else if(keyHolderColumnName.equals(Course_Info.getColumnName(Course_Info.Columns.DEPARTMENT))) {
            courseInfoModel.setDepartment((Integer) keyMap.get(keyHolderColumnName));
        }

        else if(keyHolderColumnName.equals(Course_Info.getColumnName(Course_Info.Columns.COURSE_NUMBER))) {
            courseInfoModel.setCourseNumber((Integer) keyMap.get(keyHolderColumnName));
        }

        else {
            throw new IllegalArgumentException("Invalid column name provided: " + keyHolderColumnName);
        }
    }
}
