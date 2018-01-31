package org.dselent.scheduling.server.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.config.AppConfig;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.User_Info;
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
	private User_infoDao userinfoDao;
	
	/*
	 * Not really an using this as a JUnit test
	 * More of an example on how to use the classes
	 */
    @Test
    public void testUsersDao() throws SQLException
    {
    	// INSERT
    	
    	User_Info userInfo1 = new User_Info();
    	userInfo1.setUserName("userInfo1");
    	userInfo1.setFirstName("user");
    	userInfo1.setLastName("one");
    	userInfo1.setEmail("userone@wpi.edu");
    	userInfo1.setEncryptedPassword("11111111"); // simplified for now
    	userInfo1.setSalt("11111111"); // also simplified for now
    	userInfo1.setUserStateId(1); // assumes 1 = activated
    	
    	List<String> insertColumnNameList = new ArrayList<>();
    	List<String> keyHolderColumnNameList = new ArrayList<>();
    	
    	insertColumnNameList.add(User_Info.getColumnName(User_Info.Columns.USER_NAME));
    	insertColumnNameList.add(User_Info.getColumnName(User_Info.Columns.FIRST_NAME));
    	insertColumnNameList.add(User_Info.getColumnName(User_Info.Columns.LAST_NAME));
    	insertColumnNameList.add(User_Info.getColumnName(User_Info.Columns.EMAIL));
    	insertColumnNameList.add(User_Info.getColumnName(User_Info.Columns.ENCRYPTED_PASSWORD));
    	insertColumnNameList.add(User_Info.getColumnName(User_Info.Columns.SALT));
    	insertColumnNameList.add(User_Info.getColumnName(User_Info.Columns.USER_STATE_ID));
    	
    	keyHolderColumnNameList.add(User_Info.getColumnName(User_Info.Columns.ID));
    	keyHolderColumnNameList.add(User_Info.getColumnName(User_Info.Columns.CREATED_AT));
    	keyHolderColumnNameList.add(User_Info.getColumnName(User_Info.Columns.UPDATED_AT));
   	
    	userinfoDao.insert(userInfo1, insertColumnNameList, keyHolderColumnNameList);
    	
    	
    	// UPDATE
    	
    	String updateColumnName = User_Info.getColumnName(User_Info.Columns.USER_NAME);
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
    	
    	String selectColumnName = User_Info.getColumnName(User_Info.Columns.USER_NAME);
    	String selectUserName = newUserName;
    	
    	List<QueryTerm> selectQueryTermList = new ArrayList<>();
    	
    	QueryTerm selectUseNameTerm = new QueryTerm();
    	selectUseNameTerm.setColumnName(selectColumnName);
    	selectUseNameTerm.setComparisonOperator(ComparisonOperator.EQUAL);
    	selectUseNameTerm.setValue(selectUserName);
    	selectQueryTermList.add(selectUseNameTerm);
    	
    	List<String> selectColumnNameList = User_Info.getColumnNameList();
    	
    	List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
    	Pair<String, ColumnOrder> orderPair1 = new Pair<String, ColumnOrder>(selectColumnName, ColumnOrder.ASC);
    	orderByList.add(orderPair1);
    	
		@SuppressWarnings("unused")
		List<User_Info> selectedUserInfoList = userinfoDao.select(selectColumnNameList, selectQueryTermList, orderByList);
    	
    	System.out.println();
    }
}
