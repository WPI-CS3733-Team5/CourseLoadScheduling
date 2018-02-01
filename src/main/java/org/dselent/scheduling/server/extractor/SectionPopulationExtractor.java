package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.*;


public class SectionPopulationExtractor extends Extractor<List<Section_Population>>
{

    @Override
    public List<Section_Population> extractData(ResultSet rs) throws SQLException
    {
        List<Section_Population> resultList = new ArrayList<>();

        while (rs.next())
        {
            Section_Population result = new Section_Population();

            result.setId(rs.getInt(Section_Population.getColumnName(Section_Population.Columns.ID)));

            if(rs.wasNull())
            {
                result.setId(null);
            }

            result.setExpectedPopulation(rs.getInt(Section_Population.getColumnName(Section_Population.Columns.EXPECTED_POPULATION)));

            if(rs.wasNull())
            {
                result.setExpectedPopulation(null);
            }

            result.setPopulationCap(rs.getInt(Section_Population.getColumnName(Section_Population.Columns.POPULATION_CAP)));

            if(rs.wasNull())
            {
                result.setPopulationCap(null);
            }

            result.setSectionInfoId(rs.getInt(Section_Population.getColumnName(Section_Population.Columns.SECTION_INFO_ID)));

            if(rs.wasNull())
            {
                result.setSectionInfoId(null);
            }

            resultList.add(result);
        }

        return  resultList;
    }

}
