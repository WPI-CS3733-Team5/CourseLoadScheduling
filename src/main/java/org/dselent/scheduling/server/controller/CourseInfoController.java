package org.dselent.scheduling.server.controller;

import java.util.Map;

import org.dselent.scheduling.server.requests.CreateCourse;
import org.dselent.scheduling.server.requests.GetAllCourses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/courseService")
public interface CourseInfoController
{
    
    @RequestMapping(method=RequestMethod.POST, value=CreateCourse.REQUEST_NAME)
	public ResponseEntity<String> createCourse(@RequestBody Map<String, String> request) throws Exception;

    @RequestMapping(method=RequestMethod.POST, value= GetAllCourses.REQUEST_NAME)
    public ResponseEntity<String> getAllCourses(@RequestBody Map<String, String> request) throws Exception;
}

	