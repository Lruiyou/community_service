package com.alan.project.dao;

import lombok.Data;

@Data
public class Pagination<T> {
    private T id;
    private Integer offset;
    private Integer size;
}
