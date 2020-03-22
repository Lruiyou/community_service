package com.alan.project.dao;

import lombok.Data;

@Data
public class Notification {
    private Integer id;
    private Long commentId;
    private Long replyId;
    private Integer notifierId;
    private String notifierName;
    private String notifierAvatar;
    private Integer receiverId;
    private Integer outerId;
    private String outerTitle;
    private Integer type;
    private Long createTime;
    private Integer status;
    private String content;
}
