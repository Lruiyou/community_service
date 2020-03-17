package com.alan.project.dto;

import com.alan.project.dao.Reply;
import com.alan.project.entity.Page;
import lombok.Data;

import java.util.List;

@Data
public class ReplyListDTO {
    private List<Reply> replies;
    private Page page;
}
