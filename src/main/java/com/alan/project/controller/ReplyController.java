package com.alan.project.controller;

import com.alan.project.dao.Reply;
import com.alan.project.dto.Result;
import com.alan.project.entity.ReplyRequestBody;
import com.alan.project.enums.ExitStatus;
import com.alan.project.enums.ResultCode;
import com.alan.project.service.ReplyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @GetMapping("/reply/selectOne")
    @ResponseBody
    public Result findReply(@RequestParam("id")Long id){
        Reply dbReply = replyService.findReplyById(id);
        return dbReply == null ? Result.failure(ResultCode.COMMENT_NOT_EXIT) : Result.success(dbReply);
    }
    /**
     * 增加回复接口
     * @param requestBody
     * @return
     */
    @PostMapping("/reply")
    @ResponseBody
    public Result createReply(@RequestBody ReplyRequestBody requestBody){
        Reply reply = new Reply();
        BeanUtils.copyProperties(requestBody,reply);
        reply.setCreateTime(System.currentTimeMillis());
        reply.setIsExit(ExitStatus.EXIT.getStatus());
        return replyService.createReply(reply);
    }

    /**
     * 获取回复列表
     * @param commentId
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/reply")
    @ResponseBody
    public Result getReplies(@RequestParam("comment_id")Long commentId,
                            @RequestParam(value = "currentPage",defaultValue = "1") Integer currentPage,
                            @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize){
        return replyService.getReplyList(commentId,currentPage,pageSize);
    }

    /**
     * 根据id删除回复
     * @param replyId
     * @return
     */
    @PutMapping("/reply/delete")
    @ResponseBody
    public Result deleteReply(@RequestParam("question_id")Integer questionId,
                              @RequestParam("comment_id")Long commentId,
                              @RequestParam("reply_id")Long replyId){
        replyService.deleteReplyById(questionId,commentId,replyId);
        return Result.success();
    }
}
