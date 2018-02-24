package org.dselent.scheduling.server.extractor;

import org.dselent.scheduling.server.model.InstructorInfo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InstructorInfoExtractor extends Extractor<List<InstructorInfo>> {

    @Override
    public List<InstructorInfo> extractData(ResultSet rs) throws SQLException {

        List<InstructorInfo> resultList = new ArrayList<>();

        while(rs.next())
        {
            InstructorInfo result = new InstructorInfo();

            result.setId(rs.getInt(InstructorInfo.getColumnName(InstructorInfo.Columns.ID)));

            if(rs.wasNull())
            {
                result.setId(null);
            }

            result.setRank(Integer.parseInt(rs.getString(InstructorInfo.getColumnName(InstructorInfo.Columns.RANK))));
            result.setCourseLoad(Integer.parseInt(rs.getString(InstructorInfo.getColumnName(InstructorInfo.Columns.COURSE_LOAD))));
            //result.setPhoneNumber(rs.getString(InstructorInfo.getColumnName(InstructorInfo.Columns.PHONE_NUMBER)));
            result.setOffice(rs.getString(InstructorInfo.getColumnName(InstructorInfo.Columns.OFFICE)));
            result.setUserInfoId(Integer.parseInt(rs.getString(InstructorInfo.getColumnName(InstructorInfo.Columns.USER_INFO_ID))));
            result.setDepartment(rs.getString(InstructorInfo.getColumnName(InstructorInfo.Columns.DEPARTMENT)));

            resultList.add(result);
        }

        return resultList;

    }
}
