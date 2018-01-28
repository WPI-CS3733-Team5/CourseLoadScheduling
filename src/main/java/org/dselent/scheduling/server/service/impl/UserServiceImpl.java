package org.dselent.scheduling.server.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.dao.UsersDao;
import org.dselent.scheduling.server.dao.UsersRolesLinksDao;
import org.dselent.scheduling.server.dto.RegisterUserDto;
import org.dselent.scheduling.server.model.User_Info;
import org.dselent.scheduling.server.model.UsersRolesLink;
import org.dselent.scheduling.server.service.UserService;
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
	private UsersDao usersDao;
	
	@Autowired
	private UsersRolesLinksDao usersRolesLinksDao;
	
    public UserServiceImpl()
    {
    	//
    }
    
    /*
     * (non-Javadoc)
     * @see org.dselent.scheduling.server.service.UserService#registerUser(org.dselent.scheduling.server.dto.RegisterUserDto)
     */
    @Transactional
    @Override
	public List<Integer> registerUser(RegisterUserDto dto) throws SQLException
	{
		List<Integer> rowsAffectedList = new ArrayList<>();
		
		// TODO validate business constraints
			// ^^students should do this^^
			// password strength requirements
			// email requirements
			// null values
			// etc...
		
		String salt = KeyGenerators.string().generateKey();
		String saltedPassword = dto.getPassword() + salt;
		PasswordEncoder passwordEncorder = new BCryptPasswordEncoder();
		String encryptedPassword = passwordEncorder.encode(saltedPassword);
		
		User_Info userInfo = new User_Info();
		userInfo.setUserName(dto.getUserName());
		userInfo.setFirstName(dto.getFirstName());
		userInfo.setLastName(dto.getLastName());
		userInfo.setEmail(dto.getEmail());
		userInfo.setEncryptedPassword(encryptedPassword);
		userInfo.setSalt(salt);
    	userInfo.setUserStateId(1);
    	
    	List<String> userInsertColumnNameList = new ArrayList<>();
    	List<String> userKeyHolderColumnNameList = new ArrayList<>();
    	
    	userInsertColumnNameList.add(User_Info.getColumnName(User_Info.Columns.USER_NAME));
    	userInsertColumnNameList.add(User_Info.getColumnName(User_Info.Columns.FIRST_NAME));
    	userInsertColumnNameList.add(User_Info.getColumnName(User_Info.Columns.LAST_NAME));
    	userInsertColumnNameList.add(User_Info.getColumnName(User_Info.Columns.EMAIL));
    	userInsertColumnNameList.add(User_Info.getColumnName(User_Info.Columns.ENCRYPTED_PASSWORD));
    	userInsertColumnNameList.add(User_Info.getColumnName(User_Info.Columns.SALT));
    	userInsertColumnNameList.add(User_Info.getColumnName(User_Info.Columns.USER_STATE_ID));
    	
    	userKeyHolderColumnNameList.add(User_Info.getColumnName(User_Info.Columns.ID));
    	userKeyHolderColumnNameList.add(User_Info.getColumnName(User_Info.Columns.CREATED_AT));
    	userKeyHolderColumnNameList.add(User_Info.getColumnName(User_Info.Columns.UPDATED_AT));
		
    	rowsAffectedList.add(usersDao.insert(userInfo, userInsertColumnNameList, userKeyHolderColumnNameList));

		//
     	
    	// for now, assume users can only register with default role id
    	// may change in the future
    	
		UsersRolesLink usersRolesLink = new UsersRolesLink();
		usersRolesLink.setUserId(userInfo.getId());
		usersRolesLink.setRoleId(1); // hard coded as regular userInfo
    	
    	List<String> usersRolesLinksInsertColumnNameList = new ArrayList<>();
    	List<String> usersRolesLinksKeyHolderColumnNameList = new ArrayList<>();
    	
    	usersRolesLinksInsertColumnNameList.add(UsersRolesLink.getColumnName(UsersRolesLink.Columns.USER_ID));
    	usersRolesLinksInsertColumnNameList.add(UsersRolesLink.getColumnName(UsersRolesLink.Columns.ROLE_ID));
    	
    	usersRolesLinksKeyHolderColumnNameList.add(UsersRolesLink.getColumnName(UsersRolesLink.Columns.ID));
    	usersRolesLinksKeyHolderColumnNameList.add(UsersRolesLink.getColumnName(UsersRolesLink.Columns.CREATED_AT));
    	usersRolesLinksKeyHolderColumnNameList.add(UsersRolesLink.getColumnName(UsersRolesLink.Columns.DELETED));
		
    	rowsAffectedList.add(usersRolesLinksDao.insert(usersRolesLink, usersRolesLinksInsertColumnNameList, usersRolesLinksKeyHolderColumnNameList));
		
		return rowsAffectedList;
	}
	
	//

	@Override
	public User_Info loginUser(String userName, String password)
	{
		// TODO Auto-generated method stub
		return null;
	}   

}
