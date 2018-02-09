package org.dselent.scheduling.server.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.controller.SectionInfoController;
import org.dselent.scheduling.server.dto.CreateSectionDto;
import org.dselent.scheduling.server.miscellaneous.JsonResponseCreator;
import org.dselent.scheduling.server.requests.CreateSection;
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
// Print is for testing purposes
        System.out.println("controller reached");

        // add any objects that need to be returned to the success list
        String response = "";
        List<Object> success = new ArrayList<Object>();

        Integer section_number = Integer.parseInt(request.get(CreateSection.getBodyName(CreateSection.BodyKey.SECTION_NUMBER)));
        String section_type = request.get(CreateSection.getBodyName(CreateSection.BodyKey.SECTION_TYPE));
        Integer instructor_info_id = Integer.parseInt(request.get(CreateSection.getBodyName(CreateSection.BodyKey.INSTRUCTOR_INFO_ID)));
        String location = request.get(CreateSection.getBodyName(CreateSection.BodyKey.LOCATION));
        Boolean deleted = Boolean.parseBoolean(request.get(CreateSection.getBodyName(CreateSection.BodyKey.DELETED)));
        Integer course_info_id = Integer.parseInt(request.get(CreateSection.getBodyName(CreateSection.BodyKey.COURSE_INFO_ID)));
        Integer calendar_info_id = Integer.parseInt(request.get(CreateSection.getBodyName(CreateSection.BodyKey.CALENDAR_INFO_ID)));


        CreateSectionDto.Builder builder = new CreateSectionDto.Builder();
        CreateSectionDto createSectionDto = builder.withSection_number(section_number)
                .withSection_type(section_type)
                .withInstructor_info_id(instructor_info_id)
                .withLocation(location)
                .withDeleted(deleted)
                .withCourse_info_id(course_info_id)
                .withCalendar_info_id(calendar_info_id)
                .build();

        sectionService.createSection(createSectionDto);
        response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    public ResponseEntity<String> getOneSection(@RequestBody Map<String, String> request) throws Exception{
        return null;
    }

    public ResponseEntity<String> getAllSections(@RequestBody Map<String, String> request) throws Exception{
        return  null;
    }
}