package com.alan.project.mapper;

import com.alan.project.dao.Notification;
import com.alan.project.dao.Pagination;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NotificationMapper {

    void insertNotification(Notification notification);

    List<Notification> getNotifications(Pagination pagination);

    Integer getUnreadCounts(@Param("uid") String uid,@Param("status") int status);

    void updateNotificationStatus(@Param("uid") String uid,@Param("status") int status);

    Integer getTotalByUid(@Param("uid") String uid);
}
