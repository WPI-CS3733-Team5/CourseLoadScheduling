package org.dselent.scheduling.server.controller;

import java.util.Map;

import org.dselent.scheduling.server.requests.CreateSection;
import org.dselent.scheduling.server.requests.GetAllSections;
import org.dselent.scheduling.server.requests.GetOneSection;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/section")
public interface SectionInfoController{

    @RequestMapping(method = RequestMethod.POST, value = CreateSection.REQUEST_NAME)
    public ResponseEntity<String> createSection(@RequestBody Map<String, String> request) throws Exception;

    @RequestMapping(method = RequestMethod.POST, value = GetOneSection.REQUEST_NAME)
    public ResponseEntity<String> getOneSection(@RequestBody Map<String, String> request) throws Exception;

    @RequestMapping(method = RequestMethod.POST, value = GetAllSections.REQUEST_NAME)
    public ResponseEntity<String> getAllSections(@RequestBody Map<String, String> request) throws Exception;

}