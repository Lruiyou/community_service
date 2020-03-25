package com.alan.project.dao;

import lombok.Data;

@Data
public class Question {
    private Integer id;
    private String creatorId;
    private String creatorName;
    private String avatar;
    private String githubUrl;
    private String title;
    private String content;
    private String htmlContent;
    private String tag;
    private String fileUrl;
    private Integer likeCount;
    private Integer viewCount;
    private Integer commentCount;
    private Long createTime;
    private Long modified;
}
