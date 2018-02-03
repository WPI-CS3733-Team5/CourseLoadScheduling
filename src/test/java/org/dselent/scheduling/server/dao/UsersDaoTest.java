package org.dselent.scheduling.server.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.config.AppConfig;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.UserInfo;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
public class UsersDaoTest
{
	@Autowired
	private UserInfoDao userinfoDao;
	
	/*
	 * Not really an using this as a JUnit test
	 * More of an example on how to use the classes
	 */
	/*
    @Test
    public void testUsersDao() throws SQLException
    {
    	// INSERT
    	
    	UserInfo userInfo1 = new UserInfo();
		userInfo1.setId(22222222);
		userInfo1.setUserRole(3);
    	userInfo1.setUserName("userInfo1");
    	userInfo1.setFirstName("user");
    	userInfo1.setLastName("one");
    	userInfo1.setEmail("userone@wpi.edu");
		userInfo1.setDeleted(false);
    	userInfo1.setEncryptedPassword("11111111"); // simplified for now
    	userInfo1.setSalt("11111111"); // also simplified for now
		userInfo1.setAccountState(1);

    	List<String> insertColumnNameList = new ArrayList<>();
    	List<String> keyHolderColumnNameList = new ArrayList<>();

		insertColumnNameList.add(UserInfo.getColumnName(UserInfo.Columns.ID));
		insertColumnNameList.add(UserInfo.getColumnName(UserInfo.Columns.USER_ROLE));
		insertColumnNameList.add(UserInfo.getColumnName(UserInfo.Columns.USERNAME));
    	insertColumnNameList.add(UserInfo.getColumnName(UserInfo.Columns.FIRST_NAME));
    	insertColumnNameList.add(UserInfo.getColumnName(UserInfo.Columns.LAST_NAME));
    	insertColumnNameList.add(UserInfo.getColumnName(UserInfo.Columns.EMAIL));
		insertColumnNameList.add(UserInfo.getColumnName(UserInfo.Columns.DELETED));
		insertColumnNameList.add(UserInfo.getColumnName(UserInfo.Columns.ENCRYPTED_PASSWORD));
    	insertColumnNameList.add(UserInfo.getColumnName(UserInfo.Columns.SALT));
		insertColumnNameList.add(UserInfo.getColumnName(UserInfo.Columns.ACCOUNT_STATE));
		insertColumnNameList.add(UserInfo.getColumnName(UserInfo.Columns.CREATED_AT));
		insertColumnNameList.add(UserInfo.getColumnName(UserInfo.Columns.UPDATED_AT));
		insertColumnNameList.add(UserInfo.getColumnName(UserInfo.Columns.LOGIN_TIME));

		keyHolderColumnNameList.add(UserInfo.getColumnName(UserInfo.Columns.ID));
    	keyHolderColumnNameList.add(UserInfo.getColumnName(UserInfo.Columns.CREATED_AT));
    	keyHolderColumnNameList.add(UserInfo.getColumnName(UserInfo.Columns.UPDATED_AT));
   	
    	userinfoDao.insert(userInfo1, insertColumnNameList, keyHolderColumnNameList);
    	
    	
    	// UPDATE
    	
    	String updateColumnName = UserInfo.getColumnName(UserInfo.Columns.USERNAME);
    	String oldUserName = "userInfo1";
    	String newUserName = "newUserName";
    	List<QueryTerm> updateQueryTermList = new ArrayList<>();
    	
    	QueryTerm updateUseNameTerm = new QueryTerm();
    	updateUseNameTerm.setColumnName(updateColumnName);
    	updateUseNameTerm.setComparisonOperator(ComparisonOperator.EQUAL);
    	updateUseNameTerm.setValue(oldUserName);
    	updateQueryTermList.add(updateUseNameTerm);
    	
    	userinfoDao.update(updateColumnName, newUserName, updateQueryTermList);
    	
    	
    	// SELECT
    	// by user name
    	
    	String selectColumnName = UserInfo.getColumnName(UserInfo.Columns.USERNAME);
    	String selectUserName = newUserName;
    	
    	List<QueryTerm> selectQueryTermList = new ArrayList<>();
    	
    	QueryTerm selectUseNameTerm = new QueryTerm();
    	selectUseNameTerm.setColumnName(selectColumnName);
    	selectUseNameTerm.setComparisonOperator(ComparisonOperator.EQUAL);
    	selectUseNameTerm.setValue(selectUserName);
    	selectQueryTermList.add(selectUseNameTerm);
    	
    	List<String> selectColumnNameList = UserInfo.getColumnNameList();
    	
    	List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
    	Pair<String, ColumnOrder> orderPair1 = new Pair<String, ColumnOrder>(selectColumnName, ColumnOrder.ASC);
    	orderByList.add(orderPair1);
    	
		@SuppressWarnings("unused")
		List<UserInfo> selectedUserInfoList = userinfoDao.select(selectColumnNameList, selectQueryTermList, orderByList);
    	
    	System.out.println();
    } */
}
