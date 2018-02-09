package org.dselent.scheduling.server.service;

import org.dselent.scheduling.server.dto.CreateCourseDto;
import org.dselent.scheduling.server.dto.GetAllCoursesDto;
import org.dselent.scheduling.server.model.CourseInfo;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

// created by David McHorney

@Service
public interface CourseService
{
    public List<Integer> createCourse(CreateCourseDto createCourseDto) throws SQLException;
    public List<CourseInfo> getAllCourses(GetAllCoursesDto getAllCoursesDto) throws SQLException;

}
