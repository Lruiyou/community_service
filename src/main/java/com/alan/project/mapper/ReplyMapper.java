package com.alan.project.mapper;

import com.alan.project.dao.Reply;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReplyMapper {

    void insertReply(Reply reply);
}
