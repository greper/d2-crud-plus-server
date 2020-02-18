package com.veryreader.d2p.api.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:   jackson json util类
 * @Author: xiaojunnuo
 * @CreateDate: 2018/7/30 0030$
 */
@Slf4j
public class JsonUtils {
    private static final ObjectMapper mapper = new ObjectMapper();
    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

    }

    public static ObjectMapper getMapper(){
        return mapper;
    }


    public static String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("Bean 转 json 失败:{}",obj);
            throw new RuntimeException("Bean 转 json 失败:"+e.getMessage(),e);
        }
    }

    public static <T> T toBean(String jsonStr, Class<T> objClass) {
        try {
            return mapper.readValue(jsonStr, objClass);
        } catch (IOException e) {
            log.error("json 转 Bean 失败:{}",jsonStr);
            throw new RuntimeException("json 转 Bean 失败:"+e.getMessage(),e);
        }
    }

    public static <T> T toBean(String jsonStr, TypeReference<T> typeReference) {
        try {
            return mapper.readValue(jsonStr, typeReference);
        } catch (IOException e) {
            log.error("json 转 Bean 失败:{}",jsonStr);
            throw new RuntimeException("json 转 Bean 失败:"+e.getMessage(),e);
        }
    }


    public static Map<String, Object> toBean(String jsonStr) {
        return toBean(jsonStr,new TypeReference<HashMap<String,Object>>(){});
    }
}
