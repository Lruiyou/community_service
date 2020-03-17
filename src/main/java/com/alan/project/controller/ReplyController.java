package com.alan.project.controller;

import com.alan.project.dao.Reply;
import com.alan.project.dto.Result;
import com.alan.project.entity.ReplyRequestBody;
import com.alan.project.service.ReplyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ReplyController {

    @Autowired
    private ReplyService replyService;


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
}
