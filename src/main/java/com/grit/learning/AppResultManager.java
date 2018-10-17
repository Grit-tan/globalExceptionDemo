package com.grit.learning;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 返回结果处理器，微服务统一使用该对象进行返回
 */
@Component
public class AppResultManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppResultManager.class);
    // json转换器
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Value("${server.port}")
    private Integer code;

    public String success() {
        return success(null, null);
    }

    public String success(String callback) {

        return success(null, callback);
    }

    public String success(Object data) {
        return success(data, null);
    }

    /**
     * 返回成功标记，带数据和callback
     */
    public String success(Object data, String callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        if (data != null) {
            map.put("data", data);
        }

        String res = "{}";
        try {
            res = MAPPER.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            LOGGER.warn("can not parse result to json, message: {}", e);
        }

        return StringUtils.isNotEmpty(callback) ? wrapResult(res, callback) : res;
    }

    public String error() {
        return error(null, null);
    }

    public String error(String errorMessage) {
        return error(errorMessage, null);
    }

    public String error(String errorMessage, String callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("success", false);
        map.put("message", errorMessage);
        map.put("code", code);

        String res = "{}";
        try {
            res = MAPPER.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            LOGGER.warn("can not parse result to json, message: {}", e.toString());
        }

        return StringUtils.isNotEmpty(callback) ? wrapResult(res, callback) : res;
    }

    /**
     * 包装返回结果
     */
    private static String wrapResult(String result, String callback) {
        return String.format("%s(%s)", callback, result);
    }
}
