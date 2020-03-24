package com.alan.project.controller;

import com.alan.project.dao.Question;
import com.alan.project.dao.Thumbup;
import com.alan.project.dto.HotTopicDTO;
import com.alan.project.dto.LikeDTO;
import com.alan.project.dto.QuestionListDTO;
import com.alan.project.dto.Result;
import com.alan.project.enums.ResultCode;
import com.alan.project.service.QuestionService;
import com.alan.project.utils.HandleHotissue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private HandleHotissue handleHotissue;

    /**
     * 获取问题列表，搜索问题
     * @param currentPage
     * @param pageSize
     * @param search
     * @return
     */
    @GetMapping("/question")
    @ResponseBody
    public Result getQuestions(@RequestParam(value = "currentPage",defaultValue = "1") Integer currentPage,
                               @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                               @RequestParam(value = "search",required = false) String search){
        QuestionListDTO questionList = questionService.getQuestionList(currentPage, pageSize, search);
        return Result.success(questionList);
    }

    /**
     * 获取问题详情
     * @param id
     * @return
     */
    @GetMapping("/question/details")
    @ResponseBody
    public Result getQuestionDetails(@RequestParam("question_id")Integer id,
                                    @RequestParam("type")String type){
        if ("details".equals(type)){
            questionService.increaseViewById(id);
        }
        Question question = questionService.getQuestionById(id);
        return  question != null ? Result.success(question) : Result.failure(ResultCode.ERROR);
    }

    /**
     * 新增问题接口
     * @param creator
     * @param avatar
     * @param githubUrl
     * @param title
     * @param content
     * @param htmlContent
     * @param tag
     * @param fileUrl
     * @return
     */
    @PostMapping("/question")
    @ResponseBody
    public Result createQuestion(@RequestParam(value = "creator") Integer creator,
                                 @RequestParam(value = "creator_name") String creatorName,
                                 @RequestParam(value = "avatar") String avatar,
                                 @RequestParam(value = "github_url") String githubUrl,
                                 @RequestParam(value = "title") String title,
                                 @RequestParam(value = "content") String content,
                                 @RequestParam(value = "html_content") String htmlContent,
                                 @RequestParam(value = "tag") String tag,
                                 @RequestParam(value = "file_url") String fileUrl){
        Question question = new Question();
        question.setCreatorId(creator);
        question.setCreatorName(creatorName);
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

    /**
     * 编辑问题
     * Putmapping不支持@RequestBody
     * @param id
     * @param title
     * @param content
     * @param htmlContent
     * @param tag
     * @param fileUrl
     * @return
     */
    @PutMapping("/question/edit")
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
        questionService.updateQuestionById(question);
        return Result.success();
    }

    /**
     * 热门问题接口
     * @return
     */
    @GetMapping("/question/hot")
    @ResponseBody
    public Result getHotTopics(){
        List<HotTopicDTO> hotTopicList = handleHotissue.getHots();
        return Result.success(hotTopicList);
    }

    /**
     * 用户点赞或取消点赞
     * @param questionId
     * @param userId
     * @return Result
     */
    @PutMapping("/question/thumbup")
    @ResponseBody
    public Result thumbUp(@RequestParam(value = "question_id") Integer questionId,
                          @RequestParam(value = "user_id")Integer userId){
        Thumbup record = questionService.findLikeByQidandUid(questionId, userId);
        if (record == null){ //第一次点赞
            Thumbup thumbup = new Thumbup();
            thumbup.setQuestionId(questionId);
            thumbup.setUserId(userId);
            thumbup.setStatus(1);
            questionService.createLike(thumbup);
            questionService.increaseLikeById(questionId);
        }else if (record.getStatus() == 0){ //点赞
            record.setStatus(1);
            questionService.updateLike(record);
            questionService.increaseLikeById(questionId);
        }else { //取消点赞
            record.setStatus(0);
            questionService.updateLike(record);
            questionService.decreaseLikeById(questionId);
        }
        return Result.success();
    }

    /**
     * 获取用户的点赞状态
     * @param questionId
     * @param userId
     * @return Result
     */
    @GetMapping("/question/thumbup")
    @ResponseBody
    public Result getLikeState(@RequestParam(value = "question_id") Integer questionId,
                               @RequestParam(value = "user_id")Integer userId){
        LikeDTO likeDTO = new LikeDTO();
        Thumbup record = questionService.findLikeByQidandUid(questionId, userId);
        if(record == null || record.getStatus() == 0){ //未点赞状态
            likeDTO.setStatus(0);
            return Result.success(likeDTO);
        }else { //点赞状态
            likeDTO.setStatus(1);
            return Result.success(likeDTO);
        }
    }


    /**
     * 根据标签获取相关问题
     * @param questionId
     * @return
     */
    @GetMapping("/question/relation")
    @ResponseBody
    public Result getRelatedList(@RequestParam(value = "question_id")Integer questionId){
        Question record = questionService.getQuestionById(questionId);
        List<Question> relatedQuestions = questionService.getRelatedQuestions(record);
        return Result.success(relatedQuestions);
    }
}
