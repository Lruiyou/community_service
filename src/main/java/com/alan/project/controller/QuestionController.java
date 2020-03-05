package com.alan.project.controller;

import com.alan.project.dao.Question;
import com.alan.project.dto.QuestionListDTO;
import com.alan.project.dto.Result;
import com.alan.project.enums.ResultCode;
import com.alan.project.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question")
    @ResponseBody
    public Result getQuestions(@RequestParam(value = "currentPage",defaultValue = "1") Integer currentPage,
                               @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                               @RequestParam(value = "search",required = false) String search){
        QuestionListDTO questionList = questionService.getQuestionList(currentPage, pageSize, search);
        return Result.success(questionList);
    }


    @PostMapping("/question")
    @ResponseBody
    public Result createQuestion(@RequestParam(value = "creator") String creator,
                                 @RequestParam(value = "avatar") String avatar,
                                 @RequestParam(value = "github_url") String githubUrl,
                                 @RequestParam(value = "title") String title,
                                 @RequestParam(value = "content") String content,
                                 @RequestParam(value = "html_content") String htmlContent,
                                 @RequestParam(value = "tag") String tag,
                                 @RequestParam(value = "file_url") String fileUrl){
        Question question = new Question();
        question.setCreator(creator);
        question.setAvatar(avatar);
        question.setGithubUrl(githubUrl);
        question.setTitle(title);
        question.setContent(content);
        question.setHtmlContent(htmlContent);
        question.setTag(tag);
        question.setFileUrl(fileUrl);
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
                               @RequestParam(value = "tag") String tag,
                               @RequestParam(value = "file_url") String fileUrl){
        Question question = new Question();
        question.setId(id);
        question.setTitle(title);
        question.setContent(content);
        question.setHtmlContent(htmlContent);
        question.setTag(tag);
        question.setFileUrl(fileUrl);
        question.setModified(System.currentTimeMillis());
        Boolean sqlResult = questionService.updateQuestionById(question);
        if (sqlResult){
            return Result.success();
        }else {
            return Result.failure(ResultCode.ERROR);
        }
    }
}
