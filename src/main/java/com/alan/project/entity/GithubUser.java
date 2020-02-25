package com.alan.project.entity;

import lombok.Data;

@Data
public class GithubUser {
    private Long id;
    private String login;
    private String avatarUrl;
    private String htmlUrl;
}
