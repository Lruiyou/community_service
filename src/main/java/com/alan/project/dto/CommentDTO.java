package com.alan.project.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private Long id;
    private Integer topicId;
    private String fromUid;
    private String content;
    private Long createTime;
    private  String fromName;
    private String fromAvatar;
    private Integer replyCount;
    private Integer isExit;
    private ReplyDTO reply = new ReplyDTO();
}
