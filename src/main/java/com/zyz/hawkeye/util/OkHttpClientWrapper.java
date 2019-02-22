package com.zyz.hawkeye.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zyz.hawkeye.exception.HawkEyeException;
import lombok.AllArgsConstructor;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor
@Slf4j
@Component
public class OkHttpClientWrapper {

    private static OkHttpClient okHttpClient;

    private static final String HTTP_PREFIX = "http://";

    // 线程安全的饿汉模式
    static  {
        okHttpClient = new OkHttpClient.Builder()
                .callTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();
    }

    /**
     * 执行post请求，并转换为对象
     *
     * @param url
     * @param reqBody     查询参数
     * @param targetClass 想要转换成的对象类型
     * @return
     */
    public <D, T> T post(String url, D reqBody, Class<T> targetClass) {
        String responseJson = this.post(url, reqBody);
        return JSON.parseObject(responseJson, new TypeReference<T>() {
        }.getType());
    }

    /**
     * 执行post请求
     *
     * @param url
     * @param reqBody 可以为jsonString格式或者是对象
     * @return
     */
    public <D> String post(String url, D reqBody) {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            if (reqBody instanceof String) {
                json = (String) reqBody;
            } else {
                json = mapper.writeValueAsString(reqBody);
            }
        } catch (JsonProcessingException e) {
            log.error("[okHttp query]failed,query:{}", JSON.toJSONString(reqBody), e);
        }
        Request request = new Request.Builder().url(completeUrl(url))
                .post(RequestBody.create(MediaType.parse("application/json"), json))
                .build();
        try {
            Response queryResp = okHttpClient.newCall(request).execute();
            ResponseBody responseBody = queryResp.body();
            if (null == responseBody) {
                throw new HawkEyeException("请求失败,返回内容为空,url:" + url + ",data:" + JSON.toJSONString(reqBody));
            }
            return responseBody.string();
        } catch (IOException e) {
            log.error("post请求失败,url:{},reqBody:{}", url, JSON.toJSONString(reqBody));
            throw new HawkEyeException("请求失败", e);
        }
    }


    public String get(String url) {
        Request request = new Request.Builder().url(completeUrl(url)).build();
        try {
            Response queryResp = okHttpClient.newCall(request).execute();
            ResponseBody responseBody = queryResp.body();
            if (null == responseBody) {
                throw new HawkEyeException("请求失败,返回内容为空,url:" + url);
            }
            return responseBody.string();
        } catch (IOException e) {
            log.error("get请求失败,url:{}", url);
            throw new HawkEyeException("请求失败", e);
        }
    }

    private String completeUrl(String originUrl) {
        return HTTP_PREFIX + originUrl;
    }
}
