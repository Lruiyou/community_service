package com.alan.project.entity;

import lombok.Data;

@Data
public class CommentRequestBody {
    private Integer topicId;
    private String fromUid;
    private String content;
    private  String fromName;
    private String fromAvatar;
}
