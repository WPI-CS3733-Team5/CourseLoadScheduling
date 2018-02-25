package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.ScheduleLinks;

public class ScheduleLinksExtractor extends Extractor<List<ScheduleLinks>>
{
    public List<ScheduleLinks> extractData(ResultSet rs) throws SQLException
    {
        List<ScheduleLinks> resultList = new ArrayList<>();

        while(rs.next())
        {
            ScheduleLinks result = new ScheduleLinks();

            //int
            result.setId(rs.getInt(ScheduleLinks.getColumnName(ScheduleLinks.Columns.ID)));

            if(rs.wasNull())
            {
                result.setId(null);
            }

            //int
            result.setInstructorInfoId(rs.getInt(ScheduleLinks.getColumnName(ScheduleLinks.Columns.INSTRUCTOR_INFO_ID)));

            if(rs.wasNull())
            {
                result.setInstructorInfoId(rs.getInt(ScheduleLinks.getColumnName(ScheduleLinks.Columns.INSTRUCTOR_INFO_ID)));
            }


            //int
            result.setSectionInfoId(rs.getInt(ScheduleLinks.getColumnName(ScheduleLinks.Columns.SECTION_INFO_ID)));

            if(rs.wasNull())
            {
                result.setSectionInfoId(null);
            }


            resultList.add(result);
            //create the rest of these. follow examples above
        }

        return resultList;

    }

}