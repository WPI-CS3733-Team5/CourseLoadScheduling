package org.dselent.scheduling.server.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.controller.SectionInfoController;
import org.dselent.scheduling.server.dto.CreateSectionDto;
import org.dselent.scheduling.server.dto.GetOneSectionDto;
import org.dselent.scheduling.server.miscellaneous.JsonResponseCreator;
import org.dselent.scheduling.server.requests.CreateSection;
import org.dselent.scheduling.server.requests.GetOneSection;
import org.dselent.scheduling.server.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class SectionInfoControllerImpl implements SectionInfoController{

    @Autowired
    private SectionService sectionService;

    public ResponseEntity<String> createSection(@RequestBody Map<String, String> request) throws Exception{
        System.out.println("controller reached");

        // add any objects that need to be returned to the success list
        String response = "";
        List<Object> success = new ArrayList<Object>();

        //SectionInfo
        Integer sectionNumber = Integer.parseInt(request.get(CreateSection.getBodyName(CreateSection.BodyKey.SECTION_NUMBER)));
        String sectionType = request.get(CreateSection.getBodyName(CreateSection.BodyKey.SECTION_TYPE));
        String location = request.get(CreateSection.getBodyName(CreateSection.BodyKey.LOCATION));
        Integer courseInfoId = Integer.parseInt(request.get(CreateSection.getBodyName(CreateSection.BodyKey.COURSE_INFO_ID)));

        //CalendarInfo
        Integer term = Integer.parseInt(request.get(CreateSection.getBodyName(CreateSection.BodyKey.TERM)));
        Integer startTime = Integer.parseInt(request.get(CreateSection.getBodyName(CreateSection.BodyKey.START_TIME)));
        Integer endTime = Integer.parseInt(request.get(CreateSection.getBodyName(CreateSection.BodyKey.END_TIME)));
        String days = request.get(CreateSection.getBodyName(CreateSection.BodyKey.DAYS));
        Integer year = 2018;
        String semester;
        if (term <= 2 || term == 5) semester = "FALL";
        else semester = "SPRING";
        
        CreateSectionDto.Builder builder = new CreateSectionDto.Builder();
        CreateSectionDto createSectionDto = builder.withSectionNumber(sectionNumber)
                .withSectionType(sectionType)
                .withLocation(location)
                .withDeleted(false)
                .withCourseInfoId(courseInfoId)
                .withTerm(term)
                .withStartTime(startTime)
                .withEndTime(endTime)
                .withDays(days)
                .withSemester(semester)
                .withYear(year)
                .build();

        sectionService.createSection(createSectionDto);
        response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    public ResponseEntity<String> getOneSection(@RequestBody Map<String, String> request) throws Exception{

        // add any objects that need to be returned to the success list
        String response = "";
        List<Object> success = new ArrayList<Object>();

        Integer id = Integer.parseInt(request.get(GetOneSection.getBodyName(GetOneSection.BodyKey.ID)));
        Integer sectionNumber = Integer.parseInt(request.get(GetOneSection.getBodyName(GetOneSection.BodyKey.SECTION_NUMBER)));
        String sectionType = request.get(GetOneSection.getBodyName(GetOneSection.BodyKey.SECTION_TYPE));
        Integer instructorInfoId = Integer.parseInt(request.get(GetOneSection.getBodyName(GetOneSection.BodyKey.SECTION_NUMBER)));
        String location = request.get(GetOneSection.getBodyName(GetOneSection.BodyKey.SECTION_NUMBER));
        Boolean deleted = Boolean.parseBoolean(request.get(GetOneSection.getBodyName(GetOneSection.BodyKey.DELETED)));
        Integer courseInfoId = Integer.parseInt(request.get(GetOneSection.getBodyName(GetOneSection.BodyKey.SECTION_NUMBER)));
        Integer calendarInfoId = Integer.parseInt(request.get(GetOneSection.getBodyName(GetOneSection.BodyKey.CALENDAR)));


        GetOneSectionDto.Builder builder = GetOneSectionDto.builder();
        GetOneSectionDto getOneSectionDto = builder.withId(id)
                .withSectionNumber(sectionNumber)
                .withSectionType(sectionType)
                .withInstructorInfoId(instructorInfoId)
                .withLocation(location)
                .withDeleted(deleted)
                .withCourseInfoId(courseInfoId)
                .withCalendarInfoId(calendarInfoId)
                .build();

        sectionService.getOneSection(getOneSectionDto);
        response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    public ResponseEntity<String> getAllSections(@RequestBody Map<String, String> request) throws Exception{

        // add any objects that need to be returned to the success list
        String response = "";
        List<Object> success = new ArrayList<Object>();

        sectionService.getAllSections();
        response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

        return new ResponseEntity<String>(response, HttpStatus.OK);
    }
}