package com.alan.project.mapper;

import com.alan.project.dao.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    void insertUser(User user);

    User findUserByToken(@Param("token") String token);

    User findUserByAccountId(@Param("accountId") String accountId);

    void updateUser(User u);

}
