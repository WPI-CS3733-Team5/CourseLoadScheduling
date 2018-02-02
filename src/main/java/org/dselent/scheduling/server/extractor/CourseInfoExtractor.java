package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.Course_Info;

public class CourseInfoExtractor extends Extractor<List<Course_Info>>{

    @Override
    public List<Course_Info> extractData(ResultSet rs) throws SQLException{
        List<Course_Info> resultList = new ArrayList<>();

        while(rs.next()){
            Course_Info result = new Course_Info();

            //Set Id
            result.setId(rs.getInt(Course_Info.getColumnName(Course_Info.Columns.ID)));
            if (rs.wasNull()) result.setId(null);

            //Set courseName
            result.setCourseName(rs.getString(Course_Info.getColumnName(Course_Info.Columns.COURSE_NAME)));
            if (rs.wasNull()) result.setCourseName(null);

            //Set requiredFrequencyPerTerm
            result.setRequireFrequencyPerTerm(rs.getInt(Course_Info.getColumnName(Course_Info.Columns.REQUIRED_FREQUENCY_PER_TERM)));
            if (rs.wasNull()) result.setRequireFrequencyPerTerm(null);

            //Set requiredFrequencyPerSemester
            result.setRequiredFrequencyPerSemester(rs.getInt(Course_Info.getColumnName(Course_Info.Columns.REQUIRED_FREQUENCY_PER_SEMESTER)));
            if (rs.wasNull()) result.setRequiredFrequencyPerSemester(null);

            //Set requiredFrequencyPerYear
            result.setRequiredFrequencyPerYear(rs.getInt(Course_Info.getColumnName(Course_Info.Columns.REQUIRED_FREQUENCY_PER_YEAR)));
            if (rs.wasNull()) result.setRequiredFrequencyPerYear(null);

            //Set creditAmount
            result.setCreditAmount(rs.getInt(Course_Info.getColumnName(Course_Info.Columns.CREDIT_AMOUNT)));
            if (rs.wasNull()) result.setCreditAmount(null);

            //Set deleted
            result.setDeleted(rs.getBoolean(Course_Info.getColumnName(Course_Info.Columns.DELETED)));
            if (rs.wasNull()) result.setDeleted(null);

            //Set department
            result.setDepartment(rs.getInt(Course_Info.getColumnName(Course_Info.Columns.DEPARTMENT)));
            if (rs.wasNull()) result.setDepartment(null);

            //Set courseNumber
            result.setCourseNumber(rs.getInt(Course_Info.getColumnName(Course_Info.Columns.COURSE_NUMBER)));
            if (rs.wasNull()) result.setCourseNumber(null);

            //Add result to list
            resultList.add(result);
        }

        return resultList;
    }
}
