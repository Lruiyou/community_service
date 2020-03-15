package com.alan.project.mapper;

import com.alan.project.dao.Notification;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NotificationMapper {

    void insertNotification(Notification notification);
}
