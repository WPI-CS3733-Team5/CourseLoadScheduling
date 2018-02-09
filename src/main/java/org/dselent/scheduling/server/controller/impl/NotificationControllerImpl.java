package org.dselent.scheduling.server.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.controller.NotificationController;
import org.dselent.scheduling.server.dto.CreateNotificationDto;
import org.dselent.scheduling.server.dto.GetAllNotificationsDto;
import org.dselent.scheduling.server.dto.GetOneNotificationDto;
import org.dselent.scheduling.server.miscellaneous.JsonResponseCreator;
import org.dselent.scheduling.server.requests.CreateNotification;
import org.dselent.scheduling.server.requests.GetAllNotifications;
import org.dselent.scheduling.server.requests.GetOneNotification;
import org.dselent.scheduling.server.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by dhmchorney on 2/8/2018.
 */

@Controller
public class NotificationControllerImpl implements NotificationController {
    @Autowired
    private NotificationService notificationService;

    @Override public ResponseEntity<String> createNotification(@RequestBody Map<String, String> request) throws Exception{
        System.out.println("notification controller reached");

        String response = "";
        List<Object> success = new ArrayList<Object>();

        String message = request.get(CreateNotification.getBodyName(CreateNotification.BodyKey.MESSAGE));
        Integer fromUserInfoId = Integer.parseInt(request.get(CreateNotification.getBodyName(CreateNotification.BodyKey.FROM_USER_INFO_ID)));
        Integer toUserInfoId = Integer.parseInt(request.get(CreateNotification.getBodyName(CreateNotification.BodyKey.TO_USER_INFO_ID)));

        CreateNotificationDto.Builder builder = CreateNotificationDto.builder();
        CreateNotificationDto createNotificationDto = builder.withMessage(message)
                .withFromUserInfoId(fromUserInfoId)
                .withToUserInfoId(toUserInfoId)
                .build();

        notificationService.createNotification(createNotificationDto);
        response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    @Override public ResponseEntity<String> getOneNotification(@RequestBody Map<String, String> request) throws Exception{
        System.out.println("notification controller reached");

        String response = "";
        List<Object> success = new ArrayList<Object>();

        Integer requestedNotificationId = Integer.parseInt(request.get(GetOneNotification.getBodyName(GetOneNotification.BodyKey.REQUESTED_NOTIFICATION_ID)));

        GetOneNotificationDto.Builder builder = GetOneNotificationDto.builder();
        GetOneNotificationDto getOneNotificationDto = builder.withRequestedNotificationId(requestedNotificationId)
                .build();

        notificationService.getOneNotification(getOneNotificationDto);
        response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    @Override public ResponseEntity<String> getAllNotifications(@RequestBody Map<String, String> request) throws Exception{
        System.out.println("notification controller reached");

        String response = "";
        List<Object> success = new ArrayList<Object>();

        Integer toUserInfoId = Integer.parseInt(request.get(GetAllNotifications.getBodyName(GetAllNotifications.BodyKey.REQUESTED_TO_USER_ID)));

        GetAllNotificationsDto.Builder builder = GetAllNotificationsDto.builder();
        GetAllNotificationsDto getAllNotificationsDto = builder.withToUserInfoId(toUserInfoId)
                .build();

        notificationService.getAllNotifications(getAllNotificationsDto);
        response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

        return new ResponseEntity<String>(response, HttpStatus.OK);
    }
}
