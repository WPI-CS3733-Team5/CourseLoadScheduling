package org.dselent.scheduling.server.controller;

import java.util.Map;

import org.dselent.scheduling.server.requests.CreateUser;
import org.dselent.scheduling.server.requests.EditUser;
import org.dselent.scheduling.server.requests.GetAllUser;
import org.dselent.scheduling.server.requests.GetOneUser;
import org.dselent.scheduling.server.requests.Login;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/userInfo")
public interface UserInfoController
{
    
    @RequestMapping(method=RequestMethod.POST, value=GetAllUser.REQUEST_NAME)
	public ResponseEntity<String> getAllUser(@RequestBody Map<String, String> request) throws Exception;

    @RequestMapping(method=RequestMethod.POST, value=GetOneUser.REQUEST_NAME)
    public ResponseEntity<String> getOneUser(@RequestBody Map<String, String> request) throws Exception;

    @RequestMapping(method=RequestMethod.POST, value=CreateUser.REQUEST_NAME)
    public ResponseEntity<String> createUser(@RequestBody Map<String, String> request) throws Exception;
    
    @RequestMapping(method=RequestMethod.POST, value=Login.REQUEST_NAME)
    public ResponseEntity<String> login(@RequestBody Map<String, String> request) throws Exception;
    
    @RequestMapping(method=RequestMethod.POST, value=EditUser.REQUEST_NAME)
    public ResponseEntity<String> editUser(@RequestBody Map<String, String> request) throws Exception;
    
    @RequestMapping(method=RequestMethod.POST, value="theBigOne")
    public ResponseEntity<String> theBigOne(@RequestBody Map<String, String> request) throws Exception;
}
