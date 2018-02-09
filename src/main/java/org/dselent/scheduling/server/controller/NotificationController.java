package org.dselent.scheduling.server.controller;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * Created by dhmchorney on 2/8/2018.
 */

@RequestMapping("/notifications")
public interface NotificationController {
    @RequestMapping(method = RequestMethod.POST, value = GetAllNotifications.REQUEST_NAME)
    public ResponseEntity<String> getAllNotifications(@RequestBody Map<String, String> request) throws Exception;

    @RequestMapping(method = RequestMethod.POST, value = GetOneNotification.REQUEST_NAME)
    public ResponseEntity<String> getOneNotification(@RequestBody Map<String, String> request) throws Exception;

    @RequestMapping(method = RequestMethod.POST, value = CreateNotification.REQUEST_NAME)
    public ResponseEntity<String> createNotification(@RequestBody Map<String, String> request) throws Exception;
}
