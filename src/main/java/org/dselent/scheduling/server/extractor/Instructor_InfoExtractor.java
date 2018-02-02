package org.dselent.scheduling.server.extractor;

import org.dselent.scheduling.server.model.Instructor_Info;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Instructor_InfoExtractor extends Extractor<List<Instructor_Info>> {

    @Override
    public List<Instructor_Info> extractData(ResultSet rs) throws SQLException {

        List<Instructor_Info> resultList = new ArrayList<>();

        while(rs.next())
        {
            Instructor_Info result = new Instructor_Info();

            result.setId(rs.getInt(Instructor_Info.getColumnName(Instructor_Info.Columns.ID)));

            if(rs.wasNull())
            {
                result.setId(null);
            }

            result.setRank(rs.getString(Instructor_Info.getColumnName(Instructor_Info.Columns.RANK)));
            result.setCourseLoad(Integer.parseInt(rs.getString(Instructor_Info.getColumnName(Instructor_Info.Columns.COURSE_LOAD))));
            result.setPhoneNumber(rs.getString(Instructor_Info.getColumnName(Instructor_Info.Columns.PHONE_NUMBER)));
            result.setOffice(rs.getString(Instructor_Info.getColumnName(Instructor_Info.Columns.OFFICE)));
            result.setUserInfoId(Integer.parseInt(rs.getString(Instructor_Info.getColumnName(Instructor_Info.Columns.USER_INFO_ID))));
            result.setDepartment(rs.getString(Instructor_Info.getColumnName(Instructor_Info.Columns.DEPARTMENT)));

            resultList.add(result);
        }

        return resultList;

    }
}
