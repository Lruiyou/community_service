package com.alan.project.dao;

import lombok.Data;

@Data
public class Query {
    private Integer page;
    private Integer size;
    private String search;
}
