package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.Lab_InfoDao;
import org.dselent.scheduling.server.dao.Section_PopulationDao;
import org.dselent.scheduling.server.dao.UsersRolesLinksDao;
import org.dselent.scheduling.server.dao.Wishlist_LinksDao;
import org.dselent.scheduling.server.extractor.LabInfoExtractor;
import org.dselent.scheduling.server.extractor.SectionPopulationExtractor;
import org.dselent.scheduling.server.extractor.UsersRolesLinksExtractor;
import org.dselent.scheduling.server.extractor.WishlistLinksExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.miscellaneous.QueryStringBuilder;
import org.dselent.scheduling.server.model.*;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;


@Repository
public class Wishlist_LinksDaoImpl extends BaseDaoImpl<Wishlist_Links> implements Wishlist_LinksDao
{

    @Override
    public int insert(Wishlist_Links wishlistLinksModel, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) throws SQLException
    {
        validateColumnNames(insertColumnNameList);
        validateColumnNames(keyHolderColumnNameList);

        String queryTemplate = QueryStringBuilder.generateInsertString(Wishlist_Links.TABLE_NAME, insertColumnNameList);
        MapSqlParameterSource parameters = new MapSqlParameterSource();

        List<Map<String, Object>> keyList = new ArrayList<>();
        KeyHolder keyHolder = new GeneratedKeyHolder(keyList);

        for(String insertColumnName : insertColumnNameList)
        {
            addParameterMapValue(parameters, insertColumnName, wishlistLinksModel);
        }

        int rowsAffected = namedParameterJdbcTemplate.update(queryTemplate, parameters, keyHolder);

        Map<String, Object> keyMap = keyHolder.getKeys();

        for(String keyHolderColumnName : keyHolderColumnNameList)
        {
            addObjectValue(keyMap, keyHolderColumnName, wishlistLinksModel);
        }

        return rowsAffected;

    }

    @Override
    public List<Wishlist_Links> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException
    {
        WishlistLinksExtractor extractor = new WishlistLinksExtractor();
        String queryTemplate = QueryStringBuilder.generateSelectString(Wishlist_Links.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

        List<Object> objectList = new ArrayList<Object>();

        for(QueryTerm queryTerm : queryTermList)
        {
            objectList.add(queryTerm.getValue());
        }

        Object[] parameters = objectList.toArray();

        List<Wishlist_Links> wishlistLinksList = jdbcTemplate.query(queryTemplate, extractor, parameters);

        return wishlistLinksList;

    }

    @Override
    public Wishlist_Links findById(int id) throws SQLException
    {
        String columnName = QueryStringBuilder.convertColumnName(Wishlist_Links.getColumnName(Wishlist_Links.Columns.ID), false);
        List<String> selectColumnNames = Wishlist_Links.getColumnNameList();

        List<QueryTerm> queryTermList = new ArrayList<>();
        QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
        queryTermList.add(idTerm);

        List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
        Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
        orderByList.add(order);

        List<Wishlist_Links> wishlistLinksList = select(selectColumnNames, queryTermList, orderByList);

        Wishlist_Links wishlistLinks = null;

        if(!wishlistLinksList.isEmpty())
        {
            wishlistLinks = wishlistLinksList.get(0);

        }

        return wishlistLinks;
    }

    @Override
    public int update(String columnName, Object newValue, List<QueryTerm> queryTermList)
    {

        String queryTemplate = QueryStringBuilder.generateUpdateString(Wishlist_Links.TABLE_NAME, columnName, queryTermList);

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

        String queryTemplate = QueryStringBuilder.generateDeleteString(Wishlist_Links.TABLE_NAME, queryTermList);

        List<Object> objectList = new ArrayList<Object>();

        for(QueryTerm queryTerm : queryTermList)
        {
            objectList.add(queryTerm.getValue());
        }

        Object[] parameters = objectList.toArray();

        int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);

        return rowsAffected;

    }

    private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, Wishlist_Links wishlistLinksModel)
    {

        String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);

        if(insertColumnName.equals(Wishlist_Links.getColumnName(Wishlist_Links.Columns.ID)))
        {
            parameters.addValue(parameterName, wishlistLinksModel.getId());
        }

        else if(insertColumnName.equals(Wishlist_Links.getColumnName(Wishlist_Links.Columns.INSTRUCTOR_INFO_ID)))
        {
            parameters.addValue(parameterName, wishlistLinksModel.getInstructorInfoId());
        }

        else if(insertColumnName.equals(Wishlist_Links.getColumnName(Wishlist_Links.Columns.SECTION_INFO_ID)))
        {
            parameters.addValue(parameterName, wishlistLinksModel.getSectionInfoId());
        }

        else
        {
            throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
        }

    }

    private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, Wishlist_Links wishlistLinksModel)
    {
        if(keyHolderColumnName.equals(Wishlist_Links.getColumnName(Wishlist_Links.Columns.ID)))
        {
            wishlistLinksModel.setId((Integer) keyMap.get(keyHolderColumnName));
        }

        else if(keyHolderColumnName.equals(Wishlist_Links.getColumnName(Wishlist_Links.Columns.INSTRUCTOR_INFO_ID)))
        {
            wishlistLinksModel.setInstructorInfoId((Integer) keyMap.get(keyHolderColumnName));
        }

        else if(keyHolderColumnName.equals(Wishlist_Links.getColumnName(Wishlist_Links.Columns.SECTION_INFO_ID)))
        {
            wishlistLinksModel.setSectionInfoId((Integer) keyMap.get(keyHolderColumnName));
        }

        else
        {
            throw new IllegalArgumentException("Invalid column name provided: " + keyHolderColumnName);
        }

    }

    @Override
    public void validateColumnNames(List<String> columnNameList)
    {
        List<String> actualColumnNames = Wishlist_Links.getColumnNameList();
        boolean valid = actualColumnNames.containsAll(columnNameList);

        if(!valid)
        {
            List<String> invalidColumnNames = new ArrayList<>(columnNameList);
            invalidColumnNames.removeAll(actualColumnNames);

            throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);

        }

    }

}
