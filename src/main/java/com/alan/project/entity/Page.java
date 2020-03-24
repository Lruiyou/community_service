package com.alan.project.entity;

import lombok.Data;

@Data
public class Page {
    private Integer pageSize;
    private Integer currentPage;
    private Integer total;
    private boolean more;
}
