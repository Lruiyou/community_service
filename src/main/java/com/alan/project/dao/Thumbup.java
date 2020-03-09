package com.alan.project.dao;

import lombok.Data;

@Data
public class Thumbup {
    private Float id;
    private Integer questionId;
    private Integer userId;
    private Integer status;
}
