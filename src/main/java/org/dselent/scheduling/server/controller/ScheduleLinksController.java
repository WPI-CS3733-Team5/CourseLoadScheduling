package org.dselent.scheduling.server.controller;

import java.util.Map;

import org.dselent.scheduling.server.requests.GetOneSchedule;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/schedule")
public interface ScheduleLinksController {

    @RequestMapping(method = RequestMethod.POST, value = GetOneSchedule.REQUEST_NAME)
    public ResponseEntity<String> getOneSchedule(@RequestBody Map<String, String> request) throws Exception;

}
