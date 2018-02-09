package org.dselent.scheduling.server.service;

import org.dselent.scheduling.server.dto.CreateNotificationDto;
import org.dselent.scheduling.server.dto.GetAllNotificationsDto;
import org.dselent.scheduling.server.dto.GetOneNotificationDto;
import org.dselent.scheduling.server.model.Notification;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 *  Interface created by lcgonsalves
 */

@Service
public interface NotificationService {

    Notification getOneNotification(GetOneNotificationDto dto) throws SQLException;
    List<Notification> getAllNotifications(GetAllNotificationsDto dto) throws SQLException;
    List<Integer> createNotification(CreateNotificationDto dto) throws SQLException;

}
