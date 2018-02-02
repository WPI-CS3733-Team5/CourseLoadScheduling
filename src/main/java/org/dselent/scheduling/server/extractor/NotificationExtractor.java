package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.Notification;
/**
 * Created by dhmchorney on 2/2/2018.
 */
public class NotificationExtractor extends Extractor<List<Notification>> {

    @Override
    public List<Notification> extractData(ResultSet rs) throws SQLException{
        List<Notification> resultList = new ArrayList<>();

        while(rs.next()){
            Notification result = new Notification();

            result.setId(rs.getInt(Notification.getColumnName(Notification.Columns.ID)));

            if(rs.wasNull()){
                result.setId(null);
            }

            result.setMessage(rs.getString(Notification.getColumnName(Notification.Columns.MESSAGE)));

            if(rs.wasNull()){
                result.setMessage(null);
            }

            result.setFromUserInfoId(rs.getInt(Notification.getColumnName(Notification.Columns.FROM_USER_INFO_ID)));

            if(rs.wasNull()){
                result.setFromUserInfoId(null);
            }

            result.setToUserInfoId(rs.getInt(Notification.getColumnName(Notification.Columns.TO_USER_INFO_ID)));

            if(rs.wasNull()){
                result.setToUserInfoId(null);
            }

            resultList.add(result);
        }

        return resultList;
    }
}
