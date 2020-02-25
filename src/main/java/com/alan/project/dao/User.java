package com.alan.project.dao;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String accountId;
    private String name;
    private String token;
    private Long createTime;
    private Long modified;
    private String avatarUrl;
    private String githubUrl;
}
