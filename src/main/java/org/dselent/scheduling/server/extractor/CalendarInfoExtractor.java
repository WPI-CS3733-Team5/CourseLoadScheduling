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

        return null;
    }
}

