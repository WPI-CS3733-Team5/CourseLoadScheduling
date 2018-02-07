package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.CalendarInfo;

public class CalendarInfoExtractor extends Extractor<List<CalendarInfo>>{

    @Override
    public List<CalendarInfo> extractData(ResultSet rs) throws SQLException {
        List<CalendarInfo> resultList = new ArrayList<>();
        while (rs.next()){
            CalendarInfo result = new CalendarInfo();

            //Set ID
            result.setId(rs.getInt(CalendarInfo.getColumnName(CalendarInfo.Columns.ID)));
            if (rs.wasNull()) result.setId(null);

            //Set Year
            result.setYear(rs.getInt(CalendarInfo.getColumnName(CalendarInfo.Columns.YEAR)));
            if (rs.wasNull()) result.setYear(null);

            //Set Semester
            result.setSemester(rs.getInt(CalendarInfo.getColumnName(CalendarInfo.Columns.SEMESTER)));
            if (rs.wasNull()) result.setSemester(null);

            //Set Credit Amount
            result.setCreditAmount(rs.getInt(CalendarInfo.getColumnName(CalendarInfo.Columns.CREDIT_AMOUNT)));
            if (rs.wasNull()) result.setCreditAmount(null);

            //Set Days
            result.setDays(rs.getString(CalendarInfo.getColumnName(CalendarInfo.Columns.DAYS)));
            if (rs.wasNull()) result.setDays(null);

            //Set StartTime
            result.setStartTime(rs.getInt(CalendarInfo.getColumnName(CalendarInfo.Columns.START_TIME)));
            if (rs.wasNull()) result.setStartTime(null);

            //Set EndTime
            result.setEndTime(rs.getInt(CalendarInfo.getColumnName(CalendarInfo.Columns.END_TIME)));
            if (rs.wasNull()) result.setEndTime(null);

            //add final result to list
            resultList.add(result);
        }
        return resultList;
    }
}

