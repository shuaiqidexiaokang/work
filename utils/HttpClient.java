/**
 * Copyright (C) 2016-2017 Hangzhou Elabcare Co. Ltd.
 * All right reserved.
 *
 * @author: Simon.lee
 * date: 2017-09-26 14:43
 */
package com.xjhh.framework.utils;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 使用 OKhttp 请求 Api 数据
 */
public class HttpClient {
    private static Logger logger = LoggerFactory.getLogger(HttpClient.class);
    public static final MediaType JSON = MediaType.parse("application/json;charset=utf-8");

    public static String httpGet(String url) throws IOException {
        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = httpClient.newCall(request).execute();
        // 返回的是string 类型，json的mapper可以直接处理
        return response.body().string();
    }

    public static String httpPost(String url, String json) throws IOException {
        OkHttpClient httpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Response response = httpClient.newCall(request).execute();
        return response.body().string();
    }
}
