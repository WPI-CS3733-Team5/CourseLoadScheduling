package org.dselent.scheduling.server.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.dao.InstructorInfoDao;
import org.dselent.scheduling.server.dao.UserInfoDao;
import org.dselent.scheduling.server.dto.CreateUserDto;
import org.dselent.scheduling.server.dto.GetAllUserDto;
import org.dselent.scheduling.server.dto.GetOneUserDto;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.InstructorInfo;
import org.dselent.scheduling.server.model.UserInfo;
import org.dselent.scheduling.server.service.UserService;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserInfoDao userInfoDao;
	
	@Autowired
	private InstructorInfoDao instructorInfoDao;
	
    public UserServiceImpl()
    {
    	//
    }
    
    /*
     * (non-Javadoc)
     * @see org.dselent.scheduling.server.service.UserService#registerUser(org.dselent.scheduling.server.dto.CreateUserDto)
     */
    @Transactional
    @Override
	public List<Integer> createUser(CreateUserDto dto) throws SQLException
	{
		List<Integer> rowsAffectedList = new ArrayList<>();

		String salt = KeyGenerators.string().generateKey();
		String saltedPassword = dto.getEncryptedPassword() + salt;
		PasswordEncoder passwordEncorder = new BCryptPasswordEncoder();
		String encryptedPassword = passwordEncorder.encode(saltedPassword);
		
		UserInfo userInfo = new UserInfo();
		userInfo.setUserRole(dto.getUserRole());
		userInfo.setUserName(dto.getUsername());
		userInfo.setFirstName(dto.getFirstName());
		userInfo.setLastName(dto.getLastName());
		userInfo.setEmail(dto.getEmail());
		userInfo.setDeleted(dto.getDeleted());
		userInfo.setEncryptedPassword(encryptedPassword);
		userInfo.setSalt(salt);
		userInfo.setAccountState(dto.getAccountState());

    	
    	List<String> userInsertColumnNameList = new ArrayList<>();
    	List<String> userKeyHolderColumnNameList = new ArrayList<>();
    	
    	userInsertColumnNameList.add(UserInfo.getColumnName(UserInfo.Columns.USER_ROLE));
    	userInsertColumnNameList.add(UserInfo.getColumnName(UserInfo.Columns.USERNAME));
    	userInsertColumnNameList.add(UserInfo.getColumnName(UserInfo.Columns.FIRST_NAME));
    	userInsertColumnNameList.add(UserInfo.getColumnName(UserInfo.Columns.LAST_NAME));
    	userInsertColumnNameList.add(UserInfo.getColumnName(UserInfo.Columns.EMAIL));
    	userInsertColumnNameList.add(UserInfo.getColumnName(UserInfo.Columns.ENCRYPTED_PASSWORD));
    	userInsertColumnNameList.add(UserInfo.getColumnName(UserInfo.Columns.SALT));
    	userInsertColumnNameList.add(UserInfo.getColumnName(UserInfo.Columns.ACCOUNT_STATE));

    	userKeyHolderColumnNameList.add(UserInfo.getColumnName(UserInfo.Columns.ID));
    	userKeyHolderColumnNameList.add(UserInfo.getColumnName(UserInfo.Columns.CREATED_AT));
    	userKeyHolderColumnNameList.add(UserInfo.getColumnName(UserInfo.Columns.UPDATED_AT));
		userKeyHolderColumnNameList.add(UserInfo.getColumnName(UserInfo.Columns.LOGIN_TIME));


		rowsAffectedList.add(userInfoDao.insert(userInfo, userInsertColumnNameList, userKeyHolderColumnNameList));

		if(!dto.getRank().equals(null)){
			InstructorInfo instructorInfo = new InstructorInfo();
			//Rank is incorrectly given as a string in DTO: This is a bandaid
			instructorInfo.setRank(Integer.parseInt(dto.getRank()));
			instructorInfo.setCourseLoad(dto.getCourseLoad());
			//instructorInfo.setPhoneNumber(dto.getPhoneNumber());
			instructorInfo.setOffice(dto.getOffice());
			instructorInfo.setDepartment(dto.getDepartment());
			instructorInfo.setUserInfoId(userInfo.getId());

			List<String> instructorInsertColumnNameList = new ArrayList<>();
			List<String> instructorKeyHolderColumnNameList = new ArrayList<>();

			instructorInsertColumnNameList.add(InstructorInfo.getColumnName(InstructorInfo.Columns.RANK));
			instructorInsertColumnNameList.add(InstructorInfo.getColumnName(InstructorInfo.Columns.COURSE_LOAD));
			//instructorInsertColumnNameList.add(InstructorInfo.getColumnName(InstructorInfo.Columns.PHONE_NUMBER));
			instructorInsertColumnNameList.add(InstructorInfo.getColumnName(InstructorInfo.Columns.OFFICE));
			instructorInsertColumnNameList.add(InstructorInfo.getColumnName(InstructorInfo.Columns.USER_INFO_ID));
			instructorInsertColumnNameList.add(InstructorInfo.getColumnName(InstructorInfo.Columns.DEPARTMENT));

			instructorKeyHolderColumnNameList.add(InstructorInfo.getColumnName(InstructorInfo.Columns.ID));

			rowsAffectedList.add(instructorInfoDao.insert(instructorInfo, instructorInsertColumnNameList, instructorKeyHolderColumnNameList));
			}

		return rowsAffectedList;
	}

	@Transactional
	@Override
	public List<Object> getOneUser(Integer requestedId) throws SQLException
	{

		UserInfo userInfo = userInfoDao.findById(requestedId);
		
		//Find the InstructorInfo with the matching userInfoId
		List<String> selectColumnNameList = new ArrayList<String>();
		selectColumnNameList.addAll(InstructorInfo.getColumnNameList());
		
		QueryTerm onlyTerm = new QueryTerm(InstructorInfo.getColumnName(InstructorInfo.Columns.USER_INFO_ID), ComparisonOperator.EQUAL, requestedId, null);
		List<QueryTerm> queryTermList = new ArrayList<QueryTerm>();
		queryTermList.add(onlyTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();

		InstructorInfo instructorInfo = instructorInfoDao.select(selectColumnNameList, queryTermList, orderByList).get(0);
		
		List<Object> returnList = new ArrayList<Object>();
		returnList.add(userInfo);
		returnList.add(instructorInfo);
		

		return  returnList;
	}

	@Transactional
	@Override
	public List<Object> getAllUser() throws SQLException {

		List<Object> returnList = new ArrayList<>();
		
		List<String> selectColumnNameList = new ArrayList<>();
		selectColumnNameList.addAll(UserInfo.getColumnNameList());
		List<QueryTerm> queryTermList = new ArrayList<>();
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> byId = new Pair<>(UserInfo.getColumnName(UserInfo.Columns.ID), ColumnOrder.ASC);
		orderByList.add(byId);

		returnList.add(userInfoDao.select(selectColumnNameList, queryTermList, orderByList));
		
		List<String> instructorColumnNameList = new ArrayList<>();
		instructorColumnNameList.addAll(InstructorInfo.getColumnNameList());
		List<QueryTerm> instructorQueryList = new ArrayList<>();
		List<Pair<String, ColumnOrder>> instructorOrderByList = new ArrayList<>();
		Pair<String, ColumnOrder> byUserId = new Pair<>(InstructorInfo.getColumnName(InstructorInfo.Columns.USER_INFO_ID), ColumnOrder.ASC);
		instructorOrderByList.add(byUserId);
		
		returnList.add(instructorInfoDao.select(instructorColumnNameList, instructorQueryList, instructorOrderByList));
		return returnList;
	}

	@Override
	public UserInfo loginUser(String userName, String password)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> editUser(CreateUserDto dto, Integer userId) throws SQLException {
		List<Integer> rowsAffectedList = new ArrayList<>();
		
		List<QueryTerm> queryTermList = new ArrayList<QueryTerm>();
		QueryTerm updateUser = new QueryTerm(UserInfo.getColumnName(UserInfo.Columns.ID), ComparisonOperator.EQUAL, userId, null);
		queryTermList.add(updateUser);
		
		String salt = KeyGenerators.string().generateKey();
		String saltedPassword = dto.getEncryptedPassword() + salt;
		PasswordEncoder passwordEncorder = new BCryptPasswordEncoder();
		String encryptedPassword = passwordEncorder.encode(saltedPassword);
		/*
		UserInfo userInfo = new UserInfo();
		userInfo.setUserRole(dto.getUserRole());
		userInfo.setUserName(dto.getUsername());
		userInfo.setFirstName(dto.getFirstName());
		userInfo.setLastName(dto.getLastName());
		userInfo.setEmail(dto.getEmail());
		userInfo.setDeleted(dto.getDeleted());
		userInfo.setEncryptedPassword(encryptedPassword);
		userInfo.setSalt(salt);
		userInfo.setAccountState(dto.getAccountState());
		*/
		
		Integer userRole = dto.getUserRole();
		String userName = dto.getUsername();
		String firstName = dto.getFirstName();
		String lastName = dto.getLastName();
		String email = dto.getEmail();
		Boolean deleted = dto.getDeleted();
		Integer accountState = dto.getAccountState();

		userInfoDao.update(UserInfo.getColumnName(UserInfo.Columns.USER_ROLE), userRole, queryTermList);
		userInfoDao.update(UserInfo.getColumnName(UserInfo.Columns.USERNAME), userName, queryTermList);
		userInfoDao.update(UserInfo.getColumnName(UserInfo.Columns.FIRST_NAME), firstName, queryTermList);
		userInfoDao.update(UserInfo.getColumnName(UserInfo.Columns.LAST_NAME), lastName, queryTermList);
		userInfoDao.update(UserInfo.getColumnName(UserInfo.Columns.EMAIL), email, queryTermList);
		userInfoDao.update(UserInfo.getColumnName(UserInfo.Columns.DELETED), deleted, queryTermList);
		userInfoDao.update(UserInfo.getColumnName(UserInfo.Columns.ENCRYPTED_PASSWORD), encryptedPassword, queryTermList);
		userInfoDao.update(UserInfo.getColumnName(UserInfo.Columns.SALT), salt, queryTermList);
		userInfoDao.update(UserInfo.getColumnName(UserInfo.Columns.ACCOUNT_STATE), accountState, queryTermList);
		
		QueryTerm updateInstructor = new QueryTerm(InstructorInfo.getColumnName(InstructorInfo.Columns.USER_INFO_ID), ComparisonOperator.EQUAL, userId, null);
		queryTermList.remove(updateUser);
		queryTermList.add(updateInstructor);
		
		Integer rank = (Integer.parseInt(dto.getRank()));
		Integer courseLoad = dto.getCourseLoad();
		String office = dto.getOffice();
		String department = dto.getDepartment();
		
		instructorInfoDao.update(InstructorInfo.getColumnName(InstructorInfo.Columns.RANK), rank, queryTermList);
		instructorInfoDao.update(InstructorInfo.getColumnName(InstructorInfo.Columns.COURSE_LOAD), courseLoad, queryTermList);
		instructorInfoDao.update(InstructorInfo.getColumnName(InstructorInfo.Columns.OFFICE), office, queryTermList);
		instructorInfoDao.update(InstructorInfo.getColumnName(InstructorInfo.Columns.DEPARTMENT), department, queryTermList);
		
		
		
		return rowsAffectedList;
	}   

}
