package com.alan.project.mapper;

import com.alan.project.dao.Notification;
import com.alan.project.dao.NotificationPagination;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NotificationMapper {

    void insertNotification(Notification notification);

    List<Notification> getNotifications(NotificationPagination pagination);

    Integer getUnreadCounts(@Param("uid") Integer uid,@Param("status") int status);

    void updateNotificationStatus(@Param("uid") Integer uid,@Param("status") int status);
}
