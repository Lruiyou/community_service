package com.alan.project.service;

import com.alan.project.dao.Comment;
import com.alan.project.dao.Notification;
import com.alan.project.dao.Question;
import com.alan.project.dto.Result;
import com.alan.project.enums.NotificationStatus;
import com.alan.project.enums.NotificationType;
import com.alan.project.enums.ResultCode;
import com.alan.project.mapper.CommentMapper;
import com.alan.project.mapper.NotificationMapper;
import com.alan.project.mapper.QuestionMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private QuestionMapper questionMapper;

    @Resource
    private NotificationMapper notificationMapper;

    @Transactional
    public Result createComment(Comment comment) {
        Question question = questionMapper.getQuestionById(comment.getTopicId());//先查询问题
        if (question == null){
            return Result.failure(ResultCode.QUESTION_NOT_EXIT); //问题不存在
        }else {
            commentMapper.insertComment(comment);
            questionMapper.increaseCommentById(comment.getTopicId());
            //创建通知
            createNotification(comment,question.getCreatorId(),question.getTitle(), NotificationType.REPLY_QUESTION,question.getId());
            return Result.success();
        }
    }

    private void createNotification(Comment comment, Integer receiver, String outerTitle, NotificationType notificationType, Integer outerId) {
        if (receiver == comment.getFromUid()){ //回复自己提的问题，不产生通知
            return;
        }
        Notification notification = new Notification();
        notification.setCreateTime(System.currentTimeMillis());
        notification.setNotifier(comment.getFromUid());
        notification.setNotifierName(comment.getFromName());
        notification.setReceiver(receiver);
        notification.setOuterId(outerId);
        notification.setOuterTitle(outerTitle);
        notification.setType(notificationType.getType());
        notification.setStatus(NotificationStatus.UNREAD.getStatus());
        notificationMapper.insertNotification(notification);
    }
}
