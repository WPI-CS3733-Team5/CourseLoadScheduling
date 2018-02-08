package org.dselent.scheduling.server.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.dselent.scheduling.server.controller.UserInfoController;
import org.dselent.scheduling.server.dto.CreateUserDto;
import org.dselent.scheduling.server.dto.GetAllUserDto;
import org.dselent.scheduling.server.dto.GetOneUserDto;
import org.dselent.scheduling.server.miscellaneous.JsonResponseCreator;
import org.dselent.scheduling.server.requests.Create;
import org.dselent.scheduling.server.requests.GetAll;
import org.dselent.scheduling.server.requests.GetOne;
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
	public ResponseEntity<String> create(@RequestBody Map<String, String> request) throws Exception
    {
    	// Print is for testing purposes
		System.out.println("controller reached");
    	
		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();

		Integer user_role = Integer.parseInt(request.get(Create.getBodyName(Create.BodyKey.USER_ROLE)));
		String user_name = request.get(Create.getBodyName(Create.BodyKey.USER_NAME));
		String first_name = request.get(Create.getBodyName(Create.BodyKey.FIRST_NAME));
		String last_name = request.get(Create.getBodyName(Create.BodyKey.LAST_NAME));
		String email = request.get(Create.getBodyName(Create.BodyKey.EMAIL));
		Boolean deleted = Boolean.parseBoolean(request.get(Create.getBodyName(Create.BodyKey.DELETED)));
		String encrypted_password = request.get(Create.getBodyName(Create.BodyKey.ENCRYPTED_PASSWORD));
		Integer account_state = Integer.parseInt(request.get(Create.getBodyName(Create.BodyKey.ACCOUNT_STATE)));
		String rank = request.get(Create.getBodyName(Create.BodyKey.RANK));
		Integer course_load = Integer.parseInt(request.get(Create.getBodyName(Create.BodyKey.COURSE_LOAD)));
		String phone_number = request.get(Create.getBodyName(Create.BodyKey.PHONE_NUMBER));
		String office = request.get(Create.getBodyName(Create.BodyKey.OFFICE));
		String department = request.get(Create.getBodyName(Create.BodyKey.DEPARTMENT));

		CreateUserDto.Builder builder = CreateUserDto.builder();
		CreateUserDto createUserDto = builder.withUserRole(user_role)
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
		
		userService.create(createUserDto);
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
    }

	@Override
	public ResponseEntity<String> getOne(@RequestBody Map<String, String> request) throws Exception
	{
		// Print is for testing purposes
		System.out.println("controller reached");

		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();

		Integer user_role = Integer.parseInt(request.get(GetOne.getBodyName(GetOne.BodyKey.USER_ROLE)));
		String user_name = request.get(GetOne.getBodyName(GetOne.BodyKey.USER_NAME));
		String first_name = request.get(GetOne.getBodyName(GetOne.BodyKey.FIRST_NAME));
		String last_name = request.get(GetOne.getBodyName(GetOne.BodyKey.LAST_NAME));
		String email = request.get(GetOne.getBodyName(GetOne.BodyKey.EMAIL));
		Boolean deleted = Boolean.parseBoolean(request.get(GetOne.getBodyName(GetOne.BodyKey.DELETED)));
		String encrypted_password = request.get(GetOne.getBodyName(GetOne.BodyKey.ENCRYPTED_PASSWORD));
		Integer account_state = Integer.parseInt(request.get(GetOne.getBodyName(GetOne.BodyKey.ACCOUNT_STATE)));
		String rank = request.get(GetOne.getBodyName(GetOne.BodyKey.RANK));
		Integer course_load = Integer.parseInt(request.get(GetOne.getBodyName(GetOne.BodyKey.COURSE_LOAD)));
		String phone_number = request.get(GetOne.getBodyName(GetOne.BodyKey.PHONE_NUMBER));
		String office = request.get(GetOne.getBodyName(GetOne.BodyKey.OFFICE));
		String department = request.get(GetOne.getBodyName(GetOne.BodyKey.DEPARTMENT));

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

		userService.getOne(getOneUserDto);
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> getAll(@RequestBody Map<String, String> request) throws Exception
	{
		// Print is for testing purposes
		System.out.println("controller reached");

		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();

		Integer user_role = Integer.parseInt(request.get(GetAll.getBodyName(GetAll.BodyKey.USER_ROLE)));
		String user_name = request.get(GetAll.getBodyName(GetAll.BodyKey.USER_NAME));
		String first_name = request.get(GetAll.getBodyName(GetAll.BodyKey.FIRST_NAME));
		String last_name = request.get(GetAll.getBodyName(GetAll.BodyKey.LAST_NAME));
		String email = request.get(GetAll.getBodyName(GetAll.BodyKey.EMAIL));
		Boolean deleted = Boolean.parseBoolean(request.get(GetAll.getBodyName(GetAll.BodyKey.DELETED)));
		String encrypted_password = request.get(GetAll.getBodyName(GetAll.BodyKey.ENCRYPTED_PASSWORD));
		Integer account_state = Integer.parseInt(request.get(GetAll.getBodyName(GetAll.BodyKey.ACCOUNT_STATE)));
		String rank = request.get(GetAll.getBodyName(GetAll.BodyKey.RANK));
		Integer course_load = Integer.parseInt(request.get(GetAll.getBodyName(GetAll.BodyKey.COURSE_LOAD)));
		String phone_number = request.get(GetAll.getBodyName(GetAll.BodyKey.PHONE_NUMBER));
		String office = request.get(GetAll.getBodyName(GetAll.BodyKey.OFFICE));
		String department = request.get(GetAll.getBodyName(GetAll.BodyKey.DEPARTMENT));

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

		userService.getAll(getAllUserDto);
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
}

	