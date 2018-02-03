package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
//import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import org.dselent.scheduling.server.dao.User_infoDao;
import org.dselent.scheduling.server.dao.SectionInfoDao;
//import org.dselent.scheduling.server.extractor.UsersExtractor;
import org.dselent.scheduling.server.extractor.SectionInfoExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.SectionInfo;
//import org.dselent.scheduling.server.model.sectionInfo;
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
public class SectionInfoDaoImpl extends BaseDaoImpl<SectionInfo> implements SectionInfoDao
{
    @Override
    public int insert(SectionInfo userInfoModel, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) throws SQLException
    {

        validateColumnNames(insertColumnNameList);
        validateColumnNames(keyHolderColumnNameList);

        String queryTemplate = QueryStringBuilder.generateInsertString(SectionInfo.TABLE_NAME, insertColumnNameList);
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
    public List<SectionInfo> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException
    {
        SectionInfoExtractor extractor = new SectionInfoExtractor();
        String queryTemplate = QueryStringBuilder.generateSelectString(SectionInfo.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

        List<Object> objectList = new ArrayList<Object>();

        for(QueryTerm queryTerm : queryTermList)
        {
            objectList.add(queryTerm.getValue());
        }

        Object[] parameters = objectList.toArray();

        List<SectionInfo> usersList = jdbcTemplate.query(queryTemplate, extractor, parameters);

        /*
         *
         */
        return usersList;
    }

    @Override
    public SectionInfo findById(int id) throws SQLException
    {
        String columnName = QueryStringBuilder.convertColumnName(SectionInfo.getColumnName(SectionInfo.Columns.ID), false);
        List<String> selectColumnNames = SectionInfo.getColumnNameList();

        List<QueryTerm> queryTermList = new ArrayList<>();
        QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
        queryTermList.add(idTerm);

        List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
        Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
        orderByList.add(order);

        List<SectionInfo> usersList = select(selectColumnNames, queryTermList, orderByList);

        SectionInfo userInfo = null;

        if(!usersList.isEmpty())
        {
            userInfo = usersList.get(0);
        }

        return userInfo;
    }

    @Override
    public int update(String columnName, Object newValue, List<QueryTerm> queryTermList)
    {
        String queryTemplate = QueryStringBuilder.generateUpdateString(SectionInfo.TABLE_NAME, columnName, queryTermList);

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
        String queryTemplate = QueryStringBuilder.generateDeleteString(SectionInfo.TABLE_NAME, queryTermList);

        List<Object> objectList = new ArrayList<Object>();

        for(QueryTerm queryTerm : queryTermList)
        {
            objectList.add(queryTerm.getValue());
        }

        Object[] parameters = objectList.toArray();

        int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);

        return rowsAffected;
    }

    private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, SectionInfo userInfoModel)
    {
        String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);

        // Wish this could generalize
        // The getter must be distinguished unless the models are designed as simply a map of columns-values
        // Would prefer not being that generic since it may end up leading to all code being collections of strings

        if(insertColumnName.equals(SectionInfo.getColumnName(SectionInfo.Columns.ID)))
        {
            parameters.addValue(parameterName, userInfoModel.getId());
        }
        else if(insertColumnName.equals(SectionInfo.getColumnName(SectionInfo.Columns.SECTIONNUMBER)))
        {
            parameters.addValue(parameterName, userInfoModel.getSectionNumber());
        }
        else if(insertColumnName.equals(SectionInfo.getColumnName(SectionInfo.Columns.SECTIONTYPE)))
        {
            parameters.addValue(parameterName, userInfoModel.getSectionType());
        }
        else if(insertColumnName.equals(SectionInfo.getColumnName(SectionInfo.Columns.INSTRUCTORINFOID)))
        {
            parameters.addValue(parameterName, userInfoModel.getInstructorInfoId());
        }
        else if(insertColumnName.equals(SectionInfo.getColumnName(SectionInfo.Columns.LOCATION)))
        {
            parameters.addValue(parameterName, userInfoModel.getLocation());
        }
        else if(insertColumnName.equals(SectionInfo.getColumnName(SectionInfo.Columns.DELETED)))
        {
            parameters.addValue(parameterName, userInfoModel.getDeleted());
        }
        else if(insertColumnName.equals(SectionInfo.getColumnName(SectionInfo.Columns.COURSEINFOID)))
        {
            parameters.addValue(parameterName, userInfoModel.getCourseInfoId());
        }
        else if(insertColumnName.equals(SectionInfo.getColumnName(SectionInfo.Columns.CALENDARINFOID)))
        {
            parameters.addValue(parameterName, userInfoModel.getCalendarInfoId());
        }
        else
        {
            // should never end up here
            // lists should have already been validated
            throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
        }
    }

    private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, SectionInfo userInfoModel)
    {
        if(keyHolderColumnName.equals(SectionInfo.getColumnName(SectionInfo.Columns.ID)))
        {
            userInfoModel.setId((Integer) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(SectionInfo.getColumnName(SectionInfo.Columns.SECTIONNUMBER)))
        {
            userInfoModel.setSectionNumber((Integer) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(SectionInfo.getColumnName(SectionInfo.Columns.SECTIONTYPE)))
        {
            userInfoModel.setSectionType((String) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(SectionInfo.getColumnName(SectionInfo.Columns.INSTRUCTORINFOID)))
        {
            userInfoModel.setInstructorInfoId((Integer) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(SectionInfo.getColumnName(SectionInfo.Columns.LOCATION)))
        {
            userInfoModel.setLocation((String) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(SectionInfo.getColumnName(SectionInfo.Columns.DELETED)))
        {
            userInfoModel.setDeleted((Boolean) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(SectionInfo.getColumnName(SectionInfo.Columns.COURSEINFOID)))
        {
            userInfoModel.setCourseInfoId((Integer) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(SectionInfo.getColumnName(SectionInfo.Columns.CALENDARINFOID)))
        {
            userInfoModel.setCalendarInfoId((Integer) keyMap.get(keyHolderColumnName));
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
        List<String> actualColumnNames = SectionInfo.getColumnNameList();
        boolean valid = actualColumnNames.containsAll(columnNameList);

        if(!valid)
        {
            List<String> invalidColumnNames = new ArrayList<>(columnNameList);
            invalidColumnNames.removeAll(actualColumnNames);

            throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
        }
    }
}
