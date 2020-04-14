package com.alan.project.dao;

import lombok.Data;

@Data
public class Comment {
    private Long id;
    private Integer topicId;
    private String fromUid;
    private String content;
    private Long createTime;
    private  String fromName;
    private String fromAvatar;
    private Integer replyCount;
    private Integer isExit;
}
