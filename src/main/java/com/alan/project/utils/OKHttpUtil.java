package com.alan.project.utils;

import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

public class OKHttpUtil {
    private final static int READ_TIMEOUT = 120;

    private final static int CONNECT_TIMEOUT = 120;

    private final static int WRITE_TIMEOUT = 120;

    private static volatile OkHttpClient okHttpClient;

    private OKHttpUtil(){

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        //读取超时
        clientBuilder.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS);
        //连接超时
        clientBuilder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);
        //写入超时
        clientBuilder.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);
        //自定义连接池最大空闲连接数和等待时间大小，否则默认最大5个空闲连接

        okHttpClient = clientBuilder.build();
    }

    public static OkHttpClient getInstance(){
        if (null == okHttpClient){
            synchronized (OKHttpUtil.class){
                if (okHttpClient == null){
                    new OKHttpUtil();
                    return okHttpClient;
                }
            }
        }
        return okHttpClient;
    }


}
