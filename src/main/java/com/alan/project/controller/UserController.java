package com.alan.project.controller;

import com.alan.project.dao.User;
import com.alan.project.dto.Result;
import com.alan.project.enums.ResultCode;
import com.alan.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    @ResponseBody
    public Result getUser(@RequestParam(name = "token") String token){
        if (token != null){
            User user = userService.findUserByToken(token);
            return Result.success(user);
        }else {
            return Result.failure(ResultCode.TOKEN_NULL);
        }
    }
}
