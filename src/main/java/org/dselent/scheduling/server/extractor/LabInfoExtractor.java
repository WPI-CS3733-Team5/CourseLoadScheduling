package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.Calendar_Info;
import org.dselent.scheduling.server.model.LabInfo;
import org.dselent.scheduling.server.model.User_Info;
import org.dselent.scheduling.server.model.UsersRolesLink;

public class LabInfoExtractor extends Extractor<List<LabInfo>>
{

    @Override
    public List<LabInfo> extractData(ResultSet rs) throws SQLException
    {
        List<LabInfo> resultList = new ArrayList<>();

        while(rs.next())
        {

            LabInfo result = new LabInfo();

            result.setId(rs.getInt(LabInfo.getColumnName(LabInfo.Columns.ID)));

            if(rs.wasNull())
            {
                result.setId(null);
            }

            result.setSectionInfoId(rs.getInt(LabInfo.getColumnName(LabInfo.Columns.SECTION_INFO_ID)));

            if(rs.wasNull())
            {
                result.setSectionInfoId(null);
            }

            result.setInstructorInfoId(rs.getInt(LabInfo.getColumnName(LabInfo.Columns.INSTRUCTOR_INFO_ID)));

            if(rs.wasNull())
            {
                result.setInstructorInfoId(null);
            }

            result.setLocation(rs.getString(LabInfo.getColumnName(LabInfo.Columns.LOCATION)));

            if(rs.wasNull())
            {
                result.setLocation(null);
            }

            result.setCalendarInfoId(rs.getInt(LabInfo.getColumnName(LabInfo.Columns.CALENDAR_INFO_ID)));

            if(rs.wasNull())
            {
                result.setCalendarInfoId(null);
            }

            resultList.add(result);

        }

        return resultList;

    }
}
