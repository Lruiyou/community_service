package com.alan.project.dto;

import lombok.Data;

@Data
public class HotTopicDTO {
    private Integer id;
    private String creator;
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
    private Integer heat;
}
