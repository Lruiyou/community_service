package com.alan.project.dao;

import lombok.Data;

@Data
public class Question {
    private Integer id;
    private String creator;
    private String title;
    private String content;
    private String htmlContent;
    private String tag;
    private Integer likeCount;
    private Integer viewCount;
    private Integer commentCount;
    private Long createTime;
    private Long modified;
}
