package com.alan.project.entity;

import lombok.Data;

@Data
public class ReplyRequestBody {
    private Integer topicId;
    private Long commentId;
    private Long replyId;
    private Integer fromUid;
    private String fromName;
    private String fromAvatar;
    private Integer toUid;
    private String toName;
    private String toAvatar;
    private String content;
}
