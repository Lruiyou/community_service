package com.alan.project.utils;

import com.alan.project.entity.AccessToken;
import com.alan.project.entity.GithubUser;
import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class HandleGithub {
    public static  String getAccessToken(AccessToken accessToken){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessToken));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = OKHttpUtil.getInstance().newCall(request).execute()) {
            String jsonstring = response.body().string();
            String token = jsonstring.split("&")[0].split("=")[1];
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static GithubUser getUser(String token){
        System.out.println(token);
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+token)
                .build();
        try {
            Response response = OKHttpUtil.getInstance().newCall(request).execute();
            String s = response.body().string();
            GithubUser user = JSON.parseObject(s, GithubUser.class);
            return user;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
