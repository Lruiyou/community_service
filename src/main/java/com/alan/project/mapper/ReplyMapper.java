package com.alan.project.mapper;

import com.alan.project.dao.Reply;
import com.alan.project.dao.ReplyPagination;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReplyMapper {

    Long insertReply(Reply reply);

    Integer getReplyCountsById(@Param("id") Long commentId);

    List<Reply> getReplyList(ReplyPagination replyPagination);
}
