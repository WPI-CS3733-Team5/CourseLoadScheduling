package org.dselent.scheduling.server.dao.impl;

import org.dselent.scheduling.server.dao.Calendar_InfoDao;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.Calendar_Info;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.QueryTerm;

import java.sql.SQLException;
import java.util.List;

public class CalendarInfoDaoImpl implements Calendar_InfoDao {

    @Override
    public int insert(Calendar_Info model, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) {
        return 0;
    }

    @Override
    public List<Calendar_Info> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) {
        return null;
    }

    @Override
    public int update(String columnName, Object newValue, List<QueryTerm> queryTermList) {
        return 0;
    }

    @Override
    public int delete(List<QueryTerm> queryTermList) {
        return 0;
    }

    @Override
    public void validateColumnNames(List<String> columnNameList) {

    }
}
