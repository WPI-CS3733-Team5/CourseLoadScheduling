package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.Calendar_Info;
import org.dselent.scheduling.server.model.LabInfo;
import org.dselent.scheduling.server.model.User_Info;
import org.dselent.scheduling.server.model.UsersRolesLink;

public class CalendarInfoExtractor extends Extractor<List<Calendar_Info>>{

    @Override
    public List<Calendar_Info> extractData(ResultSet rs) throws SQLException {
        List<Calendar_Info> resultList = new ArrayList<>();
        while (rs.next()){
            Calendar_Info result = new Calendar_Info();

            //Set ID
            result.setId(rs.getInt(Calendar_Info.getColumnName(Calendar_Info.Columns.ID)));
            if (rs.wasNull()) result.setId(null);

            //Set Year
            result.setYear(rs.getInt(Calendar_Info.getColumnName(Calendar_Info.Columns.YEAR)));
            if (rs.wasNull()) result.setYear(null);

            //Set Semester
            result.setSemester(rs.getInt(Calendar_Info.getColumnName(Calendar_Info.Columns.SEMESTER)));
            if (rs.wasNull()) result.setSemester(null);

            //Set Credit Amount
            result.setCreditAmount(rs.getInt(Calendar_Info.getColumnName(Calendar_Info.Columns.CREDIT_AMOUNT)));
            if (rs.wasNull()) result.setCreditAmount(null);

            //Set Days
            result.setDays(rs.getString(Calendar_Info.getColumnName(Calendar_Info.Columns.DAYS)));
            if (rs.wasNull()) result.setDays(null);

            //Set StartTime
            result.setStartTime(rs.getInt(Calendar_Info.getColumnName(Calendar_Info.Columns.START_TIME)));
            if (rs.wasNull()) result.setStartTime(null);

            //Set EndTime
            result.setEndTime(rs.getInt(Calendar_Info.getColumnName(Calendar_Info.Columns.END_TIME)));
            if (rs.wasNull()) result.setEndTime(null);

            //add final result to list
            resultList.add(result);
        }
        return resultList;
    }
}

