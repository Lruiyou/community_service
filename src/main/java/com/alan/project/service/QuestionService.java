package com.alan.project.service;

import com.alan.project.dao.Question;
import com.alan.project.mapper.QuestionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class QuestionService {

    @Resource
    private QuestionMapper questionMapper;


    public boolean createQuestion(Question question){
        return questionMapper.createQuestion(question);
    }

    public Boolean updateQuestionById(Question question) {
        return questionMapper.updateQuestionById(question);
    }
}
