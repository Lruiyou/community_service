package com.alan.project.controller;

import com.alan.project.dao.Comment;
import com.alan.project.dto.Result;
import com.alan.project.entity.CommentRequestBody;
import com.alan.project.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

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
}
