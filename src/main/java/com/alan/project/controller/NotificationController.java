package com.alan.project.controller;

import com.alan.project.dao.User;
import com.alan.project.dto.NotificationDTO;
import com.alan.project.dto.Result;
import com.alan.project.enums.NotificationStatus;
import com.alan.project.enums.ResultCode;
import com.alan.project.service.NotificationService;
import com.alan.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserService userService;

    /**
     * 将未读的消息状态设置成已读
     * @param uid
     * @return
     */
    @PutMapping("/notification/status")
    @ResponseBody
    public Result updateNotificationStatus(@RequestParam("uid")String uid){
        notificationService.updateNotificationStatus(uid,NotificationStatus.UNREAD.getStatus());
        return Result.success();
    }

    /**
     * 通过用户id获取还没查看的消息总数
     * @return
     */
    @GetMapping("/notification/unread/counts")
    @ResponseBody
    public Result getNotificationCounts(@RequestParam("uid")String uid){
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
    public Result getUnreadNotifications(@RequestParam("uid")String uid,
                                         @RequestParam(value = "currentPage",defaultValue = "1")Integer currentPage,
                                         @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize){
        User user = userService.findUserByAccountId(uid);
        if (user == null){
            return Result.failure(ResultCode.USER_NOT_EXIT);
        }
        //获取用户还没看的通知
        NotificationDTO result = notificationService.getNotifications(uid,currentPage,pageSize);
        return Result.success(result);
    }


}
