package org.dselent.scheduling.server.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.controller.SectionInfoController;
//import org.dselent.scheduling.server.dto.RegisterUserDto;
import org.dselent.scheduling.server.dto.CreateSectionDto;
import org.dselent.scheduling.server.miscellaneous.JsonResponseCreator;
//import org.dselent.scheduling.server.requests.Register;
import org.dselent.schedulling.server.requests.CreateSection;
import org.dselent.scheduling.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@controller
public class SectionInfoControllerImpl implements SectionInfoController{

    @Autowired
    private SectionService sectionService;

    public ResponseEntity<String> createSection(@RequestBody Map<String, String> request) throws Exception{
// Print is for testing purposes
        System.out.println("controller reached");

        // add any objects that need to be returned to the success list
        String response = "";
        List<Object> success = new ArrayList<Object>();

        Integer section_number = Integer.parseInt(request.get(CreateSection.getBodyName(CreateSecion.BodyKey.SECTIONNUMBER)));
        String section_type = request.get(CreateSection.getBodyName(CreateSection.BodyKey.SECTIONTYPE));
        Integer instructor_info_id = request.get(CreateSection.getBodyName(CreateSection.BodyKey.INSTRUCTORINFOID));
        String location = request.get(CreateSection.getBodyName(CreateSection.BodyKey.LOCATION));
        Boolean deleted = Boolean.parseBoolean(request.get(CreateSection.getBodyName(CreateSection.BodyKey.DELETED)));
        Integer course_info_id = Integer.parseInt(request.get(CreateSection.getBodyName(CreateSection.BodyKey.COURSEINFOID)));
        Integer calendar_info_id = Integer.parseInt(request.get(CreateSection.getBodyName(CreateSection.BodyKey.CALENDARINFOID)));


        CreateSectionDto.Builder builder = CreateSectionDto.builder();
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

    }

    public ResponseEntity<String> getAllSections(@RequestBody Map<String, String> request) throws Exception{

    }
}