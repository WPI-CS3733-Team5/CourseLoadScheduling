package org.dselent.scheduling.server.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.dselent.scheduling.server.controller.ScheduleLinksController;
import org.dselent.scheduling.server.dto.GetOneScheduleDto;
import org.dselent.scheduling.server.miscellaneous.JsonResponseCreator;
import org.dselent.scheduling.server.requests.CreateSchedule;
import org.dselent.scheduling.server.requests.GetOneSchedule;
import org.dselent.scheduling.server.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ScheduleLinksControllerImpl implements ScheduleLinksController {

    @Autowired
    private ScheduleService scheduleService;

    /**
     *
     * @param request The body of the request expected to contain a map of String key-value pairs
     * @return A ResponseEntity for the response object(s) and the status code
     * @throws Exception
     */

    public ResponseEntity<String> getOneSchedule(@RequestBody Map<String, String> request) throws Exception{

        // add any objects that need to be returned to the success list
        String response = "";
        List<Object> success = new ArrayList<Object>();

        Integer instructorInfoId = Integer.parseInt(request.get(GetOneSchedule.getBodyName(GetOneSchedule.BodyKey.INSTRUCTOR_INFO_ID)));


        //GetOneScheduleDto.Builder builder = GetOneScheduleDto.builder();
        //GetOneScheduleDto getOneScheduleDto = builder.withInstructorInfoId(instructor_info_id)
          //      .build();

        success.addAll(scheduleService.getOneSchedule(instructorInfoId));
        response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

        return new ResponseEntity<String>(response, HttpStatus.OK);

    }

	@Override
	public ResponseEntity<String> createSchedule(@RequestBody Map<String, String> request) throws Exception {
		String response = "";
		List<Object> success = new ArrayList<Object>();
		Integer instructorInfoId = Integer.parseInt(request.get(CreateSchedule.getBodyName(CreateSchedule.BodyKey.INSTRUCTOR_INFO_ID)));
		String unParsedIds = request.get(CreateSchedule.getBodyName(CreateSchedule.BodyKey.SECTION_INFO_ID_LIST));
		List<Integer> sectionInfoIdList = new ArrayList<Integer>();
		Scanner scan = new Scanner(unParsedIds);
		while(scan.hasNextInt()) {
			sectionInfoIdList.add(scan.nextInt());
		}
		scan.close();
		scheduleService.createSchedule(instructorInfoId, sectionInfoIdList);
        response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);
        return new ResponseEntity<String>(response, HttpStatus.OK);
	}


}
