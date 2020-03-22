package com.alan.project.controller;

import com.alan.project.dao.Notification;
import com.alan.project.dto.Result;
import com.alan.project.enums.NotificationStatus;
import com.alan.project.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    /**
     * 通过用户id获取还没查看的消息总数
     * @return
     */
    @GetMapping("/notification/unread/counts")
    @ResponseBody
    public Result getNotificationCounts(@RequestParam("uid")Integer uid){
        Integer unreadCounts = notificationService.getUnreadCounts(uid, NotificationStatus.UNREAD.getStatus());
        return Result.success(unreadCounts);
    }

    /**
     * 通过用户id和获取未读取的消息
     * @param uid
     * @return 未查看的消息
     */
    @GetMapping("/notification/unread/list")
    @ResponseBody
    public Result getUnreadNotifications(@RequestParam("uid")Integer uid,
                                         @RequestParam(value = "currentPage",defaultValue = "1")Integer currentPage,
                                         @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize){
        //获取用户还没看的通知
        List<Notification> notifications = notificationService.getUnreadNotifications(uid, NotificationStatus.UNREAD.getStatus());
        return Result.success(notifications);
    }


}
