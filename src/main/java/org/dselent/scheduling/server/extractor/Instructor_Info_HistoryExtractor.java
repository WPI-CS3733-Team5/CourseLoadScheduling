package org.dselent.scheduling.server.extractor;

import org.dselent.scheduling.server.model.Instructor_Info_History;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Instructor_Info_HistoryExtractor extends Extractor<List<Instructor_Info_History>>
{
    @Override
    public List<Instructor_Info_History> extractData(ResultSet rs) throws SQLException
    {
        List<Instructor_Info_History> resultList = new ArrayList<>();

        while(rs.next())
        {
            Instructor_Info_History result = new Instructor_Info_History();

            result.setId(rs.getInt(Instructor_Info_History.getColumnName(Instructor_Info_History.Columns.ID)));

            if(rs.wasNull())
            {
                result.setId(null);
            }

            result.setInstructorInfoId(rs.getInt(Instructor_Info_History.getColumnName(Instructor_Info_History.Columns.INSTRUCTOR_INFO_ID)));
            result.setRank(rs.getString(Instructor_Info_History.getColumnName(Instructor_Info_History.Columns.RANK)));
            result.setCourseLoad(rs.getInt(Instructor_Info_History.getColumnName(Instructor_Info_History.Columns.COURSE_LOAD)));
            result.setPhoneNuber(rs.getString(Instructor_Info_History.getColumnName(Instructor_Info_History.Columns.PHONE_NUMBER)));
            result.setOffice(rs.getString(Instructor_Info_History.getColumnName(Instructor_Info_History.Columns.OFFICE)));
            result.setUserInfoId(rs.getInt(Instructor_Info_History.getColumnName(Instructor_Info_History.Columns.USER_INFO_ID)));
            result.setDepartment(rs.getString(Instructor_Info_History.getColumnName(Instructor_Info_History.Columns.DEPARTMENT)));
        }

        return resultList;
    }

}
