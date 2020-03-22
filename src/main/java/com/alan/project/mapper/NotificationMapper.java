package com.alan.project.mapper;

import com.alan.project.dao.Notification;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NotificationMapper {

    void insertNotification(Notification notification);

    List<Notification> getUnreadNotifications(@Param("uid") Integer uid);

    Integer getUnreadCounts(@Param("uid") Integer uid,@Param("status") int status);
}
