package org.dselent.scheduling.server.dao;

import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.Notification;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by dhmchorney on 2/1/2018.
 */

@Repository
public interface NotificationDao extends Dao<Notification>{

    List<Notification> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException;
    int insert(Notification notificationModel, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) throws SQLException;
    int update(String columnName, Object newValue, List<QueryTerm> queryTermList);
    int delete(List<QueryTerm> queryTermList);
    void validateColumnNames(List<String> columnNameList);
}
