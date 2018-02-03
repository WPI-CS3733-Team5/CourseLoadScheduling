package org.dselent.scheduling.server.extractor;

import org.dselent.scheduling.server.model.UserInfoHistory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserInfoHistoryExtractor extends Extractor<List<UserInfoHistory>>
{
    @Override
    public List<UserInfoHistory> extractData(ResultSet rs) throws SQLException
    {
        List<UserInfoHistory> resultList = new ArrayList<>();

        while(rs.next())
        {
            UserInfoHistory result = new UserInfoHistory();

            result.setId(rs.getInt(UserInfoHistory.getColumnName(UserInfoHistory.Columns.ID)));

            if(rs.wasNull())
            {
                result.setId(null);
            }
            result.setUserInfoId(rs.getInt(UserInfoHistory.getColumnName(UserInfoHistory.Columns.USER_INFO_ID)));
            result.setUserRole(rs.getInt(UserInfoHistory.getColumnName(UserInfoHistory.Columns.USER_ROLE)));
            result.setUserName(rs.getString(UserInfoHistory.getColumnName(UserInfoHistory.Columns.USERNAME)));
            result.setFirstName(rs.getString(UserInfoHistory.getColumnName(UserInfoHistory.Columns.FIRST_NAME)));
            result.setLastName(rs.getString(UserInfoHistory.getColumnName(UserInfoHistory.Columns.LAST_NAME)));
            result.setEmail(rs.getString(UserInfoHistory.getColumnName(UserInfoHistory.Columns.EMAIL)));
            result.setEncryptedPassword(rs.getString(UserInfoHistory.getColumnName(UserInfoHistory.Columns.ENCRYPTED_PASSWORD)));
            result.setSalt(rs.getString(UserInfoHistory.getColumnName(UserInfoHistory.Columns.SALT)));
            result.setAccountState(rs.getInt(UserInfoHistory.getColumnName(UserInfoHistory.Columns.ACCOUNT_STATE)));

            if(rs.wasNull())
            {
                result.setAccountState(null);
            }

            result.setCreatedAt(rs.getTimestamp(UserInfoHistory.getColumnName(UserInfoHistory.Columns.CREATED_AT)));
            result.setCreatedAt(rs.getTimestamp(UserInfoHistory.getColumnName(UserInfoHistory.Columns.UPDATED_AT)));
            result.setUpdatedAt(rs.getTimestamp(UserInfoHistory.getColumnName(UserInfoHistory.Columns.UPDATED_AT)));
            result.setLoginTime(rs.getTimestamp(UserInfoHistory.getColumnName(UserInfoHistory.Columns.LOGIN_TIME)));

            resultList.add(result);
        }

        return resultList;
    }

}
