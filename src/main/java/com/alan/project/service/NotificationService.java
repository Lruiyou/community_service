package com.alan.project.service;

import com.alan.project.dao.Notification;
import com.alan.project.mapper.NotificationMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NotificationService {

    @Resource
    private NotificationMapper notificationMapper;

    public List<Notification> getUnreadNotifications(Integer uid, int status) {
        return notificationMapper.getUnreadNotifications(uid,status);
    }

    public Integer getUnreadCounts(Integer uid, int status) {
        return notificationMapper.getUnreadCounts(uid,status);
    }
}
