package com.alan.project.mapper;

import com.alan.project.dao.Comment;
import com.alan.project.dao.Pagination;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {

    Long insertComment(Comment comment);


    Comment getCommentById(@Param("id") Long id);

    Integer commentCountsById(@Param("questionId") Integer questionId);


    List<Comment> getCommentList(Pagination commentPagination);

    void increaseReplyCountById(@Param("id") Long id);

    Comment findCommentById(@Param("id") Long id);

    void deleteCommentById(@Param("commentId") Long commentId);

    void decreaseReplyCountById(@Param("commentId") Long commentId);
}
