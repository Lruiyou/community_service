package com.alan.project.controller;

import com.alan.project.dao.Question;
import com.alan.project.dto.Result;
import com.alan.project.enums.ResultCode;
import com.alan.project.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;


    @PostMapping("/question")
    @ResponseBody
    public Result createQuestion(@RequestParam(value = "creator") String creator,
                                 @RequestParam(value = "title") String title,
                                 @RequestParam(value = "content") String content,
                                 @RequestParam(value = "html_content") String htmlContent,
                                 @RequestParam(value = "tag") String tag ){
        Question question = new Question();
        question.setCreator(creator);
        question.setTitle(title);
        question.setContent(content);
        question.setHtmlContent(htmlContent);
        question.setTag(tag);
        question.setCreateTime(System.currentTimeMillis());
        Boolean sqlResult = questionService.createQuestion(question);
        if (sqlResult){
            return Result.success();
        }else {
            return Result.failure(ResultCode.ERROR);
        }
    }

    @PutMapping("/question")
    @ResponseBody
    public Result editQuestion(@RequestParam(value = "id") Integer id,
                                @RequestParam(value = "title") String title,
                               @RequestParam(value = "content") String content,
                               @RequestParam(value = "html_content") String htmlContent,
                               @RequestParam(value = "tag") String tag){
        Question question = new Question();
        question.setId(id);
        question.setTitle(title);
        question.setContent(content);
        question.setHtmlContent(htmlContent);
        question.setTag(tag);
        question.setModified(System.currentTimeMillis());
        Boolean sqlResult = questionService.updateQuestionById(question);
        if (sqlResult){
            return Result.success();
        }else {
            return Result.failure(ResultCode.ERROR);
        }
    }
}
