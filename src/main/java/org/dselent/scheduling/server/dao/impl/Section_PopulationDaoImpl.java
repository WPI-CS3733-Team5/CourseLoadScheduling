package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.Lab_InfoDao;
import org.dselent.scheduling.server.dao.Section_PopulationDao;
import org.dselent.scheduling.server.dao.UsersRolesLinksDao;
import org.dselent.scheduling.server.extractor.LabInfoExtractor;
import org.dselent.scheduling.server.extractor.SectionPopulationExtractor;
import org.dselent.scheduling.server.extractor.UsersRolesLinksExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.miscellaneous.QueryStringBuilder;
import org.dselent.scheduling.server.model.Lab_Info;
import org.dselent.scheduling.server.model.Section_Population;
import org.dselent.scheduling.server.model.User_Info;
import org.dselent.scheduling.server.model.UsersRolesLink;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;


@Repository
public class Section_PopulationDaoImpl extends BaseDaoImpl<Section_Population> implements Section_PopulationDao
{
    @Override
    public int insert(Section_Population sectionPopulationModel, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) throws SQLException
    {
        validateColumnNames(insertColumnNameList);
        validateColumnNames(keyHolderColumnNameList);

        String queryTemplate = QueryStringBuilder.generateInsertString(Section_Population.TABLE_NAME, insertColumnNameList);
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
    public List<Section_Population> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException
    {
        SectionPopulationExtractor extractor = new SectionPopulationExtractor();
        String queryTemplate = QueryStringBuilder.generateSelectString(Section_Population.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

        List<Object> objectList = new ArrayList<Object>();

        for(QueryTerm queryTerm : queryTermList)
        {
            objectList.add(queryTerm.getValue());
        }

        Object[] parameters = objectList.toArray();

        List<Section_Population> sectionPopulationList = jdbcTemplate.query(queryTemplate, extractor, parameters);

        return sectionPopulationList;

    }


    @Override
    public Section_Population findById(int id) throws SQLException
    {

        String columnName = QueryStringBuilder.convertColumnName(Section_Population.getColumnName(Section_Population.Columns.ID), false);
        List<String> selectColumnNames = Section_Population.getColumnNameList();

        List<QueryTerm> queryTermList = new ArrayList<>();
        QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
        queryTermList.add(idTerm);

        List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
        Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
        orderByList.add(order);

        List<Section_Population> sectionPopulationList = select(selectColumnNames, queryTermList, orderByList);

        Section_Population sectionPopulation = null;

        if(!sectionPopulationList.isEmpty())
        {
            sectionPopulation = sectionPopulationList.get(0);

        }

        return sectionPopulation;

    }

    @Override
    public int update(String columnName, Object newValue, List<QueryTerm> queryTermList)
    {

        String queryTemplate = QueryStringBuilder.generateUpdateString(Section_Population.TABLE_NAME, columnName, queryTermList);

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
        String queryTemplate = QueryStringBuilder.generateDeleteString(Section_Population.TABLE_NAME, queryTermList);

        List<Object> objectList = new ArrayList<Object>();

        for(QueryTerm queryTerm : queryTermList)
        {
            objectList.add(queryTerm.getValue());
        }

        Object[] parameters = objectList.toArray();

        int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);

        return rowsAffected;

    }

    private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, Section_Population sectionPopulationModel)
    {

        String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);

        if(insertColumnName.equals(Section_Population.getColumnName(Section_Population.Columns.ID)))
        {
            parameters.addValue(parameterName, sectionPopulationModel.getId());
        }

        else if(insertColumnName.equals(Section_Population.getColumnName(Section_Population.Columns.EXPECTED_POPULATION)))
        {
            parameters.addValue(parameterName, sectionPopulationModel.getExpectedPopulation());
        }

        else if(insertColumnName.equals(Section_Population.getColumnName(Section_Population.Columns.POPULATION_CAP)))
        {
            parameters.addValue(parameterName, sectionPopulationModel.getPopulationCap());
        }

        else if(insertColumnName.equals(Section_Population.getColumnName(Section_Population.Columns.SECTION_INFO_ID)))
        {
            parameters.addValue(parameterName, sectionPopulationModel.getSectionInfoId());
        }

        else
        {
            throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
        }

    }

    private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, Section_Population sectionPopulationModel)
    {

        if(keyHolderColumnName.equals(Section_Population.getColumnName(Section_Population.Columns.ID)))
        {
            sectionPopulationModel.setId((Integer) keyMap.get(keyHolderColumnName));
        }

        else if(keyHolderColumnName.equals(Section_Population.getColumnName(Section_Population.Columns.EXPECTED_POPULATION)))
        {
            sectionPopulationModel.setExpectedPopulation((Integer) keyMap.get(keyHolderColumnName));
        }

        else if(keyHolderColumnName.equals(Section_Population.getColumnName(Section_Population.Columns.POPULATION_CAP)))
        {
            sectionPopulationModel.setPopulationCap((Integer) keyMap.get(keyHolderColumnName));
        }

        else if(keyHolderColumnName.equals(Section_Population.getColumnName(Section_Population.Columns.SECTION_INFO_ID)))
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
        List<String> actualColumnNames = Section_Population.getColumnNameList();
        boolean valid = actualColumnNames.containsAll(columnNameList);

        if(!valid)
        {
            List<String> invalidColumnNames = new ArrayList<>(columnNameList);
            invalidColumnNames.removeAll(actualColumnNames);

            throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);

        }
    }
}
