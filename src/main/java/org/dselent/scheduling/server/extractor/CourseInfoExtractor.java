package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.CourseInfo;

public class CourseInfoExtractor extends Extractor<List<CourseInfo>>{

    @Override
    public List<CourseInfo> extractData(ResultSet rs) throws SQLException{
        List<CourseInfo> resultList = new ArrayList<>();

        while(rs.next()){
            CourseInfo result = new CourseInfo();

            //Set Id
            result.setId(rs.getInt(CourseInfo.getColumnName(CourseInfo.Columns.ID)));
            if (rs.wasNull()) result.setId(null);

            //Set courseName
            result.setCourseName(rs.getString(CourseInfo.getColumnName(CourseInfo.Columns.COURSE_NAME)));
            if (rs.wasNull()) result.setCourseName(null);

            //Set requiredFrequencyPerTerm
            result.setRequiredFrequencyPerTerm(rs.getInt(CourseInfo.getColumnName(CourseInfo.Columns.REQUIRED_FREQUENCY_PER_TERM)));
            if (rs.wasNull()) result.setRequiredFrequencyPerTerm(null);

            //Set requiredFrequencyPerSemester
            result.setRequiredFrequencyPerSemester(rs.getInt(CourseInfo.getColumnName(CourseInfo.Columns.REQUIRED_FREQUENCY_PER_SEMESTER)));
            if (rs.wasNull()) result.setRequiredFrequencyPerSemester(null);

            //Set requiredFrequencyPerYear
            result.setRequiredFrequencyPerYear(rs.getInt(CourseInfo.getColumnName(CourseInfo.Columns.REQUIRED_FREQUENCY_PER_YEAR)));
            if (rs.wasNull()) result.setRequiredFrequencyPerYear(null);

            //Set creditAmount
            result.setCreditAmount(rs.getInt(CourseInfo.getColumnName(CourseInfo.Columns.CREDIT_AMOUNT)));
            if (rs.wasNull()) result.setCreditAmount(null);

            //Set deleted
            result.setDeleted(rs.getBoolean(CourseInfo.getColumnName(CourseInfo.Columns.DELETED)));
            if (rs.wasNull()) result.setDeleted(null);

            //Set department
            result.setDepartment(rs.getString(CourseInfo.getColumnName(CourseInfo.Columns.DEPARTMENT)));
            if (rs.wasNull()) result.setDepartment(null);

            //Set courseNumber
            result.setCourseNumber(rs.getInt(CourseInfo.getColumnName(CourseInfo.Columns.COURSE_NUMBER)));
            if (rs.wasNull()) result.setCourseNumber(null);

            //Add result to list
            resultList.add(result);
        }

        return resultList;
    }
}
