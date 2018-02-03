package org.dselent.scheduling.server.dao.impl;

import org.dselent.scheduling.server.dao.NotificationDao;
import org.dselent.scheduling.server.extractor.NotificationExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.miscellaneous.QueryStringBuilder;
import org.dselent.scheduling.server.model.InstructorInfo;
import org.dselent.scheduling.server.model.Notification;
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

/**
 * Created by dhmchorney on 2/2/2018.
 */
public class NotificationDaoImpl extends BaseDaoImpl<Notification> implements NotificationDao{

    @Override
    public int insert(Notification notificationModel, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) throws SQLException{

        validateColumnNames(insertColumnNameList);
        validateColumnNames(keyHolderColumnNameList);

        String queryTemplate = QueryStringBuilder.generateInsertString(InstructorInfo.TABLE_NAME, insertColumnNameList);
        MapSqlParameterSource parameters = new MapSqlParameterSource();

        List<Map<String, Object>> keyList = new ArrayList<>();
        KeyHolder keyHolder = new GeneratedKeyHolder(keyList);

        for(String insertColumnName : insertColumnNameList)
        {
            addParameterMapValue(parameters, insertColumnName, notificationModel);
        }
        // new way, but unfortunately unnecessary class creation is slow and wasteful (i.e. wrong)
        // insertColumnNames.forEach(insertColumnName -> addParameterMap(parameters, insertColumnName, InstructorInfoModel));

        int rowsAffected = namedParameterJdbcTemplate.update(queryTemplate, parameters, keyHolder);

        Map<String, Object> keyMap = keyHolder.getKeys();

        for(String keyHolderColumnName : keyHolderColumnNameList)
        {
            addObjectValue(keyMap, keyHolderColumnName, notificationModel);
        }

        return rowsAffected;
    }

    @Override
    public List<Notification> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException
    {
        NotificationExtractor extractor = new NotificationExtractor();
        String queryTemplate = QueryStringBuilder.generateSelectString(Notification.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

        List<Object> objectList = new ArrayList<Object>();

        for(QueryTerm queryTerm : queryTermList)
        {
            objectList.add(queryTerm.getValue());
        }

        Object[] parameters = objectList.toArray();

        List<Notification> notificationList = jdbcTemplate.query(queryTemplate, extractor, parameters);

        return notificationList;
    }

    @Override
    public Notification findById(int id) throws SQLException
    {
        String columnName = QueryStringBuilder.convertColumnName(Notification.getColumnName(Notification.Columns.ID), false);
        List<String> selectColumnNames = InstructorInfo.getColumnNameList();

        List<QueryTerm> queryTermList = new ArrayList<>();
        QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
        queryTermList.add(idTerm);

        List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
        Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
        orderByList.add(order);

        List<Notification> notificationList = select(selectColumnNames, queryTermList, orderByList);

        Notification notificationObj = null;

        if(!notificationList.isEmpty())
        {
            notificationObj = notificationList.get(0);
        }

        return notificationObj;
    }

    @Override
    public int update(String columnName, Object newValue, List<QueryTerm> queryTermList)
    {
        String queryTemplate = QueryStringBuilder.generateUpdateString(Notification.TABLE_NAME, columnName, queryTermList);

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
        String queryTemplate = QueryStringBuilder.generateDeleteString(Notification.TABLE_NAME, queryTermList);

        List<Object> objectList = new ArrayList<Object>();

        for(QueryTerm queryTerm : queryTermList)
        {
            objectList.add(queryTerm.getValue());
        }

        Object[] parameters = objectList.toArray();

        int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);

        return rowsAffected;
    }

    @Override
    public void validateColumnNames(List<String> columnNameList)
    {
        List<String> actualColumnNames = Notification.getColumnNameList();
        boolean valid = actualColumnNames.containsAll(columnNameList);

        if(!valid)
        {
            List<String> invalidColumnNames = new ArrayList<>(columnNameList);
            invalidColumnNames.removeAll(actualColumnNames);

            throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
        }
    }

    private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, Notification notificationModel)
    {
        String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);

        if(insertColumnName.equals(Notification.getColumnName(Notification.Columns.ID)))
        {
            parameters.addValue(parameterName, notificationModel.getId());
        }
        else if(insertColumnName.equals(Notification.getColumnName(Notification.Columns.MESSAGE)))
        {
            parameters.addValue(parameterName, notificationModel.getMessage());
        }
        else if(insertColumnName.equals(Notification.getColumnName(Notification.Columns.FROM_USER_INFO_ID)))
        {
            parameters.addValue(parameterName, notificationModel.getFromUserInfoId());
        }
        else if(insertColumnName.equals(Notification.getColumnName(Notification.Columns.TO_USER_INFO_ID)))
        {
            parameters.addValue(parameterName, notificationModel.getToUserInfoId());
        }
        else{
            // should never end up here
            // lists should have already been validated
            throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
        }
    }

    private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, Notification notificationModel)
    {
        if(keyHolderColumnName.equals(Notification.getColumnName(Notification.Columns.ID)))
        {
            notificationModel.setId((Integer) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(Notification.getColumnName(Notification.Columns.MESSAGE)))
        {
            notificationModel.setMessage((String) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(Notification.getColumnName(Notification.Columns.FROM_USER_INFO_ID)))
        {
            notificationModel.setFromUserInfoId((Integer) keyMap.get(keyHolderColumnName));
        }
        else if(keyHolderColumnName.equals(Notification.getColumnName(Notification.Columns.TO_USER_INFO_ID)))
        {
            notificationModel.setToUserInfoId((Integer) keyMap.get(keyHolderColumnName));
        }
        else
        {
            // should never end up here
            // lists should have already been validated
            throw new IllegalArgumentException("Invalid column name provided: " + keyHolderColumnName);
        }
    }

}
