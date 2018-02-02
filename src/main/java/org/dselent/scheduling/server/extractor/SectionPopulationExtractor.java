package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.*;


public class SectionPopulationExtractor extends Extractor<List<SectionPopulation>>
{

    @Override
    public List<SectionPopulation> extractData(ResultSet rs) throws SQLException
    {
        List<SectionPopulation> resultList = new ArrayList<>();

        while (rs.next())
        {
            SectionPopulation result = new SectionPopulation();

            result.setId(rs.getInt(SectionPopulation.getColumnName(SectionPopulation.Columns.ID)));

            if(rs.wasNull())
            {
                result.setId(null);
            }

            result.setExpectedPopulation(rs.getInt(SectionPopulation.getColumnName(SectionPopulation.Columns.EXPECTED_POPULATION)));

            if(rs.wasNull())
            {
                result.setExpectedPopulation(null);
            }

            result.setPopulationCap(rs.getInt(SectionPopulation.getColumnName(SectionPopulation.Columns.POPULATION_CAP)));

            if(rs.wasNull())
            {
                result.setPopulationCap(null);
            }

            result.setSectionInfoId(rs.getInt(SectionPopulation.getColumnName(SectionPopulation.Columns.SECTION_INFO_ID)));

            if(rs.wasNull())
            {
                result.setSectionInfoId(null);
            }

            resultList.add(result);
        }

        return  resultList;
    }

}
