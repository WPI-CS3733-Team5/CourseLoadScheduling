package org.dselent.scheduling.server.extractor;

import org.dselent.scheduling.server.model.InstructorInfoHistory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InstructorInfoHistoryExtractor extends Extractor<List<InstructorInfoHistory>>
{
    @Override
    public List<InstructorInfoHistory> extractData(ResultSet rs) throws SQLException
    {
        List<InstructorInfoHistory> resultList = new ArrayList<>();

        while(rs.next())
        {
            InstructorInfoHistory result = new InstructorInfoHistory();

            result.setId(rs.getInt(InstructorInfoHistory.getColumnName(InstructorInfoHistory.Columns.ID)));

            if(rs.wasNull())
            {
                result.setId(null);
            }

            result.setInstructorInfoId(rs.getInt(InstructorInfoHistory.getColumnName(InstructorInfoHistory.Columns.INSTRUCTOR_INFO_ID)));
            result.setRank(rs.getString(InstructorInfoHistory.getColumnName(InstructorInfoHistory.Columns.RANK)));
            result.setCourseLoad(rs.getInt(InstructorInfoHistory.getColumnName(InstructorInfoHistory.Columns.COURSE_LOAD)));
            result.setPhoneNumber(rs.getString(InstructorInfoHistory.getColumnName(InstructorInfoHistory.Columns.PHONE_NUMBER)));
            result.setOffice(rs.getString(InstructorInfoHistory.getColumnName(InstructorInfoHistory.Columns.OFFICE)));
            result.setUserInfoId(rs.getInt(InstructorInfoHistory.getColumnName(InstructorInfoHistory.Columns.USER_INFO_ID)));
            result.setDepartment(rs.getString(InstructorInfoHistory.getColumnName(InstructorInfoHistory.Columns.DEPARTMENT)));
        }

        return resultList;
    }

}
