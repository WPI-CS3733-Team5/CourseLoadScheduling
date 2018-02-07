package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.dselent.scheduling.server.model.UserInfo;

public class UserInfoExtractor extends Extractor<List<UserInfo>>
{
    @Override
    public List<UserInfo> extractData(ResultSet rs) throws SQLException
    {
        List<UserInfo> resultList = new ArrayList<>();

        while(rs.next())
        {
            UserInfo result = new UserInfo();

            result.setId(rs.getInt(UserInfo.getColumnName(UserInfo.Columns.ID)));

            if(rs.wasNull())
            {
                result.setId(null);
            }

            result.setUserRole(rs.getInt(UserInfo.getColumnName(UserInfo.Columns.USER_ROLE)));
            result.setUserName(rs.getString(UserInfo.getColumnName(UserInfo.Columns.USERNAME)));
            result.setFirstName(rs.getString(UserInfo.getColumnName(UserInfo.Columns.FIRST_NAME)));
            result.setLastName(rs.getString(UserInfo.getColumnName(UserInfo.Columns.LAST_NAME)));
            result.setEmail(rs.getString(UserInfo.getColumnName(UserInfo.Columns.EMAIL)));
            result.setDeleted(rs.getBoolean(UserInfo.getColumnName(UserInfo.Columns.DELETED)));
            result.setEncryptedPassword(rs.getString(UserInfo.getColumnName(UserInfo.Columns.ENCRYPTED_PASSWORD)));
            result.setSalt(rs.getString(UserInfo.getColumnName(UserInfo.Columns.SALT)));
            result.setAccountState(rs.getInt(UserInfo.getColumnName(UserInfo.Columns.ACCOUNT_STATE)));

            if(rs.wasNull())
            {
                result.setAccountState(null);
            }

            result.setCreatedAt(rs.getTimestamp(UserInfo.getColumnName(UserInfo.Columns.CREATED_AT)));
            result.setUpdatedAt(rs.getTimestamp(UserInfo.getColumnName(UserInfo.Columns.UPDATED_AT)));
            result.setLoginTime(rs.getTimestamp(UserInfo.getColumnName(UserInfo.Columns.LOGIN_TIME)));

            resultList.add(result);
        }

        return resultList;
    }

}
