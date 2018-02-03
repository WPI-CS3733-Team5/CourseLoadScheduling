package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.SectionPopulationDao;
import org.dselent.scheduling.server.extractor.SectionPopulationExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.miscellaneous.QueryStringBuilder;
import org.dselent.scheduling.server.model.SectionPopulation;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;


@Repository
public class SectionPopulationDaoImpl extends BaseDaoImpl<SectionPopulation> implements SectionPopulationDao
{
    @Override
    public int insert(SectionPopulation sectionPopulationModel, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) throws SQLException
    {
        validateColumnNames(insertColumnNameList);
        validateColumnNames(keyHolderColumnNameList);

        String queryTemplate = QueryStringBuilder.generateInsertString(SectionPopulation.TABLE_NAME, insertColumnNameList);
        MapSqlParameterSource parameters = new MapSqlParameterSource();

        List<Map<String, Object>> keyList = new ArrayList<>();
        KeyHolder keyHolder = new GeneratedKeyHolder(keyList);

        for(String insertColumnName : insertColumnNameList)
        {
            addParameterMapValue(parameters, insertColumnName, sectionPopulationModel);
        }

        int rowsAffected = namedParameterJdbcTemplate.update(queryTemplate, parameters, keyHolder);

        Map<String, Object> keyMap = keyHolder.getKeys();

        for(String keyHolderColumnName : keyHolderColumnNameList)
        {
            addObjectValue(keyMap, keyHolderColumnName, sectionPopulationModel);
        }

        return rowsAffected;

    }

    @Override
    public List<SectionPopulation> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException
    {
        SectionPopulationExtractor extractor = new SectionPopulationExtractor();
        String queryTemplate = QueryStringBuilder.generateSelectString(SectionPopulation.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

        List<Object> objectList = new ArrayList<Object>();

        for(QueryTerm queryTerm : queryTermList)
        {
            objectList.add(queryTerm.getValue());
        }

        Object[] parameters = objectList.toArray();

        List<SectionPopulation> sectionPopulationList = jdbcTemplate.query(queryTemplate, extractor, parameters);

        return sectionPopulationList;

    }


    @Override
    public SectionPopulation findById(int id) throws SQLException
    {

        String columnName = QueryStringBuilder.convertColumnName(SectionPopulation.getColumnName(SectionPopulation.Columns.ID), false);
        List<String> selectColumnNames = SectionPopulation.getColumnNameList();

        List<QueryTerm> queryTermList = new ArrayList<>();
        QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
        queryTermList.add(idTerm);

        List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
        Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
        orderByList.add(order);

        List<SectionPopulation> sectionPopulationList = select(selectColumnNames, queryTermList, orderByList);

        SectionPopulation sectionPopulation = null;

        if(!sectionPopulationList.isEmpty())
        {
            sectionPopulation = sectionPopulationList.get(0);

        }

        return sectionPopulation;

    }

    @Override
    public int update(String columnName, Object newValue, List<QueryTerm> queryTermList)
    {

        String queryTemplate = QueryStringBuilder.generateUpdateString(SectionPopulation.TABLE_NAME, columnName, queryTermList);

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
        String queryTemplate = QueryStringBuilder.generateDeleteString(SectionPopulation.TABLE_NAME, queryTermList);

        List<Object> objectList = new ArrayList<Object>();

        for(QueryTerm queryTerm : queryTermList)
        {
            objectList.add(queryTerm.getValue());
        }

        Object[] parameters = objectList.toArray();

        int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);

        return rowsAffected;

    }

    private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, SectionPopulation sectionPopulationModel)
    {

        String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);

        if(insertColumnName.equals(SectionPopulation.getColumnName(SectionPopulation.Columns.ID)))
        {
            parameters.addValue(parameterName, sectionPopulationModel.getId());
        }

        else if(insertColumnName.equals(SectionPopulation.getColumnName(SectionPopulation.Columns.EXPECTED_POPULATION)))
        {
            parameters.addValue(parameterName, sectionPopulationModel.getExpectedPopulation());
        }

        else if(insertColumnName.equals(SectionPopulation.getColumnName(SectionPopulation.Columns.POPULATION_CAP)))
        {
            parameters.addValue(parameterName, sectionPopulationModel.getPopulationCap());
        }

        else if(insertColumnName.equals(SectionPopulation.getColumnName(SectionPopulation.Columns.SECTION_INFO_ID)))
        {
            parameters.addValue(parameterName, sectionPopulationModel.getSectionInfoId());
        }

        else
        {
            throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
        }

    }

    private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, SectionPopulation sectionPopulationModel)
    {

        if(keyHolderColumnName.equals(SectionPopulation.getColumnName(SectionPopulation.Columns.ID)))
        {
            sectionPopulationModel.setId((Integer) keyMap.get(keyHolderColumnName));
        }

        else if(keyHolderColumnName.equals(SectionPopulation.getColumnName(SectionPopulation.Columns.EXPECTED_POPULATION)))
        {
            sectionPopulationModel.setExpectedPopulation((Integer) keyMap.get(keyHolderColumnName));
        }

        else if(keyHolderColumnName.equals(SectionPopulation.getColumnName(SectionPopulation.Columns.POPULATION_CAP)))
        {
            sectionPopulationModel.setPopulationCap((Integer) keyMap.get(keyHolderColumnName));
        }

        else if(keyHolderColumnName.equals(SectionPopulation.getColumnName(SectionPopulation.Columns.SECTION_INFO_ID)))
        {
            sectionPopulationModel.setSectionInfoId((Integer) keyMap.get(keyHolderColumnName));
        }

        else
        {
            throw new IllegalArgumentException("Invalid column name provided: " + keyHolderColumnName);
        }

    }

    @Override
    public void validateColumnNames(List<String> columnNameList)
    {
        List<String> actualColumnNames = SectionPopulation.getColumnNameList();
        boolean valid = actualColumnNames.containsAll(columnNameList);

        if(!valid)
        {
            List<String> invalidColumnNames = new ArrayList<>(columnNameList);
            invalidColumnNames.removeAll(actualColumnNames);

            throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);

        }
    }
}
