package com.alan.project.service;

import com.alan.project.dao.Comment;
import com.alan.project.dao.CommentPagination;
import com.alan.project.dao.Notification;
import com.alan.project.dao.Question;
import com.alan.project.dto.CommentDTO;
import com.alan.project.dto.CommentListDTO;
import com.alan.project.dto.Result;
import com.alan.project.entity.Page;
import com.alan.project.enums.NotificationStatus;
import com.alan.project.enums.NotificationType;
import com.alan.project.enums.ResultCode;
import com.alan.project.mapper.CommentMapper;
import com.alan.project.mapper.NotificationMapper;
import com.alan.project.mapper.QuestionMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
            questionMapper.increaseCommentCountById(comment.getTopicId());
            //创建通知
            createNotification(comment,question.getCreatorId(),question.getTitle(), NotificationType.REPLY_QUESTION,question.getId());
            return Result.success();
        }
    }

    private void createNotification(Comment comment, Integer receiverId, String outerTitle, NotificationType notificationType, Integer outerId) {
        if (receiverId == comment.getFromUid()){ //回复自己提的问题，不产生通知
            return;
        }
        Notification notification = new Notification();
        notification.setNotifierId(comment.getFromUid());
        notification.setNotifierName(comment.getFromName());
        notification.setNotifierAvatar(comment.getFromAvatar());
        notification.setReceiverId(receiverId);
        notification.setOuterId(outerId);
        notification.setOuterTitle(outerTitle);
        notification.setType(notificationType.getType());
        notification.setStatus(NotificationStatus.UNREAD.getStatus());
        notification.setContent(comment.getContent());
        notification.setCreateTime(System.currentTimeMillis());
        notificationMapper.insertNotification(notification);
    }

    public CommentListDTO getComments(Integer questionId, Integer currentPage, Integer pageSize) {
        //获取问题下的所属评论总数
        Integer totalCount = commentMapper.commentCountsById(questionId);
        //计算偏移量
        Integer offset = pageSize * (currentPage - 1);
        CommentPagination commentPagination = new CommentPagination();
        commentPagination.setQuestionId(questionId);
        commentPagination.setOffset(offset);
        commentPagination.setSize(pageSize);
        Page page = new Page();
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        page.setTotal(totalCount);
        //根据时间倒序查询
        List<Comment> commentList = commentMapper.getCommentList(commentPagination);
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (Comment c : commentList){
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(c,commentDTO);
            commentDTOList.add(commentDTO);
        }
        CommentListDTO commentListDTO = new CommentListDTO();
        commentListDTO.setComments(commentDTOList);
        commentListDTO.setPage(page);
        return  commentListDTO;
    }

    public Comment getCommentById(Long commentId) {
       return commentMapper.getCommentById(commentId);
    }
}
