package org.dselent.scheduling.server.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.controller.SectionInfoController;
//import org.dselent.scheduling.server.dto.RegisterUserDto;
import org.dselent.scheduling.server.miscellaneous.JsonResponseCreator;
//import org.dselent.scheduling.server.requests.Register;
import org.dselent.scheduling.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@controller
public class SectionInfoControllerImpl implements SectionInfoController{

    @Autowired
    private UserService userService;

    public ResponseEntity<String> createSection(@RequestBody Map<String, String> request) throws Exception{

    }

    public ResponseEntity<String> getOneSection(@RequestBody Map<String, String> request) throws Exception{

    }

    public ResponseEntity<String> getAllSections(@RequestBody Map<String, String> request) throws Exception{

    }
}