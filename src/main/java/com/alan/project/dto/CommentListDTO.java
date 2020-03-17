package com.alan.project.dto;

import com.alan.project.entity.Page;
import lombok.Data;

import java.util.List;

@Data
public class CommentListDTO {
    private List<CommentDTO> comments;
    private Page page;
}
