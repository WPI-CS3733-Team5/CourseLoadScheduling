package org.dselent.scheduling.server.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/userInfo")
public interface UserInfoController
{
    
    @RequestMapping(method=RequestMethod.POST, value="getAll")
	public ResponseEntity<String> getAll(@RequestBody Map<String, String> request) throws Exception;


    @RequestMapping(method=RequestMethod.POST, value="GetOne")
    public ResponseEntity<String> getOne(@RequestBody Map<String, String> request) throws Exception;


    @RequestMapping(method=RequestMethod.POST, value="create")
    public ResponseEntity<String> create(@RequestBody Map<String, String> request) throws Exception;
}

	