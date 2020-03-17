package com.alan.project.mapper;

import com.alan.project.dao.Comment;
import com.alan.project.dao.CommentPagination;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {

    void insertComment(Comment comment);


    Comment getCommentById(@Param("id") Long id);

    Integer commentCountsById(@Param("questionId") Integer questionId);


    List<Comment> getCommentList(CommentPagination commentPagination);

    void increaseReplyCountById(@Param("id") Long id);
}
