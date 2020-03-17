package com.alan.project.dto;

import com.alan.project.dao.Reply;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CommentDTO {
    private Long id;
    private Integer topicId;
    private Integer fromUid;
    private String content;
    private Long createTime;
    private  String fromName;
    private String fromAvatar;
    private Integer replyCount;
    private List<Reply> replies = new ArrayList<>();
}
