package com.alan.project.dto;

import com.alan.project.dao.Reply;
import com.alan.project.entity.Page;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ReplyDTO {
    private List<Reply> replies = new ArrayList<>();
    private Page page;
}
