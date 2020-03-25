package com.alan.project.dao;

import lombok.Data;

@Data
public class Reply {
    private Long id;
    private Integer topicId;
    private Long commentId;
    private Long replyId;
    private String fromUid;
    private String fromName;
    private String fromAvatar;
    private String toUid;
    private String toName;
    private String toAvatar;
    private Long createTime;
    private String content;
}
