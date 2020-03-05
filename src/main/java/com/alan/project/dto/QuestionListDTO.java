package com.alan.project.dto;

import com.alan.project.dao.Question;
import com.alan.project.entity.Page;
import lombok.Data;

import java.util.List;

@Data
public class QuestionListDTO {
    private List<Question> questions;
    private Page page;
}
