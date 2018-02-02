package org.dselent.scheduling.server.extractor;

import org.dselent.scheduling.server.model.User_Info_History;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class User_Info_HistoryExtractor extends Extractor<List<User_Info_History>>
{
    @Override
    public List<User_Info_History> extractData(ResultSet rs) throws SQLException
    {
        List<User_Info_History> resultList = new ArrayList<>();

        while(rs.next())
        {
            User_Info_History result = new User_Info_History();

            result.setId(rs.getInt(User_Info_History.getColumnName(User_Info_History.Columns.ID)));

            if(rs.wasNull())
            {
                result.setId(null);
            }
            result.setUserInfoId(rs.getInt(User_Info_History.getColumnName(User_Info_History.Columns.USER_INFO_ID)));
            result.setUserRole(rs.getInt(User_Info_History.getColumnName(User_Info_History.Columns.USER_ROLE)));
            result.setUserName(rs.getString(User_Info_History.getColumnName(User_Info_History.Columns.USERNAME)));
            result.setFirstName(rs.getString(User_Info_History.getColumnName(User_Info_History.Columns.FIRST_NAME)));
            result.setLastName(rs.getString(User_Info_History.getColumnName(User_Info_History.Columns.LAST_NAME)));
            result.setEmail(rs.getString(User_Info_History.getColumnName(User_Info_History.Columns.EMAIL)));
            result.setEncryptedPassword(rs.getString(User_Info_History.getColumnName(User_Info_History.Columns.ENCRYPTED_PASSWORD)));
            result.setSalt(rs.getString(User_Info_History.getColumnName(User_Info_History.Columns.SALT)));
            result.setAccountState(rs.getInt(User_Info_History.getColumnName(User_Info_History.Columns.ACCOUNT_STATE)));

            if(rs.wasNull())
            {
                result.setAccountState(null);
            }

            result.setCreatedAt(rs.getTimestamp(User_Info_History.getColumnName(User_Info_History.Columns.CREATED_AT)));
            result.setUpdatedAt(rs.getTimestamp(User_Info_History.getColumnName(User_Info_History.Columns.UPDATED_AT)));
            result.setLoginTime(rs.getTimestamp(User_Info_History.getColumnName(User_Info_History.Columns.LOGIN_TIME)));

            resultList.add(result);
        }

        return resultList;
    }

}
