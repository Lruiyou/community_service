package com.alan.project.service;

import com.alan.project.dao.Comment;
import com.alan.project.dao.Notification;
import com.alan.project.dao.Question;
import com.alan.project.dao.Reply;
import com.alan.project.dto.Result;
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
}
