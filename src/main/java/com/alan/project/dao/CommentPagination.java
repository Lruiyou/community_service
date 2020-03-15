package com.alan.project.dao;

import lombok.Data;

@Data
public class CommentPagination {
    private Integer questionId;
    private Integer offset;
    private Integer size;
}
