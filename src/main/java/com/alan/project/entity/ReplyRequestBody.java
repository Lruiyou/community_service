package com.alan.project.entity;

import lombok.Data;

@Data
public class ReplyRequestBody {
    private Integer topicId;
    private Long commentId;
    private Long replyId;
    private String fromUid;
    private String fromName;
    private String fromAvatar;
    private String toUid;
    private String toName;
    private String toAvatar;
    private String content;
}
