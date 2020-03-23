package com.alan.project.controller;

import com.alan.project.dao.Comment;
import com.alan.project.dto.CommentListDTO;
import com.alan.project.dto.Result;
import com.alan.project.entity.CommentRequestBody;
import com.alan.project.enums.ResultCode;
import com.alan.project.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/comment/selectOne")
    @ResponseBody
    public Result findComment(@RequestParam("id")Long id){
        Comment dbComment = commentService.findCommentById(id);
        return dbComment == null ? Result.failure(ResultCode.COMMENT_NOT_EXIT) : Result.success(dbComment) ;
    }

    /**
     * 新增评论接口
     * @param commentRequestBody
     * @return
     */
    @PostMapping("/comment")
    @ResponseBody
    public Result createComment(@RequestBody CommentRequestBody commentRequestBody){
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentRequestBody,comment);
        comment.setCreateTime(System.currentTimeMillis());
        return commentService.createComment(comment);
    }

    /**
     * 获取评论列表接口
     * @param questionId
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/comment")
    @ResponseBody
    public Result getCommentList(@RequestParam("question_id")Integer questionId,
                                 @RequestParam(value = "currentPage",defaultValue = "1") Integer currentPage,
                                 @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize){
        CommentListDTO commentListDTO = commentService.getComments(questionId, currentPage, pageSize);
        return Result.success(commentListDTO);
    }
}
