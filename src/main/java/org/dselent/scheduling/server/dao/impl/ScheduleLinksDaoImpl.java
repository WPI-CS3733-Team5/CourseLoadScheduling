package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.ScheduleLinksDao;
import org.dselent.scheduling.server.model.ScheduleLinks;
import org.dselent.scheduling.server.extractor.ScheduleLinksExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryStringBuilder;
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
public class ScheduleLinksDaoImpl extends BaseDaoImpl<ScheduleLinks> implements ScheduleLinksDao
{
    @Override
    public int insert(ScheduleLinks userInfoModel, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) throws SQLException
    {

        validateColumnNames(insertColumnNameList);
        validateColumnNames(keyHolderColumnNameList);

        String queryTemplate = QueryStringBuilder.generateInsertString(ScheduleLinks.TABLE_NAME, insertColumnNameList);
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
    public List<ScheduleLinks> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException
    {
        ScheduleLinksExtractor extractor = new ScheduleLinksExtractor();
        String queryTemplate = QueryStringBuilder.generateSelectString(ScheduleLinks.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

        List<Object> objectList = new ArrayList<Object>();

        for(QueryTerm queryTerm : queryTermList)
        {
            objectList.add(queryTerm.getValue());
        }

        Object[] parameters = objectList.toArray();

        List<ScheduleLinks> usersList = jdbcTemplate.query(queryTemplate, extractor, parameters);

        /*
         *
         */
        return usersList;
    }

    @Override
    public ScheduleLinks findById(int id) throws SQLException
    {
        String columnName = QueryStringBuilder.convertColumnName(ScheduleLinks.getColumnName(ScheduleLinks.Columns.ID), false);
        List<String> selectColumnNames = ScheduleLinks.getColumnNameList();

        List<QueryTerm> queryTermList = new ArrayList<>();
        QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
        queryTermList.add(idTerm);

        List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
        Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
        orderByList.add(order);

        List<ScheduleLinks> usersList = select(selectColumnNames, queryTermList, orderByList);

        ScheduleLinks userInfo = null;

        if(!usersList.isEmpty())
        {
            userInfo = usersList.get(0);
        }

        return userInfo;
    }

    @Override
    public int update(String columnName, Object newValue, List<QueryTerm> queryTermList)
    {
        String queryTemplate = QueryStringBuilder.generateUpdateString(ScheduleLinks.TABLE_NAME, columnName, queryTermList);

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
        String queryTemplate = QueryStringBuilder.generateDeleteString(ScheduleLinks.TABLE_NAME, queryTermList);

        List<Object> objectList = new ArrayList<Object>();

        for(QueryTerm queryTerm : queryTermList)
        {
            objectList.add(queryTerm.getValue());
        }

        Object[] parameters = objectList.toArray();

        int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);

        return rowsAffected;
    }

    private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, ScheduleLinks userInfoModel)
    {
        String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);

        // Wish this could generalize
        // The getter must be distinguished unless the models are designed as simply a map of columns-values
        // Would prefer not being that generic since it may end up leading to all code being collections of strings

        if(insertColumnName.equals(ScheduleLinks.getColumnName(ScheduleLinks.Columns.ID)))
        {
            parameters.addValue(parameterName, userInfoModel.getId());
        }
        else if(insertColumnName.equals(ScheduleLinks.getColumnName(ScheduleLinks.Columns.INSTRUCTOR_INFO_ID)))
        {
            parameters.addValue(parameterName, userInfoModel.getInstructorInfoId());
        }
        else if(insertColumnName.equals(ScheduleLinks.getColumnName(ScheduleLinks.Columns.SECTION_INFO_ID)))
        {
            parameters.addValue(parameterName, userInfoModel.getSectionInfoId());
        }

        else
        {
            // should never end up here
            // lists should have already been validated
            throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
        }
    }

    private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, ScheduleLinks userInfoModel)
    {
        if(keyHolderColumnName.equals(ScheduleLinks.getColumnName(ScheduleLinks.Columns.ID)))
        {
            userInfoModel.setId((Integer) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(ScheduleLinks.getColumnName(ScheduleLinks.Columns.INSTRUCTOR_INFO_ID)))
        {
            userInfoModel.setInstructorInfoId((Integer) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(ScheduleLinks.getColumnName(ScheduleLinks.Columns.SECTION_INFO_ID)))
        {
            userInfoModel.setSectionInfoId((Integer) keyMap.get(keyHolderColumnName));
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
        List<String> actualColumnNames = ScheduleLinks.getColumnNameList();
        boolean valid = actualColumnNames.containsAll(columnNameList);

        if(!valid)
        {
            List<String> invalidColumnNames = new ArrayList<>(columnNameList);
            invalidColumnNames.removeAll(actualColumnNames);

            throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
        }
    }
}