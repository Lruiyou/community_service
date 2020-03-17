package com.alan.project.dao;

import lombok.Data;

@Data
public class ReplyPagination {
    private Long commentId;
    private Integer offset;
    private Integer size;
}
