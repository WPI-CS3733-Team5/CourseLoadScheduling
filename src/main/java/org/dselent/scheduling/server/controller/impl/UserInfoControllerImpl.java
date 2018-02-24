package org.dselent.scheduling.server.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.controller.UserInfoController;
import org.dselent.scheduling.server.dto.CreateUserDto;
import org.dselent.scheduling.server.dto.GetAllUserDto;
import org.dselent.scheduling.server.dto.GetOneUserDto;
import org.dselent.scheduling.server.miscellaneous.JsonResponseCreator;
import org.dselent.scheduling.server.model.UserInfo;
import org.dselent.scheduling.server.requests.CreateUser;
import org.dselent.scheduling.server.requests.EditUser;
import org.dselent.scheduling.server.requests.GetAllUser;
import org.dselent.scheduling.server.requests.GetOneUser;
import org.dselent.scheduling.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Controller for handling requests related to the user such as logging in or registering.
 * Controller methods are the first methods reached by the request server-side (with special exception).
 * They parse the request and then call the appropriate service method to execute the business logic.
 * Any results or data is then sent back to the client.
 * 
 * @author dselent
 */
@Controller
public class UserInfoControllerImpl implements UserInfoController
{
	@Autowired
    private UserService userService;
    
	/**
	 * 
	 * @param request The body of the request expected to contain a map of String key-value pairs
	 * @return A ResponseEntity for the response object(s) and the status code
	 * @throws Exception 
	 */

	@Override
	public ResponseEntity<String> createUser(@RequestBody Map<String, String> request) throws Exception
    {
    	// Print is for testing purposes
		System.out.println("controller reached");
    	
		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();

		Integer userRole = Integer.parseInt(request.get(CreateUser.getBodyName(CreateUser.BodyKey.USER_ROLE)));
		System.out.println(userRole);
		String userName = request.get(CreateUser.getBodyName(CreateUser.BodyKey.USER_NAME));
		String firstName = request.get(CreateUser.getBodyName(CreateUser.BodyKey.FIRST_NAME));
		String lastName = request.get(CreateUser.getBodyName(CreateUser.BodyKey.LAST_NAME));
		String email = request.get(CreateUser.getBodyName(CreateUser.BodyKey.EMAIL));
		Boolean deleted = Boolean.parseBoolean(request.get(CreateUser.getBodyName(CreateUser.BodyKey.DELETED)));
		String encryptedPassword = request.get(CreateUser.getBodyName(CreateUser.BodyKey.ENCRYPTED_PASSWORD));
		Integer accountState = Integer.parseInt(request.get(CreateUser.getBodyName(CreateUser.BodyKey.ACCOUNT_STATE)));
		String rank = request.get(CreateUser.getBodyName(CreateUser.BodyKey.RANK));
		Integer courseLoad = Integer.parseInt(request.get(CreateUser.getBodyName(CreateUser.BodyKey.COURSE_LOAD)));
		//String phoneNumber = request.get(CreateUser.getBodyName(CreateUser.BodyKey.PHONE_NUMBER));
		String office = request.get(CreateUser.getBodyName(CreateUser.BodyKey.OFFICE));
		String department = request.get(CreateUser.getBodyName(CreateUser.BodyKey.DEPARTMENT));

		CreateUserDto.Builder builder = CreateUserDto.builder();
		CreateUserDto createUserDto = builder.withUserRole(userRole)
				.withUserName(userName)
				.withFirstName(firstName)
				.withLastName(lastName)
				.withEmail(email)
				.withDeleted(deleted)
				.withPassword(encryptedPassword)
				.withAccountState(accountState)
				.withRank(rank)
				.withCourseLoad(courseLoad)
				//.withPhoneNumber(phoneNumber)
				.withOffice(office)
				.withDepartment(department)
				.build();
		
		userService.createUser(createUserDto);
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
    }

	@Override
	public ResponseEntity<String> getOneUser(@RequestBody Map<String, String> request) throws Exception
	{
		// Print is for testing purposes
		System.out.println("controller reached");

		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();
		Integer user_Id = Integer.parseInt(request.get(GetOneUser.getBodyName(GetOneUser.BodyKey.REQUESTED_USER_ID)));
		/*
		Integer user_role = Integer.parseInt(request.get(GetOneUser.getBodyName(GetOneUser.BodyKey.USER_ROLE)));
		String user_name = request.get(GetOneUser.getBodyName(GetOneUser.BodyKey.USER_NAME));
		String first_name = request.get(GetOneUser.getBodyName(GetOneUser.BodyKey.FIRST_NAME));
		String last_name = request.get(GetOneUser.getBodyName(GetOneUser.BodyKey.LAST_NAME));
		String email = request.get(GetOneUser.getBodyName(GetOneUser.BodyKey.EMAIL));
		Boolean deleted = Boolean.parseBoolean(request.get(GetOneUser.getBodyName(GetOneUser.BodyKey.DELETED)));
		String encrypted_password = request.get(GetOneUser.getBodyName(GetOneUser.BodyKey.ENCRYPTED_PASSWORD));
		Integer account_state = Integer.parseInt(request.get(GetOneUser.getBodyName(GetOneUser.BodyKey.ACCOUNT_STATE)));
		String rank = request.get(GetOneUser.getBodyName(GetOneUser.BodyKey.RANK));
		Integer course_load = Integer.parseInt(request.get(GetOneUser.getBodyName(GetOneUser.BodyKey.COURSE_LOAD)));
		String phone_number = request.get(GetOneUser.getBodyName(GetOneUser.BodyKey.PHONE_NUMBER));
		String office = request.get(GetOneUser.getBodyName(GetOneUser.BodyKey.OFFICE));
		String department = request.get(GetOneUser.getBodyName(GetOneUser.BodyKey.DEPARTMENT));
		*/
		/*
		GetOneUserDto.Builder builder = GetOneUserDto.builder();
		GetOneUserDto getOneUserDto = builder.withUserRole(user_role)
				.withUserName(user_name)
				.withFirstName(first_name)
				.withLastName(last_name)
				.withEmail(email)
				.withDeleted(deleted)
				.withPassword(encrypted_password)
				.withAccountState(account_state)
				.withRank(rank)
				.withCourseLoad(course_load)
				.withPhoneNumber(phone_number)
				.withOffice(office)
				.withDepartment(department)
				.build();
				*/

		//UserInfo foundUser = userService.getOneUser(user_Id);
		//success.add(foundUser);
		success = userService.getOneUser(user_Id);
		
		
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> getAllUser(@RequestBody Map<String, String> request) throws Exception
	{
		// Print is for testing purposes
		System.out.println("controller reached");

		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();

		/*
		Integer user_role = Integer.parseInt(request.get(GetAllUser.getBodyName(GetAllUser.BodyKey.USER_ROLE)));
		String user_name = request.get(GetAllUser.getBodyName(GetAllUser.BodyKey.USER_NAME));
		String first_name = request.get(GetAllUser.getBodyName(GetAllUser.BodyKey.FIRST_NAME));
		String last_name = request.get(GetAllUser.getBodyName(GetAllUser.BodyKey.LAST_NAME));
		String email = request.get(GetAllUser.getBodyName(GetAllUser.BodyKey.EMAIL));
		Boolean deleted = Boolean.parseBoolean(request.get(GetAllUser.getBodyName(GetAllUser.BodyKey.DELETED)));
		String encrypted_password = request.get(GetAllUser.getBodyName(GetAllUser.BodyKey.ENCRYPTED_PASSWORD));
		Integer account_state = Integer.parseInt(request.get(GetAllUser.getBodyName(GetAllUser.BodyKey.ACCOUNT_STATE)));
		String rank = request.get(GetAllUser.getBodyName(GetAllUser.BodyKey.RANK));
		Integer course_load = Integer.parseInt(request.get(GetAllUser.getBodyName(GetAllUser.BodyKey.COURSE_LOAD)));
		String phone_number = request.get(GetAllUser.getBodyName(GetAllUser.BodyKey.PHONE_NUMBER));
		String office = request.get(GetAllUser.getBodyName(GetAllUser.BodyKey.OFFICE));
		String department = request.get(GetAllUser.getBodyName(GetAllUser.BodyKey.DEPARTMENT));

		GetAllUserDto.Builder builder = GetAllUserDto.builder();
		GetAllUserDto getAllUserDto = builder.withUserRole(user_role)
				.withUserName(user_name)
				.withFirstName(first_name)
				.withLastName(last_name)
				.withEmail(email)
				.withDeleted(deleted)
				.withPassword(encrypted_password)
				.withAccountState(account_state)
				.withRank(rank)
				.withCourseLoad(course_load)
				.withPhoneNumber(phone_number)
				.withOffice(office)
				.withDepartment(department)
				.build();
		*/
		success = userService.getAllUser();
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> login(Map<String, String> request) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<String> editUser(@RequestBody Map<String, String> request) throws Exception {
    	// Print is for testing purposes
		System.out.println("controller reached");
    	
		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();
		System.out.println(EditUser.getBodyName(EditUser.BodyKey.USER_ID));
		Integer userId = Integer.parseInt(request.get(EditUser.getBodyName(EditUser.BodyKey.USER_ID)));
		
		Integer userRole = Integer.parseInt(request.get(EditUser.getBodyName(EditUser.BodyKey.USER_ROLE)));
		String userName = request.get(EditUser.getBodyName(EditUser.BodyKey.USER_NAME));
		String firstName = request.get(EditUser.getBodyName(EditUser.BodyKey.FIRST_NAME));
		String lastName = request.get(EditUser.getBodyName(EditUser.BodyKey.LAST_NAME));
		String email = request.get(EditUser.getBodyName(EditUser.BodyKey.EMAIL));
		Boolean deleted = Boolean.parseBoolean(request.get(EditUser.getBodyName(EditUser.BodyKey.DELETED)));
		String encryptedPassword = request.get(EditUser.getBodyName(EditUser.BodyKey.ENCRYPTED_PASSWORD));
		Integer accountState = Integer.parseInt(request.get(EditUser.getBodyName(EditUser.BodyKey.ACCOUNT_STATE)));
		String rank = request.get(EditUser.getBodyName(EditUser.BodyKey.RANK));
		Integer courseLoad = Integer.parseInt(request.get(EditUser.getBodyName(EditUser.BodyKey.COURSE_LOAD)));
		String office = request.get(EditUser.getBodyName(EditUser.BodyKey.OFFICE));
		String department = request.get(EditUser.getBodyName(EditUser.BodyKey.DEPARTMENT));

		CreateUserDto.Builder builder = CreateUserDto.builder();
		CreateUserDto createUserDto = builder.withUserRole(userRole)
				.withUserName(userName)
				.withFirstName(firstName)
				.withLastName(lastName)
				.withEmail(email)
				.withDeleted(deleted)
				.withPassword(encryptedPassword)
				.withAccountState(accountState)
				.withRank(rank)
				.withCourseLoad(courseLoad)
				//.withPhoneNumber(phoneNumber)
				.withOffice(office)
				.withDepartment(department)
				.build();
		
		userService.editUser(createUserDto, userId);
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
}

	