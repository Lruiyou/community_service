package com.alan.project.dto;

import com.alan.project.dao.Comment;
import com.alan.project.entity.Page;
import lombok.Data;

import java.util.List;

@Data
public class CommentListDTO {
    private List<Comment> comments;
    private Page page;
}
