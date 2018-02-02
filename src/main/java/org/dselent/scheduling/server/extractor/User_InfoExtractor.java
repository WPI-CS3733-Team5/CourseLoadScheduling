package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.Instant;
import java.sql.Timestamp;


import org.dselent.scheduling.server.model.User_Info;

public class User_InfoExtractor extends Extractor<List<User_Info>>
{
    @Override
    public List<User_Info> extractData(ResultSet rs) throws SQLException
    {
        List<User_Info> resultList = new ArrayList<>();

        while(rs.next())
        {
            User_Info result = new User_Info();

            result.setId(rs.getInt(User_Info.getColumnName(User_Info.Columns.ID)));

            if(rs.wasNull())
            {
                result.setId(null);
            }

            result.setUserRole(rs.getInt(User_Info.getColumnName(User_Info.Columns.USER_ROLE)));
            result.setUserName(rs.getString(User_Info.getColumnName(User_Info.Columns.USERNAME)));
            result.setFirstName(rs.getString(User_Info.getColumnName(User_Info.Columns.FIRST_NAME)));
            result.setLastName(rs.getString(User_Info.getColumnName(User_Info.Columns.LAST_NAME)));
            result.setEmail(rs.getString(User_Info.getColumnName(User_Info.Columns.EMAIL)));
            result.setDeleted(rs.getBoolean(User_Info.getColumnName(User_Info.Columns.DELETED)));
            result.setEncryptedPassword(rs.getString(User_Info.getColumnName(User_Info.Columns.ENCRYPTED_PASSWORD)));
            result.setSalt(rs.getString(User_Info.getColumnName(User_Info.Columns.SALT)));
            result.setAccountState(rs.getInt(User_Info.getColumnName(User_Info.Columns.ACCOUNT_STATE)));

            if(rs.wasNull())
            {
                result.setAccountState(null);
            }

            result.setCreatedAt(rs.getTimestamp(User_Info.getColumnName(User_Info.Columns.CREATED_AT)));
            result.setUpdatedAt(rs.getTimestamp(User_Info.getColumnName(User_Info.Columns.UPDATED_AT)));
            result.setLoginTime(rs.getTimestamp(User_Info.getColumnName(User_Info.Columns.LOGIN_TIME)));

            resultList.add(result);
        }

        return resultList;
    }

}
