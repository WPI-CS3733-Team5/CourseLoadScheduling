package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.Calendar_Info;
import org.dselent.scheduling.server.model.Lab_Info;
import org.dselent.scheduling.server.model.User_Info;
import org.dselent.scheduling.server.model.UsersRolesLink;

public class LabInfoExtractor extends Extractor<List<Lab_Info>>
{

    @Override
    public List<Lab_Info> extractData(ResultSet rs) throws SQLException
    {
        List<Lab_Info> resultList = new ArrayList<>();

        while(rs.next())
        {

            Lab_Info result = new Lab_Info();

            result.setId(rs.getInt(Lab_Info.getColumnName(Lab_Info.Columns.ID)));

            if(rs.wasNull())
            {
                result.setId(null);
            }

            result.setSectionInfoId(rs.getInt(Lab_Info.getColumnName(Lab_Info.Columns.SECTION_INFO_ID)));

            if(rs.wasNull())
            {
                result.setSectionInfoId(null);
            }

            result.setInstructorInfoId(rs.getInt(Lab_Info.getColumnName(Lab_Info.Columns.INSTRUCTOR_INFO_ID)));

            if(rs.wasNull())
            {
                result.setInstructorInfoId(null);
            }

            result.setLocation(rs.getString(Lab_Info.getColumnName(Lab_Info.Columns.LOCATION)));

            if(rs.wasNull())
            {
                result.setLocation(null);
            }

            result.setCalendarInfoId(rs.getInt(Lab_Info.getColumnName(Lab_Info.Columns.CALENDAR_INFO_ID)));

            if(rs.wasNull())
            {
                result.setCalendarInfoId(null);
            }

            // Foreign keys like this?
            //EX:? result.setCalendarInfoId(rs.getInt(Calendar_Info.getColumnName(Calendar_Info.Columns.ID)));

            resultList.add(result);

        }

        return resultList;

    }
}
