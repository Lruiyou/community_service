package com.alan.project.dao;

import lombok.Data;

@Data
public class NotificationPagination {
    private Integer uid;
    private Integer offset;
    private Integer size;
}
