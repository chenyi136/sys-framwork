package com.enterprise.framwork.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

/**
 * @author chenyi
 */
@Slf4j
public class JsonUtils {
    public static ObjectMapper objectMapper = new ObjectMapper();

    public static String ObjectToJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("json 序列化错误", e);
            throw new RuntimeException(e);
        }
    }

    public static <T> T jsonToObject(String json, Class<T> valueType) {
        try {
            return objectMapper.readValue(json, valueType);
        } catch (JsonProcessingException e) {
            log.error("json 反序列化出错: {}", e);
            throw new RuntimeException("json 反序列化出错");
        }
    }

    public static <T> T mapToBean(Map map, Class<T> clazz) throws Exception {
        return objectMapper.convertValue(map, clazz);
    }
}
