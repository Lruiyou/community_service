package com.alan.project.service;

import com.alan.project.dao.Notification;
import com.alan.project.dao.NotificationPagination;
import com.alan.project.mapper.NotificationMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NotificationService {

    @Resource
    private NotificationMapper notificationMapper;

    public List<Notification> getNotifications( Integer uid,Integer currentPage, Integer pageSize) {
        Integer offset = pageSize * (currentPage - 1);
        NotificationPagination pagination = new NotificationPagination();
        pagination.setUid(uid);
        pagination.setOffset(offset);
        pagination.setSize(pageSize);
        return notificationMapper.getNotifications(pagination);
    }

    public Integer getUnreadCounts(Integer uid, int status) {
        return notificationMapper.getUnreadCounts(uid,status);
    }

    public void updateNotificationStatus(Integer uid, int status) {
        notificationMapper.updateNotificationStatus(uid,status);
    }
}
