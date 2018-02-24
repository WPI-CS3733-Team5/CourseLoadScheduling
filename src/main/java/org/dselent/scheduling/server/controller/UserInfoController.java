package org.dselent.scheduling.server.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/userInfo")
public interface UserInfoController
{
    
    @RequestMapping(method=RequestMethod.POST, value="getAllUser")
	public ResponseEntity<String> getAllUser(@RequestBody Map<String, String> request) throws Exception;


    @RequestMapping(method=RequestMethod.POST, value="getOneUser")
    public ResponseEntity<String> getOneUser(@RequestBody Map<String, String> request) throws Exception;


    @RequestMapping(method=RequestMethod.POST, value="createUser")
    public ResponseEntity<String> createUser(@RequestBody Map<String, String> request) throws Exception;
}
