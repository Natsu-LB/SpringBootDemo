package com.example.demo.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * json 工具类
 */
public class JsonUtils {

    /**
     * Bean对象转JSON
     *
     * @param object           被转换对象
     * @param dataFormatString 转换规则
     * @return json对象字符串
     */
    @Contract("null, _ -> null")
    public static String beanToJsonString(Object object, String dataFormatString) {
        if (object != null) {
            if (StringUtils.isEmpty(dataFormatString)) {
                return JSONObject.toJSONString(object);
            }
            return JSON.toJSONStringWithDateFormat(object, dataFormatString);
        } else {
            return null;
        }
    }

    public static JSONObject beanToJsonObject(Object object, String dataFormatString) {
        String result = beanToJsonString(object, dataFormatString);
        if (result == null) {
            return null;
        } else {
            return JSONObject.parseObject(result);
        }
    }

    /**
     * Bean对象转JSON
     */
    @Contract("!null -> !null; null -> null")
    public static String beanToJsonString(Object object) {
        if (object != null) {
            return JSON.toJSONString(object);
        } else {
            return null;
        }
    }

    public static JSONObject beanToJsonObject(Object object) {
        String result = beanToJsonString(object);
        if (result != null) {
            return JSON.parseObject(result);
        } else {
            return null;
        }

    }

    /**
     * String转JSON字符串
     */
    @Nullable
    public static String stringToJsonByFastjson(String key, String value) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
            return null;
        }
        JSONObject result = new JSONObject();
        result.put(key, value);
        return result.toJSONString();
    }

    /**
     * 将json字符串转换成对象
     */
    @Nullable
    public static <T> T jsonToBean(String json, Class<T> clazz) {
        if (StringUtils.isEmpty(json) || clazz == null) {
            return null;
        }
        return JSON.parseObject(json, clazz);
    }

    @Nullable
    public static <T> T jsonToBean(JSONObject json, Class<T> clazz) {
        return jsonToBean(json.toJSONString(), clazz);
    }

    /**
     * json字符串转map
     */
    @Nullable
    public static Map jsonToMap(String json) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        return JSON.parseObject(json, Map.class);
    }

    @Nullable
    public static Map jsonToMap(JSONObject json) {
        return jsonToMap(json.toJSONString());
    }

    public static <T> List<T> jsonToBeanList(String json, Class<T> clazz) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        return JSON.parseArray(json, clazz);
    }

    public static <T> List<T> jsonToBeanList(JSONArray json, Class<T> clazz) {
        return jsonToBeanList(json.toJSONString(), clazz);
    }
}