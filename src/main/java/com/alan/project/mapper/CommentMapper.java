package com.alan.project.mapper;

import com.alan.project.dao.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {

    void insertComment(Comment comment);
}
