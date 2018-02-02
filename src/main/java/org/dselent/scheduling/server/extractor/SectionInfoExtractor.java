package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import org.dselent.scheduling.server.model.User_Info;
//import org.dselent.scheduling.server.model.UsersRolesLink;
import org.dselent.scheduling.server.model.SectionInfo;

public class SectionInfoExtractor extends Extractor<List<SectionInfo>>
{
    public List<SectionInfo> extractData(ResultSet rs) throws SQLException
    {
        List<SectionInfo> resultList = new ArrayList<>();

        while(rs.next())
        {
            SectionInfo result = new SectionInfo();

            //int
            result.setId(rs.getInt(SectionInfo.getColumnName(SectionInfo.Columns.ID)));

            if(rs.wasNull())
            {
                result.setId(null);
            }

            //int
            result.setSectionNumber(rs.getInt(SectionInfo.getColumnName(SectionInfo.Columns.SECTIONNUMBER)));

            if(rs.wasNull())
            {
                result.setSectionNumber(rs.getInt(SectionInfo.getColumnName(SectionInfo.Columns.SECTIONNUMBER)));
            }

            //varchar
            result.setSectionType(rs.getString(SectionInfo.getColumnName(SectionInfo.Columns.SECTIONTYPE)));

            //int
            result.setInstructorInfoId(rs.getInt(SectionInfo.getColumnName(SectionInfo.Columns.INSTRUCTORINFOID)));

            if(rs.wasNull())
            {
                result.setInstructorInfoId(null);
            }

            //varchar
            result.setLocation(rs.getString(SectionInfo.getColumnName(SectionInfo.Columns.LOCATION)));

            //booolean
            result.setDeleted(rs.getBoolean(SectionInfo.getColumnName(SectionInfo.Columns.DELETED)));

            if(rs.wasNull());
            {
                result.setDeleted(null);
            }

            //int
            result.setCourseInfoId(rs.getInt(SectionInfo.getColumnName(SectionInfo.Columns.COURSEINFOID)));

            if(rs.wasNull())
            {
                result.setCourseInfoId(null);
            }

            //int
            result.setCalendarInfoId(rs.getInt(SectionInfo.getColumnName(SectionInfo.Columns.CALENDARINFOID)));

            resultList.add(result);
            //create the rest of these. follow examples above
        }

        return resultList;

    }

}