package org.dselent.scheduling.server.service.impl;

import org.dselent.scheduling.server.dao.NotificationDao;
import org.dselent.scheduling.server.dto.CreateNotificationDto;
import org.dselent.scheduling.server.dto.GetAllNotificationsDto;
import org.dselent.scheduling.server.dto.GetOneNotificationDto;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.Notification;
import org.dselent.scheduling.server.service.NotificationService;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationDao notificationDao;

    public NotificationServiceImpl(){
        /// Empty
    }

    @Transactional
    @Override
    public Notification getOneNotification(GetOneNotificationDto dto) throws SQLException {
        Notification notification = notificationDao.findById(dto.getId());

        return notification;
    }

    @Transactional
    @Override
    public List<Notification> getAllNotifications(GetAllNotificationsDto dto) throws SQLException {
        List<String> selectColumnNameList = new ArrayList<>();
        List<QueryTerm> queryTermList = new ArrayList<>();
        List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
        selectColumnNameList.addAll(Notification.getColumnNameList());
        Pair<String, ColumnOrder> byId = new Pair<>(Notification.getColumnName(Notification.Columns.ID), ColumnOrder.ASC);

        return notificationDao.select(selectColumnNameList, queryTermList, orderByList);
    }

    @Transactional
    @Override
    public List<Integer> createNotification(CreateNotificationDto dto) throws SQLException {
        List<Integer> rowsAffectedList = new ArrayList<>();

        Notification notification = new Notification();

        notification.setMessage(dto.getMessage());
        notification.setFromUserInfoId(dto.getFromUserInfoId());
        notification.setToUserInfoId(dto.getToUserInfoId());

        List<String> notificationInsertColumnNameList = new ArrayList<>();
        List<String> notificationKeyHolderColumnNameList = new ArrayList<>();

        notificationInsertColumnNameList.add(Notification.getColumnName(Notification.Columns.MESSAGE));
        notificationInsertColumnNameList.add(Notification.getColumnName(Notification.Columns.FROM_USER_INFO_ID));
        notificationInsertColumnNameList.add(Notification.getColumnName(Notification.Columns.TO_USER_INFO_ID));

        notificationKeyHolderColumnNameList.add(Notification.getColumnName(Notification.Columns.ID));

        rowsAffectedList.add(notificationDao.insert(notification, notificationInsertColumnNameList, notificationKeyHolderColumnNameList));

        return rowsAffectedList;
    }
}
