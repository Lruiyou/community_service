package com.alan.project.service;

import com.alan.project.dao.User;
import com.alan.project.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public User findUserById(Integer id){
        return userMapper.findUserById(id);
    }

    public User findUserByToken(String token){
        return userMapper.findUserByToken(token);
    }

    public void createOrUpdate(User user){
        User u = userMapper.findUserByAccountId(user.getAccountId());
        if (u == null){
            user.setCreateTime(System.currentTimeMillis());
            user.setModified(System.currentTimeMillis());
            userMapper.insertUser(user);
        }else {
            u.setToken(user.getToken());
            u.setName(user.getName());
            u.setAvatarUrl(user.getAvatarUrl());
            u.setModified(System.currentTimeMillis());
            userMapper.updateUser(u);
        }
    }
}
