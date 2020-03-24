package com.alan.project.controller;

import com.alan.project.dao.User;
import com.alan.project.entity.AccessToken;
import com.alan.project.entity.GithubUser;
import com.alan.project.service.UserService;
import com.alan.project.utils.HandleGithub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserService userService;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse response){
        String url = state.substring(state.indexOf("/"));
        //String newState = state.substring(0,state.indexOf("/"));
        AccessToken accessToken = new AccessToken();
        accessToken.setClient_id(clientId);
        accessToken.setClient_secret(clientSecret);
        accessToken.setCode(code);
        accessToken.setState(state);
        accessToken.setRedirect_uri(redirectUri);
        String token = HandleGithub.getAccessToken(accessToken);
        GithubUser githubUser = HandleGithub.getUser(token);

        if (githubUser != null && githubUser.getId() != null) {
            User user = new User();
            String newToken = UUID.randomUUID().toString();
            user.setToken(newToken);
            user.setName(githubUser.getLogin());
            user.setAvatarUrl(githubUser.getAvatarUrl());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGithubUrl(githubUser.getHtmlUrl());
            user.setCreateTime(System.currentTimeMillis());
            userService.createOrUpdate(user);
            Cookie cookie = new Cookie("token", newToken);
            cookie.setMaxAge(60 * 60 * 24 * 7);
            response.addCookie(cookie);
            return "redirect:http://localhost:8080" + url;
            } else {
                return "";
        }

    }

}
