package org.dselent.scheduling.server.controller;

import java.util.Map;

import org.dselent.scheduling.server.requests.GetWishlistLinks;
//import org.dselent.scheduling.server.requests.Register;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/wishlistLinks")
public interface WishlistLinksController
{
    
    @RequestMapping(method=RequestMethod.POST, value=GetWishlistLinks.REQUEST_NAME)
	public ResponseEntity<String> getWishlistLinks(@RequestBody Map<String, String> request) throws Exception;
}

	