package com.alan.project.service;

import com.alan.project.dao.*;
import com.alan.project.dto.ReplyListDTO;
import com.alan.project.dto.Result;
import com.alan.project.entity.Page;
import com.alan.project.enums.NotificationStatus;
import com.alan.project.enums.NotificationType;
import com.alan.project.enums.ResultCode;
import com.alan.project.mapper.CommentMapper;
import com.alan.project.mapper.NotificationMapper;
import com.alan.project.mapper.QuestionMapper;
import com.alan.project.mapper.ReplyMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ReplyService {

    @Resource
    private ReplyMapper replyMapper;

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private QuestionMapper questionMapper;

    @Resource
    private NotificationMapper notificationMapper;

    @Transactional
    public Result createReply(Reply reply) {
        Comment dbComment = commentMapper.getCommentById(reply.getCommentId());
        if (dbComment == null){
            return Result.failure(ResultCode.COMMENT_NOT_EXIT);
        }else {
            //通过评论获取所属的问题
            Question question = questionMapper.getQuestionById(dbComment.getTopicId());
            replyMapper.insertReply(reply);
            questionMapper.increaseCommentCountById(reply.getTopicId()); //评论数加一
            commentMapper.increaseReplyCountById(reply.getCommentId());//回复数加一
            //创建通知
            createNotification(reply,question);
            return Result.success();
        }
    }

    private void createNotification(Reply reply, Question question) {
        if (reply.getFromUid() == reply.getToUid()){ //自己回复自己，不产生通知
            return;
        }
        Notification notification = new Notification();
        notification.setNotifierId(reply.getFromUid());
        notification.setNotifierName(reply.getFromName());
        notification.setNotifierAvatar(reply.getFromAvatar());
        notification.setReceiverId(reply.getToUid());
        notification.setOuterId(reply.getTopicId());
        notification.setOuterTitle(question.getTitle());
        notification.setType(NotificationType.REPLY_COMMENT.getType());
        notification.setStatus(NotificationStatus.UNREAD.getStatus());
        notification.setContent(reply.getContent());
        notification.setCreateTime(System.currentTimeMillis());
        notificationMapper.insertNotification(notification);
    }

    @Transactional
    public Result getReplyList(Long commentId, Integer currentPage, Integer pageSize) {
        //获取评论下的所属回复总数
        Integer totalCount = replyMapper.getReplyCountsById(commentId);
        //计算偏移量
        Integer offset = pageSize * (currentPage - 1);
        ReplyPagination replyPagination = new ReplyPagination();
        replyPagination.setCommentId(commentId);
        replyPagination.setOffset(offset);
        replyPagination.setSize(pageSize);
        Page page = new Page();
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        page.setTotal(totalCount);

        List<Reply> replyList = replyMapper.getReplyList(replyPagination);

        ReplyListDTO replyListDTO = new ReplyListDTO();
        replyListDTO.setReplies(replyList);
        replyListDTO.setPage(page);
        return Result.success(replyListDTO);
    }
}
