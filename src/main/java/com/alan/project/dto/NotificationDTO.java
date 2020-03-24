package com.alan.project.dto;

import com.alan.project.dao.Notification;
import com.alan.project.entity.Page;
import lombok.Data;

import java.util.List;

@Data
public class NotificationDTO {
    private List<Notification> notificationList;
    private Page page;
}
