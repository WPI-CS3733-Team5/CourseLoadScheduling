package org.dselent.scheduling.server.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.controller.CourseInfoController;
import org.dselent.scheduling.server.dto.CreateCourseDto;
import org.dselent.scheduling.server.dto.GetAllCoursesDto;
import org.dselent.scheduling.server.miscellaneous.JsonResponseCreator;
import org.dselent.scheduling.server.requests.CreateCourse;
import org.dselent.scheduling.server.service.CourseService;
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
    private CourseService courseService;
    
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
		
		String CourseName = request.get(CreateCourse.getBodyName(CreateCourse.BodyKey.COURSE_NAME));
		Integer RequiredFrequencyPerTerm = Integer.parseInt(request.get(CreateCourse.getBodyName(CreateCourse.BodyKey.REQUIRED_FREQUENCY_PER_TERM)));
		Integer RequiredFrequencyPerSemester = Integer.parseInt(request.get(CreateCourse.getBodyName(CreateCourse.BodyKey.REQUIRED_FREQUENCY_PER_SEMESTER)));
		Integer RequiredFrequencyPerYear = Integer.parseInt(request.get(CreateCourse.getBodyName(CreateCourse.BodyKey.REQUIRED_FREQUENCY_PER_YEAR)));
		Integer CreditAmount = Integer.parseInt(CreateCourse.getBodyName(CreateCourse.BodyKey.CREDIT_AMOUNT));
		String Department = request.get(CreateCourse.getBodyName(CreateCourse.BodyKey.DEPARTMENT));
		Integer CourseNumber = Integer.parseInt(CreateCourse.getBodyName(CreateCourse.BodyKey.COURSE_NUMBER));
		

		CreateCourseDto.Builder builder = CreateCourseDto.builder();
		CreateCourseDto createCourseDto = builder.withCourseName(CourseName)
				.withRequiredFrequencyPerTerm(RequiredFrequencyPerTerm)
				.withRequiredFrequencyPerSemester(RequiredFrequencyPerSemester)
				.withRequiredFrequencyPerYear(RequiredFrequencyPerYear)
				.withCreditAmount(CreditAmount)
				.withDepartment(Department)
				.withCourseNumber(CourseNumber)
				.build();
		
		courseService.createCourse(createCourseDto);
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> getAllCourses(@RequestBody Map<String, String> request) throws Exception{
        System.out.println("notification controller reached");

        String response = "";
        List<Object> success = new ArrayList<Object>();

        GetAllCoursesDto.Builder builder = GetAllCoursesDto.builder();
        GetAllCoursesDto getAllCoursesDto = builder.build();

        courseService.getAllCourses(getAllCoursesDto);
        response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

        return new ResponseEntity<String>(response, HttpStatus.OK);
    }
}

