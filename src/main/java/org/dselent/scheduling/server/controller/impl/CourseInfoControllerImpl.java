package org.dselent.scheduling.server.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.controller.CourseInfoController;
import org.dselent.scheduling.server.dto.CreateCourseDto;
import org.dselent.scheduling.server.miscellaneous.JsonResponseCreator;
import org.dselent.scheduling.server.requests.CreateCourse;
//import org.dselent.scheduling.server.requests.GetWishlistLinks;
import org.dselent.scheduling.server.service.CourseService;
//import org.dselent.scheduling.server.service.WishlistLinksService;
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
public class CourseInfoControllerImpl implements CourseInfoController
{
	@Autowired
    private CourseService createCourse;
    
	/**
	 * 
	 * @param request The body of the request expected to contain a map of String key-value pairs
	 * @return A ResponseEntity for the response object(s) and the status code
	 * @throws Exception 
	 */
	public ResponseEntity<String> createCourse(@RequestBody Map<String, String> request) throws Exception 
    {
    	// Print is for testing purposes
		System.out.println("controller reached");
    	
		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		String CourseName = request.get(CreateCourse.getBodyName(CreateCourse.BodyKey.COURSENAME));
		String RequiredFrequencyPerTerm = request.get(CreateCourse.getBodyName(CreateCourse.BodyKey.REQUIREDFREQUENCYPERTERM));
		String RequiredFrequencyPerSemester = request.get(CreateCourse.getBodyName(CreateCourse.BodyKey.REQUIREDFREQUENCYPERSEMESTER));
		String RequiredFrequencyPerYear = request.get(CreateCourse.getBodyName(CreateCourse.BodyKey.REQUIREDFREQUENCYPERYEAR));
		Float CreditAmount = Float.parseFloat(CreateCourse.getBodyName(CreateCourse.BodyKey.CREDITAMOUNT));
		String Department = request.get(CreateCourse.getBodyName(CreateCourse.BodyKey.DEPARTMENT));
		Integer CourseNumber = Integer.parseInt(CreateCourse.getBodyName(CreateCourse.BodyKey.COURSENUMBER));
		

		CreateCourseDto.Builder builder = CreateCourseDto.builder();
		CreateCourseDto createCourseDto = builder.withCourseName(CourseName)
				.withRequiredFrequencyPerTerm(RequiredFrequencyPerTerm)
				.withRequiredFrequencyPerSemester(RequiredFrequencyPerSemester)
				.withRequiredFrequencyPerYear(RequiredFrequencyPerYear)
				.withCreditAmount(CreditAmount)
				.withDepartment(Department)
				.withCourseNumber(CourseNumber)
				.build();
		
		createCourse.createCourse(createCourseDto);
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
    }

}

